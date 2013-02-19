package org.wdcode.web.engine;

import org.wdcode.web.jmx.Jmx;
import org.wdcode.web.jmx.factory.JmxFactory;

/**
 * JMX处理器 使用工厂默认对象
 * @author WD
 * @since JDK7
 * @version 1.0 2012-08-28
 */
public final class JmxEngine {
	// JMX接口
	private final static Jmx	JMX;

	/**
	 * 私有构造
	 */
	static {
		JMX = JmxFactory.getJmx();
	}

	/**
	 * 创建标准MBean代理.
	 * @param mbeanName 代理名
	 * @param mBeanInterface 代理接口
	 * @return 代理对象
	 */
	public static <T> T createMBeanProxy(String mbeanName, Class<T> mBeanInterface) {
		return JMX.createMBeanProxy(mbeanName, mBeanInterface);
	}

	/**
	 * 按属性名直接读取MBean属性(无MBean的Class文件时使用). attributeName首字母大写.
	 * @param mbeanName 代理名
	 * @param attributeName 属性名
	 * @return 属性值
	 */
	public static Object getAttribute(String mbeanName, String attributeName) {
		return JMX.getAttribute(mbeanName, attributeName);
	}

	/**
	 * 按属性名直接设置MBean属性(无MBean的Class文件时使用). attributeName首字母大写.
	 * @param mbeanName 代理名
	 * @param attributeName 属性名
	 * @param value 属性值
	 */
	public static void setAttribute(String mbeanName, String attributeName, Object value) {
		JMX.setAttribute(mbeanName, attributeName, value);
	}

	/**
	 * 按方法名直接调用MBean方法(无MBean的Class文件时使用). 调用的方法无参数时的简写函数.
	 * @param mbeanName 代理名
	 * @param methodName 方法名
	 */
	public void inoke(String mbeanName, String methodName) {
		JMX.inoke(mbeanName, methodName);
	}

	/**
	 * 按方法名直接调用MBean方法(无MBean的Class文件时使用).
	 * @param mbeanName 代理名
	 * @param methodName 方法名
	 * @param paramClassNames 所有参数的Class名全称数组.
	 * @param paramValues 参数值
	 * @return 返回对象
	 */
	public static Object invoke(String mbeanName, String methodName, String[] paramClassNames, Object[] paramValues) {
		return JMX.invoke(mbeanName, methodName, paramClassNames, paramValues);
	}

	/**
	 * 按方法名直接调用MBean方法(无MBean的Class文件时使用).
	 * @param mbeanName 代理名
	 * @param methodName 方法名
	 * @param paramClasses 所有参数的Class数组.
	 * @param paramValues 参数值
	 * @return 返回对象
	 */
	public static Object invoke(String mbeanName, String methodName, Class<?>[] paramClasses, Object[] paramValues) {
		return JMX.invoke(mbeanName, methodName, paramClasses, paramValues);
	}

	/**
	 * 私有构造
	 */
	private JmxEngine() {}
}
