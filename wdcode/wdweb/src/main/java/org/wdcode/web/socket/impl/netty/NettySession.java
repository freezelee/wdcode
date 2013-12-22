package org.wdcode.web.socket.impl.netty;

import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.Channel;

import org.wdcode.web.socket.Session;
import org.wdcode.web.socket.base.BaseSession;

/**
 * netty Session实现
 * @author WD
 * @since JDK7
 * @version 1.0 2013-12-20
 */
public class NettySession extends BaseSession implements Session {
	// 通道
	private Channel	channel;

	/**
	 * 构造
	 * @param id sessionId
	 * @param channel
	 */
	public NettySession(int id, Channel channel) {
		this.id = id;
		this.channel = channel;
	}

	@Override
	public void close() {
		channel.disconnect();
		channel.close();
	}

	@Override
	public boolean isConnect() {
		return channel.isActive();
	}

	@Override
	public boolean isClose() {
		return !channel.isOpen();
	}

	@Override
	protected void send(byte[] data) {
		channel.writeAndFlush(PooledByteBufAllocator.DEFAULT.buffer().writeBytes(data));
	}
}
