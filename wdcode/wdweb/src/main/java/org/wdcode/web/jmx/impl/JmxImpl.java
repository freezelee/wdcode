package org.wdcode.web.jmx.impl;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.management.Attribute;
import javax.management.MBeanServerConnection;
import javax.management.MBeanServerInvocationHandler;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

import org.wdcode.common.log.Logs;
import org.wdcode.common.util.EmptyUtil;
import org.wdcode.web.jmx.Jmx;

/**
 * JMX客户端实现
 * @author WD
 * @since JDK7
 * @version 1.0 2010-07-02
 */
public final class JmxImpl implements Jmx {
	// 声明JMXConnector
	private JMXConnector			connector;
	// 声明MBeanServerConnection
	private MBeanServerConnection	connection;
	// 声明AtomicBoolean
	private AtomicBoolean			connected	= new AtomicBoolean(false);

	/**
	 * 无认证信息的构造函数.
	 * @param serviceUrl 服务url
	 */
	public JmxImpl(String serviceUrl) {
		initConnector(serviceUrl, null, null);
	}

	/**
	 * 带认证信息的构造函数.
	 * @param serviceUrl 服务url
	 * @param userName 用户名
	 * @param passwd 密码
	 */
	public JmxImpl(String serviceUrl, String userName, String passwd) {
		initConnector(serviceUrl, userName, passwd);
	}

	/**
	 * 断开JMX连接.
	 */
	public void close() {
		try {
			connector.close();
			connected.set(false);
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			connector = null;
			connection = null;
		}
	}

	/**
	 * 创建标准MBean代理.
	 * @param mbeanName 代理名
	 * @param mBeanInterface 代理接口
	 * @return 代理对象
	 */
	public <T> T createMBeanProxy(String mbeanName, Class<T> mBeanInterface) {
		// 判断代理名 不能为空
		if (EmptyUtil.isEmpty(mbeanName)) {
			return null;
		}
		// 构建对象
		ObjectName objectName = buildObjectName(mbeanName);
		// 创建代理对象
		return (T) MBeanServerInvocationHandler.newProxyInstance(connection, objectName, mBeanInterface, false);
	}

	/**
	 * 按属性名直接读取MBean属性(无MBean的Class文件时使用). attributeName首字母大写.
	 * @param mbeanName 代理名
	 * @param attributeName 属性名
	 * @return 属性值
	 */
	public Object getAttribute(String mbeanName, String attributeName) {
		// 判断代理名 不能为空
		if (EmptyUtil.isEmpty(mbeanName)) {
			return null;
		}
		// 判断代理名 不能为空
		if (EmptyUtil.isEmpty(attributeName)) {
			return null;
		}
		try {
			// 获得代理对象
			ObjectName objectName = buildObjectName(mbeanName);
			// 返回属性值
			return connection.getAttribute(objectName, attributeName);
		} catch (Exception e) {
			// 记录日志
			Logs.error(e);
			// 返回null
			return null;
		}
	}

	/**
	 * 按属性名直接设置MBean属性(无MBean的Class文件时使用). attributeName首字母大写.
	 * @param mbeanName 代理名
	 * @param attributeName 属性名
	 * @param value 属性值
	 */
	public void setAttribute(String mbeanName, String attributeName, Object value) {
		// 判断代理名 不能为空
		if (EmptyUtil.isEmpty(mbeanName)) {
			return;
		}
		// 判断代理名 不能为空
		if (EmptyUtil.isEmpty(attributeName)) {
			return;
		}
		try {
			// 获得代理对象
			ObjectName objectName = buildObjectName(mbeanName);
			// 获得生成
			Attribute attribute = new Attribute(attributeName, value);
			// 设置属性
			connection.setAttribute(objectName, attribute);
		} catch (Exception e) {
			// 记录日志
			Logs.error(e);
		}
	}

	/**
	 * 按方法名直接调用MBean方法(无MBean的Class文件时使用). 调用的方法无参数时的简写函数.
	 * @param mbeanName 代理名
	 * @param methodName 方法名
	 */
	public void inoke(String mbeanName, String methodName) {
		invoke(mbeanName, methodName, new String[] {}, new Object[] {});
	}

	/**
	 * 按方法名直接调用MBean方法(无MBean的Class文件时使用).
	 * @param mbeanName 代理名
	 * @param methodName 方法名
	 * @param paramClassNames 所有参数的Class名全称数组.
	 * @param paramValues 参数值
	 * @return 返回对象
	 */
	public Object invoke(String mbeanName, String methodName, String[] paramClassNames, Object[] paramValues) {
		// 判断代理名 不能为空
		if (EmptyUtil.isEmpty(mbeanName)) {
			return null;
		}
		// 判断代理名 不能为空
		if (EmptyUtil.isEmpty(methodName)) {
			return null;
		}
		try {
			// 构建代理对象
			ObjectName objectName = buildObjectName(mbeanName);
			// 调用方法
			return connection.invoke(objectName, methodName, paramValues, paramClassNames);
		} catch (Exception e) {
			// 记录日志
			Logs.error(e);
			// 返回null
			return null;
		}
	}

	/**
	 * 按方法名直接调用MBean方法(无MBean的Class文件时使用).
	 * @param mbeanName 代理名
	 * @param methodName 方法名
	 * @param paramClasses 所有参数的Class数组.
	 * @param paramValues 参数值
	 * @return 返回对象
	 */
	public Object invoke(String mbeanName, String methodName, Class<?>[] paramClasses, Object[] paramValues) {
		// 参数数组
		String[] paramClassNames = new String[paramClasses.length];
		// 循环获得类名
		for (int i = 0; i < paramClasses.length; i++) {
			paramClassNames[i] = paramClasses[i].getName();
		}
		// 调用本类方法
		return invoke(mbeanName, methodName, paramClassNames, paramValues);
	}

	/**
	 * 构造ObjectName对象,并转换其抛出的异常为unchecked exception.
	 * @param mbeanName 代理名
	 * @return 代理对象
	 */
	private ObjectName buildObjectName(String mbeanName) {
		try {
			return new ObjectName(mbeanName);
		} catch (MalformedObjectNameException e) {
			// 记录日志
			Logs.error(e);
			// 返回null
			return null;
		}
	}

	/**
	 * 连接远程JMX Server.
	 * @param serviceUrl 服务url
	 * @param userName 用户名
	 * @param passwd 密码
	 */
	private void initConnector(String serviceUrl, String userName, String passwd) {
		try {
			// 获得jmx服务地址
			JMXServiceURL url = new JMXServiceURL(serviceUrl);
			// 判断用户名是否为空
			if (!EmptyUtil.isEmpty(userName)) {
				// 为空 获得Map
				Map<String, String[]> environment = Collections.singletonMap(JMXConnector.CREDENTIALS, new String[] { userName, passwd });
				// 连接
				connector = JMXConnectorFactory.connect(url, environment);
			} else {
				// 不为空 直接连接
				connector = JMXConnectorFactory.connect(url);
			}
			// 获得服务链接
			connection = connector.getMBeanServerConnection();
			// 设置服务已连接
			connected.set(true);
		} catch (Exception e) {
			// 记录日志
			Logs.error(e);
		}
	}
}
