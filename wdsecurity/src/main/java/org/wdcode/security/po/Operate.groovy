package org.wdcode.security.po

import java.io.Serializable

import javax.persistence.Entity
import javax.persistence.Id

import org.hibernate.annotations.Cache
import org.hibernate.annotations.CacheConcurrencyStrategy
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component
import org.springframework.beans.factory.config.ConfigurableBeanFactory
import org.wdcode.common.lang.Conversion
import org.wdcode.site.entity.base.BaseEntity

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
@DynamicInsert
@DynamicUpdate
class Operate extends BaseEntity {
	// 操作连接
	@Id
	String				link
	// 名称
	String				name

	/**
	 * 获得主键
	 */
	public Serializable getKey() {
		return getLink()
	}

	/**
	 * 设置主键
	 */
	public void setKey(Serializable key) {
		setLink(Conversion.toString(link))
	}
}