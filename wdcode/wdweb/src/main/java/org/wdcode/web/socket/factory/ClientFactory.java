package org.wdcode.web.socket.factory;

import org.wdcode.core.factory.FactoryKey;
import org.wdcode.web.params.SocketParams;
import org.wdcode.web.socket.Client;
import org.wdcode.web.socket.impl.mina.MinaClient;
import org.wdcode.web.socket.impl.netty.NettyClient;
import org.wdcode.web.socket.impl.netty3.Netty3Client;

/**
 * Socket客户端工厂
 * @author WD
 * @since JDK7
 * @version 1.0 2013-12-19
 */
public final class ClientFactory extends FactoryKey<String, Client> {
	// 客户端工厂
	private final static ClientFactory	FACTORY;
	static {
		FACTORY = new ClientFactory();
	}

	/**
	 * 获得Socket客户端
	 * @return Socket客户端
	 */
	public static Client getClient(String name) {
		return FACTORY.getInstance(name);
	}

	@Override
	public Client newInstance(String key) {
		switch (SocketParams.getParse(key)) {
			case "netty":
				return new NettyClient(key);
			case "netty3":
				return new Netty3Client(key);
			default:
				// 默认mina
				return new MinaClient(key);
		}
	}

	private ClientFactory() {}
}
