package org.wdcode.site.token;

import org.wdcode.base.entity.EntityLogin;
import org.wdcode.common.lang.Bytes;
import org.wdcode.common.util.DateUtil;
import org.wdcode.common.util.EmptyUtil;
import org.wdcode.core.json.JsonEngine;
import org.wdcode.web.util.IpUtil;

/**
 * 登录信息封装
 * @author WD
 * @since JDK7
 * @version 1.0 2010-11-29
 */
public class LoginToken implements AuthToken {
	// 用户ID
	protected int		id;
	// 用户名
	protected String	name;
	// 登录时间
	protected int		time;
	// 登录IP
	protected String	loginIp;
	// 服务器IP
	protected String	serverIp;

	/**
	 * 构造方法
	 */
	public LoginToken() {}

	/**
	 * 构造方法
	 * @param id 登录用户ID
	 * @param name 用户名
	 * @param time 登录时间
	 * @param loginIp 登录IP
	 * @param serverIp 服务器IP
	 */
	public LoginToken(EntityLogin login, String loginIp, String serverIp) {
		this(login.getId(), login.getName(), loginIp, serverIp);
	}

	/**
	 * 构造方法
	 * @param id 登录用户ID
	 * @param name 用户名
	 * @param time 登录时间
	 * @param loginIp 登录IP
	 * @param serverIp 服务器IP
	 */
	public LoginToken(int id, String name, String loginIp, String serverIp) {
		this.id = id;
		this.name = name;
		this.time = DateUtil.getTime();
		this.loginIp = loginIp;
		this.serverIp = serverIp;
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

	public String getLoginIp() {
		return loginIp;
	}

	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}

	public String getServerIp() {
		return serverIp;
	}

	public void setServerIp(String serverIp) {
		this.serverIp = serverIp;
	}

	/**
	 * 重写方法
	 */
	public String toString() {
		return JsonEngine.toJson(this);
	}

	@Override
	public byte[] toBytes() {
		return Bytes.toBytes(id, time, IpUtil.encode(loginIp), IpUtil.encode(serverIp), name);
	}

	@Override
	public LoginToken toBean(byte[] b) {
		// 判断字节数组不为空
		if (!EmptyUtil.isEmpty(b)) {
			this.id = Bytes.toInt(b);
			this.time = Bytes.toInt(b, 4);
			this.loginIp = IpUtil.decode(Bytes.toInt(b, 8));
			this.serverIp = IpUtil.decode(Bytes.toInt(b, 12));
			this.name = Bytes.toString(b, 16);
		}
		// 返回自身
		return this;
	}
}
