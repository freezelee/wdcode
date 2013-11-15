package org.wdcode.shop.job;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.wdcode.base.quartz.Job;
import org.wdcode.base.service.SuperService;
import org.wdcode.common.lang.Lists;
import org.wdcode.common.lang.Maps;

/**
 * 节目定时
 * @author WD 2013-11-11
 */
@Component
public class ProgramJob implements Job {
	// 通用业务接口
	@Resource
	private SuperService	service;

	/**
	 * 节目开播
	 */
	public void play() {
		System.out.println("节目开播了！" + service);
	}

	/**
	 * 节目结束
	 */
	public void end() {
		System.out.println("节目结束！" + service);
	}

	@Override
	public Map<String, List<String>> getTriggers() {
		Map<String, List<String>> map = Maps.getMap();
		map.put("play", Lists.getList("0/5 * * * * ?"));
		map.put("end", Lists.getList("0/10 * * * * ?"));
		return map;
	}
}
