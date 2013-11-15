package org.wdcode.shop.job;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.wdcode.base.quartz.QuartzJob;
import org.wdcode.base.service.SuperService;
import org.wdcode.common.lang.Lists;
import org.wdcode.common.lang.Maps;

/**
 * 频道定时
 * @author WD 2013-11-5
 */
@Component
public class ChannelJob implements QuartzJob {
	// 通用业务接口
	@Resource
	private SuperService	service;

	/**
	 * 获得动态数据
	 */
	public void dynamic() {
		// 获得所有频道
		System.out.println("频道执行了！");
	}

	@Override
	public Map<String, List<String>> getTriggers() {
		return Maps.getMap("dynamic", Lists.getList("0 0/1 * * * ?"));
	}
}
