package org.wdcode.web.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.Map;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.service.IoHandler;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.service.IoService;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.transport.socket.SocketAcceptor;
import org.apache.mina.transport.socket.SocketConnector;
import org.apache.mina.transport.socket.SocketSessionConfig;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;
import org.apache.mina.transport.socket.nio.NioSocketConnector;
import org.wdcode.common.lang.Bytes;
import org.wdcode.common.lang.Maps;
import org.wdcode.common.log.Logs;
import org.wdcode.common.params.CommonParams;
import org.wdcode.common.util.ClassUtil;
import org.wdcode.common.util.EmptyUtil;
import org.wdcode.web.params.MinaParams;

/**
 * mina处理引擎
 * @author WD
 * @since JDK6
 * @version 1.0 2013-9-25
 */
public final class MinaEngine {
	// 保存Mina服务器SocketAcceptor
	private final static Map<String, SocketAcceptor>	SERVERS;
	// 保存Mina客户端SocketConnector
	private final static Map<String, SocketConnector>	CLIENT;
	// 保存Mina客户端ConnectFuture
	private final static Map<String, ConnectFuture>		FUTURE;

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
		if (MinaParams.POWER) {
			// 循环数组
			for (String name : MinaParams.NAMES) {
				// 获得类型
				String type = MinaParams.getType(name);
				// 获得host
				String host = MinaParams.getHost(name);
				// 判断是否客户端
				if (!EmptyUtil.isEmpty(host) && "client".equals(type)) {
					addClient(name, host, MinaParams.getPort(name), (IoHandler) ClassUtil.newInstance(MinaParams.getHandler(name)), (ProtocolCodecFactory) ClassUtil.newInstance(MinaParams.getCodec(name)));
				} else {
					addServer(name, MinaParams.getPort(name), (IoHandler) ClassUtil.newInstance(MinaParams.getHandler(name)), (ProtocolCodecFactory) ClassUtil.newInstance(MinaParams.getCodec(name)));
				}
			}
			start();
		}
	}

	/**
	 * 添加配置
	 * @param service 服务
	 * @param handler 处理器
	 * @param codec 编码器
	 */
	private static void config(IoService service, IoHandler handler, ProtocolCodecFactory codec) {
		// 注册数据编解码器
		if (codec != null) {
			service.getFilterChain().addLast("codec", new ProtocolCodecFilter(codec));
		}
		// 获得Session配置
		SocketSessionConfig sc = (SocketSessionConfig) service.getSessionConfig();
		// 设置每一个非主监听连接的端口可以重用
		sc.setReuseAddress(true);
		// 设置最小读取缓存
		sc.setMinReadBufferSize(64);
		// 设置输入缓冲区的大小
		sc.setReceiveBufferSize(1024 * 8);
		// 设置输出缓冲区的大小
		sc.setSendBufferSize(1024 * 32);
		// flush函数的调用 设置为非延迟发送，为true则不组装成大包发送，收到东西马上发出
		sc.setTcpNoDelay(true);
		sc.setSoLinger(0);
		// 设置超时时间
		sc.setWriteTimeout(10000);
		sc.setWriterIdleTime(60);
		sc.setReaderIdleTime(30);
		sc.setBothIdleTime(180);
		// 绑定Mina服务器管理模块
		service.setHandler(handler == null ? new IoHandlerAdapter() : handler);
	}

	/**
	 * 添加Mina服务器
	 * @param name 服务器名
	 * @param port 监听端口
	 * @param handler 处理Handler
	 * @param codec 编码工厂
	 */
	public static void addServer(String name, int port, IoHandler handler, ProtocolCodecFactory codec) {
		// 注册Mina Nio端口接收
		SocketAcceptor acceptor = new NioSocketAcceptor(CommonParams.THREAD_POOL);
		// 添加配置
		config(acceptor, handler, codec);
		// 绑定服务器数据监听端口，启动服务器
		acceptor.setDefaultLocalAddress(new InetSocketAddress(port));
		// 添加到服务器池中
		SERVERS.put(name, acceptor);
	}

	/**
	 * 添加Mina客户端
	 * @param name 服务器名
	 * @param host 连接服务器
	 * @param port 连接端口
	 * @param handler 处理Handler
	 * @param codec 编码工厂
	 */
	public static void addClient(String name, String host, int port, IoHandler handler, ProtocolCodecFactory codec) {
		// Socket连接器
		SocketConnector connector = new NioSocketConnector(CommonParams.THREAD_POOL);
		// 添加配置
		config(connector, handler, codec);
		// 绑定服务器数据监听端口，启动服务器
		connector.setDefaultRemoteAddress(new InetSocketAddress(host, port));
		// 添加到客户端池中
		CLIENT.put(name, connector);
	}

	/**
	 * 启动mina
	 */
	public static void start() {
		// 服务器启动
		for (SocketAcceptor acceptor : SERVERS.values()) {
			try {
				acceptor.bind();
			} catch (IOException e) {
				Logs.error(e);
			}
		}
		// 客户端启动
		for (Map.Entry<String, SocketConnector> e : CLIENT.entrySet()) {
			FUTURE.put(e.getKey(), e.getValue().connect().awaitUninterruptibly());
		}
	}

	/**
	 * 根据名称获得客户端
	 * @param name 客户端名称
	 * @return 客户端IoSession
	 */
	public static IoSession client(String name) {
		return FUTURE.get(name).getSession();
	}

	/**
	 * 根据客户端名称发送信息
	 * @param name 客户端名称
	 * @param mess 要发送的信息
	 * @return 客户端IoSession
	 */
	public static void send(String name, Object mess) {
		client(name).write(IoBuffer.wrap(Bytes.toBytes(mess)));
	}

	/**
	 * 关闭所有连接
	 */
	public static void close() {
		for (String name : SERVERS.keySet()) {
			closeServer(name);
		}
		for (String name : CLIENT.keySet()) {
			closeClient(name);
		}
	}

	/**
	 * 关闭客户端
	 * @param name 要关闭的SocketAcceptor 名 关闭所有连接
	 */
	public static void closeClient(String name) {
		// 获得ConnectFuture
		ConnectFuture future = FUTURE.get(name);
		// 判断acceptor不为空
		if (!EmptyUtil.isEmpty(future)) {
			// 关闭future
			future.getSession().close(true);
			future.cancel();
			// 删除Map中的引用
			FUTURE.remove(name);
		}
		// 获得SocketConnector
		SocketConnector connector = CLIENT.get(name);
		// 判断acceptor不为空
		if (!EmptyUtil.isEmpty(connector)) {
			// 关闭acceptor
			connector.dispose(true);
			// 删除Map中的引用
			CLIENT.remove(name);
		}
	}

	/**
	 * 关闭服务器
	 * @param name 要关闭的SocketAcceptor 名 关闭所有连接
	 */
	public static void closeServer(String name) {
		// 获得SocketAcceptor
		SocketAcceptor acceptor = SERVERS.get(name);
		// 判断acceptor不为空
		if (!EmptyUtil.isEmpty(acceptor)) {
			// 关闭acceptor
			acceptor.dispose();
			// 删除Map中的引用
			SERVERS.remove(name);
		}
	}

	private MinaEngine() {}
}
