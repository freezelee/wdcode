package org.wdcode.web.socket;

import org.wdcode.common.interfaces.Close;

/**
 * Socket 服务器
 * @author WD
 * @since JDK7
 * @version 1.0 2013-11-28
 */
public interface Server extends Close {
	/**
	 * 添加要处理的Handler
	 * @param h
	 */
	void addHandler(Handler h);

	/**
	 * 启动服务器监听
	 */
	void start();
}
