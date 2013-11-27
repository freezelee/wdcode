package org.wdcode.core.nosql.memcache.base;

import java.util.List;

import org.wdcode.common.lang.Lists;
import org.wdcode.core.nosql.base.BaseNoSQL;
import org.wdcode.core.nosql.memcache.Memcache;
import org.wdcode.core.params.MemcacheParams;
import org.wdcode.core.zip.ZipEngine;

/**
 * MemCacheClient基础抽象
 * @author WD
 * @since JDK7
 * @version 1.0 2010-11-15
 */
public abstract class BaseMemcache extends BaseNoSQL implements Memcache {
	/**
	 * 构造方法
	 */
	protected BaseMemcache() {}

	/**
	 * 构造函数
	 * @param name 名称key
	 */
	public BaseMemcache(String name) {
		init(
			name, MemcacheParams.getServers(name), MemcacheParams.getWeights(name), MemcacheParams.getInitConn(name), MemcacheParams.getMinConn(name), MemcacheParams.getMaxConn(name), MemcacheParams.getMaxIdle(name), MemcacheParams.getSleep(name), MemcacheParams.getTO(name),
			MemcacheParams.getConnectTO(name), MemcacheParams.getBinary(name));

	}

	/**
	 * 获得多个键的数组
	 * @param keys 键
	 * @return 值
	 */
	public List<byte[]> extract(String... keys) {
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
	 * @param name 名称
	 * @param servers 服务器地址
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
	protected abstract void init(String name, String[] servers, Integer[] weights, int initConn, int minConn, int maxConn, long maxIdle, long maintSleep, int socketTO, int socketConnectTO, boolean binary);
}
