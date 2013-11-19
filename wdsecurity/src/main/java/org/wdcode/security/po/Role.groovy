package org.wdcode.security.po

import java.util.List

import javax.persistence.Entity
import javax.persistence.JoinColumn
import javax.persistence.JoinTable
import javax.persistence.ManyToMany

import org.hibernate.annotations.Cache
import org.hibernate.annotations.CacheConcurrencyStrategy
import org.hibernate.annotations.DynamicInsert
import org.hibernate.annotations.DynamicUpdate
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component
import org.springframework.beans.factory.config.ConfigurableBeanFactory
import org.wdcode.common.constants.StringConstants
import org.wdcode.common.lang.Conversion
import org.wdcode.common.lang.Lists
import org.wdcode.common.util.EmptyUtil
import org.wdcode.site.entity.base.BaseEntityId

/**
 * 角色实体
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
class Role extends BaseEntityId {
	// 名称
	String				name
	// 操作列表
	@ManyToMany
	@JoinTable(name = "role_operate", joinColumns = @JoinColumn(name = "role_id"), inverseJoinColumns = @JoinColumn(name = "operate"))
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	List<Operate>		operates
	// 菜单
	@ManyToMany
	@JoinTable(name = "role_menu", joinColumns = @JoinColumn(name = "role_id"), inverseJoinColumns = @JoinColumn(name = "menu_id"))
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	List<Menu>			menus

	/**
	 * 设置菜单
	 * @param operates
	 */
	public void setMenu(String ms) {
		//操作链接不为空
		if(!EmptyUtil.isEmpty(ms)){
			//声明列表
			menus = Lists.getList()
			//循环赋值
			for(String s:ms.split(StringConstants.COMMA)){
				//实例化操作类
				Menu m = new Menu()
				//设置链接
				m.setId(Conversion.toInt(s.trim()))
				//添加到列表
				menus.add(m)
			}
		}
	}

	/**
	 * 设置操作
	 * @param operates
	 */
	public void setOperate(String ops) {
		//操作链接不为空
		if(!EmptyUtil.isEmpty(ops)){
			//声明列表
			operates = Lists.getList()
			//循环赋值
			for(String s:ops.split(StringConstants.COMMA)){
				//实例化操作类
				Operate op = new Operate()
				//设置链接
				op.setLink(s.trim())
				//添加到列表
				operates.add(op)
			}
		}
	}
}