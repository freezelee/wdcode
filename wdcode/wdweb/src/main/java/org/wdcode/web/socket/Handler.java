package org.wdcode.web.socket;

/**
 * 回调方法
 * @author WD
 * @since JDK6
 * @version 1.0 2013-09-25
 */
public interface Handler {
	/**
	 * 发送数据
	 * @param socket session
	 * @param b 字节数组
	 * @return 返回数据
	 */
	void send(Session session, byte[] b);

	/**
	 * 接收数据
	 * @param socket session
	 * @param b 字节数组
	 * @return 接收到的数据
	 */
	void accept(Session session, byte[] b);
}
