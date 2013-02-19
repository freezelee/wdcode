package org.wdcode.web.socket;

import org.wdcode.common.interfaces.Close;

/**
 * Socket服务器接口
 * @author WD
 * @since JDK7
 * @version 1.0 2009-09-14
 */
public interface SocketServer extends Close {
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

	/**
	 * 关闭当前的Socket
	 */
	void shutdown();

	/**
	 * 监听服务器
	 * @param port 服务器端口
	 */
	void bind(int port);

	/**
	 * 监听服务器 使用配置
	 */
	void bind();
}
