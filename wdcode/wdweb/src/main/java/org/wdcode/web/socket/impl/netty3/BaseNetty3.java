package org.wdcode.web.socket.impl.netty3;

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
}
