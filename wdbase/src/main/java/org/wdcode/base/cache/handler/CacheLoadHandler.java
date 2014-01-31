package org.wdcode.base.cache.handler;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.wdcode.base.context.Context;
import org.wdcode.base.entity.Entity;
import org.wdcode.base.service.SuperService;
import org.wdcode.web.socket.interfaces.Handler;
import org.wdcode.web.socket.interfaces.Manager;
import org.wdcode.web.socket.interfaces.Session;

/**
 * 缓存Socket通知类
 * @author WD
 * @since JDK7
 * @version 1.0 2013-12-30
 */
@Component
public final class CacheLoadHandler implements Handler<String> {
	// SuperService
	@Resource
	private SuperService	service;
	// Context
	@Resource
	private Context			context;

	@Override
	public int getId() {
		return 1;
	}

	@Override
	public void handler(Session session, String data, Manager manager) {
		// 获得要更新缓存的类
		Class<? extends Entity> c = context.getClass(data);
		// 判断类是否为空
		if (c == null) {
			// 更新所有缓存
			service.cache();
		} else {
			// 更新指定类缓存
			service.load(c);
		}
	}
}
