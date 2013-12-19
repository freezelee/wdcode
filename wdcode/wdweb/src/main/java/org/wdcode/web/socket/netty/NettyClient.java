package org.wdcode.web.socket.netty;

import org.wdcode.web.socket.Client;
import org.wdcode.web.socket.Session;

/**
 * netty客户端
 * @author WD
 * @since JDK7
 * @version 1.0 2013-12-19
 */
public class NettyClient implements Client {
	// 名称
	private String	name;

	/**
	 * 构造方法
	 * @param name
	 */
	public NettyClient(String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void connect() {}

	@Override
	public Session getSession() {
		return null;
	}
}
