package org.wdcode.common.util;

import java.io.File;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.wdcode.common.constants.StringConstants;
import org.wdcode.common.lang.Lists;

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
			return null;
		}
	}

	/**
	 * 指定包下 指定类的实现
	 * @param packageName 包名
	 * @param cls 指定类
	 * @return 类列表
	 */
	public static List<Class<?>> getAssignedClass(String packageName, Class<?> cls) {
		// 声明类列表
		List<Class<?>> classes = Lists.getList();
		// 循环包下所有类
		for (Class<?> c : getPackageClasses(packageName)) {
			// 是本类实现 并且不是本类
			if (cls.isAssignableFrom(c) && !cls.equals(c)) {
				classes.add(c);
			}
		}
		// 返回列表
		return classes;
	}

	/**
	 * 获得指定包下的所有Class
	 * @param packageName 报名
	 * @return 类列表
	 */
	public static List<Class<?>> getPackageClasses(String packageName) {
		// 声明返回类列表
		List<Class<?>> classes = Lists.getList();
		// 转换报名为路径格式
		String path = packageName.replace('.', '/');
		// 获得资包所在路径目录
		File dir = new File(ResourceUtil.getResource(path).getFile());
		// 如果目录不存在
		if (!dir.exists()) {
			// 返回列表
			return classes;
		}
		// 循环目录下的所有文件与目录
		for (File f : dir.listFiles()) {
			// 如果是目录
			if (f.isDirectory()) {
				// 迭代调用本方法 获得类列表
				classes.addAll(getPackageClasses(packageName + StringConstants.POINT + f.getName()));
			} else {
				// 获得文件名
				String name = f.getName();
				// 如果是class文件
				if (name.endsWith(".class")) {
					try {
						// 反射出类对象 并添加到列表中
						classes.add(Class.forName(packageName + StringConstants.POINT + StringUtil.subString(name, 0, name.length() - 6)));
					} catch (ClassNotFoundException e) {}
				}
			}
		}
		// 返回类列表
		return classes;
	}

	/**
	 * 私有构造
	 */
	private ClassUtil() {}
}
