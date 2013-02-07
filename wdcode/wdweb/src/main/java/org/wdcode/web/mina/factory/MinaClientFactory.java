package org.wdcode.web.mina.factory;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.wdcode.core.factory.FactoryKey;
import org.wdcode.web.mina.MinaClient;
import org.wdcode.web.mina.impl.MinaClientImpl;
import org.wdcode.web.params.MinaParams;

/**
 * MinaClient工厂
 * @author WD
 * @since JDK7
 * @version 1.0 2011-07-06
 */
public final class MinaClientFactory extends FactoryKey<String, MinaClient> {
	// 工厂
	private final static MinaClientFactory	FACTORY;

	/**
	 * 静态初始化
	 */
	static {
		FACTORY = new MinaClientFactory();
	}

	/**
	 * 私有构造
	 */
	private MinaClientFactory() {}

	/**
	 * 获得工厂
	 * @return 工厂
	 */
	public final static MinaClientFactory getFactory() {
		return FACTORY;
	}

	/**
	 * 实例化一个新对象
	 */
	public MinaClient newInstance(String name) {
		return newInstance(name, MinaParams.getHost(name), MinaParams.getPort(name));
	}

	/**
	 * 实例化一个新对象
	 */
	public MinaClient newInstance(String name, String host, int port) {
		// 声明客户端
		MinaClient client = new MinaClientImpl(name);
		// 设置处理器
		client.setHandler(new IoHandlerAdapter());
		// 返回客户端
		return client;
	}
}
