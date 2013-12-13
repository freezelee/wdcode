package org.wdcode.core.nosql.mongodb.impl;

import java.util.List;
import java.util.Map;

import org.wdcode.common.constants.StringConstants;
import org.wdcode.common.lang.Lists;
import org.wdcode.common.lang.Maps;
import org.wdcode.common.util.EmptyUtil;
import org.wdcode.core.nosql.base.BaseNoSQL;
import org.wdcode.core.nosql.mongodb.Mongo;
import org.wdcode.core.params.MongoParams;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoOptions;
import com.mongodb.WriteConcern;

/**
 * MongoDB Dao 实现
 * @author WD
 * @since JDK7
 * @version 1.0 2010-11-21
 */
public final class MongoImpl extends BaseNoSQL implements Mongo {
	// MongoDB 主键常量
	private final static String	ID		= "_id";
	// 默认键值保存集合
	private final static String	KEYS	= "keys";
	// Mongo 池连接
	private com.mongodb.Mongo	mongo;
	// MongoDB
	private DB					db;

	/**
	 * 构造方法
	 * @param key 键
	 */
	public MongoImpl(String key) {
		try {
			mongo = new MongoClient(MongoParams.getHost(key), MongoParams.getPort(key));
			// 设置连接池
			MongoOptions options = mongo.getMongoOptions();
			options.setConnectionsPerHost(100);
			options.setThreadsAllowedToBlockForConnectionMultiplier(1000);
			options.setWriteConcern(WriteConcern.ERRORS_IGNORED);
			options.setAutoConnectRetry(true);
			options.setMaxWaitTime(120000);
			options.setConnectTimeout(30000);
			options.setSocketTimeout(30000);
			// 初始化数据库
			db = mongo.getDB(MongoParams.getDB(key));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 插入数据
	 * @param maps 数据对象
	 */
	public void insert(String name, Map<String, Object> maps) {
		db.requestStart();
		db.getCollection(name).insert(new BasicDBObject(getMap(maps)));
		db.requestDone();

	}

	/**
	 * 插入数据
	 * @param maps 数据对象
	 */
	public void insert(String name, Map<String, Object>... maps) {
		db.requestStart();
		// 声明DBObject数组
		DBObject[] objs = new DBObject[maps.length];
		// 循环map数组
		for (int i = 0; i < maps.length; i++) {
			// 实例化新DBObject对象
			objs[i] = new BasicDBObject(getMap(maps[i]));
		}
		// 插入数据
		db.getCollection(name).insert(objs);
		db.requestDone();
	}

	/**
	 * 获得数据总数量
	 * @return 数量
	 */
	public long count(String name) {
		db.requestStart();
		long count = db.getCollection(name).getCount();
		db.requestDone();
		return count;
	}

	/**
	 * 根据查询条件获得数量
	 * @param query 查询条件
	 * @return 数量
	 */
	public long count(String name, Map<String, Object> query) {
		db.requestStart();
		long count = db.getCollection(name).getCount(new BasicDBObject(query));
		db.requestDone();
		return count;
	}

	/**
	 * 创建索引
	 * @param keys 索引键
	 */
	public void createIndex(String name, Map<String, Object> keys) {
		db.requestStart();
		db.getCollection(name).createIndex(new BasicDBObject(keys));
		db.requestDone();
	}

	/**
	 * 删除索引
	 * @param name 索引名
	 */
	public void dropIndex(String name, String index) {
		db.requestStart();
		db.getCollection(name).dropIndex(index);
		db.requestDone();
	}

	/**
	 * 删除索引
	 * @param keys 索引键
	 */
	public void dropIndex(String name, Map<String, Object> keys) {
		db.requestStart();
		db.getCollection(name).dropIndex(new BasicDBObject(keys));
		db.requestDone();
	}

	/**
	 * 删除所以索引
	 */
	public void dropIndexes(String name) {
		db.requestStart();
		db.getCollection(name).dropIndexes();
		db.requestDone();
	}

	/**
	 * 删除数据
	 */
	public void delete(String name, Map<String, Object>... maps) {
		db.requestStart();
		// 获得数据集合
		DBCollection dbc = db.getCollection(name);
		// 循环map数组
		for (int i = 0; i < maps.length; i++) {
			// 删除对象
			dbc.remove(new BasicDBObject(getMap(maps[i])));
		}
		db.requestDone();
	}

	/**
	 * 删除数据
	 */
	public void delete(String name, Map<String, Object> data) {
		db.requestStart();
		db.getCollection(name).remove(new BasicDBObject(getMap(data)));
		db.requestDone();
	}

	/**
	 * 根据query参数,更新obj值
	 * @param query 条件值
	 * @param obj 要更新的值
	 */
	public void update(String name, Map<String, Object> query, Map<String, Object> obj) {
		db.requestStart();
		db.getCollection(name).update(new BasicDBObject(query), new BasicDBObject(obj));
		db.requestDone();
	}

	/**
	 * 根据query参数,更新obj值
	 * @param query 条件值
	 * @param obj 要更新的值
	 */
	public void updateMulti(String name, Map<String, Object> query, Map<String, Object> obj) {
		db.requestStart();
		db.getCollection(name).updateMulti(new BasicDBObject(query), new BasicDBObject(obj));
		db.requestDone();

	}

	/**
	 * 获得所有数据
	 * @return 数据列表
	 */
	public List<Map<String, Object>> query(String name) {
		return query(name, null);
	}

	/**
	 * 根据条件获得数据
	 * @param query 查询条件
	 * @return 数据列表
	 */
	public List<Map<String, Object>> query(String name, Map<String, Object> query) {
		return query(name, query, 0, 0);
	}

	/**
	 * 根据条件获得 start到end的数据
	 * @param query 查询条件
	 * @param start 开始条数
	 * @param end 结束条数
	 * @return 数据列表
	 */
	public List<Map<String, Object>> query(String name, Map<String, Object> query, int start, int end) {
		db.requestStart();
		// 获得数据库游标
		DBCursor cursor = db.getCollection(name).find(EmptyUtil.isEmpty(query) ? new BasicDBObject() : new BasicDBObject(query));
		// 设置游标开始位置
		cursor.skip(start);
		// 设置限定数量
		cursor.limit(end - start);
		// 获得列表
		List<Map<String, Object>> list = Lists.getList();
		// 设置游标开始位置

		// 循环游标
		while (cursor.hasNext()) {
			// 添加到列表中
			list.add(toMap(cursor.next()));
		}
		db.requestDone();
		// 返回列表
		return list;
	}

	/**
	 * 如果DBObject为空返回空Map 不为空返回DBObject.toMap();
	 * @param object DBObject
	 * @return Map
	 */
	private Map<String, Object> toMap(DBObject object) {
		return EmptyUtil.isEmpty(object) ? Maps.getMap() : object.toMap();
	}

	/**
	 * 更换id key 键为主键_id
	 * @param map 数据Map
	 * @return 更改完Map
	 */
	private Map<String, Object> getMap(Map<String, Object> map) {
		// 判断_id为空 赋值
		if (!EmptyUtil.isEmpty(map)) {
			// 获得ID
			Object key = map.get(ID);
			// 判断如果为空获得 id键
			key = EmptyUtil.isEmpty(key) ? map.get(StringConstants.KEY) : key;
			// 设置主键
			map.put(ID, key);
		}
		// 返回Map
		return map;
	}

	@Override
	public boolean set(String key, Object value) {
		// 获得Map
		Map<String, Object> map = Maps.getMap();
		// 设置键值
		map.put(ID, key);
		map.put(StringConstants.VALUE, value);
		// 添加数据
		insert(StringConstants.EMPTY, map);
		// 返回成功
		return true;
	}

	@Override
	public Object get(String key) {
		db.requestStart();
		Object value = toMap(db.getCollection(KEYS).findOne(key)).get(StringConstants.VALUE);
		db.requestDone();
		return value;
	}

	@Override
	public void remove(String... key) {
		db.requestStart();
		// 获得数据集合
		DBCollection dbc = db.getCollection(KEYS);
		// 循环删除
		for (String k : key) {
			dbc.remove(new BasicDBObject(ID, k));
		}
		db.requestDone();
	}

	@Override
	public long count(String name, Object key) {
		return count(name, Maps.getMap(ID, key));
	}

	@Override
	public Map<String, Object> get(String name, Object key) {
		db.requestStart();
		Map<String, Object> map = toMap(db.getCollection(name).findOne(new BasicDBObject(ID, key)));
		db.requestDone();
		return map;
	}

	@Override
	public Map<String, Object> get(String name, Map<String, Object> query) {
		db.requestStart();
		Map<String, Object> map = toMap(db.getCollection(name).findOne(new BasicDBObject(query)));
		db.requestDone();
		return map;
	}

	@Override
	public boolean exists(String key) {
		db.requestStart();
		long count = db.getCollection(KEYS).count(new BasicDBObject(ID, key));
		db.requestDone();
		return count > 0;
	}

	@Override
	public void clear() {
		db.requestStart();
		db.getCollection(KEYS).drop();
		db.requestDone();
	}

	@Override
	public void close() {
		db = null;
		mongo.close();
	}
}