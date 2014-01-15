package org.wdcode.base.action;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.wdcode.base.entity.Entity;
import org.wdcode.base.entity.EntityIp;
import org.wdcode.base.entity.EntityStartEndTime;
import org.wdcode.base.entity.EntityFile;
import org.wdcode.base.entity.EntityFiles;
import org.wdcode.base.entity.EntityTime;
import org.wdcode.common.constants.DateConstants;
import org.wdcode.common.constants.StringConstants;
import org.wdcode.common.lang.Conversion;
import org.wdcode.common.lang.Maps;
import org.wdcode.base.bean.Pagination;
import org.wdcode.common.util.ArrayUtil;
import org.wdcode.common.util.BeanUtil;
import org.wdcode.common.util.DateUtil;
import org.wdcode.common.util.EmptyUtil;
import org.wdcode.common.util.StringUtil;
import org.wdcode.core.json.JsonEngine;
import org.wdcode.web.constants.HttpConstants;

/**
 * 超级通用Action
 * @author WD
 * @since JDK7
 * @version 1.0 2012-07-4
 */
public class SuperAction<E extends Entity> extends BasicAction {
	// 时间字段
	protected final static String	TIME_FIELD	= "time";
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
	// 分页Bean
	@Resource
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
		String contentType = request.getContentType();
		// 判断为上传文件表单
		if (!EmptyUtil.isEmpty(contentType) && contentType.indexOf(HttpConstants.CONTENT_TYPE_FILE) > -1) {
			isEntity = true;
			// 获得实体
			entity = entityClass == null ? null : context.getBean(module, entityClass);
		} else {
			// 是否初始化实体
			for (Map.Entry<String, String[]> e : request.getParameterMap().entrySet()) {
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
	 * 重置缓存
	 * @return 跳转
	 * @throws Exception
	 */
	public String load() throws Exception {
		// 重载数据
		if (entityClass == null) {
			service.load();
		} else {
			service.load(entityClass);
		}
		// 返回到成功页
		return callback(SUCCESS);
	}

	/**
	 * 重置缓存
	 * @return 跳转
	 * @throws Exception
	 */
	public String cache() throws Exception {
		// 重载缓存
		if (entityClass == null) {
			service.cache();
		} else {
			service.load(entityClass);
		}
		// 返回到成功页
		return callback(SUCCESS);
	}

	/**
	 * 添加
	 * @return 跳转
	 * @throws Exception
	 */
	public String add() throws Exception {
		return callback(entity = service.insert(add(entity)).get(0));
	}

	/**
	 * 添加或修改
	 * @return 跳转
	 * @throws Exception
	 */
	public String addOrUpdata() throws Exception {
		return callback(entity = service.insertOrUpdate(add(entity)).get(0));
	}

	/**
	 * 添加
	 * @return 跳转
	 * @throws Exception
	 */
	public String adds() throws Exception {
		// 如果实体列表为空 并且key不为空
		if (EmptyUtil.isEmpty(entitys) && !EmptyUtil.isEmpty(key)) {
			entitys = JsonEngine.toList(Conversion.toString(key), entityClass);
		}
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
		// 获得要更像的实体
		E e = service.get(entityClass, entity.getKey());
		// 实体不为空 更新 否则返回错误
		return callback(entity = service.update(BeanUtil.copyProperties(upload(entity), e)).get(0));
	}

	/**
	 * 修改
	 * @return 跳转
	 * @throws Exception
	 */
	public String edits() throws Exception {
		// 如果实体列表为空 并且key不为空
		if (EmptyUtil.isEmpty(entitys) && !EmptyUtil.isEmpty(key)) {
			entitys = JsonEngine.toList(Conversion.toString(key), entityClass);
		}
		// 实体列表不为空
		if (!EmptyUtil.isEmpty(entitys)) {
			// 声明修改实体数组
			E[] es = ArrayUtil.getArray(entityClass, entitys.size());
			// 循环获取持久化数据实体
			for (int i = 0; i < entitys.size(); i++) {
				// 获得修改实体
				E e = entitys.get(i);
				// 把新修改的值赋值给修改是实体
				es[i] = BeanUtil.copyProperties(e, service.get(entityClass, e.getKey()));
			}
			// 修改实体
			entitys = service.update(es);
		}
		// 实体不为空 更新 否则返回错误
		return callback(entitys);
	}

	/**
	 * 删除
	 * @return 跳转
	 * @throws Exception
	 */
	public String del() throws Exception {
		// key为空
		if (EmptyUtil.isEmpty(key)) {
			// 实体不为空
			if (entity != null) {
				// 实体主键为空
				if (EmptyUtil.isEmpty(entity.getKey())) {
					// 按实体查询出相关列表 在删除
					entitys = service.delete(entity);
				} else {
					// 按实体主键删除
					entitys = service.delete(entityClass, entity.getKey());
				}
			}
		} else {
			// 按key删除
			entitys = service.delete(entityClass, key);
		}
		return callback(EmptyUtil.isEmpty(entitys) ? ERROR : (entity = entitys.get(0)));
	}

	/**
	 * 删除多个
	 * @return 跳转
	 * @throws Exception
	 */
	public String dels() throws Exception {
		return callback(EmptyUtil.isEmpty(entitys = service.delete(entityClass, keys)) ? ERROR : mode);
	}

	/**
	 * 查询所有
	 * @return 跳转list
	 * @throws Exception
	 */
	public String all() throws Exception {
		return callback(entitys = service.all(entityClass));
	}

	/**
	 * 分页查询
	 * @return 跳转list
	 * @throws Exception
	 */
	public String page() throws Exception {
		// 查询实体列表
		entitys = (entity == null ? service.list(entityClass, pager) : service.list(entity, pager));
		// 声明返回列表
		Map<String, Object> map = Maps.getMap();
		map.put("pager", pager);
		map.put("entitys", entitys);
		// 返回列表
		return callback(map);
	}

	/**
	 * 分页查询
	 * @return 跳转list
	 * @throws Exception
	 */
	public String order() throws Exception {
		return callback(entitys = entity == null ? service.order(entityClass, orders, pager) : service.order(entity, orders, pager));
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
			entitys = service.between(entity, TIME_FIELD, DateUtil.getTime(startDate), DateUtil.getTime(endDate) + DateConstants.DAY, pager);
		}
		// 返回列表页
		return callback(entitys);
	}

	/**
	 * 实体条件查询出所有
	 * @return 跳转SUCCESS
	 * @throws Exception
	 */
	public String get() throws Exception {
		return callback(entity = key == null ? null : service.get(entityClass, key));
	}

	/**
	 * 实体条件查询出所有
	 * @return 跳转SUCCESS
	 * @throws Exception
	 */
	public String gets() throws Exception {
		return keys == null ? callback(entitys = service.gets(entityClass, key)) : callback(entitys = service.gets(entityClass, keys));
	}

	/**
	 * 实体条件查询出所有
	 * @return 跳转SUCCESS
	 * @throws Exception
	 */
	public String entity() throws Exception {
		return callback(entity = entity == null ? null : service.get(entity));
	}

	/**
	 * 实体条件查询出所有
	 * @return 跳转list
	 * @throws Exception
	 */
	public String entitys() throws Exception {
		return callback(entity == null ? LIST : (entitys = service.list(entity, pager)));
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
	 * 直接跳转
	 * @return
	 * @throws Exception
	 */
	public String tos() throws Exception {
		return LIST;
	}

	/**
	 * 跳转到修改页
	 * @return 跳转
	 * @throws Exception
	 */
	public String theme() throws Exception {
		return callback(!EmptyUtil.isEmpty(entity = service.get(entityClass, entity.getKey())));
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
	 * 获得数量
	 * @return 跳转
	 * @throws Exception
	 */
	public String count() throws Exception {
		return callback(entity == null ? service.count(entityClass) : service.count(entity));
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
				setKey(keys[0]);
			} else {
				setKeys(keys);
			}
		} else if (key instanceof String) {
			// 传的是字符串
			String s = Conversion.toString(key);
			// 如果是json串
			if (!JsonEngine.isJson(s) && s.indexOf(StringConstants.COMMA) > -1) {
				// ,号分割的字符串 转换成数组setKey
				setKey(s.split(StringConstants.COMMA));
			} else {
				this.key = s;
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
		this.startDate = StringUtil.trim(startDate);
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
		this.endDate = StringUtil.trim(endDate);
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
	 * 添加实体
	 * @param e
	 * @return
	 */
	protected E theme(E e) {
		// 判断e==null 直接返回
		if (e == null) {
			return e;
		}
		// 判断是否EntityStartEndTime
		if (e instanceof EntityStartEndTime) {
			// 开始时间
			if (((EntityStartEndTime) e).getStartTime() != null) {
				startDate = DateUtil.toString(((EntityStartEndTime) e).getStartTime());
			}
			// 结束时间
			if (((EntityStartEndTime) e).getEndTime() != null) {
				endDate = DateUtil.toString(((EntityStartEndTime) e).getEndTime());
			}
		}
		return e;
	}

	/**
	 * 添加实体
	 * @param e
	 * @return
	 */
	protected E add(E e) {
		// 判断实体类型
		if (e instanceof EntityTime && EmptyUtil.isEmpty(((EntityTime) e).getTime())) {
			((EntityTime) e).setTime(DateUtil.getTime());
		}
		if (e instanceof EntityIp && EmptyUtil.isEmpty(((EntityIp) e).getIp())) {
			if (!EmptyUtil.isEmpty(((EntityIp) e).getIp())) {
				((EntityIp) e).setIp(getIp());
			}
		}
		if (e instanceof EntityStartEndTime) {
			// 开始时间
			if (!EmptyUtil.isEmpty(startDate) && EmptyUtil.isEmpty(((EntityStartEndTime) e).getStartTime())) {
				((EntityStartEndTime) e).setStartTime(DateUtil.getTime(startDate));
			}
			// 结束时间
			if (!EmptyUtil.isEmpty(endDate) && EmptyUtil.isEmpty(((EntityStartEndTime) e).getEndTime())) {
				((EntityStartEndTime) e).setEndTime(DateUtil.getTime(endDate));
			}
		}
		// 返回E
		return upload(e);
	}

	/**
	 * 上次文件
	 * @param e
	 * @return
	 */
	protected E upload(E e) {
		if (e instanceof EntityFile) {
			// 上次文件
			String path = upload(file, fileFileName);
			// 路径不为空
			if (!EmptyUtil.isEmpty(path)) {
				((EntityFile) e).setPath(path);
			}
		}
		if (e instanceof EntityFiles) {
			// 上次文件
			String[] paths = uploads(files, filesFileName);
			// 路径不为空
			if (!EmptyUtil.isEmpty(paths)) {
				((EntityFiles) e).setPaths(paths);
			}
		}
		return e;
	}
}
