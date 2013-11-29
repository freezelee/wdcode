package org.wdcode.web.socket;

/**
 * Socket 处理器
 * @author WD
 * @since JDK7
 * @version 1.0 2013-11-28
 */
public interface Handler {
	/**
	 * 处理器
	 * @param session Socket Session
	 * @param date 传送的数据
	 */
	void handler(Session session, byte[] date);
}
