package org.wdcode.base.entity;

/**
 * 有时间的实体接口
 * @author WD
 * @since JDK7
 * @version 1.0 2012-07-18
 */
public interface EntityTime {
	/**
	 * 时间字段
	 */
	public final static String	TIME_FIELD	= "time";

	/**
	 * 获得创建时间
	 */
	Integer getTime();

	/**
	 * 设置创建时间
	 */
	void setTime(Integer time);

	/**
	 * 获得日期
	 */
	String getDate();
}
