package org.wdcode.core.engine;

import java.util.List;
import java.util.Map;

import org.quartz.CronScheduleBuilder;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.wdcode.common.lang.Lists;
import org.wdcode.common.lang.Maps;
import org.wdcode.common.log.Logs;
import org.wdcode.common.util.ClassUtil;
import org.wdcode.common.util.ClearUtil;
import org.wdcode.core.params.QuartzParams;

/**
 * Quartz 处理引擎
 * @author WD
 * @since JDK7
 * @version 1.0 2011-05-25
 */
public final class QuartzEngine {
	// 任务执行器工厂
	private final static SchedulerFactory				FACTORY;
	// 保存任务列表
	private final static Map<JobDetail, List<Trigger>>	MAP_JOB;
	// 任务执行器
	private static Scheduler							scheduler;

	static {
		FACTORY = new StdSchedulerFactory();
		MAP_JOB = Maps.getConcurrentMap();
		try {
			scheduler = FACTORY.getScheduler();
		} catch (Exception e) {
			Logs.warn(e);
		}
	}

	/**
	 * 初始化任务
	 */
	public static void init() {
		// 循环数组
		for (String name : QuartzParams.NAMES) {
			try {
				add((Class<? extends Job>) ClassUtil.forName(QuartzParams.getClass(name)), QuartzParams.getTrigger(name));
			} catch (Exception e) {
				Logs.warn(e);
			}
		}
		// 判断任务不为空
		if (QuartzParams.POWER) {
			start();
		}
	}

	/**
	 * 添加任务
	 * @param jobClass 任务类
	 * @param trigger 任务执行时间 正则
	 */
	public static void add(Class<? extends Job> jobClass, String... trigger) {
		// 获得任务
		JobDetail job = JobBuilder.newJob(jobClass).build();
		// 声明任务执行时间列表
		List<Trigger> list = Lists.getList(trigger.length);
		// 循环获得任务执行时间对象
		for (int i = 0; i < trigger.length; i++) {
			list.add(TriggerBuilder.newTrigger().withSchedule(CronScheduleBuilder.cronSchedule(trigger[i])).build());
		}
		// 添加到任务列表中
		MAP_JOB.put(job, list);
	}

	/**
	 * 执行任务
	 */
	public static void start() {
		try {
			// 如果任务执行器为空 获得
			if (scheduler == null) {
				scheduler = FACTORY.getScheduler();
			}
			// 添加任务
			scheduler.scheduleJobs(MAP_JOB, true);
			// 执行任务
			scheduler.start();
		} catch (Exception e) {
			Logs.warn(e);
		}

	}

	/**
	 * 关闭资源
	 */
	public static void close() {
		// 关闭执行器
		try {
			scheduler.clear();
			scheduler.shutdown();
		} catch (Exception e) {
			Logs.warn(e);
		} finally {
			scheduler = null;
		}
		// 清除列表
		ClearUtil.clear(MAP_JOB);
	}

	private QuartzEngine() {}
}
