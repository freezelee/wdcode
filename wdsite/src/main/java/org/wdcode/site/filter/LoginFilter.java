package org.wdcode.site.filter;

import javax.servlet.Filter;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.wdcode.base.entity.EntityLogin;
import org.wdcode.base.params.BaseParams;
import org.wdcode.common.util.EmptyUtil;
import org.wdcode.site.engine.LoginEngine;

import java.io.IOException;

/**
 * 检测用户是否登陆
 * @author WD
 * @since JDK7
 * @version 1.0 2010-03-07
 */
public final class LoginFilter<L extends EntityLogin> implements Filter {
	// 常量
	private final static String	FORWARD	= "forward";
	// 登录过滤Key
	private String				login;

	/**
	 * 载入过滤器到服务 读取过滤器配置参数
	 */
	public void init(FilterConfig filterConfig) throws ServletException {
		login = filterConfig.getInitParameter("login");
	}

	/**
	 * 过滤
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// 转换Request
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		// 转换Response
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		// 校验页面
		boolean find = true;
		// 获得当前页面
		String paths = httpRequest.getServletPath(); // httpRequest.getHeader("Referer");
		// 跳转页
		String indexPage = BaseParams.LOGIN_FILTER_INDEX;

		String[] special = BaseParams.LOGIN_FILTER_SPECIAL;
		// 有数组
		if (!EmptyUtil.isEmpty(special)) {
			// 循环数组
			for (int i = 0; i < special.length; i++) {
				// 查找是相同页
				if (paths.indexOf(special[i]) >= 0) {
					// 不检查
					find = false;
					// 跳出循环
					break;
				}
			}
		}

		// 判断检查
		if (find) {
			// 是否登录
			if (LoginEngine.isLogin(httpRequest, login)) {
				chain.doFilter(request, response);
			} else {
				// 判断跳转方式
				if (FORWARD.equals(BaseParams.LOGIN_FILTER_RESULT)) {
					// forward
					httpRequest.getRequestDispatcher(httpRequest.getContextPath() + indexPage).forward(request, response);
				} else {
					// redirect
					httpResponse.sendRedirect(httpRequest.getContextPath() + indexPage);
				}
			}

		} else {
			// 交出控制权
			chain.doFilter(request, response);
		}
	}

	/**
	 * 销毁实例调用
	 */
	public void destroy() {}
}
