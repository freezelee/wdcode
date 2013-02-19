package org.wdcode.web.socket.nio.factory;

import org.wdcode.core.factory.FactoryKey;
import org.wdcode.web.socket.SocketClient;
import org.wdcode.web.socket.impl.SocketNioClientImpl;

/***
 * Nio Socket服务器工厂
 * @author WD
 * @since JDK7
 * @version 1.0 2011-10-19
 */
public final class SocketNioClientFactory extends FactoryKey<String, SocketClient> {
	// 工厂
	private final static SocketNioClientFactory	FACTORY;

	/**
	 * 静态初始化
	 */
	static {
		FACTORY = new SocketNioClientFactory();
	}

	/**
	 * 私有构造
	 */
	private SocketNioClientFactory() {}

	/**
	 * 获得工厂
	 * @return 工厂
	 */
	public static SocketNioClientFactory getFactory() {
		return FACTORY;
	}

	/**
	 * 实例化一个新对象
	 */
	public SocketClient newInstance(String key) {
		return new SocketNioClientImpl(key);
	}
}
