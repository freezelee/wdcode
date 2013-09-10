package org.wdcode.site.token;

/**
 * 获得当前网站用户 可获得是是否登录 和用户基本信息接口
 * @author WD
 * @since JDK7
 * @version 1.0 2010-10-13
 */
public interface AuthToken {
	/**
	 * 是否登录 不验证是IP和登录时间
	 * @return true 登录 false 未登录
	 */
	boolean isLogin();

	/**
	 * 获得用户ID
	 * @return 用户ID
	 */
	int getId();

	/**
	 * 获得用户名
	 * @return 用户名
	 */
	String getName();

	/**
	 * 获得登录时间
	 * @return 登录时间
	 */
	int getTime();
}
