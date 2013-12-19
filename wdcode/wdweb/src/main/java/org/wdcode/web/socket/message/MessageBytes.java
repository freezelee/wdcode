package org.wdcode.web.socket.message;

import org.wdcode.common.interfaces.BytesBean;
import org.wdcode.common.lang.Bytes;

/**
 * 包装字节数组
 * @author WD
 * @since JDK7
 * @version 1.0 2013-12-19
 */
public final class MessageBytes extends Message {
	// 数据流
	private byte[]	data;

	@Override
	public byte[] toBytes() {
		return Bytes.toBytes(id, data);
	}

	@Override
	public BytesBean toBean(byte[] b) {
		this.id = Bytes.toInt(b);
		this.data = Bytes.copy(b, 4, b.length - 4);
		return this;
	}

	/**
	 * 获得数据流
	 * @return 数据流
	 */
	public byte[] getData() {
		return data;
	}

	/**
	 * 设置数据流
	 * @param data 数据流
	 */
	public void setData(byte[] data) {
		this.data = data;
	}
}
