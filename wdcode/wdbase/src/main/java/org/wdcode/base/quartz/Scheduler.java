package org.wdcode.base.quartz;

import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;
import org.wdcode.core.log.Logs;

/**
 * 任务执行器
 * @author WD
 * @since JDK7
 * @version 1.0 2013-11-15
 */
@Component
public class Scheduler extends SchedulerFactoryBean {
	@Override
	public void afterPropertiesSet() {}

	/**
	 * 初始化方法
	 */
	public void init() {
		try {
			super.afterPropertiesSet();
		} catch (Exception e) {
			Logs.debug(e);
		}
	}
}
