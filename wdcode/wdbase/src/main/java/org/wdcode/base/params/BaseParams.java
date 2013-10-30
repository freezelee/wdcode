package org.wdcode.base.params;

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
	public final static String	PAGE_FLAG			= Params.getString("page.flag", "pager.currentPage");
	/**
	 * 分页大小
	 */
	public final static int		PAGE_SIZE			= Params.getInt("page.size", 20);
	/**
	 * 上传文件路径
	 */
	public final static String	UPLOAD_PATH			= Params.getString("upload.path", "upload/");
	/**
	 * 是否使用服务器地址保存文件路径
	 */
	public final static boolean	UPLOAD_SERVER		= Params.getBoolean("upload.server", true);
	/**
	 * 数据源配置
	 */
	public final static String	DATA_SOURCE_CONFIG	= Params.getString("datasource.config", "classpath:db/db.properties");
	/**
	 * 静态化配置文件
	 */
	public final static String	STAICS_CONFIG		= Params.getString("staics.config", "config/statics.xml");
	/**
	 * 缓存是否有效
	 */
	public final static boolean	CACHE_VALID			= Params.getBoolean("cache.valid", true);
	/**
	 * 缓存类型
	 */
	public final static String	CACHE_TYPE			= Params.getString("cache.type", "map");

	/**
	 * 获得是否使用缓存<br/>
	 * 需在配置文件中配置,如果不配置或配置不对将优先使用CACHE_VALID<br/>
	 * <h2>配置方式如下: <br/>
	 * Properties: cache.valid.xxx = ? <br/>
	 * XML: {@literal <cache><valid><xxx>?</xxx></valid></cache>}</h2>
	 * @return 是否使用缓存
	 */
	public static boolean getCache(String name) {
		return Params.getBoolean(Params.getKey("cache", "valid", name), CACHE_VALID);
	}

	/**
	 * 私有构造
	 */
	private BaseParams() {}
}
