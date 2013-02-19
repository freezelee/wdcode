package org.wdcode.web.netty.factory;

import org.wdcode.core.factory.FactoryKey;
import org.wdcode.web.netty.NettyClient;
import org.wdcode.web.netty.impl.NettyClientImpl;
import org.wdcode.web.params.NettyParams;

/**
 * Netty客户端工厂
 * @author WD
 * @since JDK7
 * @version 1.0 2011-07-07
 */
public final class NettyClientFactory extends FactoryKey<String, NettyClient> {
	// 工厂
	private final static NettyClientFactory	FACTORY;

	/**
	 * 静态初始化
	 */
	static {
		FACTORY = new NettyClientFactory();
	}

	/**
	 * 私有构造
	 */
	private NettyClientFactory() {}

	/**
	 * 获得工厂
	 * @return 工厂
	 */
	public final static NettyClientFactory getFactory() {
		return FACTORY;
	}

	/**
	 * 实例化一个新对象
	 */
	public NettyClient newInstance(String name) {
		return newInstance(name, NettyParams.getHost(name), NettyParams.getPort(name));
	}

	/**
	 * 实例化一个新对象
	 */
	public NettyClient newInstance(String name, String host, int port) {
		return new NettyClientImpl(name);
	}
}
