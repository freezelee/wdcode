package org.wdcode.core.memcache.impl;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.Future;

import net.spy.memcached.AddrUtil;
import net.spy.memcached.MemcachedClient;

import org.wdcode.common.constants.DateConstants;

import org.wdcode.common.lang.Conversion;
import org.wdcode.common.lang.Maps;
import org.wdcode.common.log.Logs;
import org.wdcode.common.util.ArrayUtil;
import org.wdcode.common.util.EmptyUtil;
import org.wdcode.core.memcache.base.BaseMemcache;

/**
 * MemCached net.spy包的客户端调用实现
 * @author WD
 * @since JDK7
 * @version 1.0 2010-12-10
 */
public final class MemcacheSpy extends BaseMemcache {
	// 缓存客户端
	private MemcachedClient	client;

	/**
	 * 构造方法
	 */
	public MemcacheSpy(String[] servers, String name, Integer[] weights, int initConn, int minConn, int maxConn, long maxIdle, long maintSleep, int socketTO, int socketConnectTO) {
		super(servers, name, weights, initConn, minConn, maxConn, maxIdle, maintSleep, socketTO, socketConnectTO);
	}

	/**
	 * 删除键值
	 * @param key 键
	 */
	public boolean remove(String key) {
		return getBoolean(client.delete(key));
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
	 * 获得多个键的数组
	 * @param keys 键
	 * @return 值
	 */
	public Object[] get(String... keys) {
		// 声明对象数组
		Object[] objs = new Object[keys.length];
		// 循环获得值
		for (int i = 0; i < keys.length; i++) {
			// 获得值
			objs[i] = get(keys[i]);
		}
		// 返回对象数组
		return objs;

	}

	/**
	 * 获得多个键的Map
	 * @param keys 键
	 * @return 值
	 */
	public Map<String, Object> getMap(String... keys) {
		// 声明Map
		Map<String, Object> map = Maps.getMap(keys.length);
		// 循环获得值
		for (int i = 0; i < keys.length; i++) {
			// 获得值
			map.put(keys[i], get(keys[i]));
		}
		// 返回map
		return map;
	}

	/**
	 * 验证键是否存在
	 * @param key
	 * @return true 存在 false 不存在
	 */
	public boolean keyExists(String key) {
		return EmptyUtil.isEmpty(get(key));
	}

	/**
	 * 设置键值 无论存储空间是否存在相同键，都保存
	 * @param key 键
	 * @param value 值
	 */
	public boolean set(String key, Object value) {
		return getBoolean(client.set(key, getExt(), value));
	}

	/**
	 * 关闭资源方法
	 */
	public void close() {
		client.shutdown();
	}

	/**
	 * 清除属性值
	 */
	public void clear() {}

	/**
	 * 初始化方法
	 */
	protected void init(String[] servers, String name, Integer[] weights, int initConn, int minConn, int maxConn, long maxIdle, long maintSleep, int socketTO, int socketConnectTO) {
		// 获得实例
		try {
			client = new MemcachedClient(AddrUtil.getAddresses(ArrayUtil.toString(servers)));
		} catch (IOException e) {
			Logs.warn(e);
		}
	}

	/**
	 * 根据Future获得是否成功
	 * @param future 并发结果
	 * @return 是否成功
	 */
	private boolean getBoolean(Future<Boolean> future) {
		try {
			// 返回结果
			return future.get().booleanValue();
		} catch (Exception e) {
			Logs.warn(e);
		}
		// 返回失败
		return false;
	}

	/**
	 * 获得缓存保存时间
	 * @return 缓存保存时间
	 */
	private int getExt() {
		return Conversion.toInt(DateConstants.TIME_DAY);
	}
}
