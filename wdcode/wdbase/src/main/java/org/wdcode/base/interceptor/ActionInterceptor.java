package org.wdcode.base.interceptor;

import java.util.List;

import org.wdcode.base.action.BasicAction;
import org.wdcode.common.constants.StringConstants;
import org.wdcode.common.lang.Lists;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * 拦截指定Action
 * @author WD 2013-9-22
 */
public class ActionInterceptor extends AbstractInterceptor {
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
					// 后置方法 返回结果
					return after(invocation, invocation.invoke());
				} catch (Exception e) {
					exception(invocation, e);
				}
			}
			// 返回空地址
			return BasicAction.ERROR;
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
	protected boolean before(ActionInvocation invocation) {
		return true;
	}

	/**
	 * 异常处理
	 * @param invocation
	 * @param e
	 * @return
	 */
	protected void exception(ActionInvocation invocation, Exception e) {}

	/**
	 * 后置通知方法
	 * @param invocation
	 */
	protected String after(ActionInvocation invocation, String result) {
		return result;
	}
}
