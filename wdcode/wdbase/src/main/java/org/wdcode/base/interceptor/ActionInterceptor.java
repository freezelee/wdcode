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
			if (before(invocation)) {
				// 执行方法
				try {
					String result = invocation.invoke();
					// 后置方法
					if (after(invocation)) {
						// 返回结果
						return result;
					}
				} catch (Exception e) {
					exception(invocation, e);
				}
			}
			// 返回空地址
			return StringConstants.EMPTY;
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
	protected abstract boolean before(ActionInvocation invocation);

	/**
	 * 异常处理
	 * @param invocation
	 * @param e
	 * @return
	 */
	protected abstract boolean exception(ActionInvocation invocation, Exception e);

	/**
	 * 后置通知方法
	 * @param invocation
	 */
	protected abstract boolean after(ActionInvocation invocation);
}
