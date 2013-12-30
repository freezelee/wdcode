package org.wdcode.web.socket.base;

import org.wdcode.web.socket.Handler;
import org.wdcode.web.socket.Process;
import org.wdcode.web.socket.Socket;
import org.wdcode.web.socket.simple.Processor;

/**
 * 基础Socket
 * @author WD
 * @since JDK7
 * @version 1.0 2013-12-30
 */
public abstract class BaseSocket implements Socket {
	// 名称
	protected String	name;
	// 消息处理器
	protected Process	process;

	/**
	 * 构造
	 */
	public BaseSocket() {
		process = new Processor();
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void addHandler(Handler<?>... h) {
		process.addHandler(h);
	}
}