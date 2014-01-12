package org.wdcode.web.socket.impl.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import org.wdcode.common.params.CommonParams;
import org.wdcode.web.params.SocketParams;
import org.wdcode.web.socket.base.BaseServer;

/**
 * netty实现
 * @author WD
 * @since JDK7
 * @version 1.0 2013-12-15
 */
public final class NettyServer extends BaseServer {
	// Netty ServerBootstrap
	private ServerBootstrap	bootstrap;
	// NettyHandler
	private NettyHandler	handler;

	/**
	 * 构造函数
	 * @param name 名称
	 */
	public NettyServer(String name) {
		// 名称
		this.name = name;
		// 实例化ServerBootstrap
		bootstrap = new ServerBootstrap();
		// NettyHandler
		handler = new NettyHandler(process);
		// 设置group
		bootstrap.group(new NioEventLoopGroup(CommonParams.THREAD_POOL));
		// 设置属性
		bootstrap.childOption(ChannelOption.TCP_NODELAY, true);
		bootstrap.childOption(ChannelOption.SO_KEEPALIVE, true);
		bootstrap.childOption(ChannelOption.SO_REUSEADDR, true);
		bootstrap.childOption(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000);
		bootstrap.childOption(ChannelOption.SO_TIMEOUT, 5000);
		bootstrap.childOption(ChannelOption.SO_SNDBUF, 1024 * 32);
		bootstrap.childOption(ChannelOption.SO_RCVBUF, 1024 * 8);
		// 设置channel
		bootstrap.channel(NioServerSocketChannel.class);
		// 设置初始化 handler
		bootstrap.childHandler(handler);
		// 设置监听端口
		bootstrap.localAddress(SocketParams.getPort(name));
	}

	@Override
	public void close() {
		bootstrap.group().shutdownGracefully();
		bootstrap.childGroup().shutdownGracefully();
	}

	@Override
	public void bind() {
		bootstrap.bind();
	}
}
