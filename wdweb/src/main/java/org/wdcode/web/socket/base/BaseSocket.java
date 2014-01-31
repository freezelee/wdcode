package org.wdcode.web.socket.base;

import org.wdcode.common.util.BeanUtil;
import org.wdcode.web.params.SocketParams;
import org.wdcode.web.socket.interfaces.Closed;
import org.wdcode.web.socket.interfaces.Handler;
import org.wdcode.web.socket.interfaces.Heart;
import org.wdcode.web.socket.interfaces.Manager;
import org.wdcode.web.socket.interfaces.Process;
import org.wdcode.web.socket.interfaces.Socket;
import org.wdcode.web.socket.simple.Processor;
import org.wdcode.web.socket.simple.SessionManager;

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
	// 注册Session
	protected Manager	manager;
	// 心跳处理
	protected Heart		heart;

	/**
	 * 构造
	 */
	public BaseSocket(String name) {
		this.name = name;
		manager = new SessionManager();
		process = new Processor(manager);
		// 获得关闭处理器
		Closed closed = (Closed) BeanUtil.newInstance(SocketParams.getClosed(name));
		if (closed != null) {
			process.setClosed(closed);
		}
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void addHandler(Handler<?>... h) {
		process.addHandler(h);
	}

	@Override
	public Manager getManager() {
		return manager;
	}

	@Override
	public void setHeart(Heart heart) {
		this.heart = heart;
		process.setHeart(heart);
	}
}