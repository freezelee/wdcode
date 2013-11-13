package org.wdcode.web.socket;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.MessageToByteEncoder;

import java.util.Map;

import org.wdcode.common.lang.Bytes;
import org.wdcode.common.lang.Maps;
import org.wdcode.common.params.CommonParams;
import org.wdcode.common.util.ClassUtil;
import org.wdcode.common.util.EmptyUtil;
import org.wdcode.web.params.NettyParams;

/**
 * Netty处理引擎
 * @author WD
 * @since JDK6
 * @version 1.0 2013-09-26
 */
public final class NettyEngine {
	// 保存Netty服务器 ServerBootstrap
	private final static Map<String, ServerBootstrap>	SERVERS;
	// 保存Netty客户端 Bootstrap
	private final static Map<String, Bootstrap>			CLIENT;
	// 保存Netty服务器 ChannelFuture
	private final static Map<String, ChannelFuture>		FUTURE;

	static {
		SERVERS = Maps.getConcurrentMap();
		CLIENT = Maps.getConcurrentMap();
		FUTURE = Maps.getConcurrentMap();
	}

	/**
	 * 初始化Mina
	 */
	public static void init() {
		// 判断任务不为空
		if (NettyParams.POWER) {
			// 循环数组
			for (String name : NettyParams.NAMES) {
				// 获得类型
				String type = NettyParams.getType(name);
				// 获得host
				String host = NettyParams.getHost(name);
				// 判断是否客户端
				if (!EmptyUtil.isEmpty(host) && "client".equals(type)) {
					addClient(name, host, NettyParams.getPort(name), (ChannelHandler) ClassUtil.newInstance(NettyParams.getHandler(name)), (MessageToByteEncoder<?>) ClassUtil.newInstance(NettyParams.getEncoder(name)), (ByteToMessageDecoder) ClassUtil.newInstance(NettyParams.getDecoder(name)));
				} else {
					addServer(name, NettyParams.getPort(name), (ChannelHandler) ClassUtil.newInstance(NettyParams.getHandler(name)), (MessageToByteEncoder<?>) ClassUtil.newInstance(NettyParams.getEncoder(name)), (ByteToMessageDecoder) ClassUtil.newInstance(NettyParams.getDecoder(name)));
				}
			}
			start();
		}
	}

	/**
	 * 启动netty
	 */
	public static void start() {
		// 服务器启动
		for (ServerBootstrap bootstrap : SERVERS.values()) {
			bootstrap.bind();
		}
		// 客户端启动
		for (Map.Entry<String, Bootstrap> e : CLIENT.entrySet()) {
			FUTURE.put(e.getKey(), e.getValue().connect().awaitUninterruptibly());
		}
	}

