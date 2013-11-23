package org.wdcode.core.nosql.mongodb;

import java.util.List;
import java.util.Map;

import org.wdcode.core.nosql.NoSQL;

/**
 * MongoDB Dao接口
 * @author WD
 * @since JDK7
 * @version 1.0 2010-11-21
 */
public interface Mongo extends NoSQL {
	/**
	 * 插入数据
	 * @param maps 数据对象
	 */
	void insert(Map<String, Object>... maps);

	/**
	 * 获得数据总数量
	 * @return 数量
	 */
	long getCount();

	/**
	 * 根据查询条件获得数量
	 * @param query 查询条件
	 * @return 数量
	 */
	long getCount(Map<String, Object> query);

	/**
	 * 创建索引
	 * @param keys 索引键
	 */
	void createIndex(Map<String, Object> keys);

	/**
	 * 删除索引
	 * @param name 索引名
	 */
	void dropIndex(String name);

	/**
	 * 删除索引
	 * @param keys 索引键
	 */
	void dropIndex(Map<String, Object> keys);

	/**
	 * 删除所以索引
	 */
	void dropIndexes();

	/**
	 * 删除所以索引
	 */
	void delete(Map<String, Object>... maps);

	/**
	 * 根据query参数,更新obj值
	 * @param query 条件值
	 * @param obj 要更新的值
	 */
	void update(Map<String, Object> query, Map<String, Object> obj);

	/**
	 * 查询第一个数据
	 * @return
	 */
	Map<String, Object> get();

	/**
	 * 根据条件查询出第一个数据
	 * @param map 查询条件
	 * @return
	 */
	Map<String, Object> get(Map<String, Object> map);

	/**
	 * 获得所有数据
	 * @return 数据列表
	 */
	List<Map<String, Object>> query();

	/**
	 * 根据条件获得数据
	 * @param query 查询条件
	 * @return 数据列表
	 */
	List<Map<String, Object>> query(Map<String, Object> query);

	/**
	 * 根据条件获得 start到end的数据
	 * @param query 查询条件
	 * @param start 开始条数
	 * @param end 结束条数
	 * @return 数据列表
	 */
	List<Map<String, Object>> query(Map<String, Object> query, int start, int end);
}
