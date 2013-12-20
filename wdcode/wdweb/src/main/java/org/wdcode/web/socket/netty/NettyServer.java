package org.wdcode.web.socket.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import org.wdcode.common.params.CommonParams;
import org.wdcode.web.params.SocketParams;
import org.wdcode.web.socket.Handler;
import org.wdcode.web.socket.Server;
import org.wdcode.web.socket.message.Message;

/**
 * netty实现
 * @author WD
 * @since JDK7
 * @version 1.0 2013-12-15
 */
public class NettyServer implements Server {
	// 名称
	private String			name;
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
		// NettyHandler
		handler = new NettyHandler();
		// 实例化ServerBootstrap
		bootstrap = new ServerBootstrap();
		// 设置group
		bootstrap.group(new NioEventLoopGroup(CommonParams.THREAD_POOL));
		// 设置channel
		bootstrap.channel(NioServerSocketChannel.class);
		// 设置属性
		bootstrap.option(ChannelOption.TCP_NODELAY, true);
		bootstrap.option(ChannelOption.SO_KEEPALIVE, true);
		bootstrap.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000);
		bootstrap.option(ChannelOption.SO_TIMEOUT, 5000);
		bootstrap.option(ChannelOption.SO_SNDBUF, 1024 * 32);
		bootstrap.option(ChannelOption.SO_RCVBUF, 1024 * 8);
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
	public String getName() {
		return name;
	}

	@Override
	public void addHandler(Handler<? extends Message> h) {}

	@Override
	public void bind() {
		bootstrap.bind();
	}
}
