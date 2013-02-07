package org.wdcode.back.po;

import javax.persistence.Entity;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.wdcode.base.entity.EntityUser;
import org.wdcode.base.entity.base.BaseEntityIdTime;

/**
 * 操作日志实体
 * @author WD
 * @since JDK7
 * @version 1.0 2011-04-03
 */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Entity
public final class OperateLogs extends BaseEntityIdTime implements EntityUser {
	// 序列化ID
	private static final long	serialVersionUID	= 3465376716816368851L;
	// 内容
	private String				content;
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