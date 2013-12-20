package org.wdcode.web.socket.mina;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.wdcode.common.lang.Bytes;
import org.wdcode.common.lang.Conversion;
import org.wdcode.web.socket.Session;
import org.wdcode.web.socket.message.Message;
import org.wdcode.web.socket.message.MessageBytes;
import org.wdcode.web.socket.message.MessageString;

/**
 * Mina实现
 * @author WD
 * @since JDK7
 * @version 1.0 2013-11-28
 */
public final class MinaSession implements Session {
	// Mina Session
	private IoSession	session;

	/**
	 * 构造方法
	 * @param session
	 */
	public MinaSession(IoSession session) {
		this.session = session;
	}

	@Override
	public void write(Message message) {
		// 转换成字节数组
		byte[] data = message.toBytes();
		// 发送长度与消息数据
		session.write(IoBuffer.wrap(Bytes.toBytes(data.length, data)));
	}

	@Override
	public void write(int id, Object message) {
		if (message instanceof String) {
			write(new MessageString(id, Conversion.toString(message)));
		} else {
			write(new MessageBytes(id, Bytes.toBytes(message)));
		}
	}

	@Override
	public void close() {
		session.close(false);
	}
}
