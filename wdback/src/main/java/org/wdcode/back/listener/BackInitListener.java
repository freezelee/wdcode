package org.wdcode.back.listener;

import java.io.File;

import javax.servlet.ServletContextEvent;

import org.wdcode.back.constants.BackConstants;
import org.wdcode.back.params.BackParams;
import org.wdcode.back.template.TemplateEngine;
import org.wdcode.site.listener.InitListener;

/**
 * 后台初始化监听器
 * @author WD
 * @since JDK7
 * @version 1.0 2009-12-07
 */
public class BackInitListener extends InitListener {
	/**
	 * 初始化资源
	 */
	public void contextInitialized(ServletContextEvent event) {
		// 执行父方法
		super.contextInitialized(event);
		// 设置后台主题路径
		event.getServletContext().setAttribute(BackConstants.THEME_BACK, BackParams.BACK_THEME);
		// 设置ClassPath
		TemplateEngine.classPath = event.getServletContext().getRealPath("WEB-INF/classes") + File.separator;
		// 加载后台模板
		TemplateEngine.init();
	}
}