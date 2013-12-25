package org.wdcode.web.socket.impl.netty3;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;
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
	private ClientBootstrap	bootstrap;
	// 保存Netty服务器 ChannelFuture
	private ChannelFuture	future;
	// Session
	private Session			session;
	// 服务器
	private String			host;
	// 端口
	private int				port;

	/**
	 * 构造方法
	 * @param name
	 */
	public Netty3Client(String name) {
		// 名称
		this.name = name;
		// 实例化ServerBootstrap
		bootstrap = new ClientBootstrap(new NioClientSocketChannelFactory(Executors.newCachedThreadPool(), Executors.newCachedThreadPool()));
		// NettyHandler
		handler = new Netty3Handler();
		// 设置属性
		bootstrap.setOption("tcpNoDelay", true);
		bootstrap.setOption("keepAlive", true);
		// 设置handler
		bootstrap.setPipelineFactory(new ChannelPipelineFactory() {
			@Override
			public ChannelPipeline getPipeline() throws Exception {
				return Channels.pipeline(handler);
			}
		});
		// 设置服务器和端口
		host = SocketParams.getHost(name);
		port = SocketParams.getPort(name);
	}

	@Override
	public void connect() {
		future = bootstrap.connect(new InetSocketAddress(host, port)).awaitUninterruptibly();
		session = new Netty3Session(future.getChannel());
	}

	@Override
	public Session getSession() {
		return session;
	}

	@Override
	public void close() {
		session.close();
		bootstrap.releaseExternalResources();
		bootstrap.shutdown();
	}
}
