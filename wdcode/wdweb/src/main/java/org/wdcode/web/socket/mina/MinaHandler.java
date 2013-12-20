package org.wdcode.web.socket.mina;

import java.util.Map;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;
import org.wdcode.common.lang.Maps;
import org.wdcode.common.util.ClassUtil;
import org.wdcode.web.socket.Handler;
import org.wdcode.web.socket.Session;
import org.wdcode.web.socket.message.Message;

/**
 * mina实现
 * @author WD
 * @since JDK7
 * @version 1.0 2013-11-28
 */
public final class MinaHandler extends IoHandlerAdapter {
	// Handler列表
	private Map<Integer, Handler<Message>>	handlers	= Maps.getConcurrentMap();
	// 保存Session
	private Map<Long, Session>				sessions	= Maps.getConcurrentMap();
	// 保存全局IoBuffer
	private Map<Long, IoBuffer>				buffers		= Maps.getConcurrentMap();

	/**
	 * 添加要处理的Handler
	 * @param handler
	 */
	public void addHandler(Handler<? extends Message> handler) {
		handlers.put(handler.getId(), (Handler<Message>) handler);
	}

	@Override
	public void sessionClosed(IoSession session) throws Exception {
		sessions.remove(session.getId());
	}

	@Override
	public void messageReceived(IoSession session, Object message) throws Exception {
		// 获得全局buffer
		IoBuffer io = buffers.get(session.getId());
		// io为空
		if (io == null) {
			// 赋值新缓存
			io = IoBuffer.wrap(((IoBuffer) message).buf());
			io.setAutoExpand(true);
			io.setAutoShrink(true);
			// 添加到缓存中
			buffers.put(session.getId(), io);
		} else {
			// 添加新消息到全局缓存中
			io.put((IoBuffer) message);
			// 反转缓存区
			io.flip();
		}
		// 循环读取数据
		while (true) {
			// 剩余字节长度不足，等待下次信息
			if (io.remaining() < 4) {
				// 压缩并跳出循环
				io.compact();
				break;
			}
			// 获得信息长度
			int length = Integer.reverseBytes(io.getInt());
			// 无长度 发送消息不符合 关掉连接
			if (length == 0) {
				session.close(true);
				break;
			}
			// 剩余字节长度不足，等待下次信息
			if (io.remaining() < length) {
				// 重置缓存
				io.rewind();
				// 压缩并跳出循环
				io.compact();
				break;
			} else {
				// 读取指令id
				int id = Integer.reverseBytes(io.getInt());
				// 获得相应的
				Handler<Message> handler = handlers.get(id);
				// 读取指定长度的字节数
				byte[] data = new byte[length - 4];
				// 读取指定长度字节数组
				io.get(data);
				// 解码并发布到相对的处理器
				Message mess = (Message) ClassUtil.newInstance(ClassUtil.getGenericClass(handler.getClass().getGenericInterfaces()[0], 0));
				mess.toBean(data);
				handler.handler(getSesson(session), mess);
				// 如果缓存区为空
				if (io.remaining() == 0) {
					// 清除并跳出
					io.clear();
					break;
				}
			}
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
			sessions.put(session.getId(), s = new MinaSession(session));
		}
		// 返回
		return s;
	}
}
