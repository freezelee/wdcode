package org.wdcode.web.socket.simple;

import java.util.Map;

import org.wdcode.common.lang.Maps;
import org.wdcode.web.socket.Session;

/**
 * 用户Session列表
 * @author WD
 * @since JDK7
 * @version 1.0 2013-12-30
 */
public final class UserSessions {
	// 保存用户
	protected Map<Integer, Session>	users	= Maps.getConcurrentMap();

	/**
	 * 添加用户Session列表
	 * @param id 用户ID
	 * @param session 用户Session
	 */
	public void add(int id, Session session) {
		users.put(id, session);
	}

	/**
	 * 删除用户 会关闭掉用户连接
	 * @param id 用户ID
	 * @return Session
	 */
	public Session remove(int id) {
		// 获得用户Session
		Session session = users.remove(id);
		// session不为空
		if (session != null) {
			// 关闭session
			session.close();
		}
		// 返回session
		return session;
	}

	/**
	 * 根据用户ID获得一个用户Session
	 * @param id 用户ID
	 * @return Session
	 */
	public Session get(int id) {
		return users.get(id);
	}

	/**
	 * 获得所有用户Session
	 * @return 用户Session
	 */
	public Map<Integer, Session> users() {
		return users;
	}
}
