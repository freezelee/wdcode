package org.wdcode.web.params;

import org.wdcode.common.constants.ArrayConstants;
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
	private final static String		PREFIX	= "socket";																		// 前缀
	/**
	 * Mina服务器开关
	 */
	public final static boolean		POWER	= Params.getBoolean(PREFIX + ".power", false);
	/**
	 * Mina服务器开关
	 */
	public final static boolean		SPRING	= Params.getBoolean(PREFIX + ".spring", false);
	/**
	 * Mina服务器名称数组
	 */
	public final static String[]	NAMES	= Params.getStringArray(PREFIX + ".names", new String[] { StringConstants.EMPTY });

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
	 * 获得Socket心跳检测时间 单位秒<br/>
	 * 需在配置文件中配置<br/>
	 * <h2>配置方式如下: <br/>
	 * Properties: socket.heart = ? <br/>
	 * XML: {@literal <socket><heart>?</heart></socket>}</h2>
	 * @return 获得Socket心跳检测时间 单位秒
	 */
	public static int getHeart(String name) {
		return Params.getInt(Params.getKey(PREFIX, name, "heart"));
	}

	/**
	 * 获得Socket类型server或则client 只有host和type=client是才是客户端<br/>
	 * 需在配置文件中配置<br/>
	 * <h2>配置方式如下: <br/>
	 * Properties: socket.*.type = ? <br/>
	 * XML: {@literal <socket><*><type>?</type></*></socket>}</h2>
	 * @return socket客户端连接服务器
	 */
	public static String getType(String name) {
		return Params.getString(Params.getKey(PREFIX, name, "type"));
	}

	/**
	 * 获得socket处理handler<br/>
	 * 需在配置文件中配置<br/>
	 * <h2>配置方式如下: <br/>
	 * Properties: socket.*.handler = ? <br/>
	 * XML: {@literal <socket><*><handler>?</handler></*></socket>}</h2>
	 * @return socket处理handler
	 */
	public static String[] getHandler(String name) {
		return Params.getStringArray(Params.getKey(PREFIX, name, "handler"), ArrayConstants.STRING_EMPTY);
	}

	/**
	 * 获得socket处理handler包<br/>
	 * 需在配置文件中配置<br/>
	 * <h2>配置方式如下: <br/>
	 * Properties: socket.*.package = ? <br/>
	 * XML: {@literal <socket><*><package>?</package></*></socket>}</h2>
	 * @return socket处理handler包
	 */
	public static String[] getPackage(String name) {
		return Params.getStringArray(Params.getKey(PREFIX, name, "package"), ArrayConstants.STRING_EMPTY);
	}

	/**
	 * 私有构造
	 */
	private SocketParams() {}
}
