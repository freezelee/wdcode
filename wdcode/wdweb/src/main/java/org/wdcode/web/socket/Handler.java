package org.wdcode.web.socket;


/**
 * Socket 处理器
 * @author WD
 * @since JDK7
 * @version 1.0 2013-11-28
 */
public interface Handler<E> {
	/**
	 * 处理消息ID
	 * @return
	 */
	int getId();

	/**
	 * 处理器
	 * @param session Socket Session
	 * @param data 传送的数据
	 */
	void handler(Session session, E data);
}
