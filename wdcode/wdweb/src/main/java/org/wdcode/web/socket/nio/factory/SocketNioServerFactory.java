package org.wdcode.web.socket.nio.factory;

import org.wdcode.core.factory.FactoryKey;
import org.wdcode.web.socket.SocketServer;
import org.wdcode.web.socket.impl.SocketNioServerImpl;

/**
 * Nio Socket服务器 工厂
 * @author WD
 * @since JDK7
 * @version 1.0 2011-10-19
 */
public final class SocketNioServerFactory extends FactoryKey<String, SocketServer> {
	// 工厂
	private final static SocketNioServerFactory	FACTORY;

	/**
	 * 静态初始化
	 */
	static {
		FACTORY = new SocketNioServerFactory();
	}

	/**
	 * 私有构造
	 */
	private SocketNioServerFactory() {}

	/**
	 * 获得工厂
	 * @return 工厂
	 */
	public static SocketNioServerFactory getFactory() {
		return FACTORY;
	}

	/**
	 * 实例化一个新对象
	 */
	public SocketServer newInstance(String key) {
		return new SocketNioServerImpl(key);
	}
}
