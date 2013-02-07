package org.wdcode.site.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import org.wdcode.common.log.Logs;
import org.wdcode.common.io.StreamUtil; 
import org.wdcode.common.util.ResourceUtil;
import org.wdcode.web.util.HttpUtil;
import org.wdcode.web.util.ResponseUtil;
import org.wdcode.web.util.UrlUtil;

/**
 * 过滤wdui使用的js和css请求,过滤已/wdui开头的js和css请求
 * @author WD
 * @since JDK7
 * @version 1.0 2011-05-02
 */
public final class StaticFilter implements Filter {
	// 提交路径名
	private static String	urlPath;
	// 提交文件路径名
	private static String	filePath;

	/**
	 * 静态初始化
	 */
	static {
		urlPath = "/wdstatic";
		filePath = "static";
	}

	/**
	 * 初始化过滤器
	 */
	public void init(FilterConfig filterConfig) throws ServletException {}

	/**
	 * 执行过滤器
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// 转换Request
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		try {
			// 获得ServletPath
			String servletPath = httpRequest.getServletPath();
			// 获得/wdui在哪开始
			int pos = servletPath.indexOf(urlPath);
			// 判断是要过滤的路径
			if (pos > -1) {
				// 判断是要过滤的路径
				if (pos > 0) {
					servletPath = servletPath.substring(pos);
				}
				// 设置ContentType
				ResponseUtil.setContentType(response, HttpUtil.getContentType(servletPath));
				// 写入到客户端
				StreamUtil.write(response.getOutputStream(), UrlUtil.openStream(ResourceUtil.getResource(servletPath.replaceAll(urlPath, filePath))));
			}
		} catch (RuntimeException e) {
			Logs.warn(e);
		}
	}

	/**
	 * 销毁过滤器
	 */
	public void destroy() {}
}
