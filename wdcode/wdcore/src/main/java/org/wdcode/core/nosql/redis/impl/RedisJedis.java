package org.wdcode.core.nosql.redis.impl;

import org.wdcode.common.lang.Bytes;
import org.wdcode.core.nosql.base.BaseNoSQL;
import org.wdcode.core.nosql.redis.Redis;
import org.wdcode.core.params.RedisParams;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Redis客户端Jedis实现
 * @author WD
 * @since JDK7
 * @version 1.0 2011-06-23
 */
public final class RedisJedis extends BaseNoSQL implements Redis {
	// Jedis连接池
	private JedisPool	pool;

	public RedisJedis(String name) {
		// 实例化Jedis配置
		JedisPoolConfig config = new JedisPoolConfig();
		// 设置属性
		config.setMaxActive(RedisParams.getMaxActive(name));
		config.setMaxIdle(RedisParams.getMaxIdle(name));
		config.setMaxWait(RedisParams.getMaxWait(name));
		// 实例化连接池
		pool = new JedisPool(config, RedisParams.getHost(name), RedisParams.getPort(name));
	}

	/**
	 * 关闭资源方法
	 */
	public void close() {
		pool.destroy();
	}

	/**
	 * 设置键值 无论存储空间是否存在相同键 都保存 对象将变成字节数组村储存 需要对象能序列化或则能转变为json
	 * @param key 键
	 * @param value 值
	 */
	public void set(String key, Object value) {
		// 获得Jedis对象
		Jedis jedis = pool.getResource();
		// 设置值
		jedis.set(Bytes.toBytes(key), Bytes.toBytes(value));
		// 回收Jedis
		pool.returnResource(jedis);
	}

	/**
	 * 根据键获得值 值都是字节数组
	 * @param key 键
	 * @return 值
	 */
	public byte[] get(String key) {
		// 获得Jedis对象
		Jedis jedis = pool.getResource();
		// 设置值
		byte[] b = jedis.get(Bytes.toBytes(key));
		// 回收Jedis
		pool.returnResource(jedis);
		// 返回值
		return b;
	}

	/**
	 * 删除键值
	 * @param key 键
	 */
	public void remove(String... key) {
		// 获得Jedis对象
		Jedis jedis = pool.getResource();
		// 设置值
		jedis.del(key);
		// 回收Jedis
		pool.returnResource(jedis);
	}

	/**
	 * 验证键是否存在
	 * @param key
	 * @return true 存在 false 不存在
	 */
	public boolean exists(String key) {
		// 获得Jedis对象
		Jedis jedis = pool.getResource();
		// 是否存在
		boolean b = jedis.exists(Bytes.toBytes(key));
		// 回收Jedis
		pool.returnResource(jedis);
		// 返回值
		return b;
	}
}