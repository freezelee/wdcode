package org.wdcode.web.socket.engine;

import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Map;

import org.wdcode.common.io.ChannelUtil;
import org.wdcode.common.lang.Bytes;
import org.wdcode.common.lang.Maps;

/**
 * Socket 处理引擎
 * @author WD
 * @since JDK6
 * @version 1.0 2013-9-18
 */
public final class SocketNioEngine {
	// 保存Socket服务器
	private final static Map<String, ServerSocketChannel>	SERVERS;
	// 保存Socket客户端
	private final static Map<String, SocketChannel>			CLIENT;

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
			SERVERS.put(name, ServerSocketChannel.open().bind(new InetSocketAddress(port)));
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
			SocketChannel channel = SocketChannel.open();
			channel.connect(new InetSocketAddress(host, port));
			CLIENT.put(name, channel);
		} catch (Exception e) {}
	}

	/**
	 * 根据名称获得客户端
	 * @param name 客户端名称
	 * @return 客户端Socket
	 */
	public static SocketChannel client(String name) {
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
			ChannelUtil.write(client(name), Bytes.toBytes(mess), false);
		} catch (Exception e) {}
	}

	private SocketNioEngine() {}
}