package org.wdcode.core.nosql.berkeleydb;

import java.util.List;

import org.wdcode.common.interfaces.Close;

/**
 * BerkeleyDB Dao接口
 * @author WD
 * @since JDK7
 * @version 1.0 2010-11-21
 */
public interface BerkeleyDao extends Close {
	/**
	 * 添加数据
	 * @param value 值
	 */
	void add(BerkeleyBean value);

	/**
	 * 获得数据
	 * @param key 键
	 * @return 数据对象
	 */
	BerkeleyBean get(String key);

	/**
	 * 查询所有数据
	 * @return 数据列表
	 */
	List<BerkeleyBean> query();
}
