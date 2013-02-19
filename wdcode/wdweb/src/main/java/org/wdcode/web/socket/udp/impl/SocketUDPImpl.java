package org.wdcode.web.socket.udp.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;

import org.wdcode.common.constants.ArrayConstants;
import org.wdcode.common.lang.Bytes;
import org.wdcode.common.log.Logs;
import org.wdcode.common.params.CommonParams;
import org.wdcode.web.params.SocketParams;
import org.wdcode.web.socket.SocketClient;

/**
 * UDP Socket客户端实现
 * @author WD
 * @since JDK7
 * @version 1.0 2010-01-29
 */
public final class SocketUDPImpl implements SocketClient {
	// 名称
	private String			name;
	// JDK UDP Socket
	private DatagramSocket	socket;

	/**
	 * 构造方法
	 */
	public SocketUDPImpl(String name) {
		try {
			this.name = name;
			socket = new DatagramSocket();
		} catch (SocketException e) {
			Logs.error(e);
		}
	}

	/**
	 * 构造方法
	 * @param host 主机
	 * @param port 端口
	 */
	public SocketUDPImpl(String name, String host, int port) {
		try {
			this.name = name;
			socket = new DatagramSocket(new InetSocketAddress(host, port));
		} catch (SocketException e) {
			Logs.error(e);
		}
	}

	/**
	 * 绑定到一台服务器上
	 * @param host 服务器地址
	 * @param port 服务器端口
	 */
	public void bind(String host, int port) {
		try {
			socket.bind(new InetSocketAddress(host, port));
		} catch (SocketException e) {
			// 记录日志
			Logs.error(e);
		}
	}

	/**
	 * 连接到服务器
	 * @param host 服务器地址
	 * @param port 服务器端口
	 */
	public void connect(String host, int port) {
		try {
			socket.connect(new InetSocketAddress(host, port));
		} catch (SocketException e) {
			// 记录日志
			Logs.error(e);
		}
	}

	/**
	 * 连接到服务器
	 * @param host 服务器地址
	 * @param port 服务器端口
	 * @param timeout 超时时间
	 */
	public void connect(String host, int port, int timeout) {
		try {
			socket.connect(new InetSocketAddress(host, port));
		} catch (SocketException e) {
			// 记录日志
			Logs.error(e);
		}
	}

	/**
	 * 返回连接服务器的地址
	 * @return 服务器地址
	 */
	public String getInetAddress() {
		return socket.getInetAddress().toString();
	}

	/**
	 * 返回本机端口
	 * @return 本机端口
	 */
	public int getLocalPort() {
		return socket.getLocalPort();
	}

	/**
	 * 返回本机地址
	 * @return 本机地址
	 */
	public String getLocalSocketAddress() {
		return socket.getLocalSocketAddress().toString();
	}

	/**
	 * 返回连接服务器的端口
	 * @return 服务器端口
	 */
	public int getPort() {
		return socket.getPort();
	}

	/**
	 * 关闭资源
	 */
	public void close() {
		socket.close();
	}

	/**
	 * 获得数据
	 * @return 字节数组
	 */
	public byte[] accept() {
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
				socket.receive(p);
				// 写入数据
				baos.write(p.getData());
				// 获得长度
				len = p.getLength();
			} while (len > 0 || len <= size);

			// 返回数据
			return baos.toByteArray();
		} catch (IOException e) {
			// 记录日志
			Logs.error(e);
			// 返回空字节数组
			return ArrayConstants.BYTES_EMPTY;
		}
	}

	/**
	 * 连接到服务器
	 */
	public void connect() {
		connect(SocketParams.getHost(name), SocketParams.getPort(name));
	}

	/**
	 * 发生数据
	 * @param obj 数据
	 */
	public void send(Object obj) {
		try {
			// 获得字节数组
			byte[] b = Bytes.toBytes(obj);
			// 声明UDP数据包
			DatagramPacket p = new DatagramPacket(b, b.length);
			// 发送数据
			socket.send(p);
		} catch (IOException e) {
			// 记录日志
			Logs.error(e);
		}
	}
}
