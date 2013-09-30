package org.wdcode.web.params;

import org.wdcode.common.constants.ArrayConstants;
import org.wdcode.common.params.Params;

/**
 * Quartz任务读取参数
 * @author WD
 * @since JDK7
 * @version 1.0 2012-02-26
 */
public final class MinaParams {
	// 前缀
	private final static String		PREFIX	= "mina";
	/**
	 * Mina服务器开关
	 */
	public final static boolean		POWER	= Params.getBoolean(PREFIX + ".power", false);
	/**
	 * Mina服务器名称数组
	 */
	public final static String[]	NAMES	= Params.getStringArray(PREFIX + ".names", ArrayConstants.STRING_EMPTY);

	/**
	 * 获得Mina连接监听端口<br/>
	 * 需在配置文件中配置<br/>
	 * <h2>配置方式如下: <br/>
	 * Properties: mina.*.port = ? <br/>
	 * XML: {@literal <mina><*><port>?</port></*></mina>}</h2>
	 * @return Mina连接监听端口
	 */
	public static int getPort(String name) {
		return Params.getInt(Params.getKey(PREFIX, name, "port"), 0);
	}

	/**
	 * 获得Mina客户端连接服务器<br/>
	 * 需在配置文件中配置<br/>
	 * <h2>配置方式如下: <br/>
	 * Properties: mina.*.host = ? <br/>
	 * XML: {@literal <mina><*><host>?</host></*></mina>}</h2>
	 * @return Mina客户端连接服务器
	 */
	public static String getHost(String name) {
		return Params.getString(Params.getKey(PREFIX, name, "host"));
	}

	/**
	 * 获得Mina类型server或则client 只有host和type=client是才是客户端<br/>
	 * 需在配置文件中配置<br/>
	 * <h2>配置方式如下: <br/>
	 * Properties: mina.*.type = ? <br/>
	 * XML: {@literal <mina><*><type>?</type></*></mina>}</h2>
	 * @return Mina客户端连接服务器
	 */
	public static String getType(String name) {
		return Params.getString(Params.getKey(PREFIX, name, "type"));
	}

	/**
	 * 获得Mina处理handler<br/>
	 * 需在配置文件中配置<br/>
	 * <h2>配置方式如下: <br/>
	 * Properties: mina.*.handler = ? <br/>
	 * XML: {@literal <mina><*><handler>?</handler></*></mina>}</h2>
	 * @return Mina处理handler
	 */
	public static String getHandler(String name) {
		return Params.getString(Params.getKey(PREFIX, name, "handler"));
	}

	/**
	 * 获得Mina编码器<br/>
	 * 需在配置文件中配置<br/>
	 * <h2>配置方式如下: <br/>
	 * Properties: mina.*.codec = ? <br/>
	 * XML: {@literal <mina><*><codec>?</codec></*></mina>}</h2>
	 * @return Mina编码器
	 */
	public static String getCodec(String name) {
		return Params.getString(Params.getKey(PREFIX, name, "codec"));
	}

	/**
	 * 构造方法
	 */
	private MinaParams() {}
}
