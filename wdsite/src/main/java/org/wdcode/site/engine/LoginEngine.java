package org.wdcode.site.engine;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.wdcode.base.entity.EntityLogin;
import org.wdcode.common.lang.Conversion;
import org.wdcode.common.util.EmptyUtil;
import org.wdcode.core.json.JsonEngine;
import org.wdcode.site.token.LoginToken;
import org.wdcode.web.util.AttributeUtil; 

/**
 * 登录信息Bean
 * @author WD
 * @since JDK7
 * @version 1.0 2012-07-20
 */
public final class LoginEngine {
	// 空登录信息
	private final static LoginToken	EMPTY	= new LoginToken();
	// 登录信息标识
	private final static String		INFO	= "_info";

	/**
	 * 是否登录
	 * @param request HttpServletRequest
	 * @param key 登录标识
	 * @return true 登录 false 未登录
	 */
	public static boolean isLogin(HttpServletRequest request, String key) {
		return getLoginBean(request, key).isLogin();
	}

	/**
	 * 添加登录信息
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @param login 登录实体
	 * @param maxAge 保存时间
	 */
	public static void addLogin(HttpServletRequest request, HttpServletResponse response, EntityLogin login, int maxAge) {
		AttributeUtil.set(request, response, login.getClass().getSimpleName() + INFO, new LoginToken(login), maxAge);
	}

	/**
	 * 获得用户信息
	 * @param request HttpServletRequest
	 * @param key 登录标识
	 * @return 用户信息
	 */
	public static LoginToken getLoginBean(HttpServletRequest request, String key) {
		// 读取用户信息
		String info = Conversion.toString(AttributeUtil.get(request, key + INFO));
		// 返回实体
		return EmptyUtil.isEmpty(info) ? EMPTY : JsonEngine.toBean(info, LoginToken.class);
	}

	/**
	 * 获得一样空登录信息
	 * @return
	 */
	public static LoginToken empty() {
		return EMPTY;
	}

	/**
	 * 移除登录信息
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @param key 登录标识
	 */
	public static void removeLogin(HttpServletRequest request, HttpServletResponse response, String key) {
		// 销毁用户session
//		SessionUtil.close(request.getSession());
		// 写入用户信息
		AttributeUtil.remove(request, response, key + INFO);
	}

	/**
	 * 私有构造
	 */
	private LoginEngine() {}
}