package org.wdcode.web.socket.mina;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.wdcode.web.socket.Session;

/**
 * Mina实现
 * @author WD
 * @since JDK7
 * @version 1.0 2013-11-28
 */
public final class SessionMina implements Session {
	// Mina Session
	private IoSession	session;

	/**
	 * 构造方法
	 * @param session
	 */
	public SessionMina(IoSession session) {
		this.session = session;
	}

	public void write(byte[] b) {
		session.write(IoBuffer.wrap(b));
	}

	@Override
	public void close() {
		session.close(false);
	}
}
