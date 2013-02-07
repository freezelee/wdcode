package org.wdcode.common.util;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import org.wdcode.common.log.Logs;

/**
 * 关于Class的一些操作
 * @author WD
 * @since JDK7
 * @version 1.0 2010-01-20
 */
public final class ClassUtil {
	/**
	 * 获得指定类型的泛型
	 * @param type 指定的类型
	 * @return 这个类的泛型
	 */
	public static Class<?>[] getGenericClass(Type type) {
		// 获得类型类型数组
		Type[] types = ((ParameterizedType) type).getActualTypeArguments();
		// 声明Class数组
		Class<?>[] clazzs = new Class<?>[types.length];
		// 循环
		for (int i = 0; i < types.length; i++) {
			// 强制转换
			clazzs[i] = (Class<?>) types[i];
		}
		// 返回数组
		return clazzs;
	}

	/**
	 * 获得指定类型的泛型
	 * @param type 指定的类型
	 * @param index 索引
	 * @return 这个类型的泛型
	 */
	public static <T> Class<T> getGenericClass(Type type, int index) {
		return (Class<T>) ((ParameterizedType) type).getActualTypeArguments()[index];
	}

	/**
	 * 加载类
	 * @param className 类名
	 * @return 获得的类
	 */
	public static Class<?> loadClass(String className) {
		// 声明类
		Class<?> theClass = null;
		try {
			// 获得类
			theClass = Class.forName(className);
		} catch (ClassNotFoundException e1) {
			try {
				// 当前线程获得类
				theClass = Thread.currentThread().getContextClassLoader().loadClass(className);
			} catch (ClassNotFoundException e2) {
				try {
					// 使用当前类获得类
					theClass = ClassUtil.class.getClassLoader().loadClass(className);
				} catch (ClassNotFoundException e3) {
					Logs.warn(e3);
					return null;
				}
			}
		}
		// 返回类
		return theClass;
	}

	/**
	 * 获得Class
	 * @param className Class名称
	 * @return Class
	 */
	public static Class<?> forName(String className) {
		try {
			return Class.forName(className);
		} catch (Exception e) {
			// 记录日志
			Logs.warn(e);
			// 返回null
			return null;
		}
	}

	/**
	 * 实例化对象
	 * @param className 类名
	 * @return 实例化对象
	 */
	public static Object newInstance(String className) {
		try {
			return forName(className).newInstance();
		} catch (Exception e) {
			// 记录日志
			Logs.warn(e);
			// 返回null
			return null;
		}
	}

	/**
	 * 实例化对象
	 * @param clazz 类
	 * @return 实例化对象
	 */
	public static <T> T newInstance(Class<T> clazz) {
		try {
			return clazz.newInstance();
		} catch (Exception e) {
			// 记录日志
			Logs.warn(e);
			// 返回null
			return null;
		}
	}

	/**
	 * 私有构造
	 */
	private ClassUtil() {}
}
