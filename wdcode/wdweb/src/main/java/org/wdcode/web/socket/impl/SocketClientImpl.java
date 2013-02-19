package org.wdcode.web.socket.impl;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

import org.wdcode.common.log.Logs;
import org.wdcode.web.params.SocketParams;
import org.wdcode.web.socket.SocketClient;
import org.wdcode.web.socket.base.BaseSocket;

/**
 * TCP Socket客户端实现
 * @author WD
 * @since JDK7
 * @version 1.0 2010-01-28
 */
public final class SocketClientImpl extends BaseSocket implements SocketClient {
	/**
	 * 构造方法
	 */
	public SocketClientImpl(String name) {
		super(name);
		this.socket = new Socket();
	}

	/**
	 * 构造方法
	 * @param host 主机
	 * @param port 端口
	 */
	public SocketClientImpl(String name, String host, int port) {
		super(name);
		try {
			this.socket = new Socket(host, port);
		} catch (Exception e) {
			// 记录日志
			Logs.error(e);
		}
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
		} catch (IOException e) {
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
			socket.connect(new InetSocketAddress(host, port), timeout);
		} catch (IOException e) {
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
		try {
			socket.close();
		} catch (IOException e) {
			// 记录日志
			Logs.error(e);
		}
	}
}
