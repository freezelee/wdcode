package org.wdcode.web.socket.base;

import org.wdcode.web.socket.Client;
import org.wdcode.web.socket.Session;

/**
 * 基础Client
 * @author WD
 * @since JDK7
 * @version 1.0 2013-12-30
 */
public abstract class BaseClient extends BaseSocket implements Client {
	// Session
	protected Session	session;

	@Override
	public Session getSession() {
		return session;
	}
}
