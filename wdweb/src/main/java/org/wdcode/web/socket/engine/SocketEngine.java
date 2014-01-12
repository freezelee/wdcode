package org.wdcode.web.socket.engine;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;

import org.wdcode.common.io.IOUtil;
import org.wdcode.common.lang.Bytes;
import org.wdcode.common.lang.Maps;

/**
 * Socket 处理引擎
 * @author WD
 * @since JDK6
 * @version 1.0 2013-9-18
 */
public final class SocketEngine {
	// 保存Socket服务器
	private final static Map<String, ServerSocket>	SERVERS;
	// 保存Socket客户端
	private final static Map<String, Socket>		CLIENT;

	static {
		SERVERS = Maps.getConcurrentMap();
		CLIENT = Maps.getConcurrentMap();
	}

	/**
	 * 添加Socket服务器
	 * @param name 服务器名
	 * @param port 监听端口
	 */
	public static void addServer(String name, int port) {
		try {
			SERVERS.put(name, new ServerSocket(port));
		} catch (Exception e) {}
	}

	/**
	 * 添加Socket客户端
	 * @param name 服务器名
	 * @param host 连接服务器
	 * @param port 连接端口
	 */
	public static void addClient(String name, String host, int port) {
		try {
			CLIENT.put(name, new Socket(host, port));
		} catch (Exception e) {}
	}

	/**
	 * 根据名称获得客户端
	 * @param name 客户端名称
	 * @return 客户端Socket
	 */
	public static Socket client(String name) {
		return CLIENT.get(name);
	}

	/**
	 * 根据客户端名称发送信息
	 * @param name 客户端名称
	 * @param mess 要发送的信息
	 * @return 客户端IoSession
	 */
	public static void send(String name, Object mess) {
		try {
			IOUtil.write(client(name).getOutputStream(), Bytes.toBytes(mess), false);
		} catch (Exception e) {}
	}

	private SocketEngine() {}
}