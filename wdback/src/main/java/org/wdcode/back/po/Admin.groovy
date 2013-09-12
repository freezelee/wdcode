package org.wdcode.back.po

import javax.persistence.Entity
import javax.validation.constraints.Size

import org.hibernate.annotations.Cache
import org.hibernate.annotations.CacheConcurrencyStrategy
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component
import org.springframework.beans.factory.config.ConfigurableBeanFactory
import org.wdcode.base.entity.EntityLogin
import org.wdcode.common.crypto.Digest
import org.wdcode.site.entity.base.BaseEntityIdTime

/**
 * 管理员
 * @author WD
 * @since JDK7
 * @version 1.0 2012-07-29
 */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Entity
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@DynamicInsert
@DynamicUpdate
class Admin extends BaseEntityIdTime implements EntityLogin {
	// 名称
	@Size(min=5)
	String				name
	// 密码
	String				password
	// 是否启用
	Integer				state
	// 权限
	Integer				roleId
}
