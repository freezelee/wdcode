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
	 * 添加Handler
	 * @param handler Handler
	 */
	void addHandler(Handler handler);

	/**
	 * 服务器绑定端口
	 * @param port 端口
	 */
	void bind(int port);

	/**
	 * 连接到服务器
	 * @param host 服务器地址
	 * @param port 服务器端口
	 */
	void connect(String host, int port);
}
