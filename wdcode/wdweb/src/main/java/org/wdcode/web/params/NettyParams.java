package org.wdcode.web.params;

import org.wdcode.common.params.Params;

/**
 * Mina读取配置
 * @author WD
 * @since JDK7
 * @version 1.0 2011-07-07
 */
public final class NettyParams {
	/* Mina使用 */
	private final static String	PREFIX; // 前缀
	private final static String	HOST;	// 主机
	private final static String	PORT;	// 端口

	/**
	 * 静态初始化
	 */
	static {
		/* Mina使用 */
		PREFIX = "netty"; // 前缀
		HOST = "host"; // 主机
		PORT = "port"; // 端口
	}

	/* Mina使用 */
	private static String		host;	// 主机
	private static int			port;	// 端口

	/**
	 * 静态初始化
	 */
	static {
		/* Mina使用 */
		host = "127.0.0.1"; // 主机
		port = 6666; // 端口
	}

	/**
	 * 获得Netty连接服务器<br/>
	 * 需在配置文件中配置<br/>
	 * <h2>配置方式如下: <br/>
	 * Properties: mina.host = ? <br/>
	 * XML: {@literal <mina><host>?</host></mina>}</h2>
	 * @return 获得Netty连接服务器
	 */
	public static String getHost(String name) {
		return Params.getString(Params.getKey(PREFIX, name, HOST), host);
	}

	/**
	 * 获得Netty连接端口<br/>
	 * 需在配置文件中配置<br/>
	 * <h2>配置方式如下: <br/>
	 * Properties: mina.port = ? <br/>
	 * XML: {@literal <mina><port>?</port></mina>}</h2>
	 * @return 获得Netty连接端口
	 */
	public static int getPort(String name) {
		return Params.getInt(Params.getKey(PREFIX, name, PORT), port);
	}

	/**
	 * 私有构造
	 */
	private NettyParams() {}
}
