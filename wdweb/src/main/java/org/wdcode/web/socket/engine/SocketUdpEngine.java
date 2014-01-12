package org.wdcode.web.socket.engine;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.util.Map;

import org.wdcode.common.constants.ArrayConstants;
import org.wdcode.common.lang.Bytes;
import org.wdcode.common.lang.Maps;
import org.wdcode.common.params.CommonParams;

/**
 * Socket 处理引擎
 * @author WD
 * @since JDK6
 * @version 1.0 2013-9-18
 */
public final class SocketUdpEngine {
	// 保存Socket客户端
	private final static Map<String, DatagramSocket>	CLIENT;

	static {
		CLIENT = Maps.getConcurrentMap();
	}

	/**
	 * 添加Socket客户端
	 * @param name 服务器名
	 * @param host 连接服务器
	 * @param port 连接端口
	 */
	public static void addClient(String name, String host, int port) {
		try {
			CLIENT.put(name, new DatagramSocket(new InetSocketAddress(host, port)));
		} catch (Exception e) {}
	}

	/**
	 * 根据名称获得客户端
	 * @param name 客户端名称
	 * @return 客户端Socket
	 */
	public static DatagramSocket client(String name) {
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
			// 获得字节数组
			byte[] b = Bytes.toBytes(mess);
			// 声明UDP数据包
			DatagramPacket p = new DatagramPacket(b, b.length);
			// 发送数据
			client(name).send(p);
		} catch (Exception e) {}
	}

	/**
	 * 获得数据
	 * @return 字节数组
	 */
	public static byte[] accept(String name) {
		try {
			// IO缓存大小
			int size = CommonParams.IO_BUFFERSIZE;
			// 声明字节数组
			byte[] b = new byte[size];
			// 声明UDP数据包
			DatagramPacket p = new DatagramPacket(b, b.length);
			// 声明字节数组流
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			// 声明长度
			int len = 0;
			do {
				// 接收数据
				client(name).receive(p);
				// 写入数据
				baos.write(p.getData());
				// 获得长度
				len = p.getLength();
			} while (len > 0 || len <= size);

			// 返回数据
			return baos.toByteArray();
		} catch (IOException e) {
			// 返回空字节数组
			return ArrayConstants.BYTES_EMPTY;
		}
	}

	private SocketUdpEngine() {}
}