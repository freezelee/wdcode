package org.wdcode.web.socket.impl.mina;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.wdcode.web.socket.base.BaseSession;
import org.wdcode.web.socket.interfaces.Session;

/**
 * Mina实现
 * @author WD
 * @since JDK7
 * @version 1.0 2013-11-28
 */
public final class MinaSession extends BaseSession implements Session {
	// Mina Session
	private IoSession	session;

	/**
	 * 构造方法
	 * @param id sessionId
	 * @param session
	 */
	public MinaSession(int id, IoSession session) {
		this.id = id;
		this.session = session;
		address(session.getRemoteAddress());
	}

	@Override
	public void close() {
		session.close(false);
	}

	@Override
	public boolean isConnect() {
		return session.isConnected();
	}

	@Override
	public boolean isClose() {
		return session.isClosing();
	}

	@Override
	protected void send(byte[] data) {
		session.write(IoBuffer.wrap(data));
	}
}
