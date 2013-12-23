package org.wdcode.core.collection;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.wdcode.common.constants.StringConstants;
import org.wdcode.common.lang.Conversion;
import org.wdcode.common.lang.Lists;
import org.wdcode.common.util.StringUtil;
import org.wdcode.core.nosql.memcache.Memcache;

/**
 * Memcached实现Map
 * @author WD
 * @since JDK7
 * @version 1.0 2013-12-23
 */
public final class MemcacheMap<K, V> implements Map<K, V> {
	// Memcached前缀名
	private String		name;
	// memcached客户端
	private Memcache	memcache;

	/**
	 * 构造
	 * @param key
	 * @param memcache
	 */
	public MemcacheMap(String name, Memcache memcache) {
		this.name = name;
		this.memcache = memcache;
	}

	@Override
	public int size() {
		return Conversion.toInt(memcache.get(getKeySize()));
	}

	@Override
	public boolean isEmpty() {
		return memcache.get(getKey()) == null;
	}

	@Override
	public boolean containsKey(Object key) {
		return false;
	}

	@Override
	public boolean containsValue(Object value) {
		return false;
	}

	@Override
	public V get(Object key) {
		return null;
	}

	@Override
	public V put(K key, V value) {
		return null;
	}

	@Override
	public V remove(Object key) {
		// 获得实体
		V e = get(key);
		// 删除键
		memcache.remove(getKey((Serializable) key));
		// 减数量
		memcache.set(getKeySize(), size() - 1);
		// 减key
		List<String> keys = Lists.getList(getKeys());
		// 删除key
		keys.remove(key);
		// 重新写入key
		memcache.set(getKey(), Lists.toString(keys));
		// 返回实体
		return e;
	}

	@Override
	public void putAll(Map<? extends K, ? extends V> m) {

	}

	@Override
	public void clear() {
		// 循环删除key
		for (String key : getKeys()) {
			memcache.remove(key);
		}
		// 删除数量
		memcache.remove(getKeySize());
		// 删除key集合
		memcache.remove(getKey());
	}

	@Override
	public Set<K> keySet() {
		return null;
	}

	@Override
	public Collection<V> values() {
		return null;
	}

	@Override
	public Set<java.util.Map.Entry<K, V>> entrySet() {
		return null;
	}

	// /**
	// * 根据键列表获得memcached使用键列表
	// * @param key 键
	// * @return 键
	// */
	// private String[] getKey(List<Serializable> key) {
	// // 声明键数组
	// String[] keys = new String[key.size()];
	// // 组成键数组
	// for (int i = 0; i < key.size(); i++) {
	// keys[i] = getKey(key.get(i));
	// }
	// // 返回键
	// return keys;
	// }

	/**
	 * 获得memcached保存主键key
	 * @return memcached使用键
	 */
	private String getKey() {
		return getKey("key");
	}

	/**
	 * 获得memcached保存笨类型的数量
	 * @return memcached使用键
	 */
	private String getKeySize() {
		return getKey("size");
	}

	/**
	 * 根据单个key获得memcached中保存的键
	 * @param key 原实体键
	 * @return memcached使用键
	 */
	private String getKey(Serializable key) {
		return Conversion.toString(key).startsWith(name) ? Conversion.toString(key) : name + StringConstants.UNDERLINE + key;
	}

	/**
	 * 获得本类型全部Keys
	 * @return keys
	 */
	private String[] getKeys() {
		return StringUtil.split(Conversion.toString(memcache.get(getKey())), StringConstants.COMMA);
	}
}
