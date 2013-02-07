package org.wdcode.core.constants;

/**
 * 数据库常量
 * @author WD
 * @since JDK7
 * @version 1.0 2009-11-21
 */
public final class DataBaseConstants { 
	/**
	 * 数据库连接模式 jndi
	 */
	public final static String	CONNECTION_MODE_JNDI		= "jndi";
	/**
	 * 数据库连接模式 dataSource
	 */
	public final static String	CONNECTION_MODE_DATASOURCE	= "dataSource";
	/**
	 * 数据源DBCP
	 */
	public final static String	DATABASE_TYPE_DBCP			= "dbcp";
	/**
	 * 数据源C3P0
	 */
	public final static String	DATABASE_TYPE_C3P0			= "c3p0";
	/**
	 * 数据源PROXOOL
	 */
	public final static String	DATABASE_TYPE_PROXOOL		= "proxool";
	/**
	 * 数据源bonecp
	 */
	public final static String	DATABASE_TYPE_BONECP		= "bonecp";
	/**
	 * MySQL Driver
	 */
	public final static String	MYSQL_DRIVER				= "com.mysql.jdbc.Driver";
	/**
	 * Hsqldb Driver
	 */
	public final static String	HSQLDB_DRIVER				= "org.hsqldb.jdbcDriver";
	/**
	 * Sqlite Driver
	 */
	public final static String	SQLITE_DRIVER				= "org.sqlite.JDBC";
	/**
	 * MySQL URL
	 */
	public final static String	MYSQL_URL					= "jdbc:mysql://${host}:${port}/${dbName}?useLocalSessionState=true";
	/**
	 * Hsqldb Service URL
	 */
	public final static String	HSQLDB_URL_SERVICE			= "jdbc:hsqldb:hsql://${host}:${port}/${dbName}";
	/**
	 * Hsqldb mem URL
	 */
	public final static String	HSQLDB_URL_MEM				= "jdbc:hsqldb:mem:${dbName}";
	/**
	 * Sqlite URL
	 */
	public final static String	SQLITE_URL					= "jdbc:sqlite:${fileName}";

	/**
	 * 私有构造禁止外部实例化
	 */
	private DataBaseConstants() {}
}
