package org.wdcode.core.dao.datasource.impl;

import org.wdcode.common.lang.Conversion;
import org.wdcode.core.dao.datasource.DataSource;

import com.jolbox.bonecp.BoneCPDataSource;

/**
 * bonecp连接池实现
 * @author WD
 * @since JDK7
 * @version 1.0 2010-01-30
 */
public final class DataSourceBonecp extends BoneCPDataSource implements DataSource {
	// 序列化ID
	private static final long	serialVersionUID	= 179700811362890870L;

	/**
	 * 获得 驱动类
	 * @return 驱动类
	 */
	public String getDriver() {
		return super.getDriverClass();
	}

	/**
	 * 获得多长时间检查一次空闲连接
	 * @return 多长时间检查一次空闲连接
	 */
	public long getIdleTimeout() {
		return Conversion.toLong(super.getIdleConnectionTestPeriodInMinutes());
	}

	/**
	 * 获得初始化连接数
	 * @return 初始化连接数
	 */
	public int getInitialPoolSize() {
		return Conversion.toInt(super.getMinConnectionsPerPartition());
	}

	/**
	 * 获得测试空闲连接时间 超出时间回收
	 * @return 测试空闲连接时间 超出时间回收
	 */
	public long getMaxIdleTime() {
		return Conversion.toLong(super.getIdleMaxAgeInMinutes());
	}

	/**
	 * 获得连接池最大连接数
	 * @return 连接池最大连接数
	 */
	public int getMaxPoolSize() {
		return Conversion.toInt(super.getMaxConnectionsPerPartition());
	}

	/**
	 * 获得最大连接数
	 * @return 最大连接数
	 */
	public int getMaxSize() {
		return Conversion.toInt(super.getMaxConnectionsPerPartition());
	}

	/**
	 * 连接池最小连接数
	 * @return 连接池最小连接数
	 */
	public int getMinPoolSize() {
		return Conversion.toInt(super.getMinConnectionsPerPartition());
	}

	/**
	 * 获得超时等待时间
	 * @return 超时等待时间
	 */
	public long getTimeout() {
		return 0;
	}

	/**
	 * 获得Url
	 * @return Url
	 */
	public String getUrl() {
		return super.getJdbcUrl();
	}

	/**
	 * 获得用户名
	 * @return 用户名
	 */
	public String getUser() {
		return super.getUsername();
	}

	/**
	 * 设置驱动类
	 * @param driver 驱动类
	 */
	public void setDriver(String driver) {
		super.setDriverClass(driver);
	}

	/**
	 * 设置多长时间检查一次空闲连接
	 * @param idleTimeout 多长时间检查一次空闲连接
	 */
	public void setIdleTimeout(long idleTimeout) {
		super.setIdleConnectionTestPeriodInMinutes(idleTimeout);
	}

	/**
	 * 设置初始化连接数
	 * @param initialPoolSize 初始化连接数
	 */
	public void setInitialPoolSize(int initialPoolSize) {
		super.setMinConnectionsPerPartition(initialPoolSize);
	}

	/**
	 * 设置测试空闲连接时间 超出时间回收
	 * @param maxIdleTime 测试空闲连接时间 超出时间回收
	 */
	public void setMaxIdleTime(long maxIdleTime) {
		super.setIdleMaxAgeInMinutes(maxIdleTime);
	}

	/**
	 * 设置连接池最大连接数
	 * @param maxPoolSize 连接池最大连接数
	 */
	public void setMaxPoolSize(int maxPoolSize) {
		super.setMaxConnectionsPerPartition(maxPoolSize);
	}

	/**
	 * 设置最大连接数
	 * @param maxSize 最大连接数
	 */
	public void setMaxSize(int maxSize) {
		super.setMaxConnectionsPerPartition(maxSize);
	}

	/**
	 * 设置连接池最小连接数
	 * @param minPoolSize 连接池最小连接数
	 */
	public void setMinPoolSize(int minPoolSize) {
		super.setMinConnectionsPerPartition(minPoolSize);
	}

	/**
	 * 设置超时等待时间
	 * @param timeout 超时等待时间
	 */
	public void setTimeout(long timeout) {}

	/**
	 * 设置 Url
	 * @param url Url
	 */
	public void setUrl(String url) {
		super.setJdbcUrl(url);
	}

	/**
	 * 设置用户名
	 * @param user 用户名
	 */
	public void setUser(String user) {
		super.setUsername(user);
	}
}
