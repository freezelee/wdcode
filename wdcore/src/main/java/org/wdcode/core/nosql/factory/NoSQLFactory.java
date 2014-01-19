package org.wdcode.core.nosql.factory;

import org.wdcode.core.factory.FactoryKey;
import org.wdcode.core.nosql.NoSQL;
import org.wdcode.core.nosql.berkeley.factory.BerkeleyFactory;
import org.wdcode.core.nosql.hbase.factory.HBaseFactory;
import org.wdcode.core.nosql.memcache.factory.MemcacheFactory;
import org.wdcode.core.nosql.mongo.factory.MongoFactory;
import org.wdcode.core.nosql.redis.factory.RedisFactory;
import org.wdcode.core.params.NoSQLParams;

/**
 * NoSQL工厂
 * @author WD
 * @since JDK7
 * @version 1.0 2012-11-18
 */
public final class NoSQLFactory extends FactoryKey<String, NoSQL> {
	// 工厂
	private final static NoSQLFactory	FACTORY;
	static {
		FACTORY = new NoSQLFactory();
	}

	/**
	 * 获得NoSQL接口
	 * @param name 配置名
	 * @return NoSQL接口
	 */
	public NoSQL getNoSQL(String name) {
		return FACTORY.getInstance(name);
	}

	@Override
	public NoSQL newInstance(String key) {
		switch (NoSQLParams.getParse(key)) {
			case "memcache":
				return MemcacheFactory.getMemcache(key);
			case "hbase":
				return HBaseFactory.getHBase(key);
			case "mongodb":
				return MongoFactory.getMongo(key);
			case "redis":
				return RedisFactory.getRedis(key);
			case "berkeleydb":
				return BerkeleyFactory.getBerkeley(key);
		}
		return null;
	}

	private NoSQLFactory() {}
}
