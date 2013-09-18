package org.wdcode.web.netty.impl;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

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
		bootstrap = new ServerBootstrap();
		// 设置group
		bootstrap.group(new NioEventLoopGroup(), new NioEventLoopGroup());
		// 设置channel
		bootstrap.channel(NioServerSocketChannel.class);
		// 设置属性
		bootstrap.option(ChannelOption.TCP_NODELAY, true);
		bootstrap.option(ChannelOption.SO_KEEPALIVE, true);
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
		try {
			bootstrap.bind(new InetSocketAddress(port)).sync();
		} catch (InterruptedException e) {}
	}

	/**
	 * 设置处理器
	 * @param handler 处理器
	 */
	public void setHandler(ChannelHandler handler) {
		bootstrap.childHandler(handler);
		bootstrap.handler(handler);
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
