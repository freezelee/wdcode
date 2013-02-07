package org.wdcode.base.params;

import org.wdcode.common.constants.ArrayConstants;
import org.wdcode.common.constants.StringConstants;
import org.wdcode.common.params.Params;

/**
 * WdBase包所用参数读取类
 * @author WD
 * @since JDK7
 * @version 1.0 2010-01-05
 */
public final class BaseParams {
	/**
	 * 分页使用当前页的标识
	 */
	public final static String		PAGE_FLAG				= Params.getString("page.flag", "pager.currentPage");
	/**
	 * 分页大小
	 */
	public final static int			PAGE_SIZE				= Params.getInt("page.size", 20);
	/**
	 * 上传文件路径
	 */
	public final static String		UPLOAD_PATH				= Params.getString("upload.path", "upload/images/");
	/**
	 * 数据源配置
	 */
	public final static String		DATA_SOURCE_CONFIG		= Params.getString("datasource.config", "classpath:db/db.properties");
	/**
	 * 静态化配置文件
	 */
	public final static String		STAICS_CONFIG			= Params.getString("staics.config", "config/statics.xml");
	/**
	 * 是否静态化
	 */
	public final static boolean		IS_STATICS_POWER		= Params.getBoolean("staics.power", false);
	/**
	 * 未登录跳转的主页
	 */
	public final static String		LOGIN_FILTER_INDEX		= Params.getString("login.filter.index", StringConstants.BACKSLASH);
	/**
	 * 跳过不进行过滤的
	 */
	public final static String[]	LOGIN_FILTER_SPECIAL	= Params.getStringArray("login.filter.special", ArrayConstants.STRING_EMPTY);
	/**
	 * 跳转类型
	 */
	public final static String		LOGIN_FILTER_RESULT		= Params.getString("login.filter.result", "redirect");

	/**
	 * 私有构造
	 */
	private BaseParams() {}
}
