package org.wdcode.core.nosql.mongodb.factory;

import org.wdcode.core.factory.FactoryKey;
import org.wdcode.core.nosql.mongodb.Mongo;
import org.wdcode.core.nosql.mongodb.impl.MongoImpl;

/**
 * MongoDB工厂
 * @author WD
 * @since JDK7
 * @version 1.0 2013-11-23
 */
public final class MongoFactory extends FactoryKey<String, Mongo> {
	// MongoDB工厂
	private final static MongoFactory	FACTORY;
	static {
		FACTORY = new MongoFactory();
	}

	/**
	 * 获得Mongo
	 * @return
	 */
	public static Mongo getMongo() {
		return FACTORY.getInstance();
	}

	/**
	 * 获得Mongo
	 * @param name Mongo名称
	 * @return
	 */
	public static Mongo getMongo(String name) {
		return FACTORY.getInstance(name);
	}

	@Override
	public Mongo newInstance(String key) {
		return new MongoImpl(key);
	}
}
