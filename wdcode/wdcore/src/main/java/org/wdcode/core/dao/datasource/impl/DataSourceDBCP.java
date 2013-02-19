package org.wdcode.core.dao.datasource.impl;

import java.sql.SQLException;

import org.apache.commons.dbcp.BasicDataSource;
import org.wdcode.common.log.Logs;
import org.wdcode.core.dao.datasource.DataSource;

/**
 * DBCP连接池实现
 * @author WD
 * @since JDK7
 * @version 1.0 2010-01-23
 */
public final class DataSourceDBCP extends BasicDataSource implements DataSource {
	/**
	 * 获得驱动类
	 */
	public String getDriver() {
		return super.getDriverClassName();
	}

	/**
	 * 获得多长时间检查一次空闲连接
	 */
	public long getIdleTimeout() {
		return super.getTimeBetweenEvictionRunsMillis();
	}

	/**
	 * 获得初始化连接数
	 */
	public int getInitialPoolSize() {
		return super.getInitialSize();
	}

	/**
	 * 获得测试空闲连接时间 超出时间回收
	 */
	public long getMaxIdleTime() {
		return super.getMinEvictableIdleTimeMillis();
	}

	/**
	 * 获得连接池最大连接数
	 */
	public int getMaxPoolSize() {
		return super.getMaxIdle();
	}

	/**
	 * 获得最大连接数
	 */
	public int getMaxSize() {
		return super.getMaxActive();
	}

	/**
	 * 获得连接池最小连接数
	 */
	public int getMinPoolSize() {
		return super.getMinIdle();
	}

	/**
	 * 获得超时等待时间
	 */
	public long getTimeout() {
		return super.getMaxWait();
	}

	/**
	 * 获得用户名
	 */
	public String getUser() {
		return super.getUsername();
	}

	/**
	 * 设置驱动类
	 */
	public void setDriver(String driver) {
		super.setDriverClassName(driver);
	}

	/**
	 * 设置多长时间检查一次空闲连接
	 */
	public void setIdleTimeout(long idleTimeout) {
		super.setTimeBetweenEvictionRunsMillis(idleTimeout);
	}

	/**
	 * 设置初始化连接数
	 */
	public void setInitialPoolSize(int initialPoolSize) {
		super.setInitialSize(initialPoolSize);
	}

	/**
	 * 设置 测试空闲连接时间 超出时间回收
	 */
	public void setMaxIdleTime(long maxIdleTime) {
		super.setMinEvictableIdleTimeMillis(maxIdleTime);
	}

	/**
	 * 设置连接池最大连接数
	 */
	public void setMaxPoolSize(int maxPoolSize) {
		super.setMaxIdle(maxPoolSize);
	}

	/**
	 * 设置最大连接数
	 */
	public void setMaxSize(int maxSize) {
		super.setMaxActive(maxSize);
	}

	/**
	 * 设置连接池最小连接数
	 */
	public void setMinPoolSize(int minPoolSize) {
		super.setMinIdle(minPoolSize);
	}

	/**
	 * 设置超时等待时间
	 */
	public void setTimeout(long timeout) {
		super.setMaxWait(timeout);
	}

	/**
	 * 设置用户
	 */
	public void setUser(String user) {
		super.setUsername(user);
	}

	/**
	 * 关闭资源
	 */
	public void close() {
		try {
			super.close();
		} catch (SQLException e) {
			Logs.warn(e);
		}
	}
}
