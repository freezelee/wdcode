package org.wdcode.base.interceptor;

import java.util.List;
import java.util.Map;

import org.wdcode.base.action.SuperAction;
import org.wdcode.base.params.SecurityParams;
import org.wdcode.common.lang.Maps;
import org.wdcode.common.util.EmptyUtil;

/**
 * 基于动态映射方法的安全拦截器 出来掉不合法的一些请求
 * @author WD
 * @since JDK7
 * @version 1.0 2013-12-25
 */
public final class SecurityInterceptor extends BasicInterceptor<SuperAction<?>> {
	private static final long			serialVersionUID	= -7879736892830147087L;
	// 方法对应实体Map
	private Map<String, List<String>>	methods;

	@Override
	public void init() {
		// 实例化Map
		methods = Maps.getMap();
		// 安全验证方法
		for (String method : SecurityParams.SECURITY_METHODS) {
			methods.put(method, SecurityParams.getModules(method));
		}
	}

	@Override
	protected boolean before(SuperAction<?> action) {
		// 获得执行的方法
		String method = action.getMethod();
		// 实体
		String module = action.getModule();
		// 实体玉方法相同 为自定义方法 直接通过
		if (module.equals(method)) {
			return true;
		}
		// 获得方法下的实体列表
		List<String> modules = methods.get(method);
		// 判断是可执行实体方法
		return EmptyUtil.isEmpty(modules) ? false : modules.contains(module);
	}
}
