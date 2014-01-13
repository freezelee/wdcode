package org.wdcode.security.action;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Scope;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.WebApplicationContext;
import org.wdcode.back.action.BackAction;
import org.wdcode.back.params.BackParams;
import org.wdcode.security.token.AdminToken;
import org.wdcode.site.engine.LoginEngine;

/**
 * 安全后台Action
 * @author WD
 * @since JDK7
 * @version 1.0 2014-1-12
 */
@Controller
@Scope(WebApplicationContext.SCOPE_REQUEST)
public class SecurityAction extends BackAction {
	@PostConstruct
	protected void init() {
		// 父类初始化
		super.init();
		// 获得认证凭证
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		// 认证不为空
		if (auth == null) {
			// 如果是后台主页
			if (!request.getServletPath().equals(BackParams.BACK_PATH + "index.htm")) {
				// 凭证置空
				token = LoginEngine.empty();
			}
		} else {
			// 获得登录管理员
			Object principal = auth.getPrincipal();
			if (principal instanceof AdminToken) {
				token = ((AdminToken) principal);
			} else {
				// 凭证置空
				token = LoginEngine.empty();
			}
		}
	}
}
