package org.wdcode.common.params;

import java.util.concurrent.ConcurrentMap;

import org.wdcode.common.constants.StringConstants;
import org.wdcode.common.interfaces.Config;
import org.wdcode.common.lang.Conversion;
import org.wdcode.common.lang.Maps;
import org.wdcode.common.util.BeanUtil;
import org.wdcode.common.util.EmptyUtil;

/**
 * 系统配置信息 内部使用 在config.xml或config.properties 中配置,本包实现可配置功能<br/>
 * <h2>注: 使用本类需依赖 Apache Commons-Configuration包,如果没有此包配置将不生效 而直接使用默认值</h2> 参数操作类
 * @author WD
 * @since JDK7
 * @version 1.0 2010-01-05
 */
public final class Params {
	// 保存键值并发Map
	private final static ConcurrentMap<String, Object>	MAP	= Maps.getConcurrentMap();
	// 读写配置文件接口
	private static Config								config;

	static {
		config = (Config) BeanUtil.invoke("org.wdcode.core.config.ConfigFactory", "getConfig", null, null);
	}

	/**
	 * 设置配置文件
	 * @param config 配置文件
	 */
	public static void setConfig(Config config) {
		Params.config = config;
	}

	/**
	 * 设置属性 如果这个key存在 会替换掉这个属性
	 * @param key 属性key
	 * @param value 属性value
	 */
	public static void setProperty(String key, Object value) {
		// 设置Map里的值
		MAP.put(key, value);
		// 配置不为空
		if (!EmptyUtil.isEmpty(config)) {
			config.setProperty(key, value);
		}
	}

	/**
	 * 设置属性 如果这个key存在 会替换掉这个属性
	 * @param key 属性key
	 * @param value 属性value
	 */
	public static Object getProperty(String key, Object defaultValue) {
		// 先从Map中获得值
		Object value = MAP.get(key);
		// 判断value为null
		if (value == null) {
			// 读取配置
			try {
				value = EmptyUtil.isEmpty(config) ? defaultValue : config.getProperty(key);
			} catch (Exception e) {}
			// 添加到Map中
			MAP.put(key, value == null ? value = defaultValue : value);
		}
		// 返回值
		return value;
	}

	/**
	 * 获得属性value
	 * @param key 属性key
	 * @param defaultValue 默认值
	 * @return value
	 */
	public static String[] getStringArray(String key, String[] defaultValue) {
		// 先从Map中获得值
		String[] value = (String[]) MAP.get(key);
		// 判断value为null
		if (value == null) {
			// 读取配置
			try {
				value = config.getStringArray(key);
			} catch (Exception e) {}
			// 添加到Map中
			MAP.put(key, EmptyUtil.isEmpty(value) ? value = defaultValue : value);
		}
		// 返回值
		return value;
	}

	/**
	 * 获得属性value
	 * @param key 属性key
	 * @return value
	 */
	public static String getString(String key) {
		return getString(key, StringConstants.EMPTY);
	}

	/**
	 * 获得属性value
	 * @param key 属性key
	 * @param defaultValue 默认值
	 * @return value
	 */
	public static String getString(String key, String defaultValue) {
		return Conversion.toString(getProperty(key, Conversion.toString(defaultValue)));
	}

	/**
	 * 获得属性value
	 * @param key 属性key
	 * @param defaultValue 默认值
	 * @return value
	 */
	public static boolean getBoolean(String key, boolean defaultValue) {
		return Conversion.toBoolean(getProperty(key, defaultValue));
	}

	/**
	 * 获得属性value
	 * @param key 属性key
	 * @param defaultValue 默认值
	 * @return value
	 */
	public static int getInt(String key, int defaultValue) {
		return Conversion.toInt(getProperty(key, defaultValue));
	}

	/**
	 * 获得属性value
	 * @param key 属性key
	 * @param defaultValue 默认值
	 * @return value
	 */
	public static long getLong(String key, long defaultValue) {
		return Conversion.toLong(getProperty(key, defaultValue));
	}

	/**
	 * 获得属性value
	 * @param key 属性key
	 * @param defaultValue 默认值
	 * @return value
	 */
	public static short getShort(String key, short defaultValue) {
		return Conversion.toShort(getProperty(key, defaultValue));
	}

	/**
	 * 清楚Map中的键值
	 */
	public static void clear() {
		MAP.clear();
	}

	/**
	 * 保存配置
	 */
	public static void write() {
		config.write();
	}

	/**
	 * 获得指定标识下第一级.和.之间的Key列表
	 * @param prefix 标识
	 * @return key列表
	 */
	public static String[] getKeys(String prefix) {
		return config.getKeys(prefix);
	}

	/**
	 * 根据前后缀和和名称获得键
	 * @param prefix 前缀
	 * @param suffix 后缀
	 * @param name 名称
	 * @return 替换后的键
	 */
	public static String getKey(String prefix, String name, String suffix) {
		// 声明字符串缓存
		StringBuilder sb = new StringBuilder(prefix);
		// 判断名称是否为空
		if (!EmptyUtil.isEmpty(name)) {
			sb.append(StringConstants.POINT);
			sb.append(name);
		}
		sb.append(StringConstants.POINT);
		sb.append(suffix);
		// 返回替换后的键
		return sb.toString();
	}

	/**
	 * 私有构造
	 */
	private Params() {}
}
