package org.wdcode.core.nosql.mongodb.factory;

import java.util.Set;

import org.wdcode.common.constants.StringConstants;
import org.wdcode.core.factory.FactoryKey;
import org.wdcode.core.nosql.mongodb.MongoDB;
import org.wdcode.core.nosql.mongodb.MongoDao;

import com.mongodb.DB;

/**
 * MongoDB Dao实现
 * @author WD
 * @since JDK7
 * @version 1.0 2010-11-20
 */
final class MongoDaoFactory extends FactoryKey<String, MongoDao> implements MongoDB {
	// MongoDB
	private DB	db;

	/**
	 * 构造方法
	 * @param db
	 */
	public MongoDaoFactory(DB db) {
		this.db = db;
	}

	/**
	 * 添加用户
	 * @param userName 用户名
	 * @param password 密码
	 */
	public void addUser(String userName, String password) {
		db.addUser(userName, password.toCharArray());
	}

	/**
	 * 获得Dao
	 * @param name Dao名
	 * @return Dao
	 */
	public MongoDao getDao(String name) {
		return getInstance(name);
	}

	/**
	 * 获得所有Dao名称
	 * @return Dao名称
	 */
	public Set<String> getDaoNames() {
		return db.getCollectionNames();
	}

	/**
	 * 删除数据库
	 */
	public void dropDataBase() {
		db.dropDatabase();
	}

	/**
	 * 获得DB
	 * @return DB
	 */
	public DB getDB() {
		return db;
	}

	/**
	 * 实例化一个新对象
	 */
	public MongoDao newInstance() {
		return newInstance(StringConstants.EMPTY);
	}

	/**
	 * 实例化一个新对象
	 */
	public MongoDao newInstance(String name) {
		return new MongoDaoImpl(db.getCollection(name));
	}
}
