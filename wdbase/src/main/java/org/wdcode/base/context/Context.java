package org.wdcode.base.context;

import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;
import org.wdcode.base.cache.Cache;
import org.wdcode.base.cache.impl.CacheMap;
import org.wdcode.base.cache.impl.CacheMemcached;
import org.wdcode.base.entity.Entity;
import org.wdcode.base.params.BaseParams;
import org.wdcode.common.lang.Lists;
import org.wdcode.common.lang.Maps;

/**
 * 全局Context控制
 * @author WD
 * @since JDK7
 * @version 1.0 2012-09-01
 */
@Component
public final class Context {
	// Spring ApplicationContext
	@Resource
	private ConfigurableApplicationContext					cac;
	// DefaultListableBeanFactory
	@Resource
	private DefaultListableBeanFactory						factory;
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
		return cac.getBean(name, requiredType);
	}

	/**
	 * 根据类获得实体
	 * @param requiredType 类型
	 * @return 实体
	 */
	public <E> E getBean(Class<E> requiredType) {
		return cac.getBean(requiredType);
	}

	/**
	 * 根据类获得实体
	 * @param requiredType 类型
	 * @param 参数
	 * @return 实体
	 */
	public <E> E getBean(Class<E> requiredType, Object... args) {
		return (E) cac.getBean(requiredType.getName(), args);
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
		return cac.getBeansWithAnnotation(annotationType);
	}

	/**
	 * 根据传入的注解类获得 名-对象 Map列表
	 * @param annotationType 注解类
	 * @return Map列表
	 */
	public <E> Map<String, E> getBeans(Class<E> type) {
		return cac.getBeansOfType(type);
	}

	/**
	 * 注册一个新Bean
	 * @param beanName Bean名称
	 * @param beanDefinition Bean
	 */
	public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) {
		factory.registerBeanDefinition(beanName, beanDefinition);
	}
}
