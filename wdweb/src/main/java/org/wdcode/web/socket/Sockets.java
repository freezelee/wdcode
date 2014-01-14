package org.wdcode.web.socket;

import java.util.Map;

import org.wdcode.common.constants.StringConstants;
import org.wdcode.common.lang.Maps;
import org.wdcode.common.util.BeanUtil;
import org.wdcode.common.util.ClassUtil;
import org.wdcode.common.util.EmptyUtil;
import org.wdcode.web.params.SocketParams;
import org.wdcode.web.socket.factory.ClientFactory;
import org.wdcode.web.socket.factory.ServerFactory;
import org.wdcode.web.socket.simple.HeartHandler;

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
	 * 初始化Mina
	 */
	public static void init() {
		// 判断任务不为空
		if (SocketParams.POWER) {
			// 循环数组
			for (String name : SocketParams.NAMES) {
				init(name);
			}
			// 启动服务器
			start();
		}
	}

	/**
	 * 根据名称设置
	 * @param name 名
	 */
	public static Socket init(String name) {
		// Socket
		Socket socket = null;
		// 判断是否客户端
		if (!EmptyUtil.isEmpty(SocketParams.getHost(name)) && "client".equals(SocketParams.getType(name))) {
			socket = addClient(name);
		} else {
			socket = addServer(name);
		}
		// 获得心跳时间
		int heart = SocketParams.getHeartTime(name);
		// 配置了心跳
		if (heart > 0) {
			// 设置心跳
			socket.addHandler(new HeartHandler(SocketParams.getHeartId(name), heart));
		}
		// 设置Handler
		for (String c : SocketParams.getHandler(name)) {
			socket.addHandler((Handler<?>) BeanUtil.newInstance(c));
		}
		// 按包处理
		for (String p : SocketParams.getPackage(name)) {
			for (Class<?> c : ClassUtil.getAssignedClass(p, Handler.class)) {
				socket.addHandler((Handler<?>) BeanUtil.newInstance(c));
			}
		}
		// 返回Socket
		return socket;
	}

	/**
	 * 添加服务器
	 * @param name 名称
	 */
	public static Server addServer(String name) {
		return addServer(ServerFactory.getServer(name));
	}

	/**
	 * 添加服务器
	 * @param name 名称
	 */
	public static Server addServer(Server server) {
		SERVERS.put(server.getName(), server);
		return server;
	}

	/**
	 * 添加客户端
	 * @param name 名称
	 */
	public static Client addClient(String name) {
		return addClient(ClientFactory.getClient(name));
	}

	/**
	 * 添加客户端
	 * @param name 名称
	 */
	public static Client addClient(Client client) {
		CLIENTS.put(client.getName(), client);
		return client;
	}

	/**
	 * 广播消息
	 * @param key 注册键
	 * @param id 发送指令
	 * @param message 发送消息
	 */
	public static void radio(int id, Object message) {
		// 循环发送
		for (String name : SERVERS.keySet()) {
			radio(name, id, message);
		}
	}

	/**
	 * 广播消息
	 * @param name 注册键
	 * @param id 发送指令
	 * @param message 发送消息
	 */
	public static void radio(String name, int id, Object message) {
		// 循环发送消息
		for (String key : manager(name).keys()) {
			radio(name, key, id, message);
		}
	}

	/**
	 * 广播消息
	 * @param key 注册键
	 * @param id 发送指令
	 * @param message 发送消息
	 */
	public static void radio(String name, String key, int id, Object message) {
		// 循环发送消息
		for (Session session : manager(name).sessions(key)) {
			session.send(id, message);
		}
	}

	/**
	 * 发送消息
	 * @param key 注册键
	 * @param id 发送指令
	 * @param message 发送消息
	 */
	public static void send(int id, Object message) {
		radio(StringConstants.EMPTY, id, message);
	}

	/**
	 * 发送消息
	 * @param key 注册键
	 * @param id 发送指令
	 * @param message 发送消息
	 */
	public static void send(String key, int id, Object message) {
		radio(StringConstants.EMPTY, key, id, message);
	}

	/**
	 * 获得服务器
	 * @param name
	 * @return
	 */
	public static Server server(String name) {
		return SERVERS.get(name);
	}

	/**
	 * 获得服务器
	 * @return
	 */
	public static Server server() {
		return server(StringConstants.EMPTY);
	}

	/**
	 * 获得服务器Session管理器
	 * @param name
	 * @return
	 */
	public static Manager manager(String name) {
		return server(name).getManager();
	}

	/**
	 * 获得服务器
	 * @return
	 */
	public static Manager manager() {
		return server().getManager();
	}

	/**
	 * 获得客户端
	 * @param name
	 * @return
	 */
	public static Client client(String name) {
		return CLIENTS.get(name);
	}

	/**
	 * 获得客户端
	 * @return
	 */
	public static Client client() {
		return client(StringConstants.EMPTY);
	}

	/**
	 * 获得客户端Session
	 * @param name
	 * @return
	 */
	public static Session session(String name) {
		return client(name).getSession();
	}

	/**
	 * 获得客户端Session
	 * @return
	 */
	public static Session session() {
		return client().getSession();
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

	/**
	 * 关闭所有连接
	 */
	public static void close() {
		for (String name : SERVERS.keySet()) {
			closeServer(name);
		}
		for (String name : CLIENTS.keySet()) {
			closeClient(name);
		}
	}

	/**
	 * 关闭客户端
	 * @param name 要关闭的Client 名 关闭所有连接
	 */
	public static void closeClient(String name) {
		// 获得Client
		Client client = CLIENTS.get(name);
		// 判断acceptor不为空
		if (!EmptyUtil.isEmpty(client)) {
			// 关闭Session
			client.close();
			// 删除Map中的引用
			CLIENTS.remove(name);
		}
	}

	/**
	 * 关闭服务器
	 * @param name 要关闭的Server 名 关闭所有连接
	 */
	public static void closeServer(String name) {
		// 获得Server
		Server server = SERVERS.get(name);
		// 判断acceptor不为空
		if (!EmptyUtil.isEmpty(server)) {
			// 关闭server
			server.close();
			// 删除Map中的引用
			SERVERS.remove(name);
		}
	}

	private Sockets() {}
}