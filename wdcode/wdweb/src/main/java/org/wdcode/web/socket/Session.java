package org.wdcode.web.socket;

import org.wdcode.common.interfaces.Close;
import org.wdcode.web.socket.message.Message;

/**
 * Socket Session
 * @author WD
 * @since JDK7
 * @version 1.0 2013-11-28
 */
public interface Session extends Close {
	/**
	 * 写入数据
	 * @param message
	 */
	public void write(Message message);

	/**
	 * 写入数据
	 * @param id 指令
	 * @param message 消息
	 */
	public void write(int id, Object message);
}
