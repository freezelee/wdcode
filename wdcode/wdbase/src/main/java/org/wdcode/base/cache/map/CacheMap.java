package org.wdcode.base.cache.map;

import java.io.Serializable;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.wdcode.base.cache.base.BaseCache;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.wdcode.base.entity.Entity;
import org.wdcode.common.lang.Lists;
import org.wdcode.core.cache.Cache;

/**
 * 标准的缓存Map实现
 * @author WD
 * @since JDK7
 * @version 1.0 2012-07-13
 */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public final class CacheMap<E extends Entity> extends BaseCache<E> {
	// 保存缓存的Map
	private Cache<Serializable, E>	cache;

	/**
	 * 构造方法
	 */
	public CacheMap() {
		cache = new org.wdcode.core.cache.map.CacheMap<Serializable, E>();
	}

	/**
	 * 获得所有缓存Value
	 * @return
	 */
	public List<E> list() {
		return Lists.sort(cache.list());
	}

	/**
	 * 获得缓存数据
	 * @param key 缓存Key
	 * @return 缓存Value
	 */
	public E get(Serializable key) {
		return cache.get(key);
	}

	/**
	 * 获得缓存数据
	 * @param keys 缓存Key
	 * @return 缓存Value
	 */
	public List<E> get(List<Serializable> keys) {
		return cache.get(keys);
	}

	/**
	 * 删除缓存
	 * @param key 缓存Key
	 */
	public E remove(Serializable key) {
		return cache.remove(key);
	}

	/**
	 * 删除缓存
	 * @param keys 缓存Key
	 */
	public List<E> remove(Serializable... keys) {
		return cache.remove(keys);
	}

	/**
	 * 清除所有缓存
	 */
	public void clear() {
		cache.clear();
	}

	/**
	 * 判断是否为空
	 */
	public boolean isEmpty() {
		return cache.isEmpty();
	}

	/**
	 * 获得缓存是否有效
	 * @return 是否有效
	 */
	public boolean isValid() {
		return cache.isValid();
	}

	/**
	 * 添加缓存
	 * @param key 缓存的Key
	 * @param value 缓存的Value
	 * @return 缓存的Value
	 */
	public E set(E value) {
		return cache.set(value.getKey(), value);
	}

	/**
	 * 获得缓存大小
	 * @return 缓存大小
	 */
	public int size() {
		return cache.size();
	}

	@Override
	public String toString() {
		return cache.toString();
	}
}
