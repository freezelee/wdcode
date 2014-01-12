package org.wdcode.web.socket.simple;

import java.util.Map;

import org.wdcode.common.lang.Bytes;
import org.wdcode.common.lang.Conversion;
import org.wdcode.common.lang.Maps;
import org.wdcode.common.util.DateUtil;
import org.wdcode.common.util.EmptyUtil;
import org.wdcode.common.util.ScheduledUtile;
import org.wdcode.web.socket.Handler;
import org.wdcode.web.socket.Session;

/**
 * 心跳检测
 * @author WD
 * @since JDK7
 * @version 1.0 2013-12-30
 */
public final class HeartHandler implements Handler<byte[]> {
	// 保存Session 时间
	private Map<Integer, Integer>	times;
	// 保存Session
	private Map<Integer, Session>	sessions;
	// 心跳检测时间
	private int						heart;

	/**
	 * 构造
	 */
	public HeartHandler(int time) {
		times = Maps.getConcurrentMap();
		sessions = Maps.getConcurrentMap();
		this.heart = time;
		// 定时检测
		ScheduledUtile.rate(new Runnable() {
			@Override
			public void run() {
				// 获得当前时间
				int time = DateUtil.getTime();
				// 循环检测
				for (Map.Entry<Integer, Integer> e : times.entrySet()) {
					// 获得心跳时间
					int t = Conversion.toInt(e.getValue());
					// 如果心跳时间超过发送时间
					if (time - t > heart) {
						// 关闭Session
						sessions.get(e.getKey()).close();
						sessions.remove(e.getKey());
						times.remove(e.getKey());
					}
				}
			}
		}, heart);
		// 定时发送心跳信息
		ScheduledUtile.rate(new Runnable() {
			@Override
			public void run() {
				// 获得当前时间
				int time = DateUtil.getTime();
				// 循环发送心跳信息
				for (Session session : sessions.values()) {
					session.send(0, time);
				}
			}
		}, heart / 2);
	}

	/**
	 * 添加Session
	 * @param session
	 */
	public void add(Session session) {
		sessions.put(session.getId(), session);
		times.put(session.getId(), DateUtil.getTime());
	}

	/**
	 * 删除Session
	 * @param session
	 */
	public void remove(Session session) {
		sessions.remove(session.getId());
		times.remove(session.getId());
	}

	@Override
	public int getId() {
		return 0;
	}

	@Override
	public void handler(Session session, byte[] data) {
		// 有数据 4字节为int时间戳
		if (!EmptyUtil.isEmpty(data) && data.length == 4) {
			times.put(session.getId(), Bytes.toInt(data));
		}
	}
}
