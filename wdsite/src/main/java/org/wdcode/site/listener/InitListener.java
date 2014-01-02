package org.wdcode.site.listener;

import java.net.URL;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.xml.DOMConfigurator;
import org.wdcode.common.constants.StringConstants;
import org.wdcode.common.params.Params;
import org.wdcode.common.util.EmptyUtil;
import org.wdcode.common.util.ResourceUtil;
import org.wdcode.core.config.ConfigFactory;
import org.wdcode.core.engine.QuartzEngine;
import org.wdcode.core.params.QuartzParams;
import org.wdcode.site.params.SiteParams;
import org.wdcode.web.params.SocketParams;
import org.wdcode.web.socket.Sockets;
import org.wdcode.base.engine.StaticsEngine;
import org.wdcode.base.params.BaseParams;

/**
 * 初始化监听器 在web.xml中配置 configLocation 配置文件位置,参数文件默认在classPath下
 * <p>
 * <listener> <listener-class> org.springframework.web.context.ContextLoaderListener
 * </listener-class> </listener> <context-param> <param-name>config</param-name>
 * <param-value>../config/config.xml</param-value> </context-param>
 * <p>
 * @author WD
 * @since JDK7
 * @version 1.0 2009-09-08
 */
public class InitListener implements ServletContextListener {
	/**
	 * 初始化资源
	 */
	public void contextInitialized(ServletContextEvent event) {
		// 获得Servlet上下文
		ServletContext context = event.getServletContext();
		// 设置路径
		setPath(context);
		// 设置配置文件
		setConfig(context.getInitParameter("config"));
		// 设置log4j配置
		setLog4j(context);
		// 配置数据源配置文件路径
		System.setProperty("dataSourceConfig", BaseParams.DATA_SOURCE_CONFIG);

		// 是否静态化
		if (SiteParams.POWER) {
			StaticsEngine.start();
		}
		// 是否开启任务
		if (QuartzParams.POWER) {
			QuartzEngine.init();
		}
		// 是否开启socket
		if (SocketParams.POWER) {
			Sockets.init();
		}
	}

	/**
	 * 销毁资源
	 */
	public void contextDestroyed(ServletContextEvent event) {}

	/**
	 * 设置log4j配置
	 */
	private void setLog4j(ServletContext context) {
		// 获得log4j配置文件url
		URL url = ResourceUtil.getResource("config/log4j.xml");
		// 如果url不为空
		if (url != null) {
			DOMConfigurator.configure(url);
		}
	}

	/**
	 * 设置路径
	 */
	private void setPath(ServletContext context) {
		// 工程路径Key
		String path = "path";
		// 设置工程路径为path
		context.setAttribute(path, context.getContextPath());
		// 配置系统路径
		System.setProperty(path, context.getRealPath(StringConstants.EMPTY));
	}

	/**
	 * 设置配置文件
	 * @param config 配置文件
	 */
	private void setConfig(String fileName) {
		Params.setConfig(EmptyUtil.isEmpty(fileName) ? ConfigFactory.getConfig() : ConfigFactory.getConfig(fileName));
	}
}
