package org.wdcode.web.mina.factory;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.wdcode.core.factory.FactoryKey;
import org.wdcode.web.mina.MinaServer;
import org.wdcode.web.mina.impl.MinaServerImpl;
import org.wdcode.web.params.MinaParams;

/**
 * MinaService工厂
 * @author WD
 * @since JDK7
 * @version 1.0 2011-07-06
 */
public final class MinaServerFactory extends FactoryKey<String, MinaServer> {
	// 工厂
	private final static MinaServerFactory	FACTORY;

	/**
	 * 静态初始化
	 */
	static {
		FACTORY = new MinaServerFactory();
	}

	/**
	 * 私有构造
	 */
	private MinaServerFactory() {}

	/**
	 * 获得工厂
	 * @return 工厂
	 */
	public final static MinaServerFactory getFactory() {
		return FACTORY;
	}

	/**
	 * 实例化一个新对象
	 */
	public MinaServer newInstance(String name) {
		return newInstance(name, MinaParams.getPort(name));
	}

	/**
	 * 实例化一个新对象
	 */
	public MinaServer newInstance(String name, int port) {
		// 获得Mina服务器
		MinaServer server = new MinaServerImpl(name);
		// 设置处理器
		server.setHandler(new IoHandlerAdapter());
		// 返回服务器
		return server;
	}
}
