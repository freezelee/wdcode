package org.wdcode.site.po;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.wdcode.base.entity.EntityIp;
import org.wdcode.base.entity.base.BaseEntityTime;
import org.wdcode.common.constants.StringConstants;
import org.wdcode.common.lang.Conversion;
import org.wdcode.common.util.DateUtil;
import org.wdcode.common.util.EmptyUtil;

/**
 * 登录统计实体
 * @author WD
 * @since JDK7
 * @version 1.0 2011-04-25
 */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Entity
public final class LoginStatistics extends BaseEntityTime implements EntityIp {
	// 序列化ID
	private static final long	serialVersionUID	= -5442414514241985965L;
	// 用户ID
	@Id
	private int					userId;
	// 上次登录时间
	private Integer				lastTime;
	// 上次登录IP
	private String				lastIp;
	// 登录次数
	private Integer				count;
	// 登录IP
	private String				ip;
	// 名称
	private String				name;

	/**
	 * 获得名称
	 */
	public String getName() {
		return name;
	}

	/**
	 * 设置名称
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 获得登录次数
	 * @return 登录次数
	 */
	public Integer getCount() {
		return count;
	}

	/**
	 * 设置登录次数
	 * @param count 登录次数
	 */
	public void setCount(Integer count) {
		this.count = count;
	}

	/**
	 * 获得登录IP
	 * @return 登录IP
	 */
	public String getIp() {
		return ip;
	}

	/**
	 * 设置登录IP
	 * @param ip 登录IP
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}

	/**
	 * 获得用户ID
	 * @return 用户ID
	 */
	public int getUserId() {
		return userId;
	}

	/**
	 * 设置用户ID
	 * @param userId 用户ID
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}

	/**
	 * 获得主键
	 */
	public Serializable getKey() {
		return getUserId();
	}

	/**
	 * 设置主键
	 */
	public void setKey(Serializable key) {
		setUserId(Conversion.toInt(key));
	}

	/**
	 * 获得上次登录时间
	 * @return 上次登录时间
	 */
	public Integer getLastTime() {
		return lastTime;
	}

	/**
	 * 获得上次登录日期
	 * @return 上次登录日期
	 */
	public String getLastDate() {
		return EmptyUtil.isEmpty(lastTime) ? StringConstants.EMPTY : DateUtil.toString(lastTime);
	}

	/**
	 * 设置上次登录时间
	 * @param lastTime 上次登录时间
	 */
	public void setLastTime(Integer lastTime) {
		this.lastTime = lastTime;
	}

	/**
	 * 获得上次登录IP
	 * @return 上次登录IP
	 */
	public String getLastIp() {
		return lastIp;
	}

	/**
	 * 设置上次登录IP
	 * @param lastIp 上次登录IP
	 */
	public void setLastIp(String lastIp) {
		this.lastIp = lastIp;
	}
}
