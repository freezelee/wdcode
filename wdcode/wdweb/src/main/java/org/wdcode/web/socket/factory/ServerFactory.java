package org.wdcode.web.socket.factory;

import org.wdcode.core.factory.FactoryKey;
import org.wdcode.web.params.SocketParams;
import org.wdcode.web.socket.Server;
import org.wdcode.web.socket.mina.ServerMina;

/**
 * Socket服务器工程
 * @author WD
 * @since JDK7
 * @version 1.0 2013-12-7
 */
public final class ServerFactory extends FactoryKey<String, Server> {
	// 工厂
	private final static ServerFactory	FACTORY;
	static {
		FACTORY = new ServerFactory();
	}

	/**
	 * 获得Socket服务器
	 * @param name 名称
	 * @return Server
	 */
	public static Server getServer(String name) {
		return FACTORY.getInstance(name);
	}

	@Override
	public Server newInstance(String key) {
		switch (SocketParams.getParse(key)) {
			default:
				// 默认mina
				return new ServerMina(key);
		}
	}
}
