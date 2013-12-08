package org.wdcode.web.params;

import org.wdcode.common.constants.StringConstants;
import org.wdcode.common.params.Params;

/**
 * Socket读取配置
 * @author WD
 * @since JDK7
 * @version 1.0 2011-07-07
 */
public final class SocketParams {
	/* Socket使用 */
	private final static String	PREFIX; // 前缀
	private final static String	HOST;	// 主机
	private final static String	PORT;	// 端口
	private final static String	PARSE;	// 解析

	/**
	 * 静态初始化
	 */
	static {
		/* Socket使用 */
		PREFIX = "socket"; // 前缀
		HOST = "host"; // 主机
		PORT = "port"; // 端口
		PARSE = "parse"; // 解析
	}

	/* Socket使用 */
	private static String		host;	// 主机
	private static int			port;	// 端口
	private static String		parse;	// 解析

	/**
	 * 静态初始化
	 */
	static {
		/* Socket使用 */
		host = StringConstants.EMPTY; // 主机
		port = 0; // 端口
		parse = "nio"; // 解析
	}

	/**
	 * 获得Socket使用解析包<br/>
	 * 需在配置文件中配置<br/>
	 * <h2>配置方式如下: <br/>
	 * Properties: socket.parse = ? <br/>
	 * XML: {@literal <socket><parse>?</parse></socket>}</h2>
	 * @return 获得Socket使用解析包
	 */
	public static String getParse(String name) {
		return Params.getString(Params.getKey(PREFIX, name, PARSE), parse);
	}

	/**
	 * 获得Socket连接服务器<br/>
	 * 需在配置文件中配置<br/>
	 * <h2>配置方式如下: <br/>
	 * Properties: socket.host = ? <br/>
	 * XML: {@literal <socket><host>?</host></socket>}</h2>
	 * @return 获得Socket连接服务器
	 */
	public static String getHost(String name) {
		return Params.getString(Params.getKey(PREFIX, name, HOST), host);
	}

	/**
	 * 获得Socket连接端口<br/>
	 * 需在配置文件中配置<br/>
	 * <h2>配置方式如下: <br/>
	 * Properties: socket.port = ? <br/>
	 * XML: {@literal <socket><port>?</port></socket>}</h2>
	 * @return 获得Socket连接端口
	 */
	public static int getPort(String name) {
		return Params.getInt(Params.getKey(PREFIX, name, PORT), port);
	}

	/**
	 * 私有构造
	 */
	private SocketParams() {}
}
