package org.wdcode.web.socket;

import org.wdcode.common.interfaces.BytesBean;
import org.wdcode.common.interfaces.Close;

/**
 * Socket 服务器
 * @author WD
 * @since JDK7
 * @version 1.0 2013-11-28
 */
public interface Server extends Close {
	/**
	 * 服务器名
	 */
	String getName();

	/**
	 * 添加要处理的Handler
	 * @param h
	 */
	void addHandler(Handler<BytesBean> h);

	/**
	 * 添加编码解码器
	 * @param codec
	 */
	public void addCodec(Codec<BytesBean> codec);

	/**
	 * 启动服务器监听
	 */
	void start();
}
