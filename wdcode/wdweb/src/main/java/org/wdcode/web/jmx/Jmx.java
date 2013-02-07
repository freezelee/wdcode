package org.wdcode.web.jmx;

import org.wdcode.common.interfaces.Close;

/**
 * JMX客户端接口
 * @author WD
 * @since JDK7
 * @version 1.0 2010-07-02
 */
public interface Jmx extends Close { 
	/**
	 * 创建标准MBean代理.
	 * @param mbeanName 代理名
	 * @param mBeanInterface 代理接口
	 * @return 代理对象
	 */
	<T> T createMBeanProxy(String mbeanName, Class<T> mBeanInterface);

	/**
	 * 按属性名直接读取MBean属性(无MBean的Class文件时使用). attributeName首字母大写.
	 * @param mbeanName 代理名
	 * @param attributeName 属性名
	 * @return 属性值
	 */
	Object getAttribute(String mbeanName, String attributeName);

	/**
	 * 按属性名直接设置MBean属性(无MBean的Class文件时使用). attributeName首字母大写.
	 * @param mbeanName 代理名
	 * @param attributeName 属性名
	 * @param value 属性值
	 */
	void setAttribute(String mbeanName, String attributeName, Object value);

	/**
	 * 按方法名直接调用MBean方法(无MBean的Class文件时使用). 调用的方法无参数时的简写函数.
	 * @param mbeanName 代理名
	 * @param methodName 方法名
	 */
	void inoke(String mbeanName, String methodName);

	/**
	 * 按方法名直接调用MBean方法(无MBean的Class文件时使用).
	 * @param mbeanName 代理名
	 * @param methodName 方法名
	 * @param paramClassNames 所有参数的Class名全称数组.
	 * @param paramValues 参数值
	 * @return 返回对象
	 */
	Object invoke(String mbeanName, String methodName, String[] paramClassNames, Object[] paramValues);

	/**
	 * 按方法名直接调用MBean方法(无MBean的Class文件时使用).
	 * @param mbeanName 代理名
	 * @param methodName 方法名
	 * @param paramClasses 所有参数的Class数组.
	 * @param paramValues 参数值
	 * @return 返回对象
	 */
	Object invoke(String mbeanName, String methodName, Class<?>[] paramClasses, Object[] paramValues);
}
