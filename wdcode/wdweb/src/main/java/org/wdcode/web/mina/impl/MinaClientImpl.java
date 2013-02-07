package org.wdcode.web.mina.impl;

import java.net.InetSocketAddress;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.service.IoHandler;
import org.apache.mina.core.service.IoServiceListener;
import org.apache.mina.transport.socket.SocketConnector;
import org.apache.mina.transport.socket.nio.NioSocketConnector;
import org.wdcode.common.lang.Bytes;
import org.wdcode.web.mina.MinaClient;
import org.wdcode.web.params.MinaParams;

/**
 * MinaClient实现
 * @author WD
 * @since JDK7
 * @version 1.0 2011-07-06
 */
public final class MinaClientImpl implements MinaClient {
	// 名称
	private String			name;
	// Socket连接器
	private SocketConnector	connector;
	// ConnectFuture
	private ConnectFuture	future;

	/**
	 * 构造方法
	 * @param name 名称
	 */
	public MinaClientImpl(String name) {
		this.name = name;
		this.connector = new NioSocketConnector();
	}

	/**
	 * 设置处理器
	 * @param handler 处理器
	 */
	public void setHandler(IoHandler handler) {
		connector.setHandler(handler);
	}

	/**
	 * 添加过滤器
	 * @param listener 过滤器
	 */
	public void addListener(IoServiceListener listener) {
		connector.addListener(listener);
	}

	/**
	 * 连接服务器 使用配置文件
	 */
	public void connect() {
		connect(MinaParams.getHost(name), MinaParams.getPort(name));
	}

	/**
	 * 连接服务器
	 * @param host 主机
	 * @param port 端口
	 */
	public void connect(String host, int port) {
		future = connector.connect(new InetSocketAddress(host, port)).awaitUninterruptibly();
	}

	/**
	 * 发送信息
	 * @param mess 信息
	 */
	public void send(Object mess) {
		future.getSession().write(IoBuffer.wrap(Bytes.toBytes(mess)));
	}

	/**
	 * 关闭资源
	 */
	public void close() {
		connector.dispose();
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
