package org.wdcode.core.cache;

import java.util.List;
import java.util.Map;

import org.wdcode.common.interfaces.Clear;
import org.wdcode.common.interfaces.Empty;

/**
 * 所有缓存的基础接口
 * @author WD
 * @since JDK7
 * @version 1.0 2011-05-02
 */
public interface Cache<K, V> extends Clear, Empty {
	/**
	 * 获得所有缓存Value
	 * @return
	 */
	List<V> list();

	/**
	 * 获得缓存数据
	 * @param key 缓存Key
	 * @return 缓存Value
	 */
	V get(K key);

	/**
	 * 获得缓存数据
	 * @param keys 缓存Key
	 * @return 缓存Value
	 */
	List<V> get(K... keys);

	/**
	 * 获得缓存数据
	 * @param keys 缓存Key
	 * @return 缓存Value
	 */
	List<V> get(List<K> keys);

	/**
	 * 添加缓存
	 * @param key 缓存的Key
	 * @param value 缓存的Value
	 * @return 缓存的Value
	 */
	V set(K key, V value);

	/**
	 * 添加缓存
	 * @param caches 缓存
	 * @return 添加的缓存
	 */
	List<V> set(Map<K, V> caches);

	/**
	 * 删除缓存
	 * @param key 缓存Key
	 * @return 缓存的Value
	 */
	V remove(K key);

	/**
	 * 删除缓存
	 * @param keys 缓存Key
	 * @return value列表
	 */
	List<V> remove(K... keys);

	/**
	 * 删除缓存
	 * @param keys 缓存Key
	 * @return value列表
	 */
	List<V> remove(List<K> keys);

	/**
	 * 获得缓存大小
	 * @return 缓存大小
	 */
	int size();

	/**
	 * 获得缓存是否有效
	 * @return 是否有效
	 */
	boolean isValid();
}
