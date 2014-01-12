package org.wdcode.core.nosql.factory;

import org.wdcode.core.factory.FactoryKey;
import org.wdcode.core.nosql.NoSQL;

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
		return null;
	}

	private NoSQLFactory() {}
}
