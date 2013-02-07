package org.wdcode.core.nosql.berkeleydb;

import org.wdcode.common.interfaces.Close;

/**
 * BerkeleyDB 接口
 * @author WD
 * @since JDK7
 * @version 1.0 2010-11-21
 */
public interface BerkeleyDB extends Close {
	/**
	 * 获得Dao
	 * @param name Dao 名
	 * @return Dao
	 */
	BerkeleyDao getDao(String name);
}
