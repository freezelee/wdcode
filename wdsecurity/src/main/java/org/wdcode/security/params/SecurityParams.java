package org.wdcode.security.params;

import org.wdcode.common.params.Params;

/**
 * 读取后台管理配置
 * @author WD
 * @since JDK7
 * @version 1.0 2009-08-04
 */
public final class SecurityParams {
	/**
	 * 操作日志是否开启
	 */
	public final static boolean	OPERATE_LOGS	= Params.getBoolean("operate.logs", false);

	/**
	 * 构造方法
	 */
	private SecurityParams() {}
}
