package org.wdcode.shop.po.base


import javax.persistence.MappedSuperclass

import org.wdcode.base.entity.base.BaseEntityIdTime

/**
 * 基础物品实体
 * @author WD
 * @since JDK7
 * @version 1.0 2012-07-30
 */
@MappedSuperclass
abstract class BaseGoods extends BaseEntityIdTime {
	// 编号
	String				sn
	// 价格
	BigDecimal			price
	// 成本价
	BigDecimal			cost
	// 市场价
	BigDecimal			market
	// 库存
	Integer				store
	// 重量
	Integer				weight
	// 上架
	Boolean				markeTable
}
