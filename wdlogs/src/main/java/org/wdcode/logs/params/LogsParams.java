package org.wdcode.logs.params;

import org.wdcode.common.constants.ArrayConstants;
import org.wdcode.common.params.Params;

/**
 * WdLogs包参数读取类
 * @author WD
 * @since JDK7
 * @version 1.0 2010-11-02
 */
public final class LogsParams {
	/**
	 * 登录日志是否开启
	 */
	public final static boolean		LOGIN_LOGS			= Params.getBoolean("login.logs", false);
	/**
	 * 登录统计是否开启
	 */
	public final static boolean		LOGIN_STATISTICS	= Params.getBoolean("login.statistics", false);
	/**
	 * 页面访问日志是否开启
	 */
	public final static boolean		PAGE_LOGS			= Params.getBoolean("page.logs", false);
	/**
	 * 页面访问日志不过滤IP
	 */
	public final static String[]	PAGE_LOGS_IP		= Params.getStringArray("page.logs.ip", ArrayConstants.STRING_EMPTY);
	/**
	 * 页面访问统计是否开启
	 */
	public final static boolean		PAGE_STATISTICS		= Params.getBoolean("page.statistics", false);
	/**
	 * 操作日志是否开启
	 */
	public final static boolean		OPERATE_LOGS		= Params.getBoolean("operate.logs", false);

	/**
	 * 私有构造
	 */
	private LogsParams() {}
}