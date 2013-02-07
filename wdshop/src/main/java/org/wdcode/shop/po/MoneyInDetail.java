package org.wdcode.shop.po;

import javax.persistence.Entity;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.wdcode.shop.po.base.BaseDetail;

/**
 * 金币入账
 * @author WD
 * @since JDK6
 * @version 1.0 2010-12-01
 */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Entity
public final class MoneyInDetail extends BaseDetail {
	// 序列化ID
	private static final long	serialVersionUID	= -8515855103554935977L;
}
