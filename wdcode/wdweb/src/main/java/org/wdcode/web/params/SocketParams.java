package org.wdcode.web.params;

import org.wdcode.common.params.Params;

/**
 * Socket读取配置
 * @author WD
 * @since JDK7
 * @version 1.0 2011-07-07
 */
public final class SocketParams {
	/* Socket使用 */
	private final static String	PREFIX	= "socket"; // 前缀

	/**
	 * 获得Socket使用解析包<br/>
	 * 需在配置文件中配置<br/>
	 * <h2>配置方式如下: <br/>
	 * Properties: socket.parse = ? <br/>
	 * XML: {@literal <socket><parse>?</parse></socket>}</h2>
	 * @return 获得Socket使用解析包
	 */
	public static String getParse(String name) {
		return Params.getString(Params.getKey(PREFIX, name, "parse"));
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
		return Params.getString(Params.getKey(PREFIX, name, "host"));
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
		return Params.getInt(Params.getKey(PREFIX, name, "port"));
	}

	/**
	 * 私有构造
	 */
	private SocketParams() {}
}
