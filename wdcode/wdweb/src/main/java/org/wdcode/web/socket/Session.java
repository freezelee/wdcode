package org.wdcode.web.socket;

import org.wdcode.common.interfaces.Close;

/**
 * Socket Session
 * @author WD
 * @since JDK7
 * @version 1.0 2013-11-28
 */
public interface Session extends Close {
	/**
	 * 获得SessionId
	 * @return SessionId
	 */
	int getId();

	/**
	 * 设置SessionId
	 * @param id SessionId
	 */
	void setId(int id);

	/**
	 * 写入数据
	 * @param id 指令
	 * @param message 消息
	 */
	void send(int id, Object message);

	/**
	 * 是否连接
	 * @return true 为有连接 false 未连接
	 */
	boolean isConnect();

	/**
	 * 是否关闭
	 * @return true 关闭 false 未关闭
	 */
	boolean isClose();
}
