package org.wdcode.core.cache.map;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;

import org.wdcode.common.lang.Lists;
import org.wdcode.common.lang.Maps;
import org.wdcode.common.util.EmptyUtil;
import org.wdcode.core.cache.Cache;
import org.wdcode.core.params.CoreParams;

/**
 * 标准的缓存Map实现
 * @author WD
 * @since JDK7
 * @version 1.0 2011-05-02
 */
public final class CacheMap<K, V> implements Cache<K, V> {
	// 保存缓存的Map
	private ConcurrentMap<K, V>	mapCache;
	// 空缓存V的List
	private List<V>				emptyValueList;
	// 是否有效
	private boolean				valid;

	/**
	 * 构造函数
	 */
	public CacheMap() {
		this.valid = CoreParams.CACHE_VALID;
		this.mapCache = Maps.getConcurrentMap();
		this.emptyValueList = Lists.emptyList();
	}

	/**
	 * 获得所有缓存Value
	 * @return
	 */
	public List<V> list() {
		return valid ? Lists.getList(mapCache.values()) : emptyValueList;
	}

	/**
	 * 获得缓存数据
	 * @param key 缓存Key
	 * @return 缓存Value
	 */
	public V get(K key) {
		return valid ? mapCache.get(key) : null;
	}

	/**
	 * 获得缓存数据
	 * @param keys 缓存Key
	 * @return 缓存Value
	 */
	public List<V> get(K... keys) {
		return get(Lists.getList(keys));
	}

	/**
	 * 获得缓存数据
	 * @param keys 缓存Key
	 * @return 缓存Value
	 */
	public List<V> get(List<K> keys) {
		// 判断是否生效
		if (isValid() && !EmptyUtil.isEmpty(keys)) {
			// 获得列表数量
			int size = keys.size();
			// 声明列表
			List<V> list = Lists.getList(size);
			// 循环获得值
			for (int i = 0; i < size; i++) {
				// 添加到列表
				list.add(mapCache.get(keys.get(i)));
			}
			// 返回列表
			return list;
		}
		// 返回
		return emptyValueList;
	}

	/**
	 * 添加缓存
	 * @param key 缓存的Key
	 * @param value 缓存的Value
	 */
	public V set(K key, V value) {
		// 判断是否生效
		if (isValid()) {
			// 如果Key或则value为空 直接返回value
			if (EmptyUtil.isEmpty(key) || EmptyUtil.isEmpty(value)) {
				return value;
			}
			// 设置缓存
			value.toString();
			mapCache.put(key, value);
		}
		// 添加并返回实体
		return value;
	}

	/**
	 * 添加缓存r
	 * @param cachees 缓存
	 * @return 添加的缓存
	 */
	public List<V> set(Map<K, V> caches) {
		// 判断是否生效
		if (isValid()) {
			// 如果添加的缓存为空直接返回
			if (EmptyUtil.isEmpty(caches)) {
				return emptyValueList;
			}
			// 把数据添加到缓存Map中
			mapCache.putAll(caches);
		}
		// 返回缓存
		return Lists.getList(caches.values());
	}

	/**
	 * 删除缓存
	 * @param key 缓存Key
	 */
	public V remove(K key) {
		// 声明实体
		V v = null;
		// 判断是否游戏
		if (isValid() && !EmptyUtil.isEmpty(key)) {
			// 删除缓存并添加到值列表中
			v = mapCache.remove(key);
		}
		// 返回实体
		return v;
	}

	/**
	 * 删除缓存
	 * @param keys 缓存Key
	 */
	public List<V> remove(K... keys) {
		return remove(Lists.getList(keys));
	}

	/**
	 * 删除缓存
	 * @param keys 缓存Key
	 */
	public List<V> remove(List<K> keys) {
		// 判断是否游戏
		if (isValid() && !EmptyUtil.isEmpty(keys)) {
			// 获得列表数量
			int size = keys.size();
			// 获得值列表
			List<V> lsValue = Lists.getList(size);
			// 循环Key数组
			for (int i = 0; i < size; i++) {
				// 删除缓存并添加到值列表中
				lsValue.add(remove(keys.get(i)));
			}
			// 返回值列表
			return lsValue;
		}
		// 返回
		return emptyValueList;
	}

	/**
	 * 获得实际缓存的大小
	 * @return 实际缓存的大小
	 */
	public int size() {
		return isValid() ? mapCache.size() : 0;
	}

	/**
	 * 清除所有缓存
	 */
	public void clear() {
		// 判断缓存是否生效
		if (isValid()) {
			// 清除数据缓存
			mapCache.clear();
		}
	}

	/**
	 * 判断是否为空
	 */
	public boolean isEmpty() {
		return EmptyUtil.isEmpty(mapCache);
	}

	/**
	 * 获得缓存是否有效
	 * @return 是否有效
	 */
	public boolean isValid() {
		return valid;
	}

	@Override
	public String toString() {
		return mapCache.toString();
	}
}
