package org.wdcode.web.netty.factory;

import org.wdcode.core.factory.FactoryKey;
import org.wdcode.web.netty.NettyServer;
import org.wdcode.web.netty.impl.NettyServerImpl;
import org.wdcode.web.params.NettyParams;

/**
 * netty服务端工厂
 * @author WD
 * @since JDK7
 * @version 1.0 2011-07-07
 */
public final class NettyServerFactory extends FactoryKey<String, NettyServer> {
	// 工厂
	private final static NettyServerFactory	FACTORY;

	/**
	 * 静态初始化
	 */
	static {
		FACTORY = new NettyServerFactory();
	}

	/**
	 * 私有构造
	 */
	private NettyServerFactory() {}

	/**
	 * 获得工厂
	 * @return 工厂
	 */
	public final static NettyServerFactory getFactory() {
		return FACTORY;
	}

	/**
	 * 实例化一个新对象
	 */
	public NettyServer newInstance(String name) {
		return newInstance(name, NettyParams.getPort(name));
	}

	/**
	 * 实例化一个新对象
	 */
	public NettyServer newInstance(String name, int port) {
		return new NettyServerImpl(name);
	}
}
