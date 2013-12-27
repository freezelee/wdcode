package org.wdcode.base.context;

import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.mapper.ActionMapping;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.wdcode.base.cache.Cache;
import org.wdcode.base.cache.impl.CacheMap;
import org.wdcode.base.cache.impl.CacheMemcached;
import org.wdcode.base.entity.Entity;
import org.wdcode.base.params.BaseParams;
import org.wdcode.common.lang.Lists;
import org.wdcode.common.lang.Maps;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;

/**
 * 全局Context控制
 * @author WD
 * @since JDK7
 * @version 1.0 2012-09-01
 */
@Component
public final class Context {
	// struts2 request key
	private final static String								ACTICON_MAPPING_KEY	= "struts.actionMapping";
	// Spring ApplicationContext
	@Resource
	private ApplicationContext								applicationContext;
	// 短类名对应的类对象Map
	private ConcurrentMap<String, Class<? extends Entity>>	entitys;

	/**
	 * 初始化
	 */
	@PostConstruct
	protected void init() {
		// 获得所有实体
		Map<String, Entity> map = getBeans(Entity.class);
		// 实例化短类名对应的类对象Map
		entitys = Maps.getConcurrentMap();
		// 循环赋值
		for (Map.Entry<String, ? extends Entity> e : map.entrySet()) {
			// 设置实体名对应类
			entitys.put(e.getKey(), e.getValue().getClass());
		}
	}

	/**
	 * 获得所有实体类列表
	 * @return 类列表
	 */
	public List<Class<? extends Entity>> getEntitys() {
		return Lists.getList(entitys.values());
	}

	/**
	 * 根据实体名对应的类对象
	 * @param entity 实体名
	 * @return 类对象
	 */
	public <E extends Entity> Class<E> getClass(String entity) {
		return (Class<E>) entitys.get(entity);
	}

	/**
	 * 根据名称和类获得实体
	 * @param name 名称
	 * @param requiredType 类型
	 * @return 实体
	 */
	public <E> E getBean(String name, Class<E> requiredType) {
		return applicationContext.getBean(name, requiredType);
	}

	/**
	 * 根据类获得实体
	 * @param requiredType 类型
	 * @return 实体
	 */
	public <E> E getBean(Class<E> requiredType) {
		return applicationContext.getBean(requiredType);
	}

	/**
	 * 根据类获得实体
	 * @param requiredType 类型
	 * @param 参数
	 * @return 实体
	 */
	public <E> E getBean(Class<E> requiredType, Object... args) {
		return (E) applicationContext.getBean(requiredType.getName(), args);
	}

	/**
	 * 根据类获得缓存
	 * @param type 实体类型
	 * @return 缓存
	 */
	public <E extends Entity> Cache<E> getCache() {
		return "memcached".equals(BaseParams.CACHE_TYPE) ? getBean(CacheMemcached.class) : getBean(CacheMap.class);
	}

	/**
	 * 根据传入的注解类获得 名-对象 Map列表
	 * @param annotationType 注解类
	 * @return Map列表
	 */
	public Map<String, Object> getBeansWithAnnotation(Class<? extends Annotation> annotationType) {
		return applicationContext.getBeansWithAnnotation(annotationType);
	}

	/**
	 * 根据传入的注解类获得 名-对象 Map列表
	 * @param annotationType 注解类
	 * @return Map列表
	 */
	public <E> Map<String, E> getBeans(Class<E> type) {
		return applicationContext.getBeansOfType(type);
	}

	/**
	 * 获得Request
	 * @return Request
	 */
	public HttpServletRequest getRequest() {
		return ServletActionContext.getRequest();
	}

	/**
	 * 获得Response
	 * @return Response
	 */
	public HttpServletResponse getResponse() {
		return ServletActionContext.getResponse();
	}

	/**
	 * 获得ServletContext
	 * @return ServletContext
	 */
	public ServletContext getServletContext() {
		return ServletActionContext.getServletContext();
	}

	/**
	 * 获得ActionMapping
	 * @return ActionMapping
	 */
	public ActionMapping getActionMapping() {
		return (ActionMapping) getRequest().getAttribute(ACTICON_MAPPING_KEY);
	}

	/**
	 * 获得当前Action
	 * @return Action
	 */
	public <E extends Action> E getAction() {
		// 获得值栈里的对象
		Object action = ActionContext.getContext().getValueStack().peek();
		// 判断对象是Action类型的
		if (action instanceof Action) {
			// 返回Action
			return (E) action;
		}
		// 获得Action拦截器
		ActionInvocation ai = ActionContext.getContext().getActionInvocation();
		// 如果拦截器不为空
		if (ai != null) {
			return (E) ai.getAction();
		}
		// 如果都不符合返回null
		return null;
	}
}
