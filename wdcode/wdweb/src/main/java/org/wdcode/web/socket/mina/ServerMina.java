package org.wdcode.web.socket.mina;

import java.net.InetSocketAddress;

import org.apache.mina.transport.socket.SocketAcceptor;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;
import org.wdcode.web.socket.Server;

/**
 * mina实现
 * @author WD
 * @since JDK7
 * @version 1.0 2013-11-28
 */
public final class ServerMina implements Server {
	// SocketAcceptor
	private SocketAcceptor	acceptor;

	public ServerMina(int port, int pool) {
		// 注册Mina Nio端口接收
		acceptor = new NioSocketAcceptor(pool);
		// 绑定服务器数据监听端口，启动服务器
		acceptor.setDefaultLocalAddress(new InetSocketAddress(port));
	}

	@Override
	public void close() {
		acceptor.dispose();
	}
}
