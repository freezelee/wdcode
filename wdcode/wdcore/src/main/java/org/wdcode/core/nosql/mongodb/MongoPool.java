package org.wdcode.core.nosql.mongodb;

import java.util.List;

import org.wdcode.common.interfaces.Close;

/**
 * MongoDB 池接口
 * @author WD
 * @since JDK7
 * @version 1.0 2010-11-20
 */
public interface MongoPool extends Close {
	/**
	 * 获得MongoDB
	 * @param name 数据库名
	 * @return MongoDB
	 */
	MongoDB getDB(String name); 
	
	/**
	 * 获得所有数据库名
	 * @return 数据库名列表
	 */
	List<String> getDataBaseNames();

	/**
	 * 获得主机
	 * @return 主机
	 */
	String getHost();

	/**
	 * 设置端口
	 * @return 端口
	 */
	int getPort();

	/**
	 * 删除数据库
	 * @param name 数据库名称
	 */
	void dropDatabase(String name);
}
