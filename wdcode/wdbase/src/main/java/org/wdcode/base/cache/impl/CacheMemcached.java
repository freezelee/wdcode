package org.wdcode.base.cache.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.wdcode.base.cache.base.BaseCache;
import org.wdcode.base.entity.Entity;
import org.wdcode.common.constants.StringConstants;
import org.wdcode.common.lang.Conversion;
import org.wdcode.common.lang.Lists;
import org.wdcode.common.util.EmptyUtil;
import org.wdcode.common.util.StringUtil;
import org.wdcode.core.json.JsonEngine;
import org.wdcode.core.memcache.Memcache;
import org.wdcode.core.memcache.factory.MemcacheFactory;

/**
 * 基于memcached的缓存
 * @author WD
 * @since JDK6
 * @version 1.0 2013-09-06
 */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public final class CacheMemcached<E extends Entity> extends BaseCache<E> {
	// memcached
	private final static Memcache	MEMCACHE	= MemcacheFactory.getMemcache("cache");
	// Key
	private Class<?>				clazz;

	@Override
	public E set(E entity) {
		// 获得键
		String key = getKey(entity.getKey());
		// 添加到memcached
		if (MEMCACHE.set(key, entity.toString())) {
			// 获得保存键值
			String k = getKey();
			// 追加到memcace
			if (!MEMCACHE.append(k, StringConstants.COMMA + key)) {
				// 没追加到 第一个直接set
				MEMCACHE.set(k, key);
			}
		}
		// 返回实体
		return entity;
	}

	@Override
	public E get(Serializable key) {
		return (E) JsonEngine.toBean(Conversion.toString(MEMCACHE.get(getKey(key))), clazz);
	}

	@Override
	public List<E> get(List<Serializable> keys) {
		// 判断键为空
		if (EmptyUtil.isEmpty(keys)) {
			return Lists.emptyList();
		}
		// 获得所有对象
		Object[] objs = MEMCACHE.get(getKey(keys));
		// 判断值为空
		if (EmptyUtil.isEmpty(objs)) {
			return Lists.emptyList();
		}
		// 声明返回列表
		List<E> list = Lists.getList(objs.length);
		// 循环转换对象
		for (Object o : objs) {
			list.add((E) JsonEngine.toBean(Conversion.toString(o), clazz));
		}
		// 返回列表
		return list;
	}

	@Override
	public E remove(Serializable key) {
		// 获得实体
		E e = get(key);
		// 删除键
		MEMCACHE.remove(getKey(key));
		// 返回实体
		return e;
	}

	@Override
	public List<E> remove(Serializable... keys) {
		// 声明删除列表
		List<E> list = Lists.getList(keys.length);
		// 循环删除
		for (Serializable key : keys) {
			list.add(remove(key));
		}
		// 返回列表
		return list;
	}

	@Override
	public List<E> list() {
		// 获得全部key
		String[] keys = StringUtil.split(Conversion.toString(MEMCACHE.get(getKey())), StringConstants.COMMA);
		// 获得键
		List<Serializable> list = Lists.getList(keys.length);
		// 循环键
		for (String key : keys) {
			list.add(key);
		}
		// 返回列表
		return get(list);
	}

	@Override
	public int size() {
		return list().size();
	}

	@Override
	public void clear() {
		MEMCACHE.clear();
	}

	@Override
	public boolean isEmpty() {
		return MEMCACHE.get(getKey()) == null;
	}

	/**
	 * 设置Key
	 * @param key 键
	 */
	public void setClass(Class<?> clazz) {
		this.clazz = clazz;
	}

	/**
	 * 根据键列表获得memcached使用键列表
	 * @param key 键
	 * @return 键
	 */
	private String[] getKey(List<Serializable> key) {
		// 声明键数组
		String[] keys = new String[key.size()];
		// 组成键数组
		for (int i = 0; i < key.size(); i++) {
			keys[i] = getKey(key.get(i));
		}
		// 返回键
		return keys;
	}

	/**
	 * 获得memcached保存主键key
	 * @return memcached使用键
	 */
	private String getKey() {
		return getKey("key");
	}

	/**
	 * 根据单个key获得memcached中保存的键
	 * @param key 原实体键
	 * @return memcached使用键
	 */
	private String getKey(Serializable key) {
		return Conversion.toString(key).startsWith(clazz.getSimpleName()) ? Conversion.toString(key) : clazz.getSimpleName() + StringConstants.UNDERLINE + key;
	}
}
