package org.wdcode.security.po

import java.io.Serializable

import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.wdcode.common.lang.Conversion
import org.wdcode.site.entity.base.BaseEntity

/**
 * IP实体表
 * @author WD
 * @since JDK7
 * @version 1.0 2013-10-29
 */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Entity
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@DynamicInsert
@DynamicUpdate
class Ips extends BaseEntity{
	//IP
	@Id
	String	ip
	//名称
	String	name
	//类型
	Integer	type

	/**
	 * 获得主键
	 */
	public Serializable getKey() {
		return ip
	}

	/**
	 * 设置主键
	 */
	public void setKey(Serializable key) {
		ip = Conversion.toString(key)
	}
}
