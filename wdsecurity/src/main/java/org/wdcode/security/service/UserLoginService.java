package org.wdcode.security.service;

import javax.annotation.Resource;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.wdcode.back.po.Admin;
import org.wdcode.base.service.SuperService;
import org.wdcode.common.util.DateUtil;
import org.wdcode.security.token.AdminToken;

/**
 * 该类的主要作用是为Spring Security提供一个经过用户认证后的UserDetails。 该UserDetails包括用户名、密码、是否可用、是否过期等信息。
 * @author WD
 * @since JDK6
 * @version 1.0 2012-08-22
 */
@Component
public final class UserLoginService implements UserDetailsService {
	@Resource
	private SuperService	service;

	/**
	 * Spring Security 登录
	 */
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// 根据用户名查询EntityLogin实体
		Admin admin = service.get(Admin.class, "name", username);
		// 如果为空抛出用户名为空异常
		if (admin == null) {
			throw new UsernameNotFoundException(username);
		}
		// 声明返回凭证
		return new AdminToken(admin, service, DateUtil.getTime());
	}
}