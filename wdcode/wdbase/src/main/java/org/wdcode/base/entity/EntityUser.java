package org.wdcode.base.entity;

/**
 * 用户实体接口
 * @author WD
 * @since JDK7
 * @version 1.0 2012-07-18
 */
public interface EntityUser extends EntityLogin, EntityIp, EntityTime {
	/**
	 * 获得Email
	 * @return Email
	 */
	String getEmail();

	/**
	 * 设置Email
	 * @param email Email
	 */
	void setEmail(String email);
	
	/**
	 * 获得状态
	 * @param state
	 */
	Integer getState();

	/**
	 * 设置状态
	 * @param state
	 */
	void setState(Integer state);
}
