package org.wdcode.web.socket.base;

import java.io.IOException;
import java.net.Socket;

import org.wdcode.common.constants.ArrayConstants;
import org.wdcode.common.io.StreamUtil;
import org.wdcode.common.lang.Bytes;
import org.wdcode.core.log.Logs;

/**
 * 基础Socket
 * @author WD
 * @since JDK7
 * @version 1.0 2011-10-20
 */
public abstract class BaseSocket {
	// 名称
	protected String	name;
	// JDK Socket
	protected Socket	socket;

	/**
	 * 构造方法
	 * @param name 名称
	 */
	public BaseSocket(String name) {
		this.name = name;
	}

	/**
	 * 获得数据
	 * @return 字节数组
	 */
	public byte[] accept() {
		try {
			acceptSocket();
			return StreamUtil.read(socket.getInputStream(), false);
		} catch (IOException e) {
			Logs.warn(e);
			return ArrayConstants.BYTES_EMPTY;
		}
	}

	/**
	 * 发生数据
	 * @param obj 数据
	 */
	public void send(Object obj) {
		try {
			acceptSocket();
			StreamUtil.write(socket.getOutputStream(), Bytes.toBytes(obj), false);
		} catch (IOException e) {
			Logs.error(e);
		}
	}

	/**
	 * 获得Socket连接
	 */
	protected void acceptSocket() {}
}
