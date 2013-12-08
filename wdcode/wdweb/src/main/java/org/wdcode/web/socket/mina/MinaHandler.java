package org.wdcode.web.socket.mina;

import java.util.List;
import java.util.Map;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;
import org.wdcode.common.lang.Lists;
import org.wdcode.common.lang.Maps;
import org.wdcode.web.socket.Handler;
import org.wdcode.web.socket.Session;

/**
 * mina实现
 * @author WD
 * @since JDK7
 * @version 1.0 2013-11-28
 */
public final class MinaHandler extends IoHandlerAdapter {
	// Handler列表
	private List<Handler>		handlers	= Lists.getList();
	// 保存Session
	private Map<Long, Session>	sessions	= Maps.getConcurrentMap();

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
			h.handler(getSesson(session), ((IoBuffer) message).array());
		}
	}

	/**
	 * 获得包装Session
	 * @param session Mina session
	 * @return
	 */
	private Session getSesson(IoSession session) {
		// 获得包装Session
		Session s = sessions.get(session.getId());
		// 如果为null
		if (s == null) {
			// 实例化包装Session
			sessions.put(session.getId(), s = new SessionMina(session));
		}
		// 返回
		return s;
	}
}
