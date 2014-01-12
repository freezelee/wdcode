package org.wdcode.web.socket.base;

import org.wdcode.common.constants.ArrayConstants;
import org.wdcode.common.lang.Bytes;
import org.wdcode.common.lang.Conversion;
import org.wdcode.common.util.StringUtil;
import org.wdcode.web.socket.Session;
import org.wdcode.web.socket.simple.Message;

/**
 * 基础Socket Session实现
 * @author WD
 * @since JDK7
 * @version 1.0 2013-12-22
 */
public abstract class BaseSession implements Session {
	// SessionId
	protected int	id;

	@Override
	public void send(int id, Object message) {
		// 声明字节数组
		byte[] data = null;
		// 判断类型
		if (message == null) {
			// 空
			data = ArrayConstants.BYTES_EMPTY;
		} else if (message instanceof String) {
			// 字符串
			data = StringUtil.toBytes(Conversion.toString(message));
		} else if (message instanceof Message) {
			// 消息体
			data = ((Message) message).toBytes();
		} else {
			// 不知道的类型 以字节数组发送
			data = Bytes.toBytes(message);
		}
		// 发送数据
		send(Bytes.toBytes(data.length + 4, id, data));
	}

	/**
	 * 获得SessionId
	 * @return SessionId
	 */
	public int getId() {
		return id;
	}

	/**
	 * 设置SessionId
	 * @param id SessionId
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * 发送数据
	 * @param data 字节流数据
	 */
	protected abstract void send(byte[] data);
}
