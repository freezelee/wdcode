package org.wdcode.base.params;

import java.util.List;

import org.wdcode.common.constants.ArrayConstants;
import org.wdcode.common.lang.Lists;
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
	public final static String			PAGE_FLAG			= Params.getString("page.flag", "pager.currentPage");
	/**
	 * 分页大小
	 */
	public final static int				PAGE_SIZE			= Params.getInt("page.size", 20);
	/**
	 * 上传文件路径
	 */
	public final static String			UPLOAD_PATH			= Params.getString("upload.path", "upload/");
	/**
	 * 是否上传的资源目录
	 */
	public final static boolean			UPLOAD_RESOURCE		= Params.getBoolean("upload.resource", false);
	/**
	 * 是否使用服务器地址保存文件路径
	 */
	public final static boolean			UPLOAD_SERVER		= Params.getBoolean("upload.server", true);
	/**
	 * 是否需要上传后缀名
	 */
	public final static boolean			UPLOAD_SUFFIX		= Params.getBoolean("upload.suffix", true);
	/**
	 * 是否需要上传后缀名
	 */
	public final static List<String>	UPLOAD_POSTFIX		= Lists.getList(Params.getStringArray("upload.postfix", ArrayConstants.STRING_EMPTY));
	/**
	 * 数据源配置
	 */
	public final static String			DATA_SOURCE_CONFIG	= Params.getString("datasource.config", "classpath:db/db.properties");
	/**
	 * 静态化配置文件
	 */
	public final static String			STAICS_CONFIG		= Params.getString("staics.config", "config/statics.xml");
	/**
	 * 缓存是否有效
	 */
	public final static boolean			CACHE_VALID_POWER	= Params.getBoolean("cache.valid.power", true);
	/**
	 * 缓存类型
	 */
	public final static String			CACHE_TYPE			= Params.getString("cache.type", "map");

	/**
	 * 获得是否使用缓存<br/>
	 * 需在配置文件中配置,如果不配置或配置不对将优先使用CACHE_VALID<br/>
	 * <h2>配置方式如下: <br/>
	 * Properties: cache.valid.xxx = ? <br/>
	 * XML: {@literal <cache><valid><xxx>?</xxx></valid></cache>}</h2>
	 * @return 是否使用缓存
	 */
	public static boolean getCache(String name) {
		return Params.getBoolean(Params.getKey("cache", "valid", name), CACHE_VALID_POWER);
	}

	/**
	 * 私有构造
	 */
	private BaseParams() {}
}
