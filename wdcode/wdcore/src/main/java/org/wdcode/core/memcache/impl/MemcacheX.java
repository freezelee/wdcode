package org.wdcode.core.memcache.impl;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.MemcachedClientBuilder;
import net.rubyeye.xmemcached.XMemcachedClientBuilder;
import net.rubyeye.xmemcached.command.BinaryCommandFactory;
import net.rubyeye.xmemcached.exception.MemcachedException;
import net.rubyeye.xmemcached.utils.AddrUtil;

import org.wdcode.common.constants.DateConstants;

import org.wdcode.common.lang.Conversion;
import org.wdcode.common.lang.Lists;
import org.wdcode.common.lang.Maps;
import org.wdcode.common.log.Logs;
import org.wdcode.common.util.ArrayUtil;
import org.wdcode.core.memcache.base.BaseMemcache;

/**
 * MemCached com.google包的客户端调用实现
 * @author WD
 * @since JDK7
 * @version 1.0 2010-12-10
 */
public final class MemcacheX extends BaseMemcache {
	// 客户端
	private MemcachedClient	client;

	/**
	 * 构造方法
	 */
	public MemcacheX(String[] servers, String name, Integer[] weights, int initConn, int minConn, int maxConn, long maxIdle, long maintSleep, int socketTO, int socketConnectTO, boolean binary) {
		super(servers, name, weights, initConn, minConn, maxConn, maxIdle, maintSleep, socketTO, socketConnectTO, binary);
	}

	/**
	 * 删除键值
	 * @param key 键
	 */
	public boolean remove(String key) {
		try {
			return client.delete(key);
		} catch (Exception e) {
			// 记录日志
			Logs.warn(e);
			// 返回false
			return false;
		}
	}

	/**
	 * 根据键获得值
	 * @param key 键
	 * @return 值
	 */
	public Object get(String key) {
		try {
			return client.get(key);
		} catch (Exception e) {
			// 记录日志
			Logs.warn(e);
			// 返回false
			return null;
		}
	}

	/**
	 * 获得多个键的数组
	 * @param keys 键
	 * @return 值
	 */
	public Object[] get(String... keys) {
		return getMap(keys).values().toArray();
	}

	/**
	 * 获得多个键的Map
	 * @param keys 键
	 * @return 值
	 */
	public Map<String, Object> getMap(String... keys) {
		try {
			return client.get(Lists.getList(keys));
		} catch (Exception e) {
			// 记录日志
			Logs.warn(e);
			// 返回false
			return Maps.emptyMap();
		}
	}

	/**
	 * 设置键值 无论存储空间是否存在相同键，都保存
	 * @param key 键
	 * @param value 值
	 */
	public boolean set(String key, Object value) {
		try {
			return client.set(key, getExt(), value);
		} catch (Exception e) {
			// 记录日志
			Logs.warn(e);
			// 返回false
			return false;
		}
	}

	/**
	 * 关闭资源方法
	 */
	public void close() {
		try {
			client.shutdown();
		} catch (IOException e) {
			Logs.warn(e);
		} finally {
			client = null;
		}
	}

	/**
	 * /** 清除属性值
	 */
	public void clear() {
		try {
			client.flushAll();
		} catch (TimeoutException | InterruptedException | MemcachedException e) {
			Logs.warn(e);
		}
	}

	/**
	 * 初始化
	 */
	protected void init(String[] servers, String name, Integer[] weights, int initConn, int minConn, int maxConn, long maxIdle, long maintSleep, int socketTO, int socketConnectTO, boolean binary) {
		try {
			MemcachedClientBuilder builder = new XMemcachedClientBuilder(AddrUtil.getAddresses(ArrayUtil.toString(servers)), ArrayUtil.toInt(weights));
			// 添加下面这行，采用BinaryCommandFactory即可使用二进制协议
			if (binary) {
				builder.setCommandFactory(new BinaryCommandFactory());
			}
			builder.setConnectionPoolSize(maxConn);
			builder.setConnectTimeout(socketConnectTO);
			builder.setOpTimeout(socketTO);
			// 构建客户端
			client = builder.build();
		} catch (IOException e) {
			Logs.warn(e);
		}
	}

	/**
	 * 获得缓存保存时间
	 * @return 缓存保存时间
	 */
	private int getExt() {
		return Conversion.toInt(DateConstants.TIME_DAY / 1000);
	}
}
