package org.wdcode.web.netty.impl;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.ChannelFactory;
import org.jboss.netty.channel.ChannelHandler;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.wdcode.web.netty.NettyServer;
import org.wdcode.web.params.NettyParams;

/**
 * Netty服务器实现
 * @author WD
 * @since JDK7
 * @version 1.0 2011-07-07
 */
public final class NettyServerImpl implements NettyServer {
	// 名称
	private String			name;
	// ServerBootstrap
	private ServerBootstrap	bootstrap;

	/**
	 * 构造方法
	 * @param name 名称
	 */
	public NettyServerImpl(String name) {
		this.name = name;
		// 实例化工厂
		ChannelFactory factory = new NioServerSocketChannelFactory(Executors.newCachedThreadPool(), Executors.newCachedThreadPool());
		bootstrap = new ServerBootstrap(factory);
		// 设置属性
		bootstrap.setOption("child.tcpNoDelay", true);
		bootstrap.setOption("child.keepAlive", true);
	}

	/**
	 * 连接服务器 使用配置文件
	 */
	public void bind() {
		bind(NettyParams.getPort(name));
	}

	/**
	 * 连接服务器
	 * @param port 端口
	 */
	public void bind(int port) {
		bootstrap.bind(new InetSocketAddress(port));
	}

	/**
	 * 设置处理器
	 * @param handler 处理器
	 */
	public void setHandler(ChannelHandler handler) {
		bootstrap.getPipeline().addLast("handler", handler);
	}

	/**
	 * 关闭资源
	 */
	public void close() {
		bootstrap = null;
	}

	/**
	 * 获得名称
	 * @return 名称
	 */
	public String getName() {
		return name;
	}

	/**
	 * 设置名称
	 * @param name 名称
	 */
	public void setName(String name) {
		this.name = name;
	}
}
