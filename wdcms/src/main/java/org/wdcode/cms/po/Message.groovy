package org.wdcode.cms.po

import java.util.List

import javax.persistence.Entity
import javax.persistence.JoinColumn
import javax.persistence.JoinTable
import javax.persistence.ManyToMany

import org.hibernate.annotations.Cache
import org.hibernate.annotations.CacheConcurrencyStrategy
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component
import org.springframework.beans.factory.config.ConfigurableBeanFactory
import org.wdcode.base.entity.EntityIp
import org.wdcode.base.entity.EntityUserId
import org.wdcode.site.entity.base.BaseEntityIdTime

/**
 * 消息
 * @author WD
 * @since JDK7
 * @version 1.0 2010-12-01
 */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Entity
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@DynamicInsert
@DynamicUpdate
class Message extends BaseEntityIdTime implements EntityIp, EntityUserId {
	// IP
	String				ip
	// 内容
	String				content
	// 用户ID
	Integer		 		userId
	// 名称
	String				name
	// 用户列表
	@ManyToMany
	@JoinTable(name = "user_message", joinColumns = @JoinColumn(name = "message_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	List<User>			users
}
