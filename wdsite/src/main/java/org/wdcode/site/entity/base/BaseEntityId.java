package org.wdcode.site.entity.base;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.search.annotations.DocumentId;
import org.wdcode.common.lang.Conversion;

/**
 * 实体PO实现,封装id
 * @author WD
 * @since JDK7
 * @version 1.0 2010-10-08
 */
@MappedSuperclass
public abstract class BaseEntityId extends BaseEntity {
	// 序列化ID
	private static final long	serialVersionUID	= -8658731657336767077L;
	// ID
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@DocumentId
	private int					id;

	/**
	 * 获得ID
	 * @return ID
	 */
	public int getId() {
		return id;
	}

	/**
	 * 设置ID
	 * @param id ID
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * 获得Key
	 */
	public Serializable getKey() {
		return id;
	}

	/**
	 * 设置Key
	 */
	public void setKey(Serializable key) {
		this.id = Conversion.toInt(key);
	}
}
