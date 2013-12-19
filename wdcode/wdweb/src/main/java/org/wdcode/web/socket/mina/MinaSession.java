package org.wdcode.web.socket.mina;

import org.apache.mina.core.session.IoSession;
import org.wdcode.web.socket.Session;
import org.wdcode.web.socket.message.Message;

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

	public void write(Message message) {
		session.write(message);
	}

	@Override
	public void close() {
		session.close(false);
	}
}
