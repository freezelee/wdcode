package org.wdcode.cms.po

import javax.persistence.Entity

import org.hibernate.annotations.Cache
import org.hibernate.annotations.CacheConcurrencyStrategy
import org.hibernate.annotations.DynamicInsert
import org.hibernate.annotations.DynamicUpdate
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component
import org.springframework.beans.factory.config.ConfigurableBeanFactory
import org.wdcode.base.entity.EntityLogin
import org.wdcode.base.entity.EntityUser
import org.wdcode.common.crypto.Digest
import org.wdcode.site.entity.base.BaseEntityIdTime

/**
 * 用户实体
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
class User extends BaseEntityIdTime implements EntityUser {
	// 密码
	String			password
	// 昵称
	String			nickName
	// Email
	String			email
	// 用户手机
	String			mobile
	// 用户性别
	Integer			sex
	// 注册IP
	String			ip
	// 用户电话
	String			phone
	// 用户照片
	String			photo
	// 状态
	Integer			state
	// 名称
	String			name
	//登录IP
	String			loginIp
	//登录时间
	Integer			loginTime

	/**
	 * 设置用户密码
	 * @param password 用户密码
	 */
	public void setPassword(String password){
		this.password = Digest.password(password)
	}
}