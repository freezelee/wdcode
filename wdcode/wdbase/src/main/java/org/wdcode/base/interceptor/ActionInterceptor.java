package org.wdcode.base.interceptor;

import java.util.List;

import org.wdcode.common.constants.StringConstants;
import org.wdcode.common.lang.Lists;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * 拦截指定Action
 * @author WD 2013-9-22
 */
public abstract class ActionInterceptor extends AbstractInterceptor {
	private static final long	serialVersionUID	= 7385133486187055501L;
	// 包含的Action
	protected List<String>		action				= Lists.emptyList();

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		// 验证是否拦截Action
		if (action.contains(invocation.getProxy().getActionName())) {
			// 前置方法
			before(invocation);
			// 执行方法
			String result = invocation.invoke();
			// 后置方法
			after(invocation);
			// 返回结果
			return result;
		} else {
			return invocation.invoke();
		}
	}

	/**
	 * 获得包含的Action
	 * @return 包含的Action
	 */
	public List<String> getAction() {
		return action;
	}

	/**
	 * 设置包含的Action
	 * @param include 包含的Action
	 */
	public void setAction(String action) {
		this.action = Lists.getList(action.split(StringConstants.COMMA));
	}

	/**
	 * 前置通知方法
	 * @param invocation
	 */
	protected abstract void before(ActionInvocation invocation);

	/**
	 * 后置通知方法
	 * @param invocation
	 */
	protected abstract void after(ActionInvocation invocation);
}
