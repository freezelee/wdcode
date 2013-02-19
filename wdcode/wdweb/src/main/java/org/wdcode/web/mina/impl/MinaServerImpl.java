package org.wdcode.web.mina.impl;

import java.io.IOException;
import java.net.InetSocketAddress;

import org.apache.mina.core.filterchain.IoFilter;
import org.apache.mina.core.service.IoHandler;
import org.apache.mina.core.service.IoServiceListener;
import org.apache.mina.transport.socket.SocketAcceptor;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;
import org.wdcode.common.log.Logs;
import org.wdcode.web.mina.MinaServer;
import org.wdcode.web.params.MinaParams;

/**
 * MinaService实现
 * @author WD
 * @since JDK7
 * @version 1.0 2011-07-06
 */
public final class MinaServerImpl implements MinaServer {
	// 名称
	private String			name;
	// SocketAcceptor
	private SocketAcceptor	acceptor;

	/**
	 * 构造方法
	 * @param name 名称
	 */
	public MinaServerImpl(String name) {
		this.name = name;
		this.acceptor = new NioSocketAcceptor();
	}

	/**
	 * 设置处理器
	 * @param handler 处理器
	 */
	public void setHandler(IoHandler handler) {
		acceptor.setHandler(handler);
	}

	/**
	 * 添加过滤器
	 * @param listener 过滤器
	 */
	public void addListener(IoServiceListener listener) {
		acceptor.addListener(listener);
	}

	/**
	 * 添加过滤器
	 * @param name 过滤属性名
	 * @param filter 过滤器
	 */
	public void addListener(String name, IoFilter filter) {
		acceptor.getFilterChain().addLast(name, filter);
	}

	/**
	 * 连接服务器 使用配置文件
	 */
	public void bind() {
		bind(MinaParams.getPort(name));
	}

	/**
	 * 连接服务器
	 * @param port 端口
	 */
	public void bind(int port) {
		try {
			acceptor.bind(new InetSocketAddress(port));
		} catch (IOException e) {
			Logs.error(e);
		}
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

	/**
	 * 关闭资源
	 */
	public void close() {
		acceptor.dispose();
	}
}
