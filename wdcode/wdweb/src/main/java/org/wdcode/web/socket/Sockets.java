package org.wdcode.web.socket;

import java.util.Map;

import org.wdcode.common.lang.Maps;
import org.wdcode.web.socket.factory.ClientFactory;
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
	// 保存SocketClient
	private final static Map<String, Client>	CLIENTS;

	static {
		SERVERS = Maps.getConcurrentMap();
		CLIENTS = Maps.getConcurrentMap();
	}

	/**
	 * 添加服务器
	 * @param name 名称
	 */
	public static void addServer(String name) {
		addServer(ServerFactory.getServer(name));
	}

	/**
	 * 添加服务器
	 * @param name 名称
	 */
	public static void addServer(Server server) {
		SERVERS.put(server.getName(), server);
	}

	/**
	 * 添加客户端
	 * @param name 名称
	 */
	public static void addClient(String name) {
		addClient(ClientFactory.getClient(name));
	}

	/**
	 * 添加客户端
	 * @param name 名称
	 */
	public static void addClient(Client client) {
		CLIENTS.put(client.getName(), client);
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
			server.bind();
		}
		// 启动客户端
		for (Client client : CLIENTS.values()) {
			client.connect();
		}
	}

	private Sockets() {}
}