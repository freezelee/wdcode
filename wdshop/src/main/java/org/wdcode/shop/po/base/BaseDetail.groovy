package org.wdcode.shop.po.base


import javax.persistence.MappedSuperclass

import org.wdcode.base.entity.base.BaseEntityIdTime
import org.wdcode.site.entity.EntityUser;

/**
 * 基础账单
 * @author WD
 * @since JDK6
 * @version 1.0 2010-12-1
 */
@MappedSuperclass
abstract class BaseDetail extends BaseEntityIdTime implements EntityUser {
	// 入账金钱
	BigDecimal		money
	// 上次余额
	BigDecimal		overage
	// 总数
	BigDecimal		amount
	// 类型
	Short			type
	// 操作用户ID
	Integer			opUid
	// 说明
	String			description
	// 描述
	String			detail
	// 用户ID
	Integer			userId
}