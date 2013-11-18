package org.wdcode.base.quartz;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.stereotype.Component;
import org.wdcode.core.log.Logs;

/**
 * 定时时间
 * @author WD
 * @since JDK7
 * @version 1.0 2013-11-15
 */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class CronTrigger extends CronTriggerFactoryBean {
	@Override
	public void afterPropertiesSet() {
		System.out.println(1);
	}

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
