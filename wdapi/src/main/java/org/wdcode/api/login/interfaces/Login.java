package org.wdcode.api.login.interfaces;

import org.wdcode.base.entity.EntityLogin;
import org.wdcode.site.token.AuthToken;

/**
 * 开放API登录接口
 * @author WD
 * @since JDK7
 * @version 1.0 2013-11-4
 */
public interface Login {
	/**
	 * 登录网站名称
	 * @return 登录网站名称
	 */
	String getName();

	/**
	 * 获得描述
	 * @return 描述
	 */
	String getDetail();

	/**
	 * 登录
	 * @return 登录凭证
	 */
	String login();

	/**
	 * 授权登录凭证
	 * @param token 第三方登录凭证
	 * @param login 登录实体
	 * @return 登录凭证实体
	 */
	AuthToken token(String token, EntityLogin login);
}
