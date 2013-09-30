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
	 * 判断是否开启权限认证
	 */
	public final static boolean	SECURITY		= Params.getBoolean("security", true);
	/**
	 * 判断是否开启权限角色认证
	 */
	public final static boolean	SECURITY_ROLE	= Params.getBoolean("security.role", false);

	/**
	 * 构造方法
	 */
	private SecurityParams() {}
}
