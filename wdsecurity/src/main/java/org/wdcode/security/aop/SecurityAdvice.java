package org.wdcode.security.aop;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.wdcode.base.action.BasicAction;
import org.wdcode.base.entity.Entity;
import org.wdcode.base.service.SuperService;
import org.wdcode.common.lang.Lists;
import org.wdcode.common.util.DateUtil;
import org.wdcode.common.util.EmptyUtil;
import org.wdcode.security.params.SecurityParams;
import org.wdcode.security.po.Operate;
import org.wdcode.security.po.OperateLogs;
import org.wdcode.site.action.LoginAction;
import org.wdcode.site.token.AuthToken;

/**
 * 权限拦截器
 * @author WD
 * @since JDK7
 * @version 1.0 2011-01-25
 */
@Component
@Aspect
public final class SecurityAdvice {
	@Resource
	private SuperService	service;

	/**
	 * 验证权限方法
	 * @param point
	 */
	@Before("execution(* org.wdcode.back.action.BackAction.add(..)) or execution(* org.wdcode.back.action.BackAction.edit(..)) or execution(* org.wdcode.back.action.BackAction.dels(..)) or execution(* org.wdcode.back.action.BackAction.del(..)) or execution(* org.wdcode.back.action.BackAction.trun(..))")
	public void rights(JoinPoint point) {
		// 获得后台Action
		LoginAction<?, ?> action = (LoginAction<?, ?>) point.getTarget();
		// 获得登录凭证
		AuthToken token = action.getToken();
		// URL
		String url = action.getActionName();
		// 获得自己的权限列表
		List<Operate> lsOperate = Lists.getList(token.getId());// token.getOperates();
		// 不是所有权限 继续判断
		if (EmptyUtil.isEmpty(lsOperate) || !lsOperate.contains(service.get(Operate.class, url))) {
			// throw new RightsException();
		}
	}

	/**
	 * 后置通知方法 记录日志
	 * @param point aop切点信息
	 * @param retVal 返回值
	 */
	@AfterReturning(pointcut = "execution(* org.wdcode.back.action.BackAction.add(..)) or execution(* org.wdcode.back.action.BackAction.edit(..)) or execution(* org.wdcode.back.action.BackAction.dels(..)) or execution(* org.wdcode.back.action.BackAction.del(..)) or execution(* org.wdcode.back.action.BackAction.trun(..))", returning = "retVal")
	public void logs(JoinPoint point, Object retVal) {
		// 判断是否开启操作日志记录
		if (SecurityParams.OPERATE_LOGS) {
			// 获得后台Action
			LoginAction<?, ?> action = (LoginAction<?, ?>) point.getTarget();
			// 获得登录状态
			int state = BasicAction.ERROR.equals(retVal) ? 0 : 1;
			// 用户名
			// String name = action.getAdmin().getName();
			// 获得提交的连接
			String link = action.getActionName();
			// 获得操作实体
			Entity entity = action.getEntity();
			// 获得删除的IDS
			Serializable[] keys = action.getKeys();
			// 根据link获得操作
			Operate operate = service.get(Operate.class, link);
			// 添加日志
			OperateLogs logs = new OperateLogs();
			// 设置用户ID
			logs.setUserId(action.getToken().getId());
			logs.setTime(DateUtil.getTime());
			logs.setState(state);
			logs.setName(operate == null ? link : operate.getName());
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