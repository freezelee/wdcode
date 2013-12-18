package org.wdcode.web.socket;

import org.wdcode.common.interfaces.BytesBean;

/**
 * 编码解码器
 * @author WD
 * @since JDK7
 * @version 1.0 2013-12-18
 */
public interface Codec<E extends BytesBean> {
	/**
	 * 编码
	 * @param data 数据对象
	 * @return 字节数组
	 */
	byte[] encode(E data);

	/**
	 * 解码
	 * @param data 字节数组
	 * @return 数据对象
	 */
	E decode(byte[] data);
}
