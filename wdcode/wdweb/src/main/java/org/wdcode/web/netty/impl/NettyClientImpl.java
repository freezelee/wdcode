package org.wdcode.web.netty.impl;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import org.wdcode.web.netty.NettyClient;
import org.wdcode.web.params.NettyParams;

/**
 * Netty客户端实现
 * @author WD
 * @since JDK7
 * @version 1.0 2011-07-07
 */
public final class NettyClientImpl implements NettyClient {
	// 名称
	private String		name;
	// Bootstrap
	private Bootstrap	bootstrap;
	// ChannelFuture
	ChannelFuture		future;

	/**
	 * 构造方法
	 * @param name 名称
	 */
	public NettyClientImpl(String name) {
		this.name = name;
		// // 实例化ClientBootstrap
		bootstrap = new Bootstrap();
		// 设置属性
		bootstrap.group(new NioEventLoopGroup()).channel(NioSocketChannel.class);
		bootstrap.option(ChannelOption.SO_KEEPALIVE, true);
		bootstrap.option(ChannelOption.TCP_NODELAY, true);
	}

	/**
	 * 连接服务器
	 * @param host 主机
	 * @param port 端口
	 */
	public void connect() {
		connect(NettyParams.getHost(name), NettyParams.getPort(name));
	}

	/**
	 * 连接服务器
	 * @param host 主机
	 * @param port 端口
	 */
	public void connect(String host, int port) {
		try {
			future = bootstrap.connect(host, port).sync();
		} catch (InterruptedException e) {}
	}

	/**
	 * 设置处理器
	 * @param handler 处理器
	 */
	public void setHandler(ChannelHandler handler) {
		bootstrap.handler(handler);
	}

	/**
	 * 发送信息
	 * @param mess 信息
	 */
	public void send(Object mess) {
		future.channel().write(mess);
	}

	/**
	 * 关闭资源
	 */
	public void close() {
		future.channel().close();
		future.cancel(true);
		future = null;
		bootstrap.group().shutdownGracefully();
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
