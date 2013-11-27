package org.wdcode.site.action;

import org.wdcode.base.entity.Entity;
import org.wdcode.base.entity.EntityUser;
import org.wdcode.common.constants.StringConstants;
import org.wdcode.common.crypto.Decrypts;
import org.wdcode.common.crypto.Digest;
import org.wdcode.common.crypto.Encrypts;
import org.wdcode.common.lang.Conversion;
import org.wdcode.common.util.EmptyUtil;
import org.wdcode.common.util.DateUtil;
import org.wdcode.site.constants.SiteConstants;
import org.wdcode.site.engine.LoginEngine;
import org.wdcode.site.params.SiteParams;
import org.wdcode.web.email.EmailEngine;

/***
 * 用户Action基础实现
 * @author WD
 * @since JDK7
 * @version 1.0 2011-11-02
 */
public class UserAction<E extends Entity, U extends EntityUser> extends LoginAction<E, U> {
	// 序列化ID
	private static final long	serialVersionUID	= 847404521518587435L;

	// 原密码
	private String				oldPwd;
	// 新密码
	private String				newPwd;
	// 重复密码
	private String				echoPwd;
	// 验证码
	private String				activeCoding;

	/**
	 * 修改个人密码
	 * @return
	 * @throws Exception
	 */
	public String changePwd() throws Exception {
		if (newPwd.equals(echoPwd)) {
			// 获得原Bean
			EntityUser u = service.get(user.getClass(), key);
			// 判断是否原始密码
			if (Digest.absolute(oldPwd).equals(u.getPassword())) {
				// 设置新密码
				u.setPassword(newPwd);
				// 返回成功
				return callback(service.update(u));
			}
		}
		// 返回失败
		return callback(ERROR);
	}

	/**
	 * 注册用户
	 * @return
	 * @throws Exception
	 */
	public String register() throws Exception {
		// 注册ip
		if (!EmptyUtil.isEmpty(user.getId())) {
			user.setIp(getIp());
		}
		// 创建时间
		user.setTime(DateUtil.getTime());
		// 是否Email验证
		if (SiteParams.USER_VERIFY_EMAIL) {
			// 设置状态无效
			user.setState(SiteConstants.STATE_INAVAIL);
		} else {
			// 设置状态有效
			user.setState(SiteConstants.STATE_AVAIL);
		}
		// 添加
		service.insert(user);
		// 获得用户ID
		int id = user.getId();
		// 注册成功
		if (id > 0) {
			// 是否Email验证
			if (SiteParams.USER_VERIFY_EMAIL) {
				// 获得激活码
				String activeCoding = Encrypts.encrypt(user.getId() + StringConstants.AMP + user.getEmail());
				// 邮件标题
				String subject = SiteParams.USER_VERIFY_EMAIL_SUBJECT.replaceAll(SiteParams.USER_VERIFY_EMAIL_NAME, user.getName());
				// 获得Url
				String url = SiteParams.USER_VERIFY_EMAIL_ACTION + activeCoding;
				// 邮件正文
				String content = SiteParams.USER_VERIFY_EMAIL_CONTENT.replaceAll(SiteParams.USER_VERIFY_EMAIL_URL, url);
				// 发生激活信
				EmailEngine.send(user.getEmail(), subject, content);
			} else {
				// 添加用户登录信息
				LoginEngine.addLogin(request, response, user, getLoginTime());
			}
		}
		// 返回
		return callback(user);
	}

	/**
	 * 激活用户
	 * @return
	 * @throws Exception
	 */
	public String active() throws Exception {
		// 获得验证码解析后的字符串数组
		String[] temp = Decrypts.decryptHex(activeCoding).split(StringConstants.AMP);
		// 获得用户ID
		int userId = Conversion.toInt(temp[0]);
		// 获得Email
		String email = temp[1];
		// 根据id获得用户实体
		if (userId > 0) {
			// 获得用户实体
			// 设置属性
			user.setId(userId);
			user.setEmail(email);
		}
		// 判断激活码是否正确
		if (user != null && user.getId() > 0) {
			// 设置状态为有效
			user.setState(SiteConstants.STATE_AVAIL);
			// 修改用户实体
			return callback(EmptyUtil.isEmpty(service.update(entity)) ? SUCCESS : ERROR);
		} else {
			// 验证码错误 返回到错误页
			return callback(ERROR);
		}
	}

	/**
	 * 获得原密码
	 * @return 原密码
	 */
	public String getOldPwd() {
		return oldPwd;
	}

	/**
	 * 设置原密码
	 * @param oldPwd 原密码
	 */
	public void setOldPwd(String oldPwd) {
		this.oldPwd = oldPwd;
	}

	/**
	 * 获得新密码
	 * @return 新密码
	 */
	public String getNewPwd() {
		return newPwd;
	}

	/**
	 * 设置新密码
	 * @param newPwd 新密码
	 */
	public void setNewPwd(String newPwd) {
		this.newPwd = newPwd;
	}

	/**
	 * 获得重复密码
	 * @return 重复密码
	 */
	public String getEchoPwd() {
		return echoPwd;
	}

	/**
	 * 设置重复密码
	 * @param echoPwd 重复密码
	 */
	public void setEchoPwd(String echoPwd) {
		this.echoPwd = echoPwd;
	}

	/**
	 * 获得验证码
	 * @return 验证码
	 */
	public String getActiveCoding() {
		return activeCoding;
	}

	/**
	 * 设置验证码
	 * @param activeCoding 验证码
	 */
	public void setActiveCoding(String activeCoding) {
		this.activeCoding = activeCoding;
	}
}
