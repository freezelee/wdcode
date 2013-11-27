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

/**
 * MongoDB Dao 实现
 * @author WD
 * @since JDK7
 * @version 1.0 2010-11-21
 */
public final class MongoImpl extends BaseNoSQL implements Mongo {
	// MongoDB 主键常量
	private final static String	ID	= "_id";
	// Mongo 池连接
	private com.mongodb.Mongo	mongo;
	// MongoDB
	private DB					db;
	// 数据操作对象
	private DBCollection		dbc;

	/**
	 * 构造方法
	 * @param key 键
	 */
	public MongoImpl(String key) {
		try {
			mongo = new MongoClient(MongoParams.getHost(key), MongoParams.getPort(key));
			db = mongo.getDB(MongoParams.getDB(key));
			dbc = db.getCollection(MongoParams.getCollection(key));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 插入数据
	 * @param maps 数据对象
	 */
	public void insert(Map<String, Object>... maps) {
		// 声明DBObject数组
		DBObject[] objs = new DBObject[maps.length];
		// 循环map数组
		for (int i = 0; i < maps.length; i++) {
			// 实例化新DBObject对象
			objs[i] = new BasicDBObject(getMap(maps[i]));
		}
		// 插入数据
		dbc.insert(objs);
	}

	/**
	 * 获得数据总数量
	 * @return 数量
	 */
	public long getCount() {
		return dbc.getCount();
	}

	/**
	 * 根据查询条件获得数量
	 * @param query 查询条件
	 * @return 数量
	 */
	public long getCount(Map<String, Object> query) {
		return dbc.getCount(new BasicDBObject(query));
	}

	/**
	 * 创建索引
	 * @param keys 索引键
	 */
	public void createIndex(Map<String, Object> keys) {
		dbc.createIndex(new BasicDBObject(keys));
	}

	/**
	 * 删除索引
	 * @param name 索引名
	 */
	public void dropIndex(String name) {
		dbc.dropIndex(name);
	}

	/**
	 * 删除索引
	 * @param keys 索引键
	 */
	public void dropIndex(Map<String, Object> keys) {
		dbc.dropIndex(new BasicDBObject(keys));
	}

	/**
	 * 删除所以索引
	 */
	public void dropIndexes() {
		dbc.dropIndexes();
	}

	/**
	 * 删除所以索引
	 */
	public void delete(Map<String, Object>... maps) {
		// 循环map数组
		for (int i = 0; i < maps.length; i++) {
			// 删除对象
			dbc.remove(new BasicDBObject(getMap(maps[i])));
		}
	}

	/**
	 * 根据query参数,更新obj值
	 * @param query 条件值
	 * @param obj 要更新的值
	 */
	public void update(Map<String, Object> query, Map<String, Object> obj) {
		dbc.updateMulti(new BasicDBObject(query), new BasicDBObject(obj));
	}

	/**
	 * 查询第一个数据
	 * @return
	 */
	public Map<String, Object> get() {
		return toMap(dbc.findOne());
	}

	/**
	 * 查询第一个数据
	 * @return
	 */
	public Map<String, Object> get(Map<String, Object> map) {
		return toMap(dbc.findOne(new BasicDBObject(map)));
	}

	/**
	 * 获得所有数据
	 * @return 数据列表
	 */
	public List<Map<String, Object>> query() {
		return query(null);
	}

	/**
	 * 根据条件获得数据
	 * @param query 查询条件
	 * @return 数据列表
	 */
	public List<Map<String, Object>> query(Map<String, Object> query) {
		return query(query, 0, 0);
	}

	/**
	 * 根据条件获得 start到end的数据
	 * @param query 查询条件
	 * @param start 开始条数
	 * @param end 结束条数
	 * @return 数据列表
	 */
	public List<Map<String, Object>> query(Map<String, Object> query, int start, int end) {
		// 获得数据库游标
		DBCursor cursor = dbc.find(EmptyUtil.isEmpty(query) ? new BasicDBObject() : new BasicDBObject(query));
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
		// 返回列表
		return list;
	}

	/**
	 * 获得DBCollection
	 * @return DBCollection
	 */
	public DBCollection getCollection() {
		return dbc;
	}

	/**
	 * 如果DBObject为空返回空Map 不为空返回DBObject.toMap();
	 * @param object DBObject
	 * @return Map
	 */
	private Map<String, Object> toMap(DBObject object) {
		return EmptyUtil.isEmpty(object) ? Maps.emptyMap() : object.toMap();
	}

	/**
	 * 更换id key 键为主键_id
	 * @param map 数据Map
	 * @return 更改完Map
	 */
	private Map<String, Object> getMap(Map<String, Object> map) {
		// 判断_id为空 赋值
		if (EmptyUtil.isEmpty(map)) {
			// 获得ID
			Object key = map.get(StringConstants.KEY);
			// 判断如果为空获得 id键
			key = EmptyUtil.isEmpty(key) ? map.get(StringConstants.ID) : key;
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
		insert(map);
		// 返回成功
		return true;
	}

	@Override
	public Object get(String key) {
		return toMap(dbc.findOne(key)).get(StringConstants.VALUE);
	}

	@Override
	public void remove(String... key) {
		for (String k : key) {
			dbc.remove(new BasicDBObject(ID, k));
		}
	}

	@Override
	public boolean exists(String key) {
		return dbc.count(new BasicDBObject(ID, key)) > 0;
	}

	@Override
	public void clear() {
		dbc.drop();
	}

	@Override
	public void close() {
		dbc = null;
		db = null;
		mongo.close();
	}
}