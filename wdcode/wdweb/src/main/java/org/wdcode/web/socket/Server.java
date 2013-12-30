package org.wdcode.web.socket;

import java.util.Map;

/**
 * Socket 服务器
 * @author WD
 * @since JDK7
 * @version 1.0 2013-11-28
 */
public interface Server extends Socket {
	/**
	 * 启动服务器监听
	 */
	void bind();

	/**
	 * 根据ID获得session
	 * @param id SessionId
	 * @return Session
	 */
	Session getSession(int id);

	/**
	 * 获得Session列表
	 * @return Session列表
	 */
	Map<Integer, Session> getSessions();

	/**
	 * 注册用户Session列表
	 * @param id 用户ID
	 * @param session 用户Session
	 */
	void add(int id, Session session);

	/**
	 * 删除用户 会关闭掉用户连接
	 * @param id 用户ID
	 * @return Session
	 */
	Session remove(int id);

	/**
	 * 根据用户ID获得一个用户Session
	 * @param id 用户ID
	 * @return Session
	 */
	Session get(int id);

	/**
	 * 获得所有用户Session
	 * @return 用户Session
	 */
	Map<Integer, Session> users();
}
