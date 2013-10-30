package org.wdcode.core.nosql.mongodb.factory;

import java.util.List;

import org.wdcode.common.constants.StringConstants;
import org.wdcode.core.log.Logs;

import org.wdcode.core.factory.FactoryKey;
import org.wdcode.core.nosql.mongodb.MongoPool;
import org.wdcode.core.nosql.mongodb.MongoDB;
import org.wdcode.core.params.MongoParams;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;

/**
 * MongoDB 数据库实现
 * @author WD
 * @since JDK7
 * @version 1.0 2010-11-20
 */
final class MongoDBFactory extends FactoryKey<String, MongoDB> implements MongoPool {
	// Mongo 池连接
	private Mongo	mongo;

	/**
	 * 构造方法
	 * @param host 主机
	 * @param port 端口
	 */
	public MongoDBFactory(String host, int port) {
		try {
			mongo = new MongoClient(host, port);
		} catch (Exception e) {
			Logs.error(e);
		}
	}

	/**
	 * 获得MongoDB
	 * @param name 数据库名
	 * @return MongoDB
	 */
	public MongoDB getDB(String name) {
		return getInstance(name);
	}

	/**
	 * 获得所有数据库名
	 * @return 数据库名列表
	 */
	public List<String> getDataBaseNames() {
		return mongo.getDatabaseNames();
	}

	/**
	 * 获得主机
	 * @return 主机
	 */
	public String getHost() {
		return mongo.getAddress().getHost();
	}

	/**
	 * 设置端口
	 * @return 端口
	 */
	public int getPort() {
		return mongo.getAddress().getPort();
	}

	/**
	 * 删除数据库
	 * @param name 数据库名称
	 */
	public void dropDatabase(String name) {
		mongo.dropDatabase(name);
	}

	/**
	 * 获得Mongo
	 * @return Mongo
	 */
	public Mongo getMongo() {
		return mongo;
	}

	/**
	 * 关闭资源方法
	 */
	public void close() {
		mongo.close();
	}

	/**
	 * 实例化一个新对象
	 */
	public MongoDB newInstance() {
		return newInstance(MongoParams.getDB(StringConstants.EMPTY));
	}

	/**
	 * 实例化一个新对象
	 */
	public MongoDB newInstance(String dbName) {
		return new MongoDaoFactory(mongo.getDB(dbName));
	}
}
