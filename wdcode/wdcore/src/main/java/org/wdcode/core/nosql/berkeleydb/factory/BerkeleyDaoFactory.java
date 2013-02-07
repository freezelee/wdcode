package org.wdcode.core.nosql.berkeleydb.factory;

import java.io.File;
import java.util.Map;

import org.wdcode.common.constants.StringConstants;
import org.wdcode.common.log.Logs;
import org.wdcode.common.util.EmptyUtil;
import org.wdcode.core.factory.FactoryKey;
import org.wdcode.core.nosql.berkeleydb.BerkeleyDB;
import org.wdcode.core.nosql.berkeleydb.BerkeleyDao;

import com.sleepycat.je.Environment;
import com.sleepycat.je.EnvironmentConfig;

/**
 * BerkeleyDB Dao操作实现
 * @author WD
 * @since JDK7
 * @version 1.0 2010-11-21
 */
final class BerkeleyDaoFactory extends FactoryKey<String, BerkeleyDao> implements BerkeleyDB {
	// 声明Environment
	private Environment	env;

	/**
	 * 构造方法
	 */
	public BerkeleyDaoFactory(String resource) {
		env = new Environment(new File(resource), new EnvironmentConfig());
	}

	/**
	 * 获得Dao
	 * @param name Dao 名
	 * @return Dao
	 */
	public BerkeleyDao getDao(String name) {
		return getInstance();
	}

	/**
	 * 关闭资源
	 */
	public void close() {
		// 循环关闭资源
		try {
			for (Map.Entry<String, BerkeleyDao> e : map.entrySet()) {
				e.getValue().close();
			}
		} catch (Exception e) {
			Logs.error(e);
		} finally {
			map.clear();
		}
		// 关闭Environment
		try {
			if (!EmptyUtil.isEmpty(env)) {
				env.cleanLog();
				env.close();
			}
		} catch (Exception e) {
			Logs.error(e);
		} finally {
			env = null;
		}
	}

	/**
	 * 实例化一个新对象
	 */
	public BerkeleyDao newInstance(String name) {
		return new BerkeleyDaoImpl(env.openDatabase(null, name, null));
	}

	/**
	 * 实例化一个新对象
	 */
	public BerkeleyDao newInstance() {
		return newInstance(StringConstants.EMPTY);
	}
}