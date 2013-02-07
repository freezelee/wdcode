package org.wdcode.web.netty;

import org.jboss.netty.channel.ChannelHandler;
import org.wdcode.common.interfaces.Close;

/**
 * netty服务端接口
 * @author WD
 * @since JDK7
 * @version 1.0 2011-07-07
 */
public interface NettyServer extends Close {
	/**
	 * 设置处理器
	 * @param handler 处理器
	 */
	void setHandler(ChannelHandler handler);

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
