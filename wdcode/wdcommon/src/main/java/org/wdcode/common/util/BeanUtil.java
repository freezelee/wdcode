package org.wdcode.common.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.wdcode.common.constants.StringConstants;

import org.wdcode.common.lang.Conversion;
import org.wdcode.common.lang.Lists;

/**
 * Bean工具类
 * @author WD
 * @since JDK7
 * @version 1.0 2010-06-24
 */
public final class BeanUtil {
	/**
	 * 拷贝属性
	 * @param source 原对象
	 * @param target 目标对象
	 * @return 目标对象
	 */
	public static <T> T copyProperties(T source, T target) {
		// 判读对象为空
		if (source == null || target == null) {
			return target;
		}
		// 循环字段
		for (Field field : source.getClass().getDeclaredFields()) {
			try {
				// 强行设置Field可访问.
				makeAccessible(field);
				// 设置字段值
				setFieldValue(target, field.getName(), field.get(source));
			} catch (Exception e) {}
		}
		// 返回对象
		return target;
	}

	/**
	 * 已反射方式给属性赋值
	 * @param dest 目标对象
	 * @param fieldName 属性名
	 * @param fieldValue 属性值
	 * @return 对象
	 */
	public static <T> T copyProperties(T dest, String fieldName, Object fieldValue) {
		// 设置字段值
		setFieldValue(dest, fieldName, fieldValue);
		// 返回对象
		return dest;
	}

	/**
	 * 把Map的Key与Object属性相同的字段赋值 就是把Map对应的值赋给Object
	 * @param dest 目标对象
	 * @param map 源对象
	 * @return dest 目标对象
	 */
	public static <T> T copyProperties(T dest, Map<String, ?> map) {
		// 循环Map的实体
		for (Map.Entry<String, ?> entry : map.entrySet()) {
			copyProperties(dest, entry.getKey(), entry.getValue());
		}
		// 返回对象
		return dest;
	}

	/**
	 * 把Map的Key与Class的实例属性相同的字段赋值 就是把Map对应的值赋给Object<br/>
	 * <h2>注: 此方法回返回Class的一个新实例对象</h2>
	 * @param dest 目标对象的Class用dest.newInstance()生成一个新的实例
	 * @param map 源对象
	 * @return dest 目标对象的新实例
	 */
	public static <T> T copyProperties(Class<T> dest, Map<String, ?> map) {
		return copyProperties(newInstance(dest), map);
	}

	/**
	 * 把Map的Key与Class的实例属性相同的字段赋值 就是把Map对应的值赋给Object<br/>
	 * @param dest 目标对象的E用dest.getClass().newInstance()生成一个新的实例
	 * @param list map对象列表
	 * @return List<E>转换后的对象
	 */
	public static <T> List<T> copyProperties(Class<T> dest, List<Map<String, Object>> list) {
		// 获得列表大小
		int size = list.size();
		// 获得列表
		List<T> ls = Lists.getList(size);
		// 是 ArrayList
		for (int i = 0; i < size; i++) {
			ls.add(copyProperties(dest, list.get(i)));
		}
		// 返回列表
		return ls;
	}

