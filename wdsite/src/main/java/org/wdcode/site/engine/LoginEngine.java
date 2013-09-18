package org.wdcode.site.engine;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.wdcode.base.entity.EntityLogin;
import org.wdcode.common.lang.Conversion;
import org.wdcode.common.util.EmptyUtil;
import org.wdcode.core.json.JsonEngine;
import org.wdcode.site.params.SiteParams;
import org.wdcode.site.token.LoginToken;
import org.wdcode.web.util.AttributeUtil;
import org.wdcode.web.util.RequestUtil;

/**
 * 登录信息Bean
 * @author WD
 * @since JDK7
 * @version 1.0 2012-07-20
 */
public final class LoginEngine {
	// 空登录信息
	private final static LoginToken	EMPTY		= new LoginToken();
	// 登录信息标识
	private final static String		INFO		= "_info";
	// 游客IP
	private static int				GUEST_ID	= SiteParams.LOGIN_GUEST_ID;

	/**
	 * 是否登录
	 * @param request HttpServletRequest
	 * @param key 登录标识
	 * @return true 登录 false 未登录
	 */
	public static boolean isLogin(HttpServletRequest request, HttpServletResponse response, String key) {
		return getLoginBean(request, response, key).isLogin();
	}

	/**
	 * 添加登录信息
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @param login 登录实体
	 * @param maxAge 保存时间
	 */
	public static void addLogin(HttpServletRequest request, HttpServletResponse response, EntityLogin login, int maxAge) {
		AttributeUtil.set(request, response, login.getClass().getSimpleName() + INFO, new LoginToken(login, RequestUtil.getIp(request), request.getLocalAddr()), maxAge);
	}

	/**
	 * 获得用户信息
	 * @param request HttpServletRequest
	 * @param key 登录标识
	 * @return 用户信息
	 */
	public static LoginToken getLoginBean(HttpServletRequest request, HttpServletResponse response, String key) {
		// 读取用户信息
		String info = Conversion.toString(AttributeUtil.get(request, key + INFO));
		// 如果用户信息为空
		if (EmptyUtil.isEmpty(info)) {
			LoginToken token = new LoginToken(getGuestId(), "游客", RequestUtil.getIp(request), request.getLocalAddr());
			AttributeUtil.set(request, response, key + INFO, token, -1);
			return token;
		} else {
			return JsonEngine.toBean(info, LoginToken.class);
		}
	}

	/**
	 * 移除登录信息
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @param key 登录标识
	 */
	public static void removeLogin(HttpServletRequest request, HttpServletResponse response, String key) {
		// 销毁用户session
		// SessionUtil.close(request.getSession());
		// 写入用户信息
		AttributeUtil.remove(request, response, key + INFO);
	}

	/**
	 * 获得一样空登录信息
	 * @return
	 */
	public static LoginToken empty() {
		return EMPTY;
	}

	/**
	 * 获得游客ID
	 * @return 游客ID
	 */
	private static int getGuestId() {
		return GUEST_ID--;
	}

	/**
	 * 私有构造
	 */
	private LoginEngine() {}
}