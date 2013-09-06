package org.wdcode.base.action;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.wdcode.base.entity.Entity;
import org.wdcode.base.entity.EntityTime;
import org.wdcode.base.service.QueryService;
import org.wdcode.base.service.SuperService;
import org.wdcode.common.constants.DateConstants;
import org.wdcode.common.constants.StringConstants;
import org.wdcode.common.lang.Lists;
import org.wdcode.common.lang.Maps;
import org.wdcode.base.bean.Pagination;
import org.wdcode.common.util.ArrayUtil;
import org.wdcode.common.util.DateUtil;
import org.wdcode.common.util.EmptyUtil;
import org.wdcode.core.json.JsonEngine;
import org.wdcode.web.constants.HttpConstants;

/**
 * 超级通用Action
 * @author WD
 * @since JDK7
 * @version 1.0 2012-07-4
 */
public class SuperAction<E extends Entity> extends BasicAction {
	// 序列化ID
	private static final long		serialVersionUID	= -2886589693379937807L;
	// 时间字段
	protected final static String	TIME_FIELD			= "time";
	// 通用实体
	protected E						entity;
	// 实体列表
	protected List<E>				entitys;
	// 主键
	protected Serializable			key;
	// 主键数组
	protected Serializable[]		keys;
	// 开始时间
	protected String				startDate;
	// 结束时间
	protected String				endDate;
	// 实体类
	protected Class<E>				entityClass;
	// 通用业务接口
	@Resource
	protected SuperService			service;
	// 查询器
	@Resource
	protected QueryService			query;
	// 分页Bean
	@Autowired
	protected Pagination			pager;
	// 排序参数
	protected Map<String, Object>	orders;
	// 实体是否初始化
	protected boolean				isEntity;

	@PostConstruct
	protected void init() {
		// 父类初始化
		super.init();
		// 初始化空排序
		orders = Maps.getMap();
		// 获得实体类
		entityClass = context.getClass(module);
		// 获得ContentType
		String contentType = getRequest().getContentType();
		// 判断为上传文件表单
		if (!EmptyUtil.isEmpty(contentType) && contentType.indexOf(HttpConstants.CONTENT_TYPE_FILE) > -1) {
			isEntity = true;
			// 获得实体
			entity = entityClass == null ? null : context.getBean(module, entityClass);
		} else {
			// 是否初始化实体
			for (Map.Entry<String, String[]> e : getRequest().getParameterMap().entrySet()) {
				if (e.getKey().indexOf("entity") > -1) {
					isEntity = true;
					// 获得实体
					entity = entityClass == null ? null : context.getBean(module, entityClass);
					break;
				}
			}
		}

	}

	/**
	 * 添加
	 * @return 跳转
	 * @throws Exception
	 */
	public String add() throws Exception {
		return callback(service.insert(add(entity)).get(0));
	}

	/**
	 * 添加
	 * @return 跳转
	 * @throws Exception
	 */
	public String adds() throws Exception {
		// 循环实体数组
		for (E e : entitys) {
			add(e);
		}
		// 添加并返回结果
		return callback(service.insert(ArrayUtil.toArray(entitys)));
	}

	/**
	 * 修改
	 * @return 跳转
	 * @throws Exception
	 */
	public String edit() throws Exception {
		return callback(service.update(entity).get(0));
	}

	/**
	 * 删除
	 * @return 跳转
	 * @throws Exception
	 */
	public String del() throws Exception {
		// 影响数量标识
		int size = 0;
		// key为空
		if (EmptyUtil.isEmpty(key)) {
			// 实体不为空
			if (entity != null) {
				// 实体主键为空
				if (EmptyUtil.isEmpty(entity.getKey())) {
					// 按实体查询出相关列表 在删除
					size = service.delete(Lists.toArray(service.list(entity, -1, -1))).size();
				} else {
					// 按实体主键删除
					size = service.delete(entityClass, entity.getKey()).size();
				}
			}
		} else {
			// 按key删除
			size = service.delete(entityClass, key).size();
		}
		return callback(size > 0);
	}

	/**
	 * 删除多个
	 * @return 跳转
	 * @throws Exception
	 */
	public String dels() throws Exception {
		return callback(EmptyUtil.isEmpty(service.delete(entityClass, keys)) ? ERROR : mode);
	}

	/**
	 * 清空表
	 * @return
	 * @throws Exception
	 */
	public String trun() throws Exception {
		// 清空表
		service.truncate(entityClass);
		// 返回成功
		return callback(SUCCESS);
	}

	/**
	 * 查询所有
	 * @return 跳转list
	 * @throws Exception
	 */
	public String all() throws Exception {
		return callback(entitys = (entity == null ? service.all(entityClass) : service.list(entity, -1, -1)));
	}

	/**
	 * 分页查询
	 * @return 跳转list
	 * @throws Exception
	 */
	public String page() throws Exception {
		return callback(entitys = (entity == null ? service.list(entityClass, pager) : service.list(entity, pager)));
	}

	/**
	 * 分页查询
	 * @return 跳转list
	 * @throws Exception
	 */
	public String order() throws Exception {
		return callback(entitys = entity == null ? service.order(entityClass, orders, -1, -1) : service.order(entity, orders, -1, -1));
	}

