package org.wdcode.core.params;

import org.wdcode.common.constants.StringConstants;
import org.wdcode.common.constants.SystemConstants;
import org.wdcode.common.constants.ZipConstants;
import org.wdcode.common.params.CommonParams;
import org.wdcode.common.params.Params;
import org.wdcode.core.constants.XmlConstants;

/**
 * WdCore包所用参数读取类
 * @author WD
 * @since JDK7
 * @version 1.0 2010-01-05
 */
public final class CoreParams {
	/**
	 * 数据使用格式
	 */
	public final static String	DATA_FORMAT					= Params.getString("data.format");
	/**
	 * Xml的根节点名称
	 */
	public final static String	XML_ROOT					= Params.getString("xml.root", XmlConstants.NODE_ROOT);
	/**
	 * Xml的编码格式
	 */
	public final static String	XML_ENCODING				= Params.getString("xml.encoding", CommonParams.ENCODING);
	/**
	 * 缓存是否有效
	 */
	public final static boolean	CACHE_VALID					= Params.getBoolean("cache.valid", true);
	/**
	 * 线程池大小
	 */
	public final static int		THREAD_SIZE					= Params.getInt("thread.size", SystemConstants.CPU_NUM + 1);
	/**
	 * XML解析所用的包
	 */
	public final static String	XML_PARSE					= Params.getString("xml.parse", XmlConstants.PARSE_DOM4J);
	/**
	 * 压缩算法
	 */
	public final static String	ZIP							= Params.getString(ZipConstants.ZIP, ZipConstants.ZLIB);
	/**
	 * BerkeleyDB资源文件
	 */
	public final static String	NOSQL_BERKELEYDB_RESOURCE	= Params.getString("nosql.berkeleydb.resource", StringConstants.EMPTY);
	/**
	 * MongoDB主机
	 */
	public final static String	NOSQL_HBASE_HOST			= Params.getString("nosql.hbase.host", "127.0.0.1");
	/**
	 * MongoDB端口
	 */
	public final static int		NOSQL_HBASE_PORT			= Params.getInt("nosql.hbase.port", 2181);
	/**
	 * 默认解析Excel所需要的包
	 */
	public final static String	EXCEL_PARSE					= Params.getString("excel.parse", "poi");
	/**
	 * 解析JSON所需要的包
	 */
	public final static String	JSON_PARSE					= Params.getString("json.parse", "json-lib");
	/**
	 * Lucene索引保存位置
	 */
	public final static String	LUCENE_INDEX_PATH			= Params.getString("lucene.index.path");

	/**
	 * 私有构造
	 */
	private CoreParams() {}
}
