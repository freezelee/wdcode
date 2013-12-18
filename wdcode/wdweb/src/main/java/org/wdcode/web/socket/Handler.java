package org.wdcode.web.socket;

import org.wdcode.common.interfaces.BytesBean;

/**
 * Socket 处理器
 * @author WD
 * @since JDK7
 * @version 1.0 2013-11-28
 */
public interface Handler<E extends BytesBean> {
	/**
	 * 处理消息ID
	 * @return
	 */
	int getId();

	/**
	 * 处理器
	 * @param session Socket Session
	 * @param date 传送的数据
	 */
	void handler(Session session, E date);
}
