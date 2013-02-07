package org.wdcode.cms.po;

import javax.persistence.Entity;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.wdcode.cms.po.base.BaseMessage;

/**
 * 回复留言实体
 * @author WD
 * @since JDK7
 * @version 1.0 2011-04-25
 */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Entity
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public final class Revert extends BaseMessage {
	// 序列化ID
	private static final long	serialVersionUID	= -2780366684225809760L;
	// 留言ID
	private Integer				leaveId;

	/**
	 * 获得留言
	 * @return 留言
	 */
	public Integer getLeaveId() {
		return leaveId;
	}

	/**
	 * 设置留言
	 * @param leaveId 留言
	 */
	public void setLeaveId(Integer leaveId) {
		this.leaveId = leaveId;
	}
}