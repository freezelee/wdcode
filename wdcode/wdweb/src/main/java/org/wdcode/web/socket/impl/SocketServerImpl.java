package org.wdcode.web.socket.impl;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;

import org.wdcode.core.log.Logs;
import org.wdcode.common.util.EmptyUtil;
import org.wdcode.web.params.SocketParams;
import org.wdcode.web.socket.SocketServer;
import org.wdcode.web.socket.base.BaseSocket;

/**
 * Socket服务器实现
 * @author WD
 * @since JDK7
 * @version 1.0 2010-01-28
 */
public final class SocketServerImpl extends BaseSocket implements SocketServer {
	// JDK ServerSocket
	private ServerSocket	server;

	/**
	 * 构造参数
	 */
	public SocketServerImpl(String name) {
		super(name);
		try {
			server = new ServerSocket();
		} catch (IOException e) {
			Logs.error(e);
		}
	}

	/**
	 * 构造参数
	 * @param port 端口
	 */
	public SocketServerImpl(String name, int port) {
		super(name);
		try {
			server = new ServerSocket(port);
		} catch (IOException e) {
			Logs.error(e);
		}
	}

	/**
	 * 关闭当前的Socket
	 */
	public void shutdown() {
		if (!EmptyUtil.isEmpty(socket)) {
			try {
				socket.close();
			} catch (IOException e) {
				Logs.warn(e);
			} finally {
				socket = null;
			}
		}
	}

	/**
	 * 监听服务器
	 * @param port 服务器端口
	 */
	public void bind(int port) {
		try {
			server.bind(new InetSocketAddress(port));
		} catch (IOException e) {
			Logs.error(e);
		}
	}

	/**
	 * 监听服务器
	 */
	public void bind() {
		bind(SocketParams.getPort(name));
	}

	/**
	 * 关闭资源
	 */
	public void close() {
		try {
			shutdown();
			server.close();
		} catch (IOException e) {
			Logs.warn(e);
		}
	}

	/**
	 * 获得Socket连接
	 */
	protected void acceptSocket() {
		if (EmptyUtil.isEmpty(socket)) {
			try {
				socket = server.accept();
			} catch (IOException e) {
				Logs.error(e);
			}
		}
	}
}
