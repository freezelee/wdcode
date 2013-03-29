package org.wdcode.base.entity;

/**
 * 登录接口
 * @author WD
 * @since JDK7
 * @version 1.0 2012-08-13
 */
public interface EntityLogin extends Entity {
	/**
	 * 获得ID
	 * @return ID
	 */
	int getId();

	/**
	 * 设置Id
	 * @param id ID
	 */
	void setId(int id);

	/**
	 * 获得名称
	 */
	String getName();

	/**
	 * 设置名称
	 */
	void setName(String name);

	/**
	 * 设置用户密码
	 * @param password 用户密码
	 */
	void setPassword(String password);

	/**
	 * 获得密码
	 * @return 用户密码
	 */
	String getPassword();
}
