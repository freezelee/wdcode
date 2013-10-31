package org.wdcode.base.interceptor;

import org.wdcode.base.action.BasicAction;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * 拦截指定Action
 * @author WD 2013-9-22
 */
public class BasicInterceptor<E extends BasicAction> extends AbstractInterceptor {
	private static final long	serialVersionUID	= 7385133486187055501L;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		// 验证是否拦截Action
		if (execute(invocation)) {
			// 获得Action
			E action = (E) invocation.getAction();
			// 前置方法
			if (before(action)) {
				// 执行方法
				try {
					// 后置方法 返回结果
					return after(action, invocation.invoke());
				} catch (Exception e) {
					exception(action, e);
				}
			}
			// 返回空地址
			return BasicAction.ERROR;
		} else {
			return invocation.invoke();
		}
	}

	/**
	 * 是否执行下面拦截
	 */
	protected boolean execute(ActionInvocation invocation) {
		return true;
	}

	/**
	 * 前置通知方法
	 * @param action
	 */
	protected boolean before(E action) {
		return true;
	}

	/**
	 * 异常处理
	 * @param action
	 * @param e
	 * @return
	 */
	protected void exception(E action, Exception e) {}

	/**
	 * 后置通知方法
	 * @param action
	 */
	protected String after(E action, String result) {
		return result;
	}
}
