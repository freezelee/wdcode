package org.wdcode.base.cache.handler;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.wdcode.base.cache.Cache;
import org.wdcode.base.context.Context;
import org.wdcode.base.entity.Entity;
import org.wdcode.base.service.SuperService;
import org.wdcode.core.json.JsonEngine;
import org.wdcode.web.socket.Handler;
import org.wdcode.web.socket.Session;

/**
 * 缓存Socket通知类
 * @author WD
 * @since JDK7
 * @version 1.0 2013-12-30
 */
@Component
public final class CacheEntityHandler implements Handler<CacheEntity> {
	// SuperService
	@Resource
	private SuperService	service;
	// Context
	@Resource
	private Context			context;

	@Override
	public int getId() {
		return 2;
	}

	@Override
	public void handler(Session session, CacheEntity data) {
		// 获得实体类
		Class<Entity> entity = context.getClass(data.getEntity());
		// 实体不为空
		if (entity != null) {
			// 获得缓存
			Cache<Entity> cache = service.getCache(entity);
			// 获得json
			String json = data.getJson();
			// 判断json类型
			if (JsonEngine.isObject(json)) {
				// 单实体
				if ("set".equals(data.getCommon())) {
					cache.set(JsonEngine.toBean(json, entity));
				} else if ("remove".equals(data.getCommon())) {
					cache.remove(JsonEngine.toBean(json, entity));
				}

			} else if (JsonEngine.isArray(json)) {
				// 列表
				if ("set".equals(data.getCommon())) {
					cache.set(JsonEngine.toList(json, entity));
				} else if ("remove".equals(data.getCommon())) {
					cache.remove(JsonEngine.toList(json, entity));
				}
			}
		}
	}
}
