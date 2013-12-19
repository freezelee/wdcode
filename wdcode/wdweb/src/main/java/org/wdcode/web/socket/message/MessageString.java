package org.wdcode.web.socket.message;

import org.wdcode.common.interfaces.BytesBean;
import org.wdcode.common.lang.Bytes;

/**
 * 字符串消息
 * @author WD
 * @since JDK7
 * @version 1.0 2013-12-19
 */
public final class MessageString extends Message {
	// 字符串消息
	private String	message;

	@Override
	public byte[] toBytes() {
		return Bytes.toBytes(id, message);
	}

	@Override
	public BytesBean toBean(byte[] b) {
		this.id = Bytes.toInt(b);
		this.message = Bytes.toString(b, 4);
		return this;
	}

	/**
	 * 获得字符串消息
	 * @return 字符串消息
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * 设置字符串消息
	 * @param message 字符串消息
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return message;
	}
}
