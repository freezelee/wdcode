package org.wdcode.web.socket.factory;

import org.wdcode.core.factory.FactoryKey;
import org.wdcode.web.socket.SocketServer;
import org.wdcode.web.socket.impl.SocketServerImpl;

/**
 * Socket服务端工厂
 * @author WD
 * @since JDK7
 * @version 1.0 2010-01-26
 */
public final class SocketServerFactory extends FactoryKey<String, SocketServer> {
	// 工厂
	private final static SocketServerFactory	FACTORY;

	/**
	 * 静态初始化
	 */
	static {
		FACTORY = new SocketServerFactory();
	}

	/**
	 * 返回工厂
	 * @return 工厂
	 */
	public static SocketServerFactory getFactory() {
		return FACTORY;
	}

	/**
	 * 实例化一个新对象
	 * @return SocketServer
	 */
	public SocketServer newInstance(String name) {
		//获得解析包
		
		return new SocketServerImpl(name);
	}

	/**
	 * 私有构造
	 */
	private SocketServerFactory() {}
}
