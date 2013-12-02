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
	public final static boolean		LOGS_LOGIN			= Params.getBoolean("logs.login", false);
	/**
	 * 操作日志是否开启
	 */
	public final static boolean		LOGS_OPERATE		= Params.getBoolean("logs.operate", false);
	/**
	 * 页面访问日志是否开启
	 */
	public final static boolean		LOGS_PAGE			= Params.getBoolean("logs.page", false);
	/**
	 * 页面访问统计是否开启
	 */
	public final static boolean		STATISTICS_PAGE		= Params.getBoolean("statistics.page", false);
	/**
	 * 登录统计是否开启
	 */
	public final static boolean		STATISTICS_LOGIN	= Params.getBoolean("statistics.login", false);
	/**
	 * 页面访问日志不过滤IP
	 */
	public final static String[]	PAGE_LOGS_IP		= Params.getStringArray("logs.page.ip", ArrayConstants.STRING_EMPTY);

	/**
	 * 私有构造
	 */
	private LogsParams() {}
}