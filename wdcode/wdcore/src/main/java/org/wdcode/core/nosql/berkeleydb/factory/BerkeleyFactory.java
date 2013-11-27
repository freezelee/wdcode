package org.wdcode.core.nosql.berkeleydb.factory;

import org.wdcode.core.factory.FactoryKey;
import org.wdcode.core.nosql.berkeleydb.Berkeley;
import org.wdcode.core.nosql.berkeleydb.impl.BerkeleyImpl;

/**
 * Berkeley工厂
 * @author WD
 * @since JDK7
 * @version 1.0 2013-11-27
 */
public final class BerkeleyFactory extends FactoryKey<String, Berkeley> {
	// 工厂
	private final static BerkeleyFactory	FACTORY;
	static {
		FACTORY = new BerkeleyFactory();
	}

	/**
	 * 获得Berkeley
	 * @return Berkeley
	 */
	public static Berkeley getBerkeley() {
		return FACTORY.getInstance();
	}

	@Override
	public Berkeley newInstance(String key) {
		return new BerkeleyImpl(key);
	}
}
