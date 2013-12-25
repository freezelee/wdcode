package org.wdcode.web.socket.impl.netty3;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.socket.nio.NioSocketChannel;

import org.wdcode.web.params.SocketParams;
import org.wdcode.web.socket.Client;
import org.wdcode.web.socket.Session;

/**
 * netty客户端
 * @author WD
 * @since JDK7
 * @version 1.0 2013-12-19
 */
public final class Netty3Client extends BaseNetty3 implements Client {
	// 保存Netty客户端 Bootstrap
	private Bootstrap		bootstrap;
	// 保存Netty服务器 ChannelFuture
	private ChannelFuture	future;
	// Session
	private Session			session;

	/**
	 * 构造方法
	 * @param name
	 */
	public Netty3Client(String name) {
		// 名称
		this.name = name;
		// 实例化ClientBootstrap
		bootstrap = new Bootstrap();
		// 添加配置
		config(bootstrap);
		// 设置channel
		bootstrap.channel(NioSocketChannel.class);
		// 设置初始化 handler
		bootstrap.handler(handler);
		// 设置监听端口
		bootstrap.remoteAddress(SocketParams.getHost(name), SocketParams.getPort(name));
	}

	@Override
	public void connect() {
		future = bootstrap.connect().awaitUninterruptibly();
		session = new Netty3Session(0, future.channel());
	}

	@Override
	public Session getSession() {
		return session;
	}

	@Override
	public void close() {
		session.close();
		bootstrap.group().shutdownGracefully();
	}
}
