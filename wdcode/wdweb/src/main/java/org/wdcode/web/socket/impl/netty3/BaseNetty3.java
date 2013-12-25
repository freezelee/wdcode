package org.wdcode.web.socket.impl.netty3;

import io.netty.bootstrap.AbstractBootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;

import org.wdcode.common.params.CommonParams;
import org.wdcode.web.socket.Handler;
import org.wdcode.web.socket.Socket;

/**
 * 基础netty设置
 * @author WD
 * @since JDK7
 * @version 1.0 2013-12-20
 */
public abstract class BaseNetty3 implements Socket {
	// 名称
	protected String		name;
	// NettyHandler
	protected Netty3Handler	handler;

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void addHandler(Handler<?>... h) {
		handler.addHandler(h);
	}

	protected void config(AbstractBootstrap<?, ?> bootstrap) {
		// NettyHandler
		handler = new Netty3Handler();
		// 设置group
		bootstrap.group(new NioEventLoopGroup(CommonParams.THREAD_POOL));
		// 设置属性
		bootstrap.option(ChannelOption.TCP_NODELAY, true);
		bootstrap.option(ChannelOption.SO_KEEPALIVE, true);
		bootstrap.option(ChannelOption.SO_REUSEADDR, true);
		bootstrap.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000);
		bootstrap.option(ChannelOption.SO_TIMEOUT, 5000);
		bootstrap.option(ChannelOption.SO_SNDBUF, 1024 * 32);
		bootstrap.option(ChannelOption.SO_RCVBUF, 1024 * 8);
	}
}
