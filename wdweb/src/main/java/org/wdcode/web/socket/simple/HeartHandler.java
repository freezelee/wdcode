package org.wdcode.web.socket.simple;

import java.util.Map;

import org.wdcode.common.lang.Conversion;
import org.wdcode.common.lang.Maps;
import org.wdcode.common.util.DateUtil;
import org.wdcode.common.util.ScheduledUtile;
import org.wdcode.core.log.Logs;
import org.wdcode.web.socket.interfaces.Handler;
import org.wdcode.web.socket.interfaces.Heart;
import org.wdcode.web.socket.interfaces.Manager;
import org.wdcode.web.socket.interfaces.Session;

/**
 * 心跳检测
 * @author WD
 * @since JDK7
 * @version 1.0 2013-12-30
 */
public final class HeartHandler implements Handler<Integer>, Heart {
	// 保存Session 时间
	private Map<Integer, Integer>	times;
	// 保存Session
	private Map<Integer, Session>	sessions;
	// 心跳检测时间
	private int						heart;
	// 心跳检查ID指令
	private int						id;

	/**
	 * 构造
	 */
	public HeartHandler(int id, int time) {
		this.id = id;
		this.heart = time;
		times = Maps.getConcurrentMap();
		sessions = Maps.getConcurrentMap();
		// 定时检测
		ScheduledUtile.rate(new Runnable() {
			@Override
			public void run() {
				// 获得当前时间
				int time = DateUtil.getTime();
				Logs.info("heart check=" + DateUtil.getDate());
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
						Logs.info("heart close session=" + e.getKey());
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
					session.send(getId(), time);
					Logs.info("send heart session=" + session.getId());
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
		return id;
	}

	@Override
	public void handler(Session session, Integer data, Manager manager) {
		Logs.info("receive heart time=" + DateUtil.getTime() + ";data=" + data);
		// 获得session id
		int id = session.getId();
		// data一般为服务器心跳来的时间戳
		times.put(id, data);
		// 如果session 列表中不存在
		if (!sessions.containsKey(id)) {
			sessions.put(id, session);
		}
	}
}
