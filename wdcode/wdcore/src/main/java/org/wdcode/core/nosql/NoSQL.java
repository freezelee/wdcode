package org.wdcode.core.nosql;

import org.wdcode.common.interfaces.Clear;
import org.wdcode.common.interfaces.Close;

/**
 * nosql相关操作接口
 * @author WD
 * @since JDK7
 * @version 1.0 2012-11-18
 */
public interface NoSQL extends Clear, Close {
	/**
	 * 压缩值 当值能压缩时才压缩
	 * @param key 键
	 * @param value 值
	 */
	boolean compress(String key, Object value);

	/**
	 * 根据键获得压缩值 如果是压缩的返回解压缩的byte[] 否是返回Object
	 * @param key 键
	 * @return 值
	 */
	byte[] extract(String key);

	/**
	 * 设置键值 无论存储空间是否存在相同键，都保存
	 * @param key 键
	 * @param value 值
	 */
	boolean set(String key, Object value);

	/**
	 * 根据键获得值
	 * @param key 键
	 * @return 值
	 */
	Object get(String key);

	/**
	 * 删除键值
	 * @param key 键
	 */
	void remove(String... key);

	/**
	 * 验证键是否存在
	 * @param key
	 * @return true 存在 false 不存在
	 */
	boolean exists(String key);
}