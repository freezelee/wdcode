package org.wdcode.web.socket.mina;

import org.wdcode.web.socket.Client;
import org.wdcode.web.socket.Session;

/**
 * mina客户端
 * @author WD
 * @since JDK7
 * @version 1.0 2013-12-19
 */
public final class MinaClient implements Client {
	// 名称
	private String	name;

	/**
	 * 构造方法
	 * @param name
	 */
	public MinaClient(String name) {
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
