package org.wdcode.web.mina;

import org.apache.mina.core.service.IoHandler;
import org.apache.mina.core.service.IoServiceListener;
import org.wdcode.common.interfaces.Close;

/**
 * Mina客户端
 * @author WD
 * @since JDK7
 * @version 1.0 2011-07-06
 */
public interface MinaClient extends Close {
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
	 * 连接服务器 使用配置文件
	 */
	void connect();

	/**
	 * 连接服务器
	 * @param host 主机
	 * @param port 端口
	 */
	void connect(String host, int port);

	/**
	 * 发送信息
	 * @param mess 信息
	 */
	void send(Object mess);
}
