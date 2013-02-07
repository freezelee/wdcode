package org.wdcode.web.mina;

import org.apache.mina.core.filterchain.IoFilter;
import org.apache.mina.core.service.IoHandler;
import org.apache.mina.core.service.IoServiceListener;
import org.wdcode.common.interfaces.Close;

/**
 * Mina服务器端
 * @author WD
 * @since JDK7
 * @version 1.0 2011-07-06
 */
public interface MinaServer extends Close {
	/**
	 * 设置处理器
	 * @param handler 处理器
	 */
	void setHandler(IoHandler handler);

	/**
	 * 添加过滤器
	 * @param listener 过滤器
	 */
	void addListener(IoServiceListener listener);

	/**
	 * 添加过滤器
	 * @param name 过滤属性名
	 * @param filter 过滤器
	 */
	void addListener(String name, IoFilter filter);

	/**
	 * 连接服务器
	 * @param port 端口
	 */
	public void bind(int port);

	/**
	 * 连接服务器 使用配置文件
	 */
	public void bind();
}
