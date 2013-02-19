package org.wdcode.cms.po.base;

import javax.persistence.MappedSuperclass;

import org.wdcode.base.entity.EntityIp;
import org.wdcode.base.entity.EntityUser;
import org.wdcode.base.entity.base.BaseEntityIdTime;

/**
 * 基础留言
 * @author WD
 * @since JDK7
 * @version 1.0 2011-04-25
 */
@MappedSuperclass
public abstract class BaseMessage extends BaseEntityIdTime implements EntityIp, EntityUser {
	// 序列化ID
	private static final long	serialVersionUID	= 8048085518926147441L;
	// IP
	private String				ip;
	// 内容
	private String				content;
	// 用户ID
	private Integer				userId;
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
	 * 获得IP
	 * @return IP
	 */
	public String getIp() {
		return ip;
	}

	/**
	 * 设置IP
	 * @param ip IP
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}

	/**
	 * 获得内容
	 * @return 内容
	 */
	public String getContent() {
		return content;
	}

	/**
	 * 设置内容
	 * @param content 内容
	 */
	public void setContent(String content) {
		this.content = content;
	}
}
