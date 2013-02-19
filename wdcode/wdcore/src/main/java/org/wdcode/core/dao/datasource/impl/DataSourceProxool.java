package org.wdcode.core.dao.datasource.impl;

import org.logicalcobwebs.proxool.ProxoolDataSource;
import org.logicalcobwebs.proxool.ProxoolFacade;
import org.wdcode.common.lang.Conversion;
import org.wdcode.core.dao.datasource.DataSource;

/**
 * Proxool连接池实现
 * @author WD
 * @since JDK7
 * @version 1.0 2010-1-23
 */
public final class DataSourceProxool extends ProxoolDataSource implements DataSource {
	/**
	 * 获得多长时间检查一次空闲连接
	 */
	public long getIdleTimeout() {
		return super.getHouseKeepingSleepTime();
	}

	/**
	 * 获得初始化连接数
	 */
	public int getInitialPoolSize() {
		return super.getMinimumConnectionCount();
	}

	/**
	 * 获得测试空闲连接时间 超出时间回收
	 */
	public long getMaxIdleTime() {
		return super.getMaximumActiveTime();
	}

	/**
	 * 获得连接池最大连接数
	 */
	public int getMaxPoolSize() {
		return super.getMaximumConnectionCount();
	}

	/**
	 * 获得最大连接数
	 */
	public int getMaxSize() {
		return super.getMaximumConnectionCount();
	}

	/**
	 * 获得连接池最小连接数
	 */
	public int getMinPoolSize() {
		return super.getMinimumConnectionCount();
	}

	/**
	 * 获得超时等待时间
	 */
	public long getTimeout() {
		return super.getOverloadWithoutRefusalLifetime();
	}

	/**
	 * 设置多长时间检查一次空闲连接
	 */
	public void setIdleTimeout(long idleTimeout) {
		super.setHouseKeepingSleepTime(Conversion.toInt(idleTimeout));
	}

	/**
	 * 设置初始化连接数
	 */
	public void setInitialPoolSize(int initialPoolSize) {
		super.setMinimumConnectionCount(initialPoolSize);
	}

	/**
	 * 设置 测试空闲连接时间 超出时间回收
	 */
	public void setMaxIdleTime(long maxIdleTime) {
		super.setMaximumActiveTime(maxIdleTime);
	}

	/**
	 * 设置连接池最大连接数
	 */
	public void setMaxPoolSize(int maxPoolSize) {
		super.setMaximumConnectionCount(maxPoolSize);
	}

	/**
	 * 设置最大连接数
	 */
	public void setMaxSize(int maxSize) {
		super.setMaximumConnectionCount(maxSize);
	}

	/**
	 * 设置连接池最小连接数
	 */
	public void setMinPoolSize(int minPoolSize) {
		super.setMinimumConnectionCount(minPoolSize);
	}

	/**
	 * 设置超时等待时间
	 */
	public void setTimeout(long timeout) {
		super.setOverloadWithoutRefusalLifetime(Conversion.toInt(timeout));
	}

	/**
	 * 关闭资源
	 */
	public void close() {
		ProxoolFacade.shutdown();
	}

	/**
	 * 获得url
	 */
	public String getUrl() {
		return super.getDriverUrl();
	}

	/**
	 * 设置url
	 */
	public void setUrl(String url) {
		super.setDriverUrl(url);
	}
}
