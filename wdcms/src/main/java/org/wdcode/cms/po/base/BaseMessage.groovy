package org.wdcode.cms.po.base

import javax.persistence.MappedSuperclass

import org.wdcode.base.entity.base.BaseEntityIdTime
import org.wdcode.site.entity.EntityIp;
import org.wdcode.site.entity.EntityUser;

/**
 * 基础留言
 * @author WD
 * @since JDK7
 * @version 1.0 2011-04-25
 */
@MappedSuperclass
abstract class BaseMessage extends BaseEntityIdTime implements EntityIp, EntityUser {
	// IP
	String				ip
	// 内容
	String				content
	// 用户ID
	Integer				userId
	// 名称
	String				name
}
