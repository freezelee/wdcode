package org.wdcode.web.socket.factory;

import org.wdcode.core.factory.FactoryKey;
import org.wdcode.web.socket.SocketClient;
import org.wdcode.web.socket.impl.SocketClientImpl;

/**
 * Socket客户端工厂
 * @author WD
 * @since JDK7
 * @version 1.0 2010-01-26
 */
public final class SocketClientFactory extends FactoryKey<String, SocketClient> {
	// 同步锁
	private final static SocketClientFactory	FACTORY;

	/**
	 * 静态初始化
	 */
	static {
		FACTORY = new SocketClientFactory();
	}

	/**
	 * 返回工厂
	 * @return 工厂
	 */
	public static SocketClientFactory getFactory() {
		return FACTORY;
	}

	/**
	 * 获得新对象
	 */
	public SocketClient newInstance(String key) {
		return new SocketClientImpl(key);
	}

	/**
	 * 私有构造
	 */
	private SocketClientFactory() {}
}