	/**
	 * 使用Class的newInstance()方法实例一个对象 封装异常为运行时异常
	 * @param dest 对象的类
	 * @return 实例的对象
	 */
	public static <T> T newInstance(Class<T> dest) {
		try {
			return dest.newInstance();
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 使用Class的newInstance()方法实例一个对象 封装异常为运行时异常
	 * @param dest 对象的类
	 * @return 实例的对象
	 */
	public static Object newInstance(String className) {
		return newInstance(ClassUtil.forName(className));
	}

	/**
	 * 反射调用get方法
	 * @param target 调用的对象
	 * @param name 属性名
	 * @return 调用返回的值
	 */
	public static Object invokeGetterMethod(Object target, String name) {
		return invokeMethod(target, StringUtil.getMethodName(StringConstants.GET, name), new Class[] {}, new Object[] {});
	}

	/**
	 * 反射调用Setter方法.使用value的Class来查找Setter方法.
	 * @param target 调用的对象
	 * @param name 属性名
	 * @param value 属性值
	 */
	public static void invokeSetterMethod(Object target, String name, Object value) {
		invokeSetterMethod(target, name, value, null);
	}

	/**
	 * 反射调用Setter方法
	 * @param target 调用的对象
	 * @param name 属性名
	 * @param value 属性值
	 * @param type 用于查找Setter方法,为空时使用value的Class替代.
	 */
	public static void invokeSetterMethod(Object target, String name, Object value, Class<?> type) {
		invokeMethod(target, StringUtil.getMethodName(StringConstants.SET, name), new Class[] { EmptyUtil.isEmpty(type) ? value.getClass() : type }, new Object[] { value });
	}

	/**
	 * 直接读取对象属性值, 无视private/protected修饰符, 不经过getter函数.
	 * @param list 列表
	 * @param fieldName 属性名
	 * @return 属性值
	 */
	public static List<Object> getFieldValues(Collection<?> list, String fieldName) {
		// 声明返回列表
		List<Object> ls = Lists.getList(list.size());
		// 循环添加
		for (Object e : list) {
			// 获得值
			Object val = BeanUtil.getFieldValue(e, fieldName);
			// 判断值是否为集合
			if (val instanceof Collection<?>) {
				ls.addAll((Collection<?>) val);
			} else {
				ls.add(val);
			}
		}
		// 返回列表
		return ls;
	}

	/**
	 * 直接读取对象属性值, 无视private/protected修饰符, 不经过getter函数.
	 * @param object 调用的对象
	 * @param fieldName 属性名
	 * @return 属性值
	 */
	public static Object getFieldValue(Object object, String fieldName) {
		// 如果有复杂字段
		if (fieldName.indexOf(StringConstants.POINT) > -1) {
			return getFieldValue(getFieldValue(object, StringUtil.subStringEnd(fieldName, StringConstants.POINT)), StringUtil.subString(fieldName, StringConstants.POINT));
		}
		// 获得字段
		Field field = getDeclaredField(object, fieldName);
		// 判断字段为空 返回null
		if (EmptyUtil.isEmpty(field)) {
			return null;
		}
		// 强行设置Field可访问.
		makeAccessible(field);
		// 声明对象 保存返回值
		Object result = null;
		try {
			// 获得字段值
			result = field.get(object);
		} catch (IllegalAccessException e) {}
		return result;
	}

	/**
	 * 直接设置对象属性值, 无视private/protected修饰符, 不经过setter函数.
	 */
	public static void setFieldValue(Object object, String fieldName, Object value) {
		// 获得字段
		Field field = getDeclaredField(object, fieldName);
		// 判断字段为空 返回
		if (field == null || value == null) {
			return;
		}
		// 强行设置Field可访问.
		makeAccessible(field);
		// 设置字段值
		try {
			field.set(object, Conversion.to(value, field.getType()));
		} catch (IllegalAccessException e) {}
	}

	/**
	 * 直接调用对象方法
	 * @param object 调用的对象
	 * @param methodName 方法名
	 * @param parameterTypes 参数类型
	 * @param parameters 参数
	 * @return 方法返回值
	 */
	public static Object invoke(Object object, String methodName, Class<?>[] parameterTypes, Object[] parameters) {
		// 声明Class
		Class<?> c = null;
		// 字符串
		if (object instanceof String) {
			try {
				c = Class.forName(Conversion.toString(object));
			} catch (ClassNotFoundException e) {}
		} else if (object instanceof Class<?>) {
			c = (Class<?>) object;
		} else {
			c = object.getClass();
		}
		// Class不为空
		if (c == null) {
			return null;
		} else {
			try {
				return c.getMethod(methodName, parameterTypes).invoke(c, parameters);
			} catch (Exception e) {
				return null;
			}
		}
	}

	/**
	 * 直接调用对象方法, 无视private/protected修饰符.
	 * @param object 调用的对象
	 * @param methodName 方法名
	 * @param parameterTypes 参数类型
	 * @param parameters 参数
	 * @return 方法返回值
	 */
	public static Object invokeMethod(Object object, String methodName, Class<?>[] parameterTypes, Object[] parameters) {
		// 获得方法
		Method method = getDeclaredMethod(object, methodName, parameterTypes);
		// 判断方法为空
		if (EmptyUtil.isEmpty(method)) {
			return null;
		}
		// 强行设置Field可访问.
		method.setAccessible(true);
		// 反射调用方法
		try {
			return method.invoke(object, parameters);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 循环向上转型, 获取对象的DeclaredField. 如向上转型到Object仍无法找到, 返回null.
	 */
	public static Field getDeclaredField(Object object, String fieldName) {
		// 判断对象和字段名是否为空
		if (object == null || EmptyUtil.isEmpty(fieldName)) {
			return null;
		}
		// 循环对象类
		for (Class<?> superClass = object.getClass(); superClass != Object.class; superClass = superClass.getSuperclass()) {
			// 声明字段
			Field field = null;
			try {
				// 返回字段
				field = superClass.getDeclaredField(fieldName);
			} catch (Exception e) {}
			// 字段不为空
			if (field != null) {
				return field;
			}
		}
		// 没有找到返回null
		return null;
	}

	/**
	 * 循环向上转型, 获取对象的DeclaredField. 如向上转型到Object仍无法找到, 返回null.
	 */
	public static Field getField(Class<?> clazz, String name) {
		// 获得所有字段并循环
		for (Field f : getFields(clazz)) {
			// 如果字段名相同 返回字段
			if (f.getName().equals(name)) {
				return f;
			}
		}
		// 返回null
		return null;
	}

	/**
	 * 循环向上转型, 获取对象的DeclaredField. 如向上转型到Object仍无法找到, 返回null.
	 */
	public static List<Field> getFields(Class<?> clazz) {
		// 判断对象和字段名是否为空
		if (EmptyUtil.isEmpty(clazz)) {
			return Lists.emptyList();
		}
		// 声明列表
		List<Field> fields = Lists.getList();
		// 循环对象类
		for (; clazz != Object.class; clazz = clazz.getSuperclass()) {
			try {
				// 添加字段列表
				fields.addAll(Lists.getList(clazz.getDeclaredFields()));
			} catch (Exception e) {}
		}
		// 没有找到返回null
		return fields;
	}

	/**
	 * 判断是否是基础类型
	 * @param clazz 要检查的类
	 * @return 是否基础类型
	 */
	public static boolean isBaseType(Class<?> clazz) {
		if (clazz == null) {
			return true;
		} else if (clazz.equals(Integer.class) || clazz.equals(int.class)) {
			return true;
		} else if (clazz.equals(Long.class) || clazz.equals(long.class)) {
			return true;
		} else if (clazz.equals(Double.class) || clazz.equals(double.class)) {
			return true;
		} else if (clazz.equals(Float.class) || clazz.equals(float.class)) {
			return true;
		} else if (clazz.equals(Short.class) || clazz.equals(short.class)) {
			return true;
		} else if (clazz.equals(Byte.class) || clazz.equals(byte.class)) {
			return true;
		} else if (clazz.equals(Boolean.class) || clazz.equals(boolean.class)) {
			return true;
		} else if (clazz.equals(String.class) || clazz.equals(BigDecimal.class)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 循环向上转型,获取对象的DeclaredMethod. 如向上转型到Object仍无法找到, 返回null.
	 */
	public static Method getDeclaredMethod(Object object, String methodName, Class<?>... parameterTypes) {
		// 判断对象和字段名是否为空
		if (object == null || EmptyUtil.isEmpty(methodName)) {
			return null;
		}
		// 声明Method
		Method method = null;
		// 循环对象类
		for (Class<?> superClass = object instanceof Class<?> ? (Class<?>) object : object.getClass(); superClass != Object.class; superClass = superClass.getSuperclass()) {
			try {
				// 返回方法
				method = superClass.getDeclaredMethod(methodName, parameterTypes);
			} catch (NoSuchMethodException e) {
				// // 如果有包装类型 添加类型递归
				// for (int i = 0; i < parameterTypes.length; i++) {
				// try {
				// // 判断是Integer类型
				// if (parameterTypes[i] == Integer.class) {
				// method = superClass.getDeclaredMethod(methodName, int.class);
				// } else if (parameterTypes[i] == Double.class) {
				// method = superClass.getDeclaredMethod(methodName, double.class);
				// } else if (parameterTypes[i] == Float.class) {
				// method = superClass.getDeclaredMethod(methodName, float.class);
				// } else if (parameterTypes[i] == Long.class) {
				// method = superClass.getDeclaredMethod(methodName, long.class);
				// }
				// } catch (Exception nme) {
				// // Method不在当前类定义,继续向上转型
				// }
				// }
			}
			// 方法不为空返回
			if (!EmptyUtil.isEmpty(method)) {
				break;
			}
		}
		// 返回方法
		return method;
	}

	/**
	 * 强行设置Field可访问.
	 */
	private static void makeAccessible(Field field) {
		// 判断字段是否公有
		if (!Modifier.isPublic(field.getModifiers()) || !Modifier.isPublic(field.getDeclaringClass().getModifiers())) {
			// 设置可访问
			field.setAccessible(true);
		}
	}

	/**
	 * 私有构造
	 */
	private BeanUtil() {}
}
