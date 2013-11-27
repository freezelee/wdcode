package org.wdcode.web.socket.impl;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;

import org.wdcode.core.log.Logs;
import org.wdcode.web.params.SocketParams;
import org.wdcode.web.socket.SocketServer;
import org.wdcode.web.socket.base.BaseSocketNio;

/**
 * Nio Socket服务器
 * @author WD
 * @since JDK7
 * @version 1.0 2011-10-19
 */
public final class SocketNioServerImpl extends BaseSocketNio implements SocketServer {
	// Socket服务器通道
	private ServerSocketChannel	server;

	/**
	 * 构造方法
	 * @param name 名称
	 */
	public SocketNioServerImpl(String name) {
		super(name);
		try {
			this.server = ServerSocketChannel.open();
		} catch (IOException e) {}
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
	 * 监听服务器 使用配置
	 */
	public void bind() {
		bind(SocketParams.getPort(name));
	}

	/**
	 * 关闭当前的Socket
	 */
	public void shutdown() {
		try {
			socket.close();
		} catch (IOException e) {} finally {
			socket = null;
		}
	}

	/**
	 * 关闭资源方法
	 */
	public void close() {
		try {
			shutdown();
			server.close();
		} catch (IOException e) {}
	}

	/**
	 * 获得Socket
	 */
	protected void acceptSocket() {
		if (socket == null || !socket.isConnected()) {
			try {
				socket = server.accept();
			} catch (IOException e) {}
		}
	}
}
