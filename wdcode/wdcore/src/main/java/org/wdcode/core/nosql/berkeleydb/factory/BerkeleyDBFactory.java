package org.wdcode.core.nosql.berkeleydb.factory;

import org.wdcode.core.factory.FactoryKey;
import org.wdcode.core.nosql.berkeleydb.BerkeleyDB;
import org.wdcode.core.params.CoreParams;

/**
 * BerkeleyDB 工厂
 * @author WD
 * @since JDK7
 * @version 1.0 2010-11-21
 */
public final class BerkeleyDBFactory extends FactoryKey<String, BerkeleyDB> {
	// 工厂
	private final static BerkeleyDBFactory	FACTORY;

	/**
	 * 静态初始化
	 */
	static {
		FACTORY = new BerkeleyDBFactory();
	}

	/**
	 * 私有构造
	 */
	private BerkeleyDBFactory() {}

	/**
	 * 获得工厂
	 * @return 工厂
	 */
	public static BerkeleyDBFactory getFactory() {
		return FACTORY;
	}

	/**
	 * 实例化一个新对象
	 */
	public BerkeleyDB newInstance(String resource) {
		return new BerkeleyDaoFactory(resource);
	}

	/**
	 * 实例化一个新对象
	 */
	public BerkeleyDB newInstance() {
		return newInstance(CoreParams.NOSQL_BERKELEYDB_RESOURCE);
	}
}
