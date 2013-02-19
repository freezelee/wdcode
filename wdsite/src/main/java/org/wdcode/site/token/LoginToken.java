package org.wdcode.site.token;

import org.wdcode.common.util.EmptyUtil;
import org.wdcode.core.engine.JsonEngine;

/**
 * 登录信息封装
 * @author WD
 * @since JDK7
 * @version 1.0 2010-11-29
 */
public final class LoginToken implements AuthToken {
	// 用户ID
	private int		id;
	// 用户名
	private String	name;
	// 登录时间
	private int		time;

	/**
	 * 构造方法
	 */
	public LoginToken() {}

	/**
	 * 构造方法
	 * @param id 登录用户ID
	 * @param name 用户名
	 * @param time 登录时间
	 * @param ip 登录IP
	 */
	public LoginToken(int id, String name, int time) {
		this.id = id;
		this.name = name;
		this.time = time;
	}

	/**
	 * 是否登录
	 * @return 是否登录
	 */
	public boolean isLogin() {
		return id > 0 && !EmptyUtil.isEmpty(name) && time > 0;
	}

	/**
	 * 获得用户ID
	 * @return 用户ID
	 */
	public int getId() {
		return id;
	}

	/**
	 * 获得用户名
	 * @return 用户名
	 */
	public String getName() {
		return name;
	}

	/**
	 * 获得登录时间
	 * @return 登录时间
	 */
	public int getTime() {
		return time;
	}

	/**
	 * 设置ID
	 * @param id ID
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * 设置用户名
	 * @param name 用户名
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 设置登录时间
	 * @param time 登录时间
	 */
	public void setTime(int time) {
		this.time = time;
	}

	/**
	 * 重写方法
	 */
	public String toString() {
		return JsonEngine.toJson(this);
	}
}
