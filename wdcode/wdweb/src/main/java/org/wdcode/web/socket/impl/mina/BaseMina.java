package org.wdcode.web.socket.impl.mina;

import org.apache.mina.core.service.IoService;
import org.apache.mina.transport.socket.SocketSessionConfig;
import org.wdcode.web.socket.Handler;
import org.wdcode.web.socket.Socket;

/**
 * 基础Mina设置
 * @author WD
 * @since JDK7
 * @version 1.0 2013-12-20
 */
public abstract class BaseMina implements Socket {
	// 名称
	protected String		name;
	// MinaHandler
	protected MinaHandler	handler;

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void addHandler(Handler<?>... h) {
		handler.addHandler(h);
	}

	/**
	 * 添加配置
	 * @param service 服务
	 * @param handler 处理器
	 * @param codec 编码器
	 */
	protected void config(IoService service) {
		// 实例化handler
		handler = new MinaHandler();
		// 获得Session配置
		SocketSessionConfig sc = (SocketSessionConfig) service.getSessionConfig();
		// 设置每一个非主监听连接的端口可以重用
		sc.setReuseAddress(true);
		// 设置最小读取缓存
		sc.setMinReadBufferSize(64);
		// 设置输入缓冲区的大小
		sc.setReceiveBufferSize(1024 * 8);
		// 设置输出缓冲区的大小
		sc.setSendBufferSize(1024 * 32);
		// flush函数的调用 设置为非延迟发送，为true则不组装成大包发送，收到东西马上发出
		sc.setTcpNoDelay(true);
		sc.setSoLinger(0);
		// 设置超时时间
		sc.setWriteTimeout(10000);
		sc.setWriterIdleTime(60);
		sc.setReaderIdleTime(30);
		sc.setBothIdleTime(180);
		// 绑定Mina服务器管理模块
		service.setHandler(handler);
	}
}
