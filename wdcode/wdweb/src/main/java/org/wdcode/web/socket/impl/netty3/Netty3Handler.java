package org.wdcode.web.socket.impl.netty3;

import org.wdcode.common.constants.StringConstants;
import org.wdcode.common.lang.Conversion;
import org.wdcode.common.util.StringUtil;
import org.wdcode.web.socket.Handler;
import org.wdcode.web.socket.Process;
import org.wdcode.web.socket.Session;
import org.wdcode.web.socket.simple.Processor;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author WD
 * @since JDK7
 * @version 1.0 2013-12-15
 */
public final class Netty3Handler extends SimpleChannelInboundHandler<ByteBuf> {
	// 消息处理器
	private Process	process	= new Processor();

	/**
	 * 添加要处理的Handler
	 * @param handler
	 */
	public void addHandler(Handler<?>... handler) {
		process.addHandler(handler);
	}

	@Override
	public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
		process.closed(getSesson(ctx));
		super.channelUnregistered(ctx);
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		process.connected(getSesson(ctx), new Netty3Buffer());
		super.channelActive(ctx);
	}

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
		// 声明字节流
		byte[] data = new byte[msg.readableBytes()];
		// 读取字节流
		msg.readBytes(data);
		// 交给数据处理器
		process.process(getSesson(ctx), data);
	}

	/**
	 * 获得包装Session
	 * @param session Mina session
	 * @return
	 */
	private Session getSesson(ChannelHandlerContext ctx) {
		// 获得SessionId
		int id = Conversion.toInt(StringUtil.subString(ctx.name(), StringConstants.WELL));
		// 获得包装Session
		Session s = process.getSession(id);
		// 如果为null
		if (s == null) {
			// 实例化包装Session
			s = new Netty3Session(id, ctx.channel());
		}
		// 返回
		return s;
	}
}
