package org.wdcode.logs.aop;

import java.io.Serializable;
import java.util.Arrays;

import javax.annotation.Resource;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.wdcode.base.action.BasicAction;
import org.wdcode.base.entity.Entity;
import org.wdcode.base.service.SuperService;
import org.wdcode.common.lang.Conversion;
import org.wdcode.common.util.DateUtil;
import org.wdcode.common.util.EmptyUtil;
import org.wdcode.logs.action.PageLogsAction;
import org.wdcode.logs.params.LogsParams;
import org.wdcode.logs.po.LoginLogs;
import org.wdcode.logs.po.LoginStatistics;
import org.wdcode.logs.po.OperateLogs;
import org.wdcode.logs.po.PageStatistics;
import org.wdcode.site.action.LoginAction;
import com.opensymphony.xwork2.Action;

/**
 * AOP通知 用于拦截Action
 * @author WD
 * @since JDK7
 * @version 1.0 2009-09-24
 */
@Component
@Aspect
public class LogsAdvice {
	// 业务
	@Resource
	private SuperService	service;

	/**
	 * 后置通知方法 记录页面访问统计
	 * @param point aop切点信息
	 * @param retVal 返回值
	 */
	@AfterReturning(pointcut = "execution(* org.wdcode.logs.action.PageLogsAction.in())", returning = "retVal")
	public void page(JoinPoint point, Object retVal) {
		// 登录统计
		if (LogsParams.PAGE_STATISTICS) {
			// 获得页面日志Action
			PageLogsAction action = (PageLogsAction) point.getTarget();
			// 获得页面
			String page = Conversion.toString((action.getEntity()).getKey());
			// 获得用户ID
			int uid = action.getToken().getId();
			// 获得IP
			String ip = action.getIp();
			// 根据用户ID获得统计数据
			PageStatistics statistics = service.get(PageStatistics.class, page);
			// 如果统计实体为null
			if (EmptyUtil.isEmpty(statistics)) {
				// 获得一个新实体
				statistics = new PageStatistics();
				// 设置属性
				statistics.setPage(page);
			}
			// 设置属性
			statistics.setUserId(uid);
			statistics.setCount(Conversion.toInt(statistics.getCount()) + 1);
			statistics.setTime(DateUtil.getTime());
			statistics.setIp(ip);
			// 添加或删除
			service.insertOrUpdate(statistics);
		}
	}

	/**
	 * 后置通知方法 记录登录日志日志
	 * @param point aop切点信息
	 * @param retVal 返回值
	 */
	@AfterReturning(pointcut = "execution(* org.wdcode.site.action.LoginAction.login())", returning = "retVal")
	public void login(JoinPoint point, Object retVal) {
		// 获得登录Login
		LoginAction<?, ?> login = (LoginAction<?, ?>) point.getTarget();
		// 获得用户ID
		int uid = login.getToken().getId();
		// 获得登录key
		String key = login.getLoginKey();
		// 获得IP
		String ip = login.getIp();
		// 判断是否开启登录日志记录
		if (LogsParams.LOGIN_LOGS) {
			// 获得登录状态
			int state = Action.SUCCESS.equals(retVal) ? 1 : 0;
			// 声明一个新的登录日志实体
			LoginLogs logs = new LoginLogs();
			// 设置属性
			logs.setUserId(uid);
			logs.setName(key);
			logs.setTime(DateUtil.getTime());
			logs.setIp(ip);
			logs.setState(state);
			// 添加到数据库
			service.insert(logs);
		}
		// 登录统计
		if (LogsParams.LOGIN_STATISTICS) {
			// 根据用户ID获得统计数据
			LoginStatistics statistics = service.get(LoginStatistics.class, uid);
			// 如果统计实体为null
			if (EmptyUtil.isEmpty(statistics)) {
				// 获得一个新实体
				statistics = new LoginStatistics();
				// 设置标识
				statistics.setName(key);
			}
			// 判断标识相同
			if (key.equals(statistics.getName())) {
				// 设置属性
				statistics.setUserId(uid);
				statistics.setCount(Conversion.toInt(statistics.getCount()) + 1);
				statistics.setLastTime(statistics.getTime());
				statistics.setLastIp(statistics.getIp());
				statistics.setTime(DateUtil.getTime());
				statistics.setIp(ip);
				// 添加或删除
				service.insertOrUpdate(statistics);
			}
		}
	}

	/**
	 * 后置通知方法 记录日志
	 * @param point aop切点信息
	 * @param retVal 返回值
	 */
	@AfterReturning(pointcut = "execution(* org.wdcode.base.action.SuperAction.add(..)) or execution(* org.wdcode.base.action.SuperAction.adds(..)) or execution(* org.wdcode.base.action.SuperAction.edit(..)) or execution(* org.wdcode.base.action.SuperAction.dels(..)) or execution(* org.wdcode.base.action.SuperAction.del(..)) or execution(* org.wdcode.base.action.SuperAction.trun(..))", returning = "retVal")
	public void operate(JoinPoint point, Object retVal) {
		// 判断是否开启操作日志记录
		if (LogsParams.OPERATE_LOGS) {
			// 获得后台Action
			LoginAction<?, ?> action = (LoginAction<?, ?>) point.getTarget();
			// 获得登录状态
			int state = BasicAction.ERROR.equals(retVal) ? 0 : 1;
			// 获得提交的连接
			String link = action.getLink();
			// 获得操作实体
			Entity entity = action.getEntity();
			// 获得删除的IDS
			Serializable[] keys = action.getKeys();
			// 添加日志
			OperateLogs logs = new OperateLogs();
			// 设置用户ID
			logs.setUserId(action.getToken().getId());
			logs.setTime(DateUtil.getTime());
			logs.setState(state);
			logs.setName(link);
			// 判断操作
			if (EmptyUtil.isEmpty(keys)) {
				// 判断实体不为空
				if (!EmptyUtil.isEmpty(entity)) {
					logs.setContent(entity.toString());
				}
			} else {
				// 删除多个数据
				logs.setContent(Arrays.toString((keys)));
			}
			// 记录日志
			service.insert(logs);
		}
	}
}
