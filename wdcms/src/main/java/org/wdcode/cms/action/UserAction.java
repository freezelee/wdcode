package org.wdcode.cms.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.WebApplicationContext;
import org.wdcode.cms.po.User;
import org.wdcode.common.constants.StringConstants;
import org.wdcode.common.crypto.Decrypts;
import org.wdcode.common.crypto.Encrypts;
import org.wdcode.common.lang.Conversion;
import org.wdcode.common.util.EmptyUtil;
import org.wdcode.common.util.DateUtil;
import org.wdcode.site.action.LoginAction;
import org.wdcode.site.engine.LoginEngine;
import org.wdcode.site.params.SiteParams;
import org.wdcode.web.engine.EmailEngine;

/***
 * 用户Action基础实现
 * @author WD
 * @since JDK7
 * @version 1.0 2011-11-02
 */
@Controller
@Scope(WebApplicationContext.SCOPE_REQUEST)
public class UserAction extends LoginAction<User, User> {
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
	 * 修改用户
	 * @return
	 * @throws Exception
	 */
	public String edit() throws Exception {
		// 设置密码
		entity.setPassword(newPwd);
		// 执行父方法
		return super.edit();
	}

	/**
	 * 修改个人密码
	 * @return
	 * @throws Exception
	 */
	public String changePwd() throws Exception {
		// 获得原Bean
		entity = service.get(User.class, entity.getId());
		// 判断是否原始密码
		if (oldPwd.equals(entity.getPassword())) {
			// 设置新密码
			entity.setPassword(newPwd);
			// 修改
			service.update(entity);
			// 返回成功
			return callback(SUCCESS);
		} else {
			// 返回失败
			return callback(ERROR);
		}
	}

	/**
	 * 重置密码
	 * @return
	 * @throws Exception
	 */
	public String resetPwd() throws Exception {
		// 设置密码
		entity.setPassword(newPwd);
		// 获得原Bean
		entity = service.get(User.class, entity.getId());
		// 判断是否原始密码
		// 设置新密码
		entity.setPassword(newPwd);
		// 修改
		service.update(entity);
		// 返回成功
		return callback(SUCCESS);
	}

	/**
	 * 注册用户
	 * @return
	 * @throws Exception
	 */
	public String register() throws Exception {
		// 注册ip
		entity.setRegisterIp(getIp());
		// 创建时间
		entity.setTime(DateUtil.getTime());
		// 是否Email验证
		if (SiteParams.USER_VERIFY_EMAIL) {
			// 设置状态无效
			entity.setState(0);
			// 发生激活信
		}
		// 添加
		service.insert(entity);
		// 获得用户ID
		int id = entity.getId();
		// 注册成功
		if (id > 0) {
			// 是否Email验证
			if (SiteParams.USER_VERIFY_EMAIL) {
				// 获得激活码
				String activeCoding = Encrypts.encrypt(entity.getId() + StringConstants.AMP + entity.getEmail());
				// 邮件标题
				String subject = SiteParams.USER_VERIFY_EMAIL_SUBJECT.replaceAll(SiteParams.USER_VERIFY_EMAIL_NAME, entity.getName());
				// 获得Url
				String url = SiteParams.USER_VERIFY_EMAIL_ACTION + activeCoding;
				// 邮件正文
				String content = SiteParams.USER_VERIFY_EMAIL_CONTENT.replaceAll(SiteParams.USER_VERIFY_EMAIL_URL, url);
				// 发生激活信
				EmailEngine.send(entity.getEmail(), subject, content);
			} else {
				// 添加用户登录信息
				LoginEngine.addLogin(getRequest(), getResponse(), entity, getLoginTime());
			}
		}
		// 返回
		return callback(id > 0 ? SUCCESS : ERROR);
	}

	/**
	 * 激活用户
	 * @return
	 * @throws Exception
	 */
	public String active() throws Exception {
		// 获得验证码解析后的字符串数组
		String[] temp = Decrypts.decrypt(activeCoding).split(StringConstants.AMP);
		// 获得用户ID
		int userId = Conversion.toInt(temp[0]);
		// 获得Email
		String email = temp[1];
		// 根据id获得用户实体
		if (userId > 0) {
			// 获得用户实体
			User user = new User();
			// 设置属性
			user.setId(userId);
			user.setEmail(email);
			// 返回用户实体
			entity = user;
		}
		// 判断激活码是否正确
		if (entity != null && entity.getId() > 0) {
			// 设置状态为有效
			entity.setState(1);
			// 修改用户实体
			return callback(EmptyUtil.isEmpty(service.update(entity)) ? SUCCESS : ERROR);
		} else {
			// 验证码错误 返回到错误页
			return callback(ERROR);
		}
	}

	/**
	 * 添加用户
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		// 加密密码
		((User) entity).setPassword(((User) entity).getPassword());
		// 返回到成功页
		return super.add();
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
