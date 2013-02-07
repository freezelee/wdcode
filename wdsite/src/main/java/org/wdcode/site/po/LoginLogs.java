package org.wdcode.site.po;

import javax.persistence.Entity;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.wdcode.base.entity.EntityIp;
import org.wdcode.base.entity.EntityUser;
import org.wdcode.base.entity.base.BaseEntityIdTime;

/**
 * 登录日志实体
 * @author WD
 * @since JDK7
 * @version 1.0 2011-04-03
 */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Entity
public final class LoginLogs extends BaseEntityIdTime implements EntityUser, EntityIp {
	// 序列化ID
	private static final long	serialVersionUID	= 4774112841672223695L;
	// 登录IP
	private String				ip;
	// 用户User_Agent
	private String				userAgent;
	// 语言
	private String				language;
	// 用户ID
	private Integer				userId;
	// 状态
	private Integer				state;
	// 名称
	private String				name;

	/**
	 * 获得名称
	 */
	public String getName() {
		return name;
	}

	/**
	 * 设置名称
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 获得用户ID
	 * @return 用户ID
	 */
	public Integer getUserId() {
		return userId;
	}

	/**
	 * 设置用户ID
	 * @param userId 用户ID
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	/**
	 * 获得登录IP
	 * @return
	 */
	public String getIp() {
		return ip;
	}

	/**
	 * 设置登录IP
	 * @param ip 登录IP
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}

	/**
	 * 获得用户User_Agent
	 * @return 用户User_Agent
	 */
	public String getUserAgent() {
		return userAgent;
	}

	/**
	 * 设置用户User_Agent
	 * @param userAgent 用户User_Agent
	 */
	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

	/**
	 * 获得语言
	 * @return 语言
	 */
	public String getLanguage() {
		return language;
	}

	/**
	 * 设置语言
	 * @param language 语言
	 */
	public void setLanguage(String language) {
		this.language = language;
	}

	/**
	 * 获得状态
	 */
	public Integer getState() {
		return state;
	}

	/**
	 * 设置状态
	 */
	public void setState(Integer state) {
		this.state = state;
	}
}
