package org.wdcode.core.nosql.mongodb;

import java.util.Set;

/**
 * MongoDB DB接口
 * @author WD
 * @since JDK7
 * @version 1.0 2010-11-20
 */
public interface MongoDB {
	/**
	 * 添加用户
	 * @param userName 用户名
	 * @param password 密码
	 */
	void addUser(String userName, String password);

	/**
	 * 获得Dao
	 * @param name Dao名
	 * @return Dao
	 */
	MongoDao getDao(String name);

	/**
	 * 获得所有Dao名称
	 * @return Dao名称
	 */
	Set<String> getDaoNames();

	/**
	 * 删除数据库
	 */
	void dropDataBase();
}
