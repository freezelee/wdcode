package org.wdcode.common.util;

import java.io.OutputStream;

import org.wdcode.common.interfaces.Close;

/**
 * 关闭各种资源方法
 * @author WD
 * @since JDK7
 * @version 1.0 2009-03-04
 */
public final class CloseUtil {
	/**
	 * 关闭Closeable流数据源接口
	 * @param c 流数据源
	 */
	public static void close(Close... cs) {
		// 判断不为空
		if (!EmptyUtil.isEmpty(cs)) {
			// Close接口
			Close c = null;
			// 循环关闭资源
			for (int i = 0; i < cs.length; i++) {
				try {
					// 获得Close
					c = cs[i];
					// 判断不为空
					if (!EmptyUtil.isEmpty(c)) {
						c.close();
					}
				} catch (Exception e) {}
			}
		}
	}

	/**
	 * 关闭Close流数据源接口
	 * @param cs 流数据源
	 */
	public static void close(AutoCloseable... cs) {
		// 判断不为空
		if (!EmptyUtil.isEmpty(cs)) {
			// Closeable接口
			AutoCloseable c = null;
			// 循环关闭资源
			for (int i = 0; i < cs.length; i++) {
				try {
					// 获得Closeable
					c = cs[i];
					// 判断不为空
					if (!EmptyUtil.isEmpty(c)) {
						// 是输出流
						if (c instanceof OutputStream) {
							((OutputStream) c).flush();
						}
						// 关闭
						c.close();
					}
				} catch (Exception e) {}
			}
		}
	}

	/**
	 * 私有构造，禁止外部实例化
	 */
	private CloseUtil() {}
}
