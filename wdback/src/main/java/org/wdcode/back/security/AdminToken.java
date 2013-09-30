package org.wdcode.back.security;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.wdcode.back.params.BackParams;
import org.wdcode.back.po.Admin;
import org.wdcode.back.po.Authority;
import org.wdcode.base.service.SuperService;
import org.wdcode.common.lang.Conversion;
import org.wdcode.common.util.EmptyUtil;
import org.wdcode.core.json.JsonEngine;
import org.wdcode.site.token.AuthToken;
import org.wdcode.site.token.LoginToken;

/**
 * Spring Security 登录凭证
 * @author WD
 * @since JDK7
 * @version 1.0 2013-1-7
 */
public final class AdminToken extends LoginToken implements UserDetails, AuthToken {
	private static final long	serialVersionUID	= -3399985197865994512L;
	// 密码
	private String				password;
	// 权限
	private List<Authority>		authorities;
	// 是否启用
	private boolean				enabled;
	// 管理员实体
	private Admin				admin;

	public AdminToken(Admin admin, SuperService service, int time) {
		this.admin = admin;
		// 对权限和菜单赋值
		if (BackParams.ADMIN.equals(admin.getName())) {
			// 是创建者 获得权限
			authorities = service.all(Authority.class);
		} else {
			// 不是创建者 权限ID不为空
			if (admin.getRoleId() != null) {
				// 获得权限
				authorities = service.eq(Authority.class, "roleId", admin.getRoleId(), -1, -1);
			}
		}
		this.id = admin.getId();
		this.name = admin.getName();
		this.password = admin.getPassword();
		this.time = time;
		this.enabled = Conversion.toInt(admin.getState()) == 1;
	}

	/**
	 * 获得管理员
	 * @return 管理员
	 */
	public Admin getAdmin() {
		return admin;
	}

	@Override
	public boolean isLogin() {
		return !EmptyUtil.isEmpty(name) && time > 0;
	}

	@Override
	public List<Authority> getAuthorities() {
		return authorities;
	}

	@Override
	public int getTime() {
		return time;
	}

	@Override
	public int getId() {
		return id;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return name;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public String toString() {
		return JsonEngine.toJson(this);
	}
}
