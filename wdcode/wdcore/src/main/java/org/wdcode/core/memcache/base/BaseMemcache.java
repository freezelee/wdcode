package org.wdcode.core.memcache.base;

import java.util.List;

import org.wdcode.common.lang.Lists;
import org.wdcode.core.memcache.Memcache;
import org.wdcode.core.zip.ZipEngine;

/**
 * MemCacheClient基础抽象
 * @author WD
 * @since JDK7
 * @version 1.0 2010-11-15
 */
public abstract class BaseMemcache implements Memcache {
	protected BaseMemcache() {}

	/**
	 * 构造方法
	 * @param servers 服务器地址
	 * @param name 缓存名称
	 * @param weights 权重列表
	 * @param initConn 初始化连接
	 * @param minConn 最小连接
	 * @param maxConn 最大连接
	 * @param maxIdle 空闲时间
	 * @param maintSleep 睡眠时间
	 * @param socketTO 超时读取
	 * @param socketConnectTO 连接超时
	 */
	public BaseMemcache(String[] servers, String name, Integer[] weights, int initConn, int minConn, int maxConn, long maxIdle, long maintSleep, int socketTO, int socketConnectTO, boolean binary) {
		init(servers, name, weights, initConn, minConn, maxConn, maxIdle, maintSleep, socketTO, socketConnectTO, binary);
	}

	/**
	 * 压缩值 当值能压缩时才压缩
	 * @param key 键
	 * @param value 值
	 */
	public final boolean compress(String key, Object value) {
		return set(key, ZipEngine.compress(value));
	}

	/**
	 * 根据键获得压缩值 如果是压缩的返回解压缩的byte[] 否是返回Object
	 * @param key 键
	 * @return 值
	 */
	public final byte[] extract(String key) {
		return ZipEngine.extract(get(key));
	}

	/**
	 * 获得多个键的数组
	 * @param keys 键
	 * @return 值
	 */
	public final List<byte[]> extract(String... keys) {
		// 声明列表
		List<byte[]> list = Lists.getList(keys.length);
		// 循环解压数据
		for (Object o : get(keys)) {
			list.add(ZipEngine.extract(o));
		}
		// 返回列表
		return list;
	}

	/**
	 * 初始化方法
	 * @param servers 服务器地址
	 * @param name 缓存名称
	 * @param weights 权重列表
	 * @param initConn 初始化连接
	 * @param minConn 最小连接
	 * @param maxConn 最大连接
	 * @param maxIdle 空闲时间
	 * @param maintSleep 睡眠时间
	 * @param socketTO 超时读取
	 * @param socketConnectTO 连接超时
	 * @param binary 是否使用binary(二进制协议)
	 */
	protected abstract void init(String[] servers, String name, Integer[] weights, int initConn, int minConn, int maxConn, long maxIdle, long maintSleep, int socketTO, int socketConnectTO, boolean binary);
}
