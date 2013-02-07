package org.wdcode.web.socket.base;

import java.nio.channels.SocketChannel;

import org.wdcode.common.io.ChannelUtil;
import org.wdcode.common.lang.Bytes;

/**
 * 基础Nio Socket
 * @author WD
 * @since JDK7
 * @version 1.0 2011-10-20
 */
public abstract class BaseSocketNio {
	// 名称
	protected String		name;
	// Socket通道
	protected SocketChannel	socket;

	/**
	 * 构造方法
	 * @param name 名称
	 */
	public BaseSocketNio(String name) {
		this.name = name;
	}

	/**
	 * 获得数据
	 * @return 字节数组
	 */
	public byte[] accept() {
		acceptSocket();
		return ChannelUtil.read(socket);
	}

	/**
	 * 发生数据
	 * @param obj 数据
	 */
	public void send(Object obj) {
		acceptSocket();
		ChannelUtil.write(socket, Bytes.toBytes(obj));
	}

	/**
	 * 获得Socket
	 */
	protected void acceptSocket() {}
}
