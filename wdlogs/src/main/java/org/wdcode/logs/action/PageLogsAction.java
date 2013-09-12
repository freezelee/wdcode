package org.wdcode.logs.action;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.WebApplicationContext;
import org.wdcode.base.entity.EntityLogin;
import org.wdcode.common.io.StreamUtil;
import org.wdcode.common.lang.Conversion;
import org.wdcode.common.util.EmptyUtil;
import org.wdcode.common.util.DateUtil;   
import org.wdcode.logs.params.LogsParams;
import org.wdcode.logs.po.PageLogs;
import org.wdcode.site.action.LoginAction;
import org.wdcode.web.util.RequestUtil;

/**
 * 页面日志Action
 * @author WD
 * @since JDK7
 * @version 1.0 2011-04-05
 */
@Controller
@Scope(WebApplicationContext.SCOPE_REQUEST)
public class PageLogsAction extends LoginAction<PageLogs, EntityLogin> {
	// 序列化ID
	private static final long	serialVersionUID	= -9081711038252503304L;

	/**
	 * 进入页面
	 * @return
	 * @throws Exception
	 */
	public String in() throws Exception {
		// 判断是否开启页面访问日志记录
		if (LogsParams.PAGE_LOGS && !isFilter()) {
			// 获得request
			HttpServletRequest request = getRequest();
			// 设置日志属性
			entity.setIp(RequestUtil.getIp(request));
			entity.setLanguage(RequestUtil.getLanguage(request));
			entity.setUserAgent(RequestUtil.getUserAgent(request));
			entity.setUserId(token.getId());
			entity.setTime(DateUtil.getTime());
			// 添加日志
			service.insert(entity);
			// 返回日志ID
			StreamUtil.write(getResponse().getOutputStream(), Conversion.toString(entity.getKey()));
		}
		// 返回null
		return null;
	}

	/**
	 * 离开页面
	 * @return
	 * @throws Exception
	 */
	public String out() throws Exception {
		// 判断是否开启页面访问日志记录
		if (LogsParams.PAGE_LOGS && !isFilter()) {
			// 获得日志实体
			PageLogs pageLogs = service.get(PageLogs.class, entity.getKey());
			// 设置离开时间
			pageLogs.setOutTime(DateUtil.getTime());
			// 更新日志
			service.update(pageLogs);
		}
		// 返回null
		return null;
	}

	/**
	 * 是否过滤
	 * @return 是否过滤
	 */
	private boolean isFilter() {
		// 获得不过滤IP
		String[] ips = LogsParams.PAGE_LOGS_IP;
		// 获得提交IP
		String ip = getIp();
		// 数组不为空
		if (!EmptyUtil.isEmpty(ips)) {
			// 循环判断
			for (int i = 0; i < ips.length; i++) {
				if (ip.indexOf(ips[i]) > -1) {
					return true;
				}
			}
		}
		// 返回否
		return false;
	}
}
