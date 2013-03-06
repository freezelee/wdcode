package org.wdcode.back.listener;

import java.io.File;

import javax.servlet.ServletContextEvent;
 
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
		// 设置ClassPath
		TemplateEngine.classPath = event.getServletContext().getRealPath("WEB-INF/classes") + File.separator;
		// 加载后台模板
		TemplateEngine.init();
	}
}