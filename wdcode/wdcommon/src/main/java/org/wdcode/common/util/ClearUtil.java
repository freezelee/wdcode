package org.wdcode.common.util;

import java.util.Collection;
import java.util.Map;

import org.wdcode.common.interfaces.Clear;
import org.wdcode.common.log.Logs;

/**
 * 关闭各种资源方法
 * @author WD
 * @since JDK7
 * @version 1.0 2009-03-04
 */
public final class ClearUtil {
	/**
	 * 清除Clear接口数据
	 * @param cs Clear接口
	 */
	public static void clear(Clear... cs) {
		// 判断不为空
		if (!EmptyUtil.isEmpty(cs)) {
			try {
				// 循环清除资源
				for (int i = 0; i < cs.length; i++) {
					cs[i].clear();
				}
			} catch (Exception e) {
				Logs.debug(e);
			}
		}
	}

	/**
	 * 清除Collection接口数据
	 * @param c Collection接口
	 */
	public static void clear(Collection<?>... c) {
		// 判断不为空
		if (!EmptyUtil.isEmpty(c)) {
			try {
				// 循环清除资源
				for (int i = 0; i < c.length; i++) {
					c[i].clear();
				}
			} catch (Exception e) {
				Logs.debug(e);
			}
		}
	}

	/**
	 * 清除Map接口数据
	 * @param m Map接口
	 */
	public static void clear(Map<?, ?>... m) {
		// 判断不为空
		if (!EmptyUtil.isEmpty(m)) {
			try {
				// 循环清除资源
				for (int i = 0; i < m.length; i++) {
					m[i].clear();
				}
			} catch (Exception e) {
				Logs.debug(e);
			}
		}
	}

	/**
	 * 私有构造，禁止外部实例化
	 */
	private ClearUtil() {}
}
