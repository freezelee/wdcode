package org.wdcode.core.nosql.hbase.factory;

import org.wdcode.core.factory.Factory;
import org.wdcode.core.nosql.hbase.HBase;
import org.wdcode.core.params.CoreParams;

/**
 * HBase工厂
 * @author WD
 * @since JDK7
 * @version 1.0 2010-12-12
 */
public final class HBaseFactory extends Factory<HBase> {
	// 工厂
	private final static HBaseFactory	FACTORY;

	/**
	 * 静态初始化
	 */
	static {
		FACTORY = new HBaseFactory();
	}

	/**
	 * 私有构造
	 */
	private HBaseFactory() {}

	/**
	 * 获得工厂
	 * @return 工厂
	 */
	public static HBaseFactory getFactory() {
		return FACTORY;
	}

	/**
	 * 实例化一个新对象
	 */
	public HBase newInstance() {
		return newInstance(CoreParams.NOSQL_HBASE_HOST, CoreParams.NOSQL_HBASE_PORT);
	}

	/**
	 * 实例化一个新对象
	 * @param host 主机
	 * @param port 端口
	 * @return HBase
	 */
	public HBase newInstance(String host, int port) {
		return new HBaseImpl(host, port);
	}
}
