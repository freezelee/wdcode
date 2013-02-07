package org.wdcode.cms.po;

import javax.persistence.Entity;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.wdcode.base.entity.base.BaseEntityId;

/**
 * 导航
 * @author WD
 * @since JDK7
 * @version 1.0 2012-05-30
 */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Entity
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public final class Navigation extends BaseEntityId {
	// 序列化ID
	private static final long	serialVersionUID	= 3410509941579353647L;
	// 链接
	private String				url;
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
	 * 获得链接
	 * @return 链接
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * 设置链接
	 * @param url 链接
	 */
	public void setUrl(String url) {
		this.url = url;
	}
}
