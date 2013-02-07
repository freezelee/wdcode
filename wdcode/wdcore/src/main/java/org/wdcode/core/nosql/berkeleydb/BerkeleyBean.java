package org.wdcode.core.nosql.berkeleydb;

/**
 * BerkeleyDB 数据实体接口
 * @author WD
 * @since JDK7
 * @version 1.0 2010-11-21
 */
public interface BerkeleyBean {
	/**
	 * 获得本实体的Key
	 * @return
	 */
	String getKey();
}