	/**
	 * 添加服务器
	 * @param name 服务器名称
	 * @param port 服务器监听端口
	 * @param handler 处理handler
	 * @param encoder 编码器
	 * @param decoder 解码器
	 */
	public static void addServer(String name, int port, final ChannelHandler handler, final MessageToByteEncoder<?> encoder, final ByteToMessageDecoder decoder) {
		// Netty ServerBootstrap
		ServerBootstrap bootstrap = new ServerBootstrap();
		// 设置group
		bootstrap.group(new NioEventLoopGroup(CommonParams.THREAD_POOL));
		// 设置channel
		bootstrap.channel(NioServerSocketChannel.class);
		// 设置属性
		bootstrap.option(ChannelOption.TCP_NODELAY, true);
		bootstrap.option(ChannelOption.SO_KEEPALIVE, true);
		// bootstrap.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000);
		// bootstrap.option(ChannelOption.SO_TIMEOUT, 5000);
		bootstrap.option(ChannelOption.SO_SNDBUF, 1024 * 32);
		bootstrap.option(ChannelOption.SO_RCVBUF, 1024 * 8);
		// 设置初始化 handler
		bootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
			@Override
			protected void initChannel(SocketChannel ch) throws Exception {
				// 获得ChannelPipeline
				ChannelPipeline pipeline = ch.pipeline();
				// 编码器不为空
				if (encoder != null) {
					pipeline.addLast("encoder", encoder);
				}
				// 解码器不为空
				if (decoder != null) {
					pipeline.addLast("decoder", decoder);
				}
				// 添加handler
				pipeline.addLast("handler", handler == null ? new ChannelInboundHandlerAdapter() : handler);
			}
		});
		// 设置监听端口
		bootstrap.localAddress(port);
		// 添加到服务器列表中
		SERVERS.put(name, bootstrap);
	}

	/**
	 * 添加客户端
	 * @param name 客户端名称
	 * @param host 要连接的服务器地址
	 * @param port 要连接的端口
	 * @param handler 处理handler
	 * @param encoder 编码器
	 * @param decoder 解码器
	 */
	public static void addClient(String name, String host, int port, final ChannelHandler handler, final MessageToByteEncoder<?> encoder, final ByteToMessageDecoder decoder) {
		// // 实例化ClientBootstrap
		Bootstrap bootstrap = new Bootstrap();
		// 设置group
		bootstrap.group(new NioEventLoopGroup(CommonParams.THREAD_POOL));
		// 设置channel
		bootstrap.channel(NioSocketChannel.class);
		// 设置属性
		bootstrap.option(ChannelOption.TCP_NODELAY, true);
		bootstrap.option(ChannelOption.SO_KEEPALIVE, true);
		// bootstrap.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000);
		// bootstrap.option(ChannelOption.SO_TIMEOUT, 5000);
		bootstrap.option(ChannelOption.SO_SNDBUF, 1024 * 32);
		bootstrap.option(ChannelOption.SO_RCVBUF, 1024 * 8);
		// 设置初始化 handler
		bootstrap.handler(new ChannelInitializer<SocketChannel>() {
			@Override
			protected void initChannel(SocketChannel ch) throws Exception {
				// 获得ChannelPipeline
				ChannelPipeline pipeline = ch.pipeline();
				// 编码器不为空
				if (encoder != null) {
					pipeline.addLast("encoder", encoder);
				}
				// 解码器不为空
				if (decoder != null) {
					pipeline.addLast("decoder", decoder);
				}
				// 添加handler
				pipeline.addLast("handler", handler == null ? new ChannelInboundHandlerAdapter() : handler);
			}
		});
		// 设置监听端口
		bootstrap.remoteAddress(host, port);
		// 添加到服务器列表中
		CLIENT.put(name, bootstrap);
	}

	/**
	 * 根据名称获得客户端
	 * @param name 客户端名称
	 * @return 客户端Channel
	 */
	public static Channel client(String name) {
		return FUTURE.get(name).channel();
	}

	/**
	 * 发送数据
	 * @param name 客户端名称
	 * @param mess 数据消息
	 */
	public static void send(String name, Object mess) {
		client(name).writeAndFlush(PooledByteBufAllocator.DEFAULT.heapBuffer().writeBytes(Bytes.toBytes(mess)));
	}

	/**
	 * 关闭资源
	 */
	public static void close() {
		// 关闭服务器
		for (String name : SERVERS.keySet()) {
			closeServer(name);
		}
		// 关闭客户端
		for (String name : CLIENT.keySet()) {
			closeClient(name);
		}
	}

	/**
	 * 关闭客户端
	 * @param name 客户端名称
	 */
	public static void closeClient(String name) {
		// 获得客户端ChannelFuture
		ChannelFuture future = FUTURE.get(name);
		// ChannelFuture不为空
		if (!EmptyUtil.isEmpty(future)) {
			future.channel().close();
			future.cancel(true);
			FUTURE.remove(name);
		}
		// 获得客户端Bootstrap
		Bootstrap bootstrap = CLIENT.get(name);
		// 判断Bootstrap不为空
		if (!EmptyUtil.isEmpty(bootstrap)) {
			bootstrap.group().shutdownGracefully();
			CLIENT.remove(name);
		}
	}

	/**
	 * 关闭服务器
	 * @param name 服务器名称
	 */
	public static void closeServer(String name) {
		// 获得客户端ServerBootstrap
		ServerBootstrap bootstrap = SERVERS.get(name);
		// 判断ServerBootstrap不为空
		if (!EmptyUtil.isEmpty(bootstrap)) {
			bootstrap.group().shutdownGracefully();
			bootstrap.childGroup().shutdownGracefully();
			SERVERS.remove(name);
		}
	}

	private NettyEngine() {}
}
