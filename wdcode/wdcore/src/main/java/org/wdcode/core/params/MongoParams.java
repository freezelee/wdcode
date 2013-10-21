package org.wdcode.core.params;

import org.wdcode.common.params.Params;
import org.wdcode.core.constants.IpConstants;

/**
 * MongoDB配置读取
 * @author WD
 * @since JDK7
 * @version 1.0 2011-06-23
 */
public final class MongoParams {
	/* Redis使用 */
	private final static String	PREFIX; // 前缀
	private final static String	HOST;	// 服务器地址
	private final static String	PORT;	// 服务器端口
	private final static String	DB;	// 数据库名

	/**
	 * 静态初始化
	 */
	static {
		/* Redis使用 */
		PREFIX = "nosql.mongo"; // 键
		HOST = "host"; // 服务器地址
		PORT = "port"; // 服务器端口
		DB = "db";// 数据库名
	}

	/* Redis使用 */
	private static String		host;	// 服务器地址
	private static int			port;	// 服务器端口
	private static String		db;	// 数据库名

	/**
	 * 静态初始化
	 */
	static {
		host = IpConstants.LOCAL_IP; // 服务器地址
		port = 27017; // 服务器端口
		db = "wdcode";// 数据库名
	}

	/**
	 * Mongo服务器地址<br/>
	 * 需在配置文件中配置<br/>
	 * <h2>配置方式如下: <br/>
	 * Properties: nosql.mongo.host = ? <br/>
	 * XML: {@literal <nosql><mongo><host>?</host></mongo></nosql>}</h2>
	 * @return Mongo服务器地址
	 */
	public static String getHost(String name) {
		return Params.getString(getKey(name, HOST), host);
	}

	/**
	 * Mongo数据库名<br/>
	 * 需在配置文件中配置<br/>
	 * <h2>配置方式如下: <br/>
	 * Properties: nosql.mongo.db = ? <br/>
	 * XML: {@literal <nosql><mongo><db>?</db></mongo></nosql>}</h2>
	 * @return Mongo数据库名
	 */
	public static String getDB(String name) {
		return Params.getString(getKey(name, DB), db);
	}

	/**
	 * Mongo服务器端口<br/>
	 * 需在配置文件中配置<br/>
	 * <h2>配置方式如下: <br/>
	 * Properties: nosql.mongo.port = ? <br/>
	 * XML: {@literal <nosql><mongo><port>?</port></mongo></nosql>}</h2>
	 * @return Mongo服务器端口
	 */
	public static int getPort(String name) {
		return Params.getInt(getKey(name, PORT), port);
	}

	/**
	 * 用name替换键
	 * @param name 名称
	 * @param key 键
	 * @return 替换后的键
	 */
	private static String getKey(String name, String key) {
		return Params.getKey(PREFIX, name, key);
	}

	/**
	 * 私有构造
	 */
	private MongoParams() {}
}
