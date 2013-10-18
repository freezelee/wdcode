package org.wdcode.base.aop;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.wdcode.base.action.SuperAction;
import org.wdcode.base.bean.Inform;
import org.wdcode.base.constants.InformConstants;
import org.wdcode.common.constants.StringConstants;
import org.wdcode.common.util.StringUtil;

/**
 * 回执消息处理
 * @author WD
 * @since JDK6
 * @version 1.0 2013-10-14
 */
@Component
@Aspect
public class InformAdvice {
	/**
	 * 验证权限方法
	 * @param point
	 */
	@AfterThrowing(pointcut = "execution(* org.wdcode.base.action.SuperAction.add(..)) or execution(* org.wdcode.base.action.SuperAction.adds(..)) or execution(* org.wdcode.base.action.SuperAction.edit(..)) or execution(* org.wdcode.base.action.SuperAction.dels(..)) or execution(* org.wdcode.base.action.SuperAction.del(..)) or execution(* org.wdcode.base.action.SuperAction.trun(..))", throwing = "error")
	public void inform(JoinPoint point, Throwable error) throws Exception {
		// 获得SuperAction
		SuperAction<?> action = (SuperAction<?>) point.getTarget();
		// 声明消息字符串
		StringBuilder sb = new StringBuilder();
		// 判断是JavaBean验证框架的异常
		if (error instanceof ConstraintViolationException) {
			sb.append(violation(action, error));
		} else if (error.getCause() instanceof ConstraintViolationException) {
			sb.append(violation(action, error.getCause()));
		} else {
			sb.append(error.getMessage());
		}
		// 回执消息
		action.callback(new Inform(InformConstants.FAIL, sb.toString()));
	}

	/**
	 * JavaBean验证框架返回异常信息
	 * @param action
	 * @param error
	 * @return
	 */
	private String violation(SuperAction<?> action, Throwable error) {
		// 声明消息字符串
		StringBuilder sb = new StringBuilder();
		// 设置验证消息
		for (ConstraintViolation<?> c : ((ConstraintViolationException) error).getConstraintViolations()) {
			sb.append(action.getText(c.getMessageTemplate())).append(StringConstants.COMMA);
		}
		// 返回错误消息
		return StringUtil.subString(sb.toString(), 0, sb.length() - 1);
	}
}
