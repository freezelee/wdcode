package org.wdcode.base.starter;

import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.wdcode.base.context.Context;
import org.wdcode.web.params.SocketParams;
import org.wdcode.web.socket.Sockets;
import org.wdcode.web.socket.interfaces.Handler;
import org.wdcode.web.socket.interfaces.Socket;

/**
 * Socket 相关类
 * @author WD
 * @since JDK7
 * @version 1.0 2013-12-7
 */
@Component
public final class SocketStarter {
	// Context
	@Resource
	private Context	context;

	/**
	 * 初始化Mina
	 */
	@SuppressWarnings("rawtypes")
	@PostConstruct
	public void init() {
		// 获得全部Handler
		Collection<Handler> handlers = context.getBeans(Handler.class).values();
		// 判断任务不为空
		if (SocketParams.SPRING) {
			// 循环数组
			for (String name : SocketParams.NAMES) {
				init(name, handlers);
			}
			Sockets.start();
		}
	}

	/**
	 * 根据名称设置
	 * @param name 名
	 */
	@SuppressWarnings("rawtypes")
	private void init(String name, Collection<Handler> handlers) {
		// Socket
		Socket socket = Sockets.init(name);
		// 设置Handler
		for (Handler<?> handler : handlers) {
			socket.addHandler(handler);
		}
	}

	private SocketStarter() {}
}