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
import org.wdcode.common.lang.Conversion;
import org.wdcode.common.util.DateUtil;
import org.wdcode.common.util.EmptyUtil;
import org.wdcode.security.exception.SecurityException;
import org.wdcode.security.params.SecurityParams;
import org.wdcode.security.po.Ips;
import org.wdcode.security.po.Operate;
import org.wdcode.security.po.OperateLogs;
import org.wdcode.security.po.Role;
import org.wdcode.site.action.LoginAction;

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
	@Before("execution(* org.wdcode.base.action.SuperAction.add(..)) or execution(* org.wdcode.base.action.SuperAction.adds(..)) or execution(* org.wdcode.base.action.SuperAction.edit(..)) or execution(* org.wdcode.base.action.SuperAction.dels(..)) or execution(* org.wdcode.base.action.SuperAction.del(..)) or execution(* org.wdcode.base.action.SuperAction.trun(..))")
	public void security(JoinPoint point) {
		// 判断是否开启权限验证
		if (SecurityParams.SECURITY_POWER) {
			// 获得后台Action
			LoginAction<?, ?> action = (LoginAction<?, ?>) point.getTarget();
			// 是否IP验证
			if (SecurityParams.SECURITY_IP) {
				// 获得IP
				String ip = action.getIp();
				// 获得IP
				Ips ips = service.get(Ips.class, ip);
				// 没有次IP
				if (ips == null) {
					throw new SecurityException("no.ip=" + ip);
				}
			}
			// link
			String link = action.getLink();
			// 获得操作
			Operate operate = service.get(Operate.class, link);
			// 如果操作为空 直接异常
			if (operate == null) {
				throw new SecurityException("no.operate=" + link);
			}
			// 判断是否类型验证
			if (SecurityParams.SECURITY_TYPE > 0) {
				if (SecurityParams.SECURITY_TYPE != Conversion.toInt(operate.getType())) {
					throw new SecurityException("no.type=" + operate.getType());
				}
			}
			// 判断是否开启角色权限验证
			if (SecurityParams.SECURITY_ROLE) {
				// 获得角色
				Role role = service.get(Role.class, action.getToken().getId());
				// 如果角色为空
				if (role == null) {
					throw new SecurityException("no.role");
				}
				// 获得自己的权限列表
				List<Operate> lsOperate = role.getOperates();
				// 不是所有权限 继续判断
				if (EmptyUtil.isEmpty(lsOperate) || !lsOperate.contains(operate)) {
					throw new SecurityException("role.operate");
				}
			}
		}
	}

	/**
	 * 后置通知方法 记录日志
	 * @param point aop切点信息
	 * @param retVal 返回值
	 */
	@AfterReturning(pointcut = "execution(* org.wdcode.base.action.SuperAction.add(..)) or execution(* org.wdcode.base.action.SuperAction.adds(..)) or execution(* org.wdcode.base.action.SuperAction.edit(..)) or execution(* org.wdcode.base.action.SuperAction.dels(..)) or execution(* org.wdcode.base.action.SuperAction.del(..)) or execution(* org.wdcode.base.action.SuperAction.trun(..))", returning = "retVal")
	public void logs(JoinPoint point, Object retVal) {
		// 判断是否开启操作日志记录
		if (SecurityParams.OPERATE_LOGS) {
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