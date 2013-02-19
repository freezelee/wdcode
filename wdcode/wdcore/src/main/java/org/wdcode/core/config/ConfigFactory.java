package org.wdcode.core.config;

import org.wdcode.core.factory.FactoryKey;
import org.wdcode.common.interfaces.Config;

/**
 * 获得配置管理类ConfigManage的工厂<br/>
 * 使用buildXXX(X)获得ConfigManage实例<br/>
 * <h2>注: 使用本类需依赖 Apache Commons-Configuration包</h2>
 * @see org.wdcode.common.config.ConfigManage
 * @author WD
 * @since JDK7
 * @version 1.0 2009-03-25
 */
public final class ConfigFactory extends FactoryKey<String, Config> {
	// 工厂
	private final static ConfigFactory	FACTORY	= new ConfigFactory();

	/**
	 * 获得Config
	 * @return config
	 */
	public static Config getConfig() {
		return FACTORY.getInstance();
	}

	/**
	 * 获得Config
	 * @param key 键
	 * @return config
	 */
	public static Config getConfig(String key) {
		return FACTORY.getInstance(key);
	}

	/**
	 * 实例化一个新对象
	 */
	public Config newInstance(String key) {
		return new ConfigFile(key);
	}

	/**
	 * 实例化一个新对象
	 */
	public Config newInstance() {
		return newInstance("config.xml");
	}

	/**
	 * 私有构造 禁止外部实例化
	 */
	private ConfigFactory() {}
}
