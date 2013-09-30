package org.wdcode.web.params;

import org.wdcode.common.constants.ArrayConstants;
import org.wdcode.common.params.Params;

/**
 * Netty读取配置
 * @author WD
 * @since JDK7
 * @version 1.0 2011-07-07
 */
public final class NettyParams {
	// 前缀
	private final static String		PREFIX	= "netty";
	/**
	 * Netty服务器开关
	 */
	public final static boolean		POWER	= Params.getBoolean(PREFIX + ".power", false);
	/**
	 * Netty服务器名称数组
	 */
	public final static String[]	NAMES	= Params.getStringArray(PREFIX + ".names", ArrayConstants.STRING_EMPTY);

	/**
	 * 获得Netty连接监听端口<br/>
	 * 需在配置文件中配置<br/>
	 * <h2>配置方式如下: <br/>
	 * Properties: netty.*.port = ? <br/>
	 * XML: {@literal <netty><*><port>?</port></*></netty>}</h2>
	 * @return Netty连接监听端口
	 */
	public static int getPort(String name) {
		return Params.getInt(Params.getKey(PREFIX, name, "port"), 0);
	}

	/**
	 * 获得Netty客户端连接服务器<br/>
	 * 需在配置文件中配置<br/>
	 * <h2>配置方式如下: <br/>
	 * Properties: netty.*.host = ? <br/>
	 * XML: {@literal <netty><*><host>?</host></*></netty>}</h2>
	 * @return Netty客户端连接服务器
	 */
	public static String getHost(String name) {
		return Params.getString(Params.getKey(PREFIX, name, "host"));
	}

	/**
	 * 获得Netty类型server或则client 只有host和type=client是才是客户端<br/>
	 * 需在配置文件中配置<br/>
	 * <h2>配置方式如下: <br/>
	 * Properties: netty.*.type = ? <br/>
	 * XML: {@literal <netty><*><type>?</type></*></netty>}</h2>
	 * @return Netty客户端连接服务器
	 */
	public static String getType(String name) {
		return Params.getString(Params.getKey(PREFIX, name, "type"));
	}

	/**
	 * 获得Netty处理handler<br/>
	 * 需在配置文件中配置<br/>
	 * <h2>配置方式如下: <br/>
	 * Properties: netty.*.handler = ? <br/>
	 * XML: {@literal <netty><*><handler>?</handler></*></netty>}</h2>
	 * @return Netty处理handler
	 */
	public static String getHandler(String name) {
		return Params.getString(Params.getKey(PREFIX, name, "handler"));
	}

	/**
	 * 获得Netty编码器<br/>
	 * 需在配置文件中配置<br/>
	 * <h2>配置方式如下: <br/>
	 * Properties: netty.*.encoder = ? <br/>
	 * XML: {@literal <netty><*><encoder>?</encoder></*></netty>}</h2>
	 * @return Netty编码器
	 */
	public static String getEncoder(String name) {
		return Params.getString(Params.getKey(PREFIX, name, "encoder"));
	}

	/**
	 * 获得Netty编码器<br/>
	 * 需在配置文件中配置<br/>
	 * <h2>配置方式如下: <br/>
	 * Properties: netty.*.decoder = ? <br/>
	 * XML: {@literal <netty><*><decoder>?</decoder></*></netty>}</h2>
	 * @return Netty解码器
	 */
	public static String getDecoder(String name) {
		return Params.getString(Params.getKey(PREFIX, name, "decoder"));
	}

	/**
	 * 私有构造
	 */
	private NettyParams() {}
}
