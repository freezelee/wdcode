package org.wdcode.web.netty.impl;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.ChannelFactory;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelHandler;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;
import org.wdcode.common.lang.Bytes;
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
	private String			name;
	// ClientBootstrap
	private ClientBootstrap	bootstrap;
	// ChannelFuture
	private ChannelFuture	future;

	/**
	 * 构造方法
	 * @param name 名称
	 */
	public NettyClientImpl(String name) {
		this.name = name;
		// 实例化公测
		ChannelFactory factory = new NioClientSocketChannelFactory(Executors.newCachedThreadPool(), Executors.newCachedThreadPool());
		// 实例化ClientBootstrap
		bootstrap = new ClientBootstrap(factory);
		// 设置属性
		bootstrap.setOption("tcpNoDelay", true);
		bootstrap.setOption("keepAlive", true);
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
		future = bootstrap.connect(new InetSocketAddress(host, port)).awaitUninterruptibly();
	}

	/**
	 * 设置处理器
	 * @param handler 处理器
	 */
	public void setHandler(ChannelHandler handler) {
		bootstrap.getPipeline().addLast("handler", handler);
	}

	/**
	 * 发送信息
	 * @param mess 信息
	 */
	public void send(Object mess) {
		future.getChannel().write(ChannelBuffers.wrappedBuffer(Bytes.toBytes(mess)));
	}

	/**
	 * 关闭资源
	 */
	public void close() {
		future.cancel();
		future = null;
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
