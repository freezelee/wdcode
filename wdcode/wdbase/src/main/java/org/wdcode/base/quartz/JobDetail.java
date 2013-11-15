package org.wdcode.base.quartz;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.stereotype.Component;

/**
 * 任务详细类
 * @author WD
 * @since JDK7
 * @version 1.0 2013-11-15
 */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class JobDetail extends MethodInvokingJobDetailFactoryBean {
	public JobDetail() {}

	public JobDetail(Object obj, String method) {
		this.setTargetObject(obj);
		this.setTargetMethod(method);
	}
}
