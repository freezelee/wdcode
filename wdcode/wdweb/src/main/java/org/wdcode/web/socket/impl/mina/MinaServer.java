package org.wdcode.web.socket.impl.mina;

import java.io.IOException;
import java.net.InetSocketAddress;

import org.apache.mina.transport.socket.SocketAcceptor;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;
import org.wdcode.common.params.CommonParams;
import org.wdcode.core.log.Logs;
import org.wdcode.web.params.SocketParams;
import org.wdcode.web.socket.Server;

/**
 * mina实现
 * @author WD
 * @since JDK7
 * @version 1.0 2013-11-28
 */
public final class MinaServer extends BaseMina implements Server {
	// SocketAcceptor
	private SocketAcceptor	acceptor;

	/**
	 * 构造方法
	 * @param name 名称
	 */
	public MinaServer(String name) {
		// 名称
		this.name = name;
		// 服务器
		this.acceptor = new NioSocketAcceptor(CommonParams.THREAD_POOL);
		// 添加配置
		config(acceptor);
		// 绑定服务器数据监听端口，启动服务器
		acceptor.setDefaultLocalAddress(new InetSocketAddress(SocketParams.getPort(name)));

	}

	/**
	 * 启动服务器监听
	 */
	public void bind() {
		// 绑定端口并启动
		try {
			acceptor.bind();
		} catch (IOException e) {
			Logs.warn(e);
		}
	}

	@Override
	public void close() {
		acceptor.dispose();
	}
}
