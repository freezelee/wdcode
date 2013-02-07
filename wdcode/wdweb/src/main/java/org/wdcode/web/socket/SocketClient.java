package org.wdcode.web.socket;

import org.wdcode.common.interfaces.Close;

/**
 * Socket客户端接口
 * @author WD
 * @since JDK7
 * @version 1.0 2009-09-14
 */
public interface SocketClient extends Close {
	/**
	 * 连接到服务器
	 */
	void connect();

	/**
	 * 连接到服务器
	 * @param host 服务器地址
	 * @param port 服务器端口
	 */
	void connect(String host, int port);

	/**
	 * 连接到服务器
	 * @param host 服务器地址
	 * @param port 服务器端口
	 * @param timeout 超时时间
	 */
	void connect(String host, int port, int timeout);

	/**
	 * 返回连接服务器的地址
	 * @return 服务器地址
	 */
	String getInetAddress();

	/**
	 * 返回连接服务器的端口
	 * @return 服务器端口
	 */
	int getPort();

	/**
	 * 返回本机端口
	 * @return 本机端口
	 */
	int getLocalPort();

	/**
	 * 返回本机地址
	 * @return 本机地址
	 */
	String getLocalSocketAddress();

	/**
	 * 获得数据
	 * @return 字节数组
	 */
	byte[] accept();

	/**
	 * 发生数据
	 * @param obj 数据
	 */
	void send(Object obj);
}
