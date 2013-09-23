package org.wdcode.web.socket;

/**
 * Socket 套接字 Session接口
 * @author WD
 * @since JDK6
 * @version 1.0 2013-9-18
 */
public interface Session {
	/**
	 * 发送数据
	 * @param b 字节数组
	 * @return 返回数据
	 */
	byte[] send(byte[] b);

	/**
	 * 接收数据
	 * @return 接收到的数据
	 */
	byte[] accept();
}
