package org.wdcode.web.socket;

import java.util.Map;

import org.wdcode.common.lang.Maps;
import org.wdcode.web.socket.factory.ServerFactory;

/**
 * Socket 相关类
 * @author WD
 * @since JDK7
 * @version 1.0 2013-12-7
 */
public final class Sockets {
	// 保存SocketServer
	private final static Map<String, Server>	SERVERS;

	static {
		SERVERS = Maps.getConcurrentMap();
	}

	/**
	 * 添加服务器
	 * @param name 名称
	 */
	public static void addServer(String name) {
		SERVERS.put(name, ServerFactory.getServer(name));
	}

	/**
	 * 获得服务器
	 * @param name
	 * @return
	 */
	public static Server getServer(String name) {
		return SERVERS.get(name);
	}

	/**
	 * 启动Socket
	 */
	public static void start() {
		// 启动服务器
		for (Server server : SERVERS.values()) {
			server.start();
		}
	}

	private Sockets() {}
}