package org.wdcode.web.socket;

import org.wdcode.common.interfaces.Close;

/**
 * Socket 套接字接口
 * @author WD
 * @since JDK6
 * @version 1.0 2013-09-18
 */
public interface Socket extends Close {
	/**
	 * 获得Session
	 * @return Session
	 */
	Session getSession();

	/**
	 * 设置要连接的主机
	 * @param host 要连接的主机
	 */
	void setHost(String host);

	/**
	 * 获得要连接的主机
	 * @return 要连接的主机
	 */
	String getHost();

	/**
	 * 设置连接主机端口
	 * @param port 连接主机端口
	 */
	void setPort(int port);

	/**
	 * 获得连接主机端口
	 * @return 连接主机端口
	 */
	int getPort();
}
