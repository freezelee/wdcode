package org.wdcode.web.socket.impl.mina;

import java.net.InetSocketAddress;

import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.transport.socket.SocketConnector;
import org.apache.mina.transport.socket.nio.NioSocketConnector;
import org.wdcode.common.lang.Conversion;
import org.wdcode.common.params.CommonParams;
import org.wdcode.web.params.SocketParams;
import org.wdcode.web.socket.Client;
import org.wdcode.web.socket.Session;

/**
 * mina客户端
 * @author WD
 * @since JDK7
 * @version 1.0 2013-12-19
 */
public final class MinaClient extends BaseMina implements Client {
	// 名称
	private String			name;
	// 客户端连接
	private SocketConnector	connector;
	// 客户端ConnectFuture
	private ConnectFuture	future;
	// Session
	private Session			session;

	/**
	 * 构造方法
	 * @param name
	 */
	public MinaClient(String name) {
		// 名称
		this.name = name;
		// 客户端
		this.connector = new NioSocketConnector(CommonParams.THREAD_POOL);
		// 添加配置
		config(connector);
		// 绑定服务器数据监听端口，启动服务器
		connector.setDefaultRemoteAddress(new InetSocketAddress(SocketParams.getHost(name), SocketParams.getPort(name)));
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void connect() {
		future = connector.connect().awaitUninterruptibly();
		IoSession io = future.getSession();
		session = new MinaSession(Conversion.toInt(io.getId()), io);
	}

	@Override
	public Session getSession() {
		return session;
	}

	@Override
	public void close() {
		// 关闭Session
		future.getSession().close(false).awaitUninterruptibly();
		// 关闭acceptor
		connector.dispose(false);

	}
}
