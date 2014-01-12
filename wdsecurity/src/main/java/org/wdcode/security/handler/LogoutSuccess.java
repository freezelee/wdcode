package org.wdcode.security.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.stereotype.Component;
import org.wdcode.back.params.BackParams;
import org.wdcode.site.engine.LoginEngine;

/**
 * Spring Security 登录成功处理器
 * @author WD
 * @since JDK6
 * @version 1.0 2013-1-10
 */
@Component
public final class LogoutSuccess extends SimpleUrlLogoutSuccessHandler {

	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
		// 删除登录凭证
		LoginEngine.removeLogin(request, response, BackParams.BACK_LOGIN);
		setDefaultTargetUrl(BackParams.BACK_PATH);
		super.onLogoutSuccess(request, response, authentication);
	}
}
