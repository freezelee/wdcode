package org.wdcode.web.socket.base;

import org.wdcode.common.constants.StringConstants;
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

	/**
	 * 设置 Session
	 * @param session Session
	 */
	protected void setSession(Session session) {
		this.session = session;
		manager.register(StringConstants.EMPTY, session.getId(), session);
		// 心跳出来不为空
		if (heart != null) {
			heart.add(session);
		}
	}
}
