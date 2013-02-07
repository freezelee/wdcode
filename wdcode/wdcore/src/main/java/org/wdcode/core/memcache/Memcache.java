package org.wdcode.core.memcache;

import java.util.List;
import java.util.Map;

import org.wdcode.common.interfaces.Clear;
import org.wdcode.common.interfaces.Close;

/**
 * MemCached的客户端调用接口
 * @author WD
 * @since JDK7
 * @version 1.0 2010-08-29
 */
public interface Memcache extends Close, Clear {
	/**
	 * 压缩值 当值能压缩时才压缩
	 * @param key 键
	 * @param value 值
	 */
	boolean compress(String key, Object value);

	/**
	 * 设置键值 无论存储空间是否存在相同键，都保存
	 * @param key 键
	 * @param value 值
	 */
	boolean set(String key, Object value);

	/**
	 * 删除键值
	 * @param key 键
	 */
	boolean remove(String key);

	/**
	 * 根据键获得压缩值 如果是压缩的返回解压缩的byte[] 否是返回Object
	 * @param key 键
	 * @return 值
	 */
	byte[] extract(String key);

	/**
	 * 根据键获得值
	 * @param key 键
	 * @return 值
	 */
	Object get(String key);

	/**
	 * 获得多个键的Map
	 * @param keys 键
	 * @return 值
	 */
	Map<String, Object> getMap(String... keys);

	/**
	 * 获得多个键的数组
	 * @param keys 键
	 * @return 值
	 */
	Object[] get(String... keys);

	/**
	 * 获得多个键的数组
	 * @param keys 键
	 * @return 值
	 */
	List<byte[]> extract(String... keys);

	/**
	 * 验证键是否存在
	 * @param key
	 * @return true 存在 false 不存在
	 */
	boolean keyExists(String key);
}