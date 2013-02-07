package org.wdcode.shop.po;

import javax.persistence.Entity;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.wdcode.base.entity.base.BaseEntityId;

/**
 * 快递公司
 * @author WD
 * @since JDK7
 * @version 1.0 2012-7-27
 */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Entity
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public final class Corp extends BaseEntityId {
	// 序列化ID
	private static final long	serialVersionUID	= 1871995350404049454L;
	// 网址
	private String				url;
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
	 * 获得网址
	 * @return 网址
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * 设置网址
	 * @param url 网址
	 */
	public void setUrl(String url) {
		this.url = url;
	}
}
