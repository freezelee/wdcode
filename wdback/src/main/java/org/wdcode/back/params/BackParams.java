package org.wdcode.back.params;

import org.wdcode.back.constants.BackConstants;
import org.wdcode.back.po.Admin;
import org.wdcode.common.constants.StringConstants;
import org.wdcode.common.params.Params;

/**
 * 读取后台管理配置
 * @author WD
 * @since JDK7
 * @version 1.0 2009-08-04
 */
public final class BackParams {
	/**
	 * 创建者ID
	 */
	public final static String	ADMIN			= Params.getString("admin", "admin");
	/**
	 * 操作日志是否开启
	 */
	public final static boolean	OPERATE_LOGS	= Params.getBoolean("operate.logs", false);
	/**
	 * 后台页面记录是否开启
	 */
	public final static boolean	BACK_URL		= Params.getBoolean("back.url", false);
	/**
	 * 后台主题
	 */
	public final static String	BACK_THEME		= Params.getString(BackConstants.BACK_THEME_KEY, StringConstants.DEFAULT);
	/**
	 * 后台登录Key
	 */
	public final static String	BACK_LOGIN		= Params.getString("back.login", Admin.class.getSimpleName());
	/**
	 * 后台路径
	 */
	public final static String	BACK_PATH		= Params.getString("back.path", "/back/");

	/**
	 * 构造方法
	 */
	private BackParams() {}
}
