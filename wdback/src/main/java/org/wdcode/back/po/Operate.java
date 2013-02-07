package org.wdcode.back.po;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.wdcode.base.entity.base.BaseEntity;
import org.wdcode.common.lang.Conversion;

/**
 * 操作实体
 * @author WD
 * @since JDK7
 * @version 1.0 2009-11-23
 */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Entity
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public final class Operate extends BaseEntity {
	// 序列化ID
	private static final long	serialVersionUID	= 2676838100904761490L;
	// 操作连接
	@Id
	private String				link;
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
	 * 获得操作连接
	 * @return 操作连接
	 */
	public String getLink() {
		return this.link;
	}

	/**
	 * 设置操作连接
	 * @param link 操作连接
	 */
	public void setLink(String link) {
		this.link = link;
	}

	/**
	 * 获得主键
	 */
	public Serializable getKey() {
		return getLink();
	}

	/**
	 * 设置主键
	 */
	public void setKey(Serializable key) {
		setLink(Conversion.toString(link));
	}
}