package org.wdcode.site.po;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.wdcode.base.entity.EntityIp;
import org.wdcode.base.entity.EntityUser;
import org.wdcode.base.entity.base.BaseEntityTime;
import org.wdcode.common.lang.Conversion;
import org.wdcode.common.util.EmptyUtil;

/**
 * 页面统计实体
 * @author WD
 * @since JDK7
 * @version 1.0 2011-04-02
 */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Entity
public final class PageStatistics extends BaseEntityTime implements EntityIp, EntityUser {
	// 序列化ID
	private static final long	serialVersionUID	= -713787685386604863L;
	// 页面
	@Id
	private String				page;
	// 用户ID
	private Integer				userId;
	// 登录次数
	private Integer				count;
	// 登录IP
	private String				ip;

	/**
	 * 获得登录次数
	 * @return 登录次数
	 */
	public Integer getCount() {
		return count;
	}

	/**
	 * 设置登录次数
	 * @param count 登录次数
	 */
	public void setCount(Integer count) {
		this.count = count;
	}

	/**
	 * 获得登录IP
	 * @return 登录IP
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
	 * 获得键
	 */
	public Serializable getKey() {
		return page;
	}

	/**
	 * 设置键
	 */
	public void setKey(Serializable key) {
		this.page = Conversion.toString(key);
	}

	/**
	 * 获得页面
	 * @return 页面
	 */
	public String getPage() {
		return page;
	}

	/**
	 * 设置页面
	 * @param page 页面
	 */
	public void setPage(String page) {
		this.page = page;
	}

	/**
	 * 是否为空
	 */
	public boolean isEmpty() {
		return EmptyUtil.isEmpty(page);
	}
}
