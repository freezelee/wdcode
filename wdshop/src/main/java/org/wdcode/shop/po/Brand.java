package org.wdcode.shop.po;

import javax.persistence.Entity;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.wdcode.base.entity.EntityFile;
import org.wdcode.base.entity.base.BaseEntityIdTime;

/**
 * 品牌
 * @author WD
 * @since JDK7
 * @version 1.0 2012-01-10
 */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Entity
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public final class Brand extends BaseEntityIdTime implements EntityFile {
	// 序列化ID
	private static final long	serialVersionUID	= -1647002603109838939L;
	// 网址
	private String				url;
	// LOGO
	private String				path;
	// 描述
	private String				detail;
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

	/**
	 * 获得LOGO
	 * @return LOGO
	 */
	public String getPath() {
		return path;
	}

	/**
	 * 设置LOGO
	 * @param path LOGO
	 */
	public void setPath(String path) {
		this.path = path;
	}

	/**
	 * 获得描述
	 * @return 描述
	 */
	public String getDetail() {
		return detail;
	}

	/**
	 * 设置描述
	 * @param detail 描述
	 */
	public void setDetail(String detail) {
		this.detail = detail;
	}
}
