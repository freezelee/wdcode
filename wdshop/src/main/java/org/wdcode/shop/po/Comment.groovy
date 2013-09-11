package org.wdcode.shop.po

import javax.persistence.Entity

import org.hibernate.annotations.Cache
import org.hibernate.annotations.CacheConcurrencyStrategy
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component
import org.springframework.beans.factory.config.ConfigurableBeanFactory
import org.wdcode.base.entity.EntityIp;
import org.wdcode.site.entity.base.BaseEntityIdTime;

/**
 * 商品评论
 * @author WD
 * @since JDK7
 * @version 1.0 2012-06-29
 */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Entity
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@DynamicInsert
@DynamicUpdate
class Comment extends BaseEntityIdTime implements EntityIp {
	// 商品ID
	Integer				goodsId
	// IP
	String				ip
	// 内容
	String				content
	// 评论ID
	Integer				commentId
	// 联系方式
	String				contact
	// 名称
	String				name
}
