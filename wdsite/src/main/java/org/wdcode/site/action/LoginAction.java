package org.wdcode.site.action;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.wdcode.base.action.SuperAction;
import org.wdcode.base.entity.Entity;
import org.wdcode.base.entity.EntityLogin;
import org.wdcode.base.entity.EntityUserId;
import org.wdcode.common.lang.Conversion;
import org.wdcode.common.util.EmptyUtil;
import org.wdcode.site.engine.LoginEngine;
import org.wdcode.site.params.SiteParams;
import org.wdcode.site.po.Entitys;
import org.wdcode.site.token.AuthToken;
import org.wdcode.web.util.VerifyCodeUtil;

/**
 * 登录Action
 * @author WD
 * @since JDK7
 * @version 1.0 2009-07-14
 */
public class LoginAction<E extends Entity, L extends EntityLogin> extends SuperAction<E> {
	// 序列化 ID
	private static final long	serialVersionUID	= 5542364549112574333L;
	// 用户实体
	private L					user;
	// 验证码
	private String				verifyCode;
	// 保存属性时间
	private boolean				autoLogin;
	// 验证登录标识
	protected AuthToken			token;

	@PostConstruct
	protected void init() {
		// 父类初始化
		super.init();
		// 获得登录信息
		token = LoginEngine.getLoginBean(context.getRequest(), getLoginKey());
		// 如果查询自己的数据 添加登录用户名
		if (entity == null && entityClass != null && EntityUserId.class.isAssignableFrom(entityClass)) {
			entity = context.getBean(module, entityClass);
		}
		if (entity instanceof EntityUserId) {
			((EntityUserId) entity).setUserId(token.getId());
		}
		// 如果实体为空 并且 模块名和模式名不同
		if (isEntity && entity == null && !module.equals(mode)) {
			entity = (E) new Entitys(module);
		}
	}

	/**
	 * 获得验证登录标识
	 * @return 验证登录标识
	 */
	public AuthToken getToken() {
		return token;
	}

	/**
	 * 添加实体
	 * @param e
	 * @return
	 */
	protected E add(E e) {
		e = super.add(e);
		// 判断实体类型
		if (e instanceof EntityUserId) {
			((EntityUserId) e).setUserId(token.getId());
		}
		// 返回E
		return e;
	}

	/**
	 * 主页
	 */
	public String index() throws Exception {
		if (token.isLogin()) {
			return SUCCESS;
		} else {
			return LOGIN;
		}
	}

	/**
	 * 执行退出
	 */
	public String logout() throws Exception {
		// 移除登录信息
		LoginEngine.removeLogin(getRequest(), getResponse(), getLoginKey());
		// 返回登录页
		return callback(SUCCESS);
	}

	/**
	 * 用户登录方法
	 */
	public String login() throws Exception {
		// 验证验证码 判断验证码都为空 跳过验证码检查
		if (!VerifyCodeUtil.check(getRequest(), getResponse(), verifyCode)) {
			// 添加错误信息
			addFieldError("verify.code.error");
			// 返回登陆页
			return callback(INPUT);
		}
		// 查询获得用户实体
		L bean = service.get(user);
		// 登录标识
		boolean is = false;
		// 获得用户ID
		int uid = bean == null ? 0 : Conversion.toInt(bean.getKey());
		// 判断用户是否存在
		if (!EmptyUtil.isEmpty(bean) && uid > 0) {
			// 判断用户名和密码相等
			if (user.getPassword().equals(bean.getPassword())) {
				// 返回成功
				is = true;
			}
		}
		// 登录验证
		if (is) {
			// 添加用户登录信息
			LoginEngine.addLogin(getRequest(), getResponse(), bean, getLoginTime());
			// 登录成功
			return callback(SUCCESS);
		} else {
			// 添加错误信息
			addFieldError("login.fail");
			// 登录失败
			return callback(LOGIN);
		}
	}

	/**
	 * 获得验证码
	 * @return 验证码
	 */
	public String getVerifyCode() {
		return verifyCode;
	}

	/**
	 * 设置验证码
	 * @param verifyCode 验证码
	 */
	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}

	/**
	 * 是否登录
	 * @return 是否自动登录
	 */
	public String isLogin() throws Exception {
		return callback(token.isLogin());
	}

	/**
	 * 是否登录
	 * @return 是否自动登录
	 */
	public String token() throws Exception {
		return callback(token);
	}

	/**
	 * 是否自动登录
	 * @return 是否自动登录
	 */
	public boolean isAutoLogin() {
		return autoLogin;
	}

	/**
	 * 设置是否自动登录
	 * @param autoLogin 是否自动登录
	 */
	public void setAutoLogin(boolean autoLogin) {
		this.autoLogin = autoLogin;
	}

	/**
	 * 获得用户实体
	 * @return 用户实体
	 */
	public L getUser() {
		return user;
	}

	/**
	 * 设置登录实体
	 * @param user 登录实体
	 */
	@Autowired
	public void setUser(L user) {
		this.user = user;
	}

	/**
	 * 获得登录Key
	 * @return 登录Key
	 */
	public String getLoginKey() {
		return user.getClass().getSimpleName();
	}

	/**
	 * 获得登录保存时间
	 * @return 登录保存时间
	 */
	protected int getLoginTime() {
		return autoLogin ? SiteParams.LOGIN_MAX_AGE : SiteParams.LOGIN_MIN_AGE;
	}
}
