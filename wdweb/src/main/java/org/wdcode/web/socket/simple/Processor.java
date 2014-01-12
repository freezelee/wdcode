package org.wdcode.web.socket.simple;

import java.util.Map;

import org.wdcode.common.lang.Bytes;
import org.wdcode.common.lang.Maps;
import org.wdcode.common.util.ClassUtil;
import org.wdcode.common.util.StringUtil;
import org.wdcode.web.socket.Buffer;
import org.wdcode.web.socket.Handler;
import org.wdcode.web.socket.Process;
import org.wdcode.web.socket.Session;

/**
 * Socket 数据处理器实现
 * @author WD
 * @since JDK7
 * @version 1.0 2013-12-22
 */
public final class Processor implements Process {
	// Handler列表
	protected Map<Integer, Handler<Object>>	handlers	= Maps.getConcurrentMap();
	// 保存Session
	protected Map<Integer, Session>			sessions	= Maps.getConcurrentMap();
	// 保存全局IoBuffer
	protected Map<Integer, Buffer>			buffers		= Maps.getConcurrentMap();
	// 心跳处理
	private HeartHandler					heart;

	@Override
	public void addHandler(Handler<?>... handler) {
		for (Handler<?> h : handler) {
			// 如果是心跳处理
			if (h instanceof HeartHandler) {
				heart = (HeartHandler) h;
			}
			// 正常添加到处理器列表
			handlers.put(h.getId(), (Handler<Object>) h);

		}
	}

	@Override
	public void connected(Session session, Buffer buffer) {
		sessions.put(session.getId(), session);
		buffers.put(session.getId(), buffer);
		// 如果心跳处理不为空
		if (heart != null) {
			heart.add(session);
		}
	}

	@Override
	public void closed(Session session) {
		sessions.remove(session.getId());
		buffers.remove(session.getId());
		// 如果心跳处理不为空
		if (heart != null) {
			heart.remove(session);
		}
	}

	@Override
	public Session getSession(int id) {
		return sessions.get(id);
	}

	@Override
	public Map<Integer, Session> getSessions() {
		return sessions;
	}

	@Override
	public void process(Session session, byte[] message) {
		// 获得全局buffer
		Buffer buff = buffers.get(session.getId());
		// 添加新消息到全局缓存中
		buff.put(message);
		// 反转缓存区
		buff.flip();
		// 循环读取数据
		while (true) {
			// 剩余字节长度不足，等待下次信息
			if (buff.remaining() < 4) {
				// 压缩并跳出循环
				buff.compact();
				break;
			}
			// 获得信息长度
			int length = Integer.reverseBytes(buff.getInt());
			// 无长度 发送消息不符合 关掉连接
			if (length == 0) {
				session.close();
				break;
			}
			// 剩余字节长度不足，等待下次信息
			if (buff.remaining() < length) {
				// 重置缓存
				buff.rewind();
				// 压缩并跳出循环
				buff.compact();
				break;
			} else {
				// 读取指令id
				int id = Integer.reverseBytes(buff.getInt());
				// 获得相应的
				Handler<Object> handler = handlers.get(id);
				// 消息长度
				int len = length - 4;
				// 如果消息长度为0
				if (len == 0) {
					handler.handler(session, null);
				} else {
					// 读取指定长度的字节数
					byte[] data = new byte[len];
					// 读取指定长度字节数组
					buff.get(data);
					// 获得处理器消息类
					Class<?> type = ClassUtil.getGenericClass(handler.getClass());
					// 判断消息实体类型
					if (type.equals(String.class)) {
						// 字符串
						handler.handler(session, StringUtil.toString(data));
					} else if (type.equals(int.class) || type.equals(Integer.class)) {
						// 整型
						handler.handler(session, Bytes.toInt(data));
					} else if (type.equals(long.class) || type.equals(Long.class)) {
						// 长整型
						handler.handler(session, Bytes.toLong(data));
					} else if (type.equals(boolean.class) || type.equals(Boolean.class)) {
						// 布尔
						handler.handler(session, Bytes.toLong(data));
					} else if (type.equals(float.class) || type.equals(Float.class)) {
						// float型
						handler.handler(session, Bytes.toFloat(data));
					} else if (type.equals(double.class) || type.equals(Double.class)) {
						// Double型
						handler.handler(session, Bytes.toDouble(data));
					} else if (type.equals(byte[].class)) {
						// 字节流
						handler.handler(session, data);
					} else {
						// 默认使用消息体
						handler.handler(session, ((Message) ClassUtil.newInstance(type)).toBean(data));
					}
				}
				// 如果缓存区为空
				if (buff.remaining() == 0) {
					// 清除并跳出
					buff.clear();
					break;
				} else {
					// 压缩
					buff.compact();
					// 反转缓存区
					buff.flip();
				}
			}
		}
	}
}
