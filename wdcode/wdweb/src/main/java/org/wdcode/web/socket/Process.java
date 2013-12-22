package org.wdcode.web.socket;


/**
 * Socket 数据处理接口
 * @author WD
 * @since JDK7
 * @version 1.0 2013-12-22
 */
public interface Process {
	/**
	 * 根据ID获得session
	 * @param id SessionId
	 * @return Session
	 */
	Session getSession(int id);

	/**
	 * 添加要处理的Handler
	 * @param handler
	 */
	void addHandler(Handler<?>... handler);

	/**
	 * Session连接时
	 * @param session
	 */
	void connected(Session session, Buffer buffer);

	/**
	 * Session关闭时
	 * @param session
	 */
	void closed(Session session);

	/**
	 * 处理数据
	 * @param session Session
	 * @param data 字节流
	 */
	void process(Session session, byte[] message);
}
