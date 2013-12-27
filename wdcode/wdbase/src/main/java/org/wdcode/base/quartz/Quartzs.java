package org.wdcode.base.quartz;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.quartz.Trigger;
import org.springframework.stereotype.Component;
import org.wdcode.base.context.Context;
import org.wdcode.common.constants.StringConstants;
import org.wdcode.common.lang.Lists;
import org.wdcode.common.util.EmptyUtil;
import org.wdcode.core.params.QuartzParams;

/**
 * quartz启动器
 * @author WD
 * @since JDK7
 * @version 1.0 2013-12-27
 */
@Component
public final class Quartzs {
	// Context
	@Resource
	private Context	context;

	/**
	 * 初始化
	 */
	@PostConstruct
	protected void init() {
		// 定时任务
		if (QuartzParams.SPRING) {
			// 声明定时对象
			List<Trigger> triggers = Lists.getList();
			// 循环设置
			for (Job job : context.getBeans(Job.class).values()) {
				// 设置任务
				for (Map.Entry<String, String> e : job.getTriggers().entrySet()) {
					// 声明方法执行bean
					JobDetail method = context.getBean(JobDetail.class);
					// 设置任务对象
					method.setTargetObject(job);
					// // 设置执行方法
					method.setTargetMethod(e.getKey());
					// 设置group
					method.setGroup(job.getClass().getSimpleName());
					// 设置beanName
					method.setBeanName(e.getKey());
					// 执行初始化
					method.init();
					// 执行执行时间
					for (String trigger : e.getValue().split(StringConstants.COMMA)) {
						// 执行时间对象
						CronTrigger cron = context.getBean(CronTrigger.class);
						// 设置group
						cron.setGroup(method.getTargetObject().getClass().getSimpleName());
						// 设置beanName
						cron.setBeanName(method.getTargetMethod());
						// 设置任务对象
						cron.setJobDetail(method.getObject());
						// 设置时间
						cron.setCronExpression(trigger);
						// 执行初始化
						cron.init();
						// 添加到定时列表中
						triggers.add(cron.getObject());
					}
				}
			}
			// 定时任务不为空
			if (!EmptyUtil.isEmpty(triggers)) {
				// 声明执行定时方法工厂
				Scheduler scheduler = context.getBean(Scheduler.class);
				// 设置执行时间
				scheduler.setTriggers(Lists.toArray(triggers));
				// 执行初始化
				scheduler.init();
				// 执行
				scheduler.start();
			}
		}
	}
}
