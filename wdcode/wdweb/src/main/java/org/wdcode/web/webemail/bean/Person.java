package org.wdcode.web.webemail.bean;

import org.wdcode.common.util.EmptyUtil;
import org.wdcode.common.util.StringUtil;

/**
 * Email联系人实体
 * @author WD
 * @since JDK6
 * @version 1.0 2009-03-01
 */
public final class Person {
	// 用户名
	private String	name;
	// Email地址
	private String	email;

	/**
	 * 构造方法
	 */
	public Person() {}

	/**
	 * 构造方法
	 * @param name 用户名
	 * @param email Email地址
	 */
	public Person(String name, String email) {
		this.name = name;
		this.email = email;
	}

	/**
	 * 设置用户名
	 * @param name 用户名
	 */
	public final void setName(String name) {
		this.name = name;
	}

	/**
	 * 获得用户名
	 * @return 用户名
	 */
	public final String getName() {
		return name;
	}

	/**
	 * 设置Email
	 * @param email Email
	 */
	public final void setEmail(String email) {
		this.email = email;
	}

	/**
	 * 获得Email
	 * @return Email
	 */
	public final String getEmail() {
		return email;
	}

	/**
	 * 如果用户名为空 返回email.substring(0, email.indexOf('@')) 否则返回name
	 * @return 用户名
	 */
	public final String getGeneratedName() {
		return EmptyUtil.isEmpty(name) ? StringUtil.subString(email, "@") : name;
	}
}
