package org.wdcode.security.params;

import java.util.List;

import org.wdcode.common.constants.ArrayConstants;
import org.wdcode.common.lang.Lists;
import org.wdcode.common.params.Params;

/**
 * 读取后台管理配置
 * @author WD
 * @since JDK7
 * @version 1.0 2009-08-04
 */
public final class SecurityParams {
	/**
	 * 判断是否开启权限认证
	 */
	public final static boolean			SECURITY_POWER	= Params.getBoolean("security.power", true);
	/**
	 * 判断是否开启IP认证
	 */
	public final static boolean			SECURITY_IP		= Params.getBoolean("security.ip", false);
	/**
	 * ip过滤列表
	 */
	public final static List<String>	SECURITY_IPS	= Params.getList("security.ips", Lists.getList(ArrayConstants.STRING_EMPTY));
	/**
	 * 判断是否开启权限角色认证
	 */
	public final static boolean			SECURITY_ROLE	= Params.getBoolean("security.role", true);
	/**
	 * 判断是否开启操作类型认证
	 */
	public final static int				SECURITY_TYPE	= Params.getInt("security.type", 0);

	/**
	 * 构造方法
	 */
	private SecurityParams() {}
}
