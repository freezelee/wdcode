package org.wdcode.web.socket.simple;

import org.wdcode.web.socket.Handler;

/**
 * 带用户列表Handler实现
 * @author WD
 * @since JDK7
 * @version 1.0 2013-12-30
 */
public abstract class UserHandler<E> implements Handler<E> {
	// 用户Session
	protected UserSessions	users;

	/**
	 * 设置用户Session列表
	 * @param users 用户Session列表
	 */
	public void setUsers(UserSessions users) {
		this.users = users;
	}
}
