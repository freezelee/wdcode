package org.wdcode.api.login.impl;

import org.wdcode.api.login.interfaces.Login;
import org.wdcode.base.entity.EntityLogin;
import org.wdcode.site.token.AuthToken;

/**
 * 腾讯登录
 * @author WD
 * @since JDK7
 * @version 1.0 2013-11-4
 */
public class TencentLogin implements Login { 
	@Override
	public String getName() {
		return null;
	}

	@Override
	public String getDetail() {
		return null;
	}

	@Override
	public String login() {
		return null;
	}

	@Override
	public AuthToken token(String token, EntityLogin login) {
		return null;
	}
}
