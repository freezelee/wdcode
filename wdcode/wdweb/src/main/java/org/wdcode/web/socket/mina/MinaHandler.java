package org.wdcode.web.socket.mina;

import java.util.List;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;
import org.wdcode.common.lang.Lists;
import org.wdcode.web.socket.Handler;

/**
 * mina实现
 * @author WD
 * @since JDK7
 * @version 1.0 2013-11-28
 */
public final class MinaHandler extends IoHandlerAdapter {
	// Handler列表
	private List<Handler>	handlers	= Lists.getList();

	/**
	 * 添加要处理的Handler
	 * @param handler
	 */
	public void addHandler(Handler handler) {
		handlers.add(handler);
	}

	@Override
	public void messageReceived(IoSession session, Object message) throws Exception {
		for (Handler h : handlers) {
			h.handler(null, ((IoBuffer) message).array());
		}
	}
}
