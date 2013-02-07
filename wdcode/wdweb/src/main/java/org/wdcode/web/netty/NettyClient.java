package org.wdcode.web.netty;

import org.jboss.netty.channel.ChannelHandler;
import org.wdcode.common.interfaces.Close;

/**
 * netty客户端接口
 * @author WD
 * @since JDK7
 * @version 1.0 2011-07-07
 */
public interface NettyClient extends Close {
	/**
	 * 设置处理器
	 * @param handler 处理器
	 */
	void setHandler(ChannelHandler handler);

	/**
	 * 发送信息
	 * @param mess 信息
	 */
	void send(Object mess);

	/**
	 * 连接服务器 使用配置文件
	 */
	void connect();

	/**
	 * 连接服务器
	 * @param host 主机
	 * @param port 端口
	 */
	void connect(String host, int port);
}