	/**
	 * 分页查询
	 * @return 跳转list
	 * @throws Exception
	 */
	public String date() throws Exception {
		// 如果开始时间和结束时间都为空
		if (EmptyUtil.isEmpty(startDate) && EmptyUtil.isEmpty(endDate)) {
			// 直接分页查询
			entitys = service.list(entity, pager);
		} else {
			// 判断开始时间为空 为当前时间
			if (EmptyUtil.isEmpty(startDate)) {
				startDate = DateUtil.getShortDate();
			}
			// 判断结束时间为空 为当前时间
			if (EmptyUtil.isEmpty(endDate)) {
				endDate = DateUtil.getShortDate();
			}
			// 按时间查询
			entitys = service.between(entity, TIME_FIELD, startDate + StringConstants.BLANK + DateConstants.DATE_DAY_STATR, endDate + StringConstants.BLANK + DateConstants.DATE_DAY_END, pager);
		}
		// 返回列表页
		return callback(entitys);
	}

	/**
	 * 实体条件查询出所有
	 * @return 跳转list
	 * @throws Exception
	 */
	public String entity() throws Exception {
		return entity == null ? LIST : callback(entitys = service.list(entity, -1, -1));
	}

	/**
	 * 直接跳转
	 * @return
	 * @throws Exception
	 */
	public String to() throws Exception {
		return SUCCESS;
	}

	/**
	 * 跳转到修改页
	 * @return 跳转
	 * @throws Exception
	 */
	public String theme() throws Exception {
		return EmptyUtil.isEmpty(entity) ? SUCCESS : callback(entity = service.get(entityClass, entity.getKey()));
	}

	/**
	 * 跳转到列表页
	 * @return 跳转
	 * @throws Exception
	 */
	public String list() throws Exception {
		// 排序参数为空
		if (EmptyUtil.isEmpty(orders)) {
			entitys = entity == null ? service.list(entityClass, pager) : service.list(entity, pager);
		} else {
			entitys = entity == null ? service.order(entityClass, orders, pager) : service.order(entity, orders, pager);
		}
		// 返回结果
		return callback(entitys);
	}

	/**
	 * 跳转到列表页
	 * @return 跳转
	 * @throws Exception
	 */
	public String search() throws Exception {
		return callback(entitys = service.search(entity, pager));
	}

	/**
	 * 获得通用实体
	 * @return 通用实体
	 */
	public E getEntity() {
		return entity;
	}

	/**
	 * 设置通用实体
	 * @param entity 通用实体
	 */
	public void setEntity(E entity) {
		this.entity = entity;
	}

	/**
	 * 获得业务
	 * @return 业务
	 */
	public SuperService getService() {
		return service;
	}

	/**
	 * 设置业务
	 * @param service 业务
	 */
	public void setService(SuperService service) {
		this.service = service;
	}

	/**
	 * 获得主键
	 * @return 主键
	 */
	public Serializable getKey() {
		return key;
	}

	/**
	 * 设置主键
	 * @param key 主键
	 */
	public void setKey(Serializable key) {
		// 如果传递进来的是数组
		if (key.getClass().isArray()) {
			// 转换成数组
			Serializable[] keys = (Serializable[]) key;
			// 如果只有一个值 赋值给key 否则赋值给keys
			if (keys.length == 1) {
				this.key = keys[0];
			} else {
				setKeys(keys);
			}
		} else {
			this.key = key;
		}
	}

	/**
	 * 获得主键数组
	 * @return 主键数组
	 */
	public Serializable[] getKeys() {
		return keys;
	}

	/**
	 * 设置主键数组
	 * @param keys 主键数组
	 */
	public void setKeys(Serializable[] keys) {
		this.keys = keys;
	}

	/**
	 * 获得通用实体列表
	 * @return 通用实体列表
	 */
	public List<E> getEntitys() {
		return entitys;
	}

	/**
	 * 设置通用实体列表
	 * @param entitys 通用实体列表
	 */
	public void setEntitys(List<E> entitys) {
		this.entitys = entitys;
	}

	/**
	 * 获得开始时间
	 * @return 开始时间
	 */
	public String getStartDate() {
		return startDate;
	}

	/**
	 * 设置开始时间
	 * @param startDate 开始时间
	 */
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	/**
	 * 获得结束时间
	 * @return 结束时间
	 */
	public String getEndDate() {
		return endDate;
	}

	/**
	 * 设置结束时间
	 * @param endDate 结束时间
	 */
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	/**
	 * 获得分页Bean
	 * @return 分页Bean
	 */
	public Pagination getPager() {
		return pager;
	}

	/**
	 * 设置分页Bean
	 * @param pager 分页Bean
	 */
	public void setPager(Pagination pager) {
		this.pager = pager;
	}

	/**
	 * 获得排序参数
	 * @return 排序参数
	 */
	public Map<String, Object> getOrders() {
		return orders;
	}

	/**
	 * 设置排序参数
	 * @param orders 排序参数
	 */
	public void setOrders(String orders) {
		this.orders = JsonEngine.toMap(orders);
	}

	/**
	 * 获得查询器
	 * @return 查询器
	 */
	public QueryService getQuery() {
		return query;
	}

	/**
	 * 添加实体
	 * @param e
	 * @return
	 */
	protected E add(E e) {
		// 判断实体类型
		if (e instanceof EntityTime) {
			((EntityTime) e).setTime(DateUtil.getTime());
		}
		// 返回E
		return e;
	}
}
