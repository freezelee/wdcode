package org.wdcode.back.action;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Scope;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.WebApplicationContext;
import org.wdcode.back.constants.BackConstants;
import org.wdcode.back.params.BackParams;
import org.wdcode.back.po.Admin;
import org.wdcode.back.security.AdminToken;
import org.wdcode.back.template.TemplateEngine;
import org.wdcode.base.entity.Entity;
import org.wdcode.common.constants.StringConstants;
import org.wdcode.common.io.FileUtil;
import org.wdcode.common.params.Params;
import org.wdcode.common.lang.Conversion;
import org.wdcode.common.lang.Lists;
import org.wdcode.common.util.EmptyUtil;
import org.wdcode.site.action.LoginAction;
import org.wdcode.site.engine.LoginEngine;
import org.wdcode.web.constants.HttpConstants;
import org.wdcode.web.util.HttpUtil;

/**
 * 后台Action
 * @author WD
 * @since JDK7
 * @version 1.0 2012-07-19
 */
@Controller
@Scope(WebApplicationContext.SCOPE_REQUEST)
public class BackAction extends LoginAction<Entity, Admin> {
	// 序列化ID
	private static final long		serialVersionUID	= -7800766281669330292L;
	// 模板
	protected Map<String, Object>	template;
	// 模版名
	private String					themeName;
	// 模版目录列表
	private List<String>			themes;

	@PostConstruct
	protected void init() {
		// 父类初始化
		super.init();
		// 获得认证凭证
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		// 认证不为空
		if (auth == null) {
			// 清除登录凭证
			LoginEngine.removeLogin(request, response, getLoginKey());
		} else {
			// 凭证置空
			token = LoginEngine.empty();
			// 获得登录管理员
			Object principal = auth.getPrincipal();
			if (principal instanceof AdminToken) {
				token = ((AdminToken) principal);
				// 如果显示未登录 添加新登录凭证
				LoginEngine.addLogin(request, response, ((AdminToken) principal).getAdmin(), -1);
			}
		}
	}

	/**
	 * 主入口
	 */
	public String main() throws Exception {
		// 是否开启
		if (BackParams.BACK_URL) {
			// 获得上次url
			url = Conversion.toString(get(StringConstants.URL));
			// 设置空url
			set(StringConstants.URL, null);
		}
		// 如果为空设置欢迎页
		url = EmptyUtil.isEmpty(url) ? BackConstants.WELCOME : url;
		// 返回成功页
		return SUCCESS;
	}

	/**
	 * 静态化页面
	 * @return
	 * @throws Exception
	 */
	public String statics() throws Exception {
		// 网址
		String url = HttpConstants.HTTP + request.getLocalAddr() + request.getContextPath() + StringConstants.BACKSLASH;
		// 保存路径
		String path = getRealPath(StringConstants.BACKSLASH);
		String name = entity.getClass().getSimpleName().toLowerCase();
		// 静态化实体
		for (Entity e : service.list(entity, 0, 0)) {
			String f = name + StringConstants.BACKSLASH + e.getKey() + ".html";
			HttpUtil.saveToFile(url + f, path + f);
		}
		// 保存主页
		HttpUtil.saveToFile(url + "index.htm", path + "index.html");
		// 返回成功
		return SUCCESS;
	}

	/**
	 * 跳转到修改页
	 * @return 跳转
	 * @throws Exception
	 */
	public String toThemes() throws Exception {
		// 获得模版路径
		String path = getRealPath(File.separator) + "back";
		// 获得目录文件
		File theme = FileUtil.getFile(path);
		// 获得目录下所有文件
		File[] files = theme.listFiles();
		// 临时文件
		File temp = null;
		// 判断文件不为空
		if (!EmptyUtil.isEmpty(files)) {
			// 获得模版目录列表
			themes = Lists.getList(files.length);
			// 循环目录
			for (int i = 0; i < files.length; i++) {
				// 获得文件
				temp = files[i];
				// 判断是目录
				if (temp.isDirectory()) {
					// 添加到列表
					themes.add(temp.getName());
				}
			}
		}
		// 返回成功
		return SUCCESS;
	}

	/**
	 * 修改模版
	 * @return 跳转
	 * @throws Exception
	 */
	public String themes() throws Exception {
		// 替换内存的模版名
		getServletContext().setAttribute(BackConstants.THEME_BACK, themeName);
		// 设置到参数中
		Params.setProperty(BackConstants.BACK_THEME_KEY, themeName);
		// 写配置文件
		Params.write();
		// 返回成功
		return SUCCESS;
	}

	/**
	 * 加载模板
	 * @return
	 * @throws Exception
	 */
	public String templates() throws Exception {
		// 重新初始化模板
		TemplateEngine.init();
		// 返回成功
		return addMessage(SUCCESS);
	}

	/**
	 * 获得模板
	 * @return
	 */
	public Map<String, Object> getTemplate() {
		// 判断如果模板为空
		if (template == null) {
			// 获得模板
			Map<String, Map<String, Object>> mapModule = TemplateEngine.TEMPLATES.get(module);
			// 获得方面模板
			template = mapModule.get(mode);
			// 如果template为空 默认返回list
			template = EmptyUtil.isEmpty(template) ? mapModule.get(LIST) : template;
		}
		// 返回模板
		return template;
	}

	/**
	 * 获得模版目录列表
	 * @return 模版目录列表
	 */
	public List<String> getThemes() {
		return themes;
	}

	/**
	 * 设置模版目录列表
	 * @param themes 模版目录列表
	 */
	public void setThemes(List<String> themes) {
		this.themes = themes;
	}

	/**
	 * 获得模版名
	 * @return 模版名
	 */
	public String getThemeName() {
		return themeName;
	}

	/**
	 * 设置模版名
	 * @param themeName 模版名
	 */
	public void setThemeName(String themeName) {
		this.themeName = themeName;
	}
}
