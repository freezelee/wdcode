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
	// 指令
	private int		id;
	// 数据流
	private byte[]	data;

	/**
	 * 构造
	 */
	public MessageBytes() {}

	/**
	 * 构造
	 * @param id
	 * @param data
	 */
	public MessageBytes(int id, byte[] data) {
		this.id = id;
		this.data = data;
	}

	@Override
	public byte[] toBytes() {
		return Bytes.toBytes(id, data);
	}

	@Override
	public BytesBean toBean(byte[] b) {
		this.data = b;
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

	@Override
	public int getId() {
		return id;
	}
}
