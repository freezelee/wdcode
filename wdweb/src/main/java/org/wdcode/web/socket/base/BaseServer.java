package org.wdcode.web.socket.base;

import java.util.Map;

import org.wdcode.web.socket.Handler;
import org.wdcode.web.socket.Server;
import org.wdcode.web.socket.Session;

/**
 * 基础Server
 * @author WD
 * @since JDK7
 * @version 1.0 2013-12-30
 */
public abstract class BaseServer extends BaseSocket implements Server {

	@Override
	public Session getSession(int id) {
		return process.getSession(id);
	}

	@Override
	public void addHandler(Handler<?>... handler) {
		process.addHandler(handler);
	}

	@Override
	public Map<Integer, Session> getSessions() {
		return process.getSessions();
	}
}
