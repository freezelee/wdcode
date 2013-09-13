package org.wdcode.back.security;

import java.util.List;
import java.util.Map;

import org.springframework.security.core.userdetails.UserDetails;
import org.wdcode.back.params.BackParams;
import org.wdcode.back.po.Admin;
import org.wdcode.back.po.Authority;
import org.wdcode.back.po.Menu;
import org.wdcode.back.po.Operate;
import org.wdcode.back.po.Role;
import org.wdcode.base.service.SuperService;
import org.wdcode.common.lang.Conversion;
import org.wdcode.common.lang.Lists;
import org.wdcode.common.lang.Maps;
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
	private static final long			serialVersionUID	= -3399985197865994512L;
	// 密码
	private String						password;
	// 权限
	private List<Authority>				authorities;
	// 菜单
	private Map<Integer, List<Menu>>	menus;
	// 操作
	private List<Operate>				operates;
	// 是否启用
	private boolean						enabled;
	// 管理员实体
	private Admin						admin;

	public AdminToken(Admin admin, SuperService service, int time) {
		this.admin = admin;
		// 对权限和菜单赋值
		if (BackParams.ADMIN.equals(admin.getName())) {
			// 是创建者 获得权限
			authorities = service.all(Authority.class);
			// 获得菜单
			menus = getMenus(service.all(Menu.class));
			// 获得操作
			operates = service.all(Operate.class);
		} else {
			// 不是创建者 权限ID不为空
			if (admin.getRoleId() != null) {
				// 获得角色
				Role role = service.get(Role.class, admin.getRoleId());
				// 获得权限
				authorities = role.getAuthorities();
				// 获得菜单
				menus = getMenus(role.getMenus());
				// 获得操作
				operates = role.getOperates();
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

	/**
	 * 获得菜单
	 * @param list 菜单列表
	 * @return 菜单
	 */
	private Map<Integer, List<Menu>> getMenus(List<Menu> list) {
		// 声明菜单列表
		Map<Integer, List<Menu>> menus = Maps.getMap();
		// 列表不为空
		if (!EmptyUtil.isEmpty(list)) {
			// 循环赋值
			for (Menu menu : list) {
				int id = Conversion.toInt(menu.getMenuId());
				List<Menu> ls = Lists.getList(menus.get(id));
				ls.add(menu);
				menus.put(id, ls);
			}
		}
		// 返回菜单
		return menus;
	}

	@Override
	public boolean isLogin() {
		return !EmptyUtil.isEmpty(name) && time > 0;
	}

	@Override
	public List<Authority> getAuthorities() {
		return authorities;
	}

	public Map<Integer, List<Menu>> getMenus() {
		return menus;
	}

	public List<Operate> getOperates() {
		return operates;
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
