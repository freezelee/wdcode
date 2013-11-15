package org.wdcode.web.socket.impl;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;

import org.wdcode.web.params.SocketParams;
import org.wdcode.web.socket.SocketClient;
import org.wdcode.web.socket.base.BaseSocketNio;

/**
 * Nio Socket客户端
 * @author WD
 * @since JDK7
 * @version 1.0 2011-10-19
 */
public final class SocketNioClientImpl extends BaseSocketNio implements SocketClient {
	/**
	 * 构造方法
	 * @param name 名称
	 */
	public SocketNioClientImpl(String name) {
		super(name);
		try {
			socket = SocketChannel.open();
		} catch (IOException e) {}
	}

	/**
	 * 连接到服务器
	 */
	public void connect() {
		connect(SocketParams.getHost(name), SocketParams.getPort(name));
	}

	/**
	 * 连接到服务器
	 * @param host 服务器地址
	 * @param port 服务器端口
	 */
	public void connect(String host, int port) {
		try {
			socket.connect(new InetSocketAddress(host, port));
		} catch (IOException e) {}
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
		} catch (IOException e) {}
	}

	/**
	 * 返回连接服务器的地址
	 * @return 服务器地址
	 */
	public String getInetAddress() {
		return socket.socket().getInetAddress().toString();
	}

	/**
	 * 返回本机端口
	 * @return 本机端口
	 */
	public int getLocalPort() {
		return socket.socket().getLocalPort();
	}

	/**
	 * 返回本机地址
	 * @return 本机地址
	 */
	public String getLocalSocketAddress() {
		return socket.socket().getLocalSocketAddress().toString();
	}

	/**
	 * 返回连接服务器的端口
	 * @return 服务器端口
	 */
	public int getPort() {
		return socket.socket().getPort();
	}

	/**
	 * 关闭资源
	 */
	public void close() {
		try {
			socket.close();
		} catch (IOException e) {} finally {
			socket = null;
		}
	}

}
