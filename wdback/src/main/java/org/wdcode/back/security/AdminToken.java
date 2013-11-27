package org.wdcode.back.security;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.wdcode.back.params.BackParams;
import org.wdcode.back.po.Admin;
import org.wdcode.back.po.Authority;
import org.wdcode.base.service.SuperService;
import org.wdcode.common.lang.Conversion;
import org.wdcode.core.json.JsonEngine;
import org.wdcode.security.po.Menu;
import org.wdcode.security.po.Role;
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
	// 用户名
	private String				username;
	// 管理员实体
	private Admin				admin;
	// 角色
	private Role				role;

	public AdminToken(Admin admin, SuperService service, int time) {
		this.admin = admin;
		// 获得权限
		authorities = service.all(Authority.class);
		// 获得角色
		role = service.get(Role.class, Conversion.toInt(admin.getRoleId()));
		// 对权限和菜单赋值
		if (role != null && BackParams.ADMIN.equals(admin.getName())) {
			role.setMenus(service.all(Menu.class));
		}
		// // 对权限和菜单赋值
		// if (BackParams.ADMIN.equals(admin.getName())) {
		//
		// } else {
		// // 不是创建者 权限ID不为空
		// if (admin.getRoleId() != null) {
		// // 获得权限
		// authorities = service.eq(Authority.class, "roleId", admin.getRoleId(), -1, -1);
		// }
		// }
		this.id = admin.getId();
		this.password = admin.getPassword();
		this.username = admin.getName();
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
	public String getPassword() {
		return password;
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

	@Override
	public String getUsername() {
		return username;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
}
