package org.wdcode.base.cache.base;

import java.io.Serializable;
import java.util.List;

import org.wdcode.base.cache.Cache;
import org.wdcode.base.entity.Entity;
import org.wdcode.common.lang.Lists;

/**
 * 基础缓存实体
 * @author WD
 * @since JDK7
 * @version 1.0 2012-07-18
 */
public abstract class BaseCache<E extends Entity> implements Cache<E> {

	/**
	 * 获得缓存数据
	 * @param keys 缓存Key
	 * @return 缓存Value
	 */
	public List<E> get(Serializable... keys) {
		return get(Lists.getList(keys));
	}

	/**
	 * 添加缓存
	 * @param key 缓存的Key
	 * @param values 缓存的Value
	 * @return 缓存的Value
	 */
	public List<E> set(List<E> values) {
		// 循环添加
		for (E entity : values) {
			set(entity);
		}
		// 返回列表
		return values;
	}

	/**
	 * 添加缓存
	 * @param entity 缓存的Value
	 * @return 缓存的Value
	 */
	public E remove(E entity) {
		return remove(entity.getKey());
	}

	/**
	 * 添加缓存
	 * @param entitys 缓存的Value
	 * @return 缓存的Value
	 */
	public List<E> remove(List<E> entitys) {
		// 循环删除
		for (E entity : entitys) {
			remove(entity);
		}
		// 返回列表
		return entitys;
	}
}
