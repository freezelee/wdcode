package org.wdcode.cms.po;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.wdcode.cms.po.base.BaseMessage;
import org.wdcode.site.po.User;

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
public final class Message extends BaseMessage {
	// 序列化ID
	private static final long	serialVersionUID	= -9176201871738531999L;
	// 用户列表
	@ManyToMany
	@JoinTable(name = "user_message", joinColumns = @JoinColumn(name = "message_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private List<User>			users;

	/**
	 * 获得用户列表
	 * @return 用户列表
	 */
	public List<User> getUsers() {
		return users;
	}

	/**
	 * 设置用户列表
	 * @param users 用户列表
	 */
	public void setUsers(List<User> users) {
		this.users = users;
	}
}
