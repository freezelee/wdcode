package org.wdcode.web.jmx.factory;

import org.wdcode.core.factory.FactoryKey;
import org.wdcode.web.jmx.Jmx;
import org.wdcode.web.jmx.impl.JmxImpl;
import org.wdcode.web.params.WebParams;

/**
 * JMX客户端接口工厂
 * @author WD
 * @since JDK7
 * @version 1.0 2010-12-07
 */
public final class JmxFactory extends FactoryKey<String, Jmx> {
	// 工厂
	private final static JmxFactory	FACTORY	= new JmxFactory();

	/**
	 * 获得Jmx
	 * @return Jmx
	 */
	public static Jmx getJmx() {
		return FACTORY.getInstance();
	}

	/**
	 * 获得Jmx
	 * @return Jmx
	 */
	public static Jmx getJmx(String service) {
		return FACTORY.getInstance(service);
	}

	/**
	 * 获得Jmx
	 * @return Jmx
	 */
	public static Jmx newJmx(String service, String userName, String passwd) {
		return FACTORY.newInstance(service, userName, passwd);
	}

	/**
	 * 实例化一个新对象
	 */
	public Jmx newInstance() {
		return newInstance(WebParams.JMX_SERVICE);
	}

	/**
	 * 实例化一个新对象
	 */
	public Jmx newInstance(String service) {
		return new JmxImpl(service);
	}

	/**
	 * 实例化一个新对象
	 */
	public Jmx newInstance(String service, String userName, String passwd) {
		return new JmxImpl(service, userName, passwd);
	}

	/**
	 * 私有构造
	 */
	private JmxFactory() {}
}
