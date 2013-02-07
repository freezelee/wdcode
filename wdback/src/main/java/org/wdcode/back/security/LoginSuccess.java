package org.wdcode.back.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.wdcode.site.engine.LoginEngine;

/**
 * Spring Security 登录成功处理器
 * @author WD
 * @since JDK6
 * @version 1.0 2013-1-10
 */
@Component
public final class LoginSuccess extends SavedRequestAwareAuthenticationSuccessHandler { 
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
		super.onAuthenticationSuccess(request, response, authentication);
		// 写入登录凭证
		LoginEngine.addLogin(request, response, ((AdminToken) authentication.getPrincipal()).getAdmin(), -1);
	}
}
