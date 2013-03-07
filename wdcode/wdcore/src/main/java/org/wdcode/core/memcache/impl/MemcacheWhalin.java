package org.wdcode.core.memcache.impl;

import java.util.Map;

import org.wdcode.common.lang.Conversion;
import org.wdcode.common.util.EmptyUtil;
import org.wdcode.core.memcache.base.BaseMemcache;

import com.whalin.MemCached.MemCachedClient;
import com.whalin.MemCached.SockIOPool;

/**
 * MemCached com.danga包的客户端调用实现
 * @author WD
 * @since JDK7
 * @version 1.0 2010-08-29
 */
public final class MemcacheWhalin extends BaseMemcache {
	// MemCache客户端
	private MemCachedClient	client;
	// SockIO连接池
	private SockIOPool		pool;

	/**
	 * 构造方法
	 */
	public MemcacheWhalin(String[] servers, String name, Integer[] weights, int initConn, int minConn, int maxConn, long maxIdle, long maintSleep, int socketTO, int socketConnectTO) {
		super(servers, name, weights, initConn, minConn, maxConn, maxIdle, maintSleep, socketTO, socketConnectTO);
	}

	/**
	 * 添加键值
	 * @param key 键
	 */
	public boolean remove(String key) {
		return client.delete(key);
	}

	/**
	 * 获得多个键的Map
	 * @param keys 键
	 * @return 值
	 */
	public Map<String, Object> getMap(String... keys) {
		return client.getMulti(keys);
	}

	/**
	 * 获得多个键的数组
	 * @param key 键
	 * @return 值
	 */
	public Object[] get(String... keys) {
		return client.getMultiArray(keys);
	}

	/**
	 * 清除所有键值
	 */
	public void clear() {
		client.flushAll();
	}

	/**
	 * 设置键值
	 * @param key 键
	 * @param value 值
	 */
	public boolean set(String key, Object value) {
		return client.set(key, value);
	}

	/**
	 * 根据键获得值
	 * @param key 键
	 * @return 值
	 */
	public Object get(String key) {
		return client.get(key);
	}

	/**
	 * 初始化方法
	 */
	protected void init(String[] servers, String name, Integer[] weights, int initConn, int minConn, int maxConn, long maxIdle, long maintSleep, int socketTO, int socketConnectTO) {
		// 获得池
		pool = SockIOPool.getInstance(name);
		// 设置服务器
		pool.setServers(servers);
		// 设置权重列表
		pool.setWeights(weights);
		// 设置初始化连接
		pool.setInitConn(initConn);
		// 设置最小连接
		pool.setMinConn(minConn);
		// 设置最大连接
		pool.setMaxConn(maxConn);
		// 设置空闲时间
		pool.setMaxIdle(Conversion.toInt(maxIdle));
		// 设置睡眠时间
		pool.setMaintSleep(maintSleep);
		// 设置超时读取
		pool.setSocketTO(socketTO);
		// 设置连接超时
		pool.setSocketConnectTO(socketConnectTO);
		// 池初始化
		pool.initialize();
		// 实例化client
		client = new MemCachedClient(name);
	}

	/**
	 * 关闭方法
	 */
	public void close() {
		// 判断连接池不为空
		if (!EmptyUtil.isEmpty(pool)) {
			pool.shutDown();
		}
		// 置为空
		client = null;
		pool = null;
	}
}
