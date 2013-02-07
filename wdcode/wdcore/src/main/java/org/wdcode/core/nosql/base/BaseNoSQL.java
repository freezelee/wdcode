package org.wdcode.core.nosql.base;

import org.wdcode.core.engine.ZipEngine;
import org.wdcode.core.nosql.NoSQL;

/**
 * NoSQL基类
 * @author WD
 * @since JDK7
 * @version 1.0 2012-11-18
 */
public abstract class BaseNoSQL implements NoSQL {
	/**
	 * 压缩值 当值能压缩时才压缩
	 * @param key 键
	 * @param value 值
	 */
	public final void compress(String key, Object value) {
		set(key, ZipEngine.compress(value));
	}

	/**
	 * 根据键获得压缩值 如果是压缩的返回解压缩的byte[] 否是返回Object
	 * @param key 键
	 * @return 值
	 */
	public final byte[] extract(String key) {
		return ZipEngine.extract(get(key));
	}
}
