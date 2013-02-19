package org.wdcode.web.socket.udp.factory;

import org.wdcode.core.factory.FactoryKey;
import org.wdcode.web.socket.SocketClient;
import org.wdcode.web.socket.udp.impl.SocketUDPImpl;

/**
 * Socket客户端工厂
 * @author WD
 * @since JDK7
 * @version 1.0 2010-01-26
 */
public final class SocketUDPFactory extends FactoryKey<String, SocketClient> {
	// 工厂
	private final static SocketUDPFactory	FACTORY;

	/**
	 * 静态初始化
	 */
	static {
		FACTORY = new SocketUDPFactory();
	}

	/**
	 * 私有构造
	 */
	private SocketUDPFactory() {}

	/**
	 * 获得工厂
	 * @return 工厂
	 */
	public static SocketUDPFactory getFactory() {
		return FACTORY;
	}

	/**
	 * 获得一个新对象
	 */
	public SocketClient newInstance(String key) {
		return new SocketUDPImpl(key);
	}
}
