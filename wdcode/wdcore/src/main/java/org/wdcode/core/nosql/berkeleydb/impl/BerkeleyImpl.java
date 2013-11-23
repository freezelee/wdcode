package org.wdcode.core.nosql.berkeleydb.impl;

import java.io.File;
import java.util.List;

import org.wdcode.common.lang.Lists;
import org.wdcode.core.log.Logs;
import org.wdcode.common.util.EmptyUtil;
import org.wdcode.core.nosql.base.BaseNoSQL;
import org.wdcode.core.nosql.berkeleydb.Berkeley;

import com.sleepycat.bind.EntryBinding;
import com.sleepycat.bind.serial.SerialBinding;
import com.sleepycat.bind.serial.StoredClassCatalog;
import com.sleepycat.je.Cursor;
import com.sleepycat.je.CursorConfig;
import com.sleepycat.je.Database;
import com.sleepycat.je.DatabaseEntry;
import com.sleepycat.je.Environment;
import com.sleepycat.je.EnvironmentConfig;
import com.sleepycat.je.LockMode;
import com.sleepycat.je.OperationStatus;

/**
 * BerkeleyDao 实现
 * @author WD
 * @since JDK7
 * @version 1.0 2010-11-21
 */
public final class BerkeleyImpl extends BaseNoSQL implements Berkeley {
	// 声明Database
	private Database				db;
	// 声明StoredClassCatalog
	private StoredClassCatalog		catalog;
	// 声明EntryBinding
	private EntryBinding<Object>	dataBinding;
	// 声明Environment
	private Environment				env;

	/**
	 * 构造方法
	 * @param resource
	 */
	public BerkeleyImpl(String resource) {
		env = new Environment(new File(resource), new EnvironmentConfig());
		db = env.openDatabase(null, "wd", null);
		catalog = new StoredClassCatalog(db);
		dataBinding = new SerialBinding<Object>(catalog, Object.class);
	}

	/**
	 * 获得数据
	 * @param key 键
	 * @return 数据对象
	 */
	public Object get(String key) {
		// 实例化数据库Entry
		DatabaseEntry dataEntry = new DatabaseEntry();
		// 查询数据
		db.get(null, getKey(key), dataEntry, LockMode.DEFAULT);
		// 返回对象
		return dataBinding.entryToObject(dataEntry);
	}

	/**
	 * 查询所有数据
	 * @return 数据列表
	 */
	public List<Object> query() {
		// 声明游标
		Cursor cursor = null;
		// 声明List
		List<Object> list = Lists.getList();
		try {
			// 打开游标
			cursor = db.openCursor(null, new CursorConfig());
			// 声明Key
			DatabaseEntry foundKey = new DatabaseEntry();
			// 声明Data
			DatabaseEntry foundData = new DatabaseEntry();
			// 循环游标
			while (cursor.getNext(foundKey, foundData, LockMode.DEFAULT) == OperationStatus.SUCCESS) {
				// 添加到列表中
				list.add(dataBinding.entryToObject(foundData));
			}
		} catch (Exception e) {
			Logs.error(e);
		} finally {
			cursor.close();
		}
		// 返回一个空列表
		return Lists.emptyList();
	}

	@Override
	public boolean set(String key, Object value) {
		db.put(null, getKey(key), objectToEntry(value));
		return true;
	}

	@Override
	public void remove(String... key) {}

	@Override
	public boolean exists(String key) {
		return false;
	}

	@Override
	public void clear() {}

	/**
	 * 关闭资源
	 */
	public void close() {
		// 关闭StoredClassCatalog
		try {
			if (!EmptyUtil.isEmpty(catalog)) {
				catalog.close();
			}
		} catch (Exception e) {
			Logs.warn(e);
		} finally {
			catalog = null;
		}
		// 关闭Database
		try {
			if (!EmptyUtil.isEmpty(db)) {
				db.close();
			}
		} catch (Exception e) {
			Logs.warn(e);
		} finally {
			db = null;
		}
		// 关闭env
		try {
			if (!EmptyUtil.isEmpty(env)) {
				env.close();
			}
		} catch (Exception e) {
			Logs.warn(e);
		} finally {
			env = null;
		}

	}

	/**
	 * 获得数据库Key
	 * @param key 字符串Key
	 * @return 数据库Key
	 */
	private DatabaseEntry getKey(String key) {
		return new DatabaseEntry(key.getBytes());
	}

	/**
	 * 对象转换成数据库Entry
	 * @param value 对象
	 * @return 数据库Entry
	 */
	private DatabaseEntry objectToEntry(Object value) {
		// 实例化数据库Entry
		DatabaseEntry dataEntry = new DatabaseEntry();
		// 转换类型
		dataBinding.objectToEntry(value, dataEntry);
		// 返回数据库Entry
		return dataEntry;
	}
}
