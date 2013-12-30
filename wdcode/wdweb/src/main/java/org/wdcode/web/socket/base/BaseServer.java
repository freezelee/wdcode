package org.wdcode.web.socket.base;

import java.util.Map;

import org.wdcode.web.socket.Handler;
import org.wdcode.web.socket.Server;
import org.wdcode.web.socket.Session;
import org.wdcode.web.socket.simple.UserHandler;
import org.wdcode.web.socket.simple.UserSessions;

/**
 * 基础Server
 * @author WD
 * @since JDK7
 * @version 1.0 2013-12-30
 */
public abstract class BaseServer extends BaseSocket implements Server {
	// 保存用户列表
	private UserSessions	users;

	/**
	 * 构造
	 */
	public BaseServer() {
		users = new UserSessions();
	}

	@Override
	public Session getSession(int id) {
		return process.getSession(id);
	}

	@Override
	public void addHandler(Handler<?>... handler) {
		// 处理带用户Session列表
		for (Handler<?> h : handler) {
			// 如果是带用户列表Session
			if (h instanceof UserHandler) {
				((UserHandler<?>) h).setUsers(users);
			}
		}
		// 正常添加到处理器列表
		process.addHandler(handler);
	}

	@Override
	public Map<Integer, Session> getSessions() {
		return process.getSessions();
	}

	@Override
	public void add(int id, Session session) {
		users.add(id, session);
	}

	@Override
	public Session remove(int id) {
		return users.remove(id);
	}

	@Override
	public Session get(int id) {
		return users.get(id);
	}

	@Override
	public Map<Integer, Session> users() {
		return users.users();
	}
}
