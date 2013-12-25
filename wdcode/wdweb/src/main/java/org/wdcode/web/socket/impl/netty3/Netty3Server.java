package org.wdcode.web.socket.impl.netty3;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.wdcode.web.params.SocketParams;
import org.wdcode.web.socket.Server;

/**
 * netty实现
 * @author WD
 * @since JDK7
 * @version 1.0 2013-12-15
 */
public final class Netty3Server extends BaseNetty3 implements Server {
	// Netty ServerBootstrap
	private ServerBootstrap	bootstrap;
	// 端口
	private int				port;

	/**
	 * 构造函数
	 * @param name 名称
	 */
	public Netty3Server(String name) {
		// 名称
		this.name = name;
		// 实例化ServerBootstrap
		bootstrap = new ServerBootstrap(new NioServerSocketChannelFactory(Executors.newCachedThreadPool(), Executors.newCachedThreadPool()));
		// NettyHandler
		handler = new Netty3Handler();
		// 设置属性
		bootstrap.setOption("child.tcpNoDelay", true);
		bootstrap.setOption("child.keepAlive", true);
		bootstrap.setOption("child.reuseAddress", true);
		bootstrap.setOption("reuseAddress", true);
		// 设置handler
		bootstrap.setPipelineFactory(new ChannelPipelineFactory() {
			@Override
			public ChannelPipeline getPipeline() throws Exception {
				return Channels.pipeline(handler);
			}
		});
		// 设置监听端口
		port = SocketParams.getPort(name);
	}

	@Override
	public void close() {
		bootstrap.releaseExternalResources();
		bootstrap.shutdown();
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void bind() {
		bootstrap.bind(new InetSocketAddress(port));
	}
}
