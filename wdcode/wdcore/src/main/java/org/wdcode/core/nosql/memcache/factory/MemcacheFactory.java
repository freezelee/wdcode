package org.wdcode.core.nosql.memcache.factory;

import org.wdcode.common.constants.StringConstants;
import org.wdcode.common.util.EmptyUtil;
import org.wdcode.core.factory.FactoryKey;
import org.wdcode.core.nosql.memcache.Memcache;
import org.wdcode.core.nosql.memcache.impl.MemcacheArray;
import org.wdcode.core.nosql.memcache.impl.MemcacheWhalin;
import org.wdcode.core.nosql.memcache.impl.MemcacheX;
import org.wdcode.core.params.MemcacheParams;

/**
 * MemCached的客户端调用工厂
 * @author WD
 * @since JDK7
 * @version 1.0 2011-05-26
 */
public final class MemcacheFactory extends FactoryKey<String, Memcache> {
	// 工厂
	private final static MemcacheFactory	FACTORY;
	static {
		FACTORY = new MemcacheFactory();
	}

	/**
	 * 获得工厂
	 * @return MemCache
	 */
	public static Memcache getMemcache() {
		return FACTORY.getInstance();
	}

	/**
	 * 获得工厂
	 * @param name 缓存名
	 * @return MemCache
	 */
	public static Memcache getMemcache(String name) {
		return FACTORY.getInstance(name);
	}

	/**
	 * 实例化一个新对象
	 */
	public Memcache newInstance() {
		// 获得集群发送名称服务器
		String[] names = MemcacheParams.NAMES;
		// 判断集群是否为空
		if (EmptyUtil.isEmpty(names)) {
			return newInstance(StringConstants.EMPTY);
		} else {
			return new MemcacheArray(names);
		}
	}

	/**
	 * 实例化一个新对象
	 * @param name 缓存名
	 * @return MemCache
	 */
	public Memcache newInstance(String name) {
		return newInstance(
			name, MemcacheParams.getServers(name), MemcacheParams.getWeights(name), MemcacheParams.getInitConn(name), MemcacheParams.getMinConn(name), MemcacheParams.getMaxConn(name), MemcacheParams.getMaxIdle(name), MemcacheParams.getSleep(name), MemcacheParams.getTO(name),
			MemcacheParams.getConnectTO(name), MemcacheParams.getBinary(name));
	}

	/**
	 * 实例一个新的索引写入器
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
	 * @return MemCache
	 */
	public Memcache newInstance(String name, String[] servers, Integer[] weights, int initConn, int minConn, int maxConn, long maxIdle, long maintSleep, int socketTO, int socketConnectTO, boolean binary) {
		switch (MemcacheParams.getParse(name)) {
			case "x":
				return new MemcacheX(servers, name, weights, initConn, minConn, maxConn, maxIdle, maintSleep, socketTO, socketConnectTO, binary);
			default:
				return new MemcacheWhalin(servers, name, weights, initConn, minConn, maxConn, maxIdle, maintSleep, socketTO, socketConnectTO, binary);
		}
	}

	private MemcacheFactory() {}
}
