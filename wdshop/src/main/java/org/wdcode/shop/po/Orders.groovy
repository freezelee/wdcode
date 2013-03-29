package org.wdcode.shop.po

import java.io.Serializable
import java.util.List

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.Type
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component
import org.springframework.beans.factory.config.ConfigurableBeanFactory
import org.wdcode.base.entity.EntityUser
import org.wdcode.common.lang.Conversion
import org.wdcode.site.entity.base.BaseEntityTime

/**
 * 物品订单
 * @author WD
 * @since JDK7
 * @version 1.0 2012-08-08
 */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Entity
class Orders extends BaseEntityTime implements EntityUser {
	// 编号
	@Id
	@GenericGenerator(name = "uuid", strategy = "uuid")
	@GeneratedValue(generator = "uuid")
	String				sn
	// 用户ID
	Integer				userId
	// 配送方式
	Integer				dispatchId
	// 支付类型
	String				pay
	// 收货地址
	Integer				receiverId
	// 备注
	String				detail
	// 状态
	Integer				status
	// 总价格
	BigDecimal			total
	// 物品列表
	@Type(type = "org.wdcode.base.dao.hibernate.type.JsonType")
	List<Products>		products

	/**
	 * 收货人
	 * @author WD
	 * @since JDK7
	 * @version 1.0 2012-08-08
	 */
	static class Products implements Serializable {
		// 主键
		int					id
		// 名称
		String				name
		// 价钱
		BigDecimal			price
		// 总价钱
		BigDecimal			total
		// 数量
		Integer				count
	}

	@Override
	public Serializable getKey() {
		return sn
	}

	@Override
	public void setKey(Serializable key) {
		this.sn = Conversion.toString(key)
	}
}
