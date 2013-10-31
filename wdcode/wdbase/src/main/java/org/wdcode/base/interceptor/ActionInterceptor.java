package org.wdcode.base.interceptor;

import java.util.List;

import org.wdcode.base.action.BasicAction;
import org.wdcode.common.constants.StringConstants;
import org.wdcode.common.lang.Lists;

import com.opensymphony.xwork2.ActionInvocation;

/**
 * 拦截指定Action
 * @author WD 2013-9-22
 */
public class ActionInterceptor<E extends BasicAction> extends BasicInterceptor<E> {
	private static final long	serialVersionUID	= 3389700856431833675L;
	// 包含的Action
	protected List<String>		action				= Lists.emptyList();

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

	@Override
	protected boolean execute(ActionInvocation invocation) {
		return action.contains(invocation.getProxy().getActionName());
	}
}
