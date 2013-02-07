package org.wdcode.core.nosql.mongodb.factory;

import org.wdcode.core.factory.FactoryKey;
import org.wdcode.core.nosql.mongodb.MongoPool;
import org.wdcode.core.params.MongoParams;

/**
 * MongoDB工厂
 * @author WD
 * @since JDK7
 * @version 1.0 2010-11-20
 */
public final class MongoPoolFactory extends FactoryKey<String, MongoPool> {
	// 工厂
	private final static MongoPoolFactory	FACTORY;

	/**
	 * 静态初始化
	 */
	static {
		FACTORY = new MongoPoolFactory();
	}

	/**
	 * 私有构造
	 */
	private MongoPoolFactory() {}

	/**
	 * 获得工厂
	 * @return 工厂
	 */
	public static MongoPoolFactory getFactory() {
		return FACTORY;
	}

	/**
	 * 实例化一个新对象
	 */
	public MongoPool newInstance(String key) {
		return newInstance(MongoParams.getHost(key), MongoParams.getPort(key));
	}

	/**
	 * 实例化一个新对象
	 * @param host 主机
	 * @param port 端口
	 * @return 新实例
	 */
	public MongoPool newInstance(String host, int port) {
		return new MongoDBFactory(host, port);
	}
}
