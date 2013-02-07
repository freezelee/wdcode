package org.wdcode.cms.po;

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
 * 属性信息实体
 * @author WD
 * @since JDK7
 * @version 1.0 2009-11-23
 */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Entity
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public final class Property extends BaseEntity {
	// 序列化ID
	private static final long	serialVersionUID	= -5810248075064517133L;
	// 属性Key
	@Id
	private String				name;
	// 属性Value
	private String				value;

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
	 * 获得属性Key
	 * @return 属性Key
	 */
	public Serializable getKey() {
		return name;
	}

	/**
	 * 设置属性Key
	 * @param key 属性Key
	 */
	public void setKey(Serializable key) {
		this.name = Conversion.toString(key);
	}

	/**
	 * 获得属性Value
	 * @return 属性Value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * 设置属性Value
	 * @param value 属性Value
	 */
	public void setValue(String value) {
		this.value = value;
	}
}
