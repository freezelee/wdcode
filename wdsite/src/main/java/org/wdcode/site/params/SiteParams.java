package org.wdcode.site.params;

import org.wdcode.common.constants.ArrayConstants;
import org.wdcode.common.constants.DateConstants;
import org.wdcode.common.lang.Conversion;
import org.wdcode.common.params.Params;

/**
 * WdLogs包参数读取类
 * @author WD
 * @since JDK7
 * @version 1.0 2010-11-02
 */
public final class SiteParams {
	/**
	 * email验证是否开启
	 */
	public final static boolean		USER_VERIFY_EMAIL			= Params.getBoolean("user.verify.email", false);
	/**
	 * email验证Action
	 */
	public final static String		USER_VERIFY_EMAIL_ACTION	= Params.getString("user.verify.email.action");
	/**
	 * email验证标题
	 */
	public final static String		USER_VERIFY_EMAIL_SUBJECT	= Params.getString("user.verify.email.subject");
	/**
	 * email验证内容
	 */
	public final static String		USER_VERIFY_EMAIL_CONTENT	= Params.getString("user.verify.email.content");
	/**
	 * email验证替换url
	 */
	public final static String		USER_VERIFY_EMAIL_URL		= Params.getString("user.verify.email.url");
	/**
	 * email验证替换用户名
	 */
	public final static String		USER_VERIFY_EMAIL_NAME		= Params.getString("user.verify.email.name");

	/**
	 * 登录信息最大保存时间
	 */
	public final static int			LOGIN_MAX_AGE				= Params.getInt("login.maxAge", Conversion.toInt(DateConstants.TIME_WEEK / 1000));
	/**
	 * 登录信息最小保存时间
	 */
	public final static int			LOGIN_MIN_AGE				= Params.getInt("login.minAge", Conversion.toInt(DateConstants.TIME_HOUR / 1000) / 2);
	/**
	 * 登录日志是否开启
	 */
	public final static boolean		LOGIN_LOGS					= Params.getBoolean("login.logs", false);
	/**
	 * 登录统计是否开启
	 */
	public final static boolean		LOGIN_STATISTICS			= Params.getBoolean("login.statistics", false);
	/**
	 * 页面访问日志是否开启
	 */
	public final static boolean		PAGE_LOGS					= Params.getBoolean("page.logs", false);
	/**
	 * 页面访问日志不过滤IP
	 */
	public final static String[]	PAGE_LOGS_IP				= Params.getStringArray("page.logs.ip", ArrayConstants.STRING_EMPTY);
	/**
	 * 页面访问统计是否开启
	 */
	public final static boolean		PAGE_STATISTICS				= Params.getBoolean("page.statistics", false);
	/**
	 * 是否静态化
	 */
	public final static boolean		IS_STATICS_POWER			= Params.getBoolean("staics.power", false);

	/**
	 * 私有构造
	 */
	private SiteParams() {}
}