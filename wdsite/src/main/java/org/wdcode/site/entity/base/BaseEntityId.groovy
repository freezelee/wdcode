package org.wdcode.site.entity.base

import java.io.Serializable

import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.MappedSuperclass

import org.hibernate.search.annotations.DocumentId
import org.wdcode.common.lang.Conversion

/**
 * 实体PO实现,封装id
 * @author WD
 * @since JDK7
 * @version 1.0 2010-10-08
 */
@MappedSuperclass
abstract class BaseEntityId extends BaseEntity {
	// ID
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@DocumentId
	int				id

	/**
	 * 获得Key
	 */
	public Serializable getKey() {
		return id
	}

	/**
	 * 设置Key
	 */
	public void setKey(Serializable key) {
		this.id = Conversion.toInt(key)
	}
}
