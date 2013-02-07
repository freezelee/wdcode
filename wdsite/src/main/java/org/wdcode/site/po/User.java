package org.wdcode.site.po;

import javax.persistence.Entity;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.wdcode.base.entity.EntityLogin;
import org.wdcode.base.entity.base.BaseEntityIdTime;
import org.wdcode.common.crypto.Digest;

/**
 * 用户实体
 * @author WD
 * @since JDK7
 * @version 1.0 2009-11-23
 */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Entity
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public final class User extends BaseEntityIdTime implements EntityLogin {
	// 序列化ID
	private static final long	serialVersionUID	= -4741348448017529495L;
	// 密码
	private String				password;
	// 昵称
	private String				nickName;
	// Email
	private String				email;
	// 用户手机
	private String				mobile;
	// 用户性别
	private Integer				sex;
	// 注册IP
	private String				registerIp;
	// 用户电话
	private String				phone;
	// 用户照片
	private String				photo;
	// 状态
	private Integer				state;
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
	 * 获得状态
	 */
	public Integer getState() {
		return state;
	}

	/**
	 * 设置状态
	 */
	public void setState(Integer state) {
		this.state = state;
	}

	/**
	 * 获得用户昵称
	 * @return 用户昵称
	 */
	public String getNickName() {
		return nickName;
	}

	/**
	 * 设置用户昵称
	 * @param nickName 用户昵称
	 */
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	/**
	 * 设置用户密码
	 * @param password 用户密码
	 */
	public void setPassword(String password) {
		this.password = Digest.absolute(password);
	}

	/**
	 * 获得密码
	 * @return 用户密码
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * 获得用户Email
	 * @return 用户Email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * 设置用户Email
	 * @param email 用户Email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * 获得用户手机
	 * @return 用户手机
	 */
	public String getMobile() {
		return this.mobile;
	}

	/**
	 * 设置用户手机
	 * @param mobile 用户手机
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * 获得用户性别
	 * @return 用户性别
	 */
	public Integer getSex() {
		return sex;
	}

	/**
	 * 设置用户性别
	 * @param sex 用户性别
	 */
	public void setSex(Integer sex) {
		this.sex = sex;
	}

	/**
	 * 获得用户电话
	 * @return 用户电话
	 */
	public String getPhone() {
		return this.phone;
	}

	/**
	 * 设置用户电话
	 * @param phone 用户电话
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * 获得用户照片
	 * @return 用户照片
	 */
	public String getPhoto() {
		return photo;
	}

	/**
	 * 设置用户照片
	 * @param photo 用户照片
	 */
	public void setPhoto(String photo) {
		this.photo = photo;
	}

	/**
	 * 设置注册IP
	 * @param registerIp 注册IP
	 */
	public void setRegisterIp(String registerIp) {
		this.registerIp = registerIp;
	}

	/**
	 * 获得注册IP
	 * @return 注册IP
	 */
	public String getRegisterIp() {
		return registerIp;
	}
}