package org.wdcode.common.util;

import java.util.Random;

/**
 * 随机数工具类
 * @author WD
 * @since JDK7
 * @version 1.0 2010-07-11
 */
public final class RandomUtil {
	// 声明随机数对象
	private final static Random	RANDOM;

	/**
	 * 静态实例化
	 */
	static {
		RANDOM = new Random();
	}

	/**
	 * 返回Random
	 * @return Random
	 */
	public static Random getRandom() {
		return RANDOM;
	}

	/**
	 * 同方法Random.nextInt()
	 * @return int 返回的随机数
	 */
	public static int nextInt() {
		return RANDOM.nextInt();
	}

	/**
	 * 同方法Random.nextInt(n)
	 * @param n 在0-n的范围中
	 * @return int 返回的随机数
	 */
	public static int nextInt(int n) {
		return RANDOM.nextInt(n);
	}

	/**
	 * 在m-n的范围中随机获得
	 * @param m 起始数
	 * @param n 结束数
	 * @return int 返回的随机数
	 */
	public static int nextInt(int m, int n) {
		// 获得随机数
		int r = nextInt(n);
		// 判断不在范围内递归
		return m > r ? nextInt(m, n) : r;
	}

	/**
	 * 同方法Random.nextDouble()
	 * @return double 返回的随机数
	 */
	public static double nextDouble() {
		return RANDOM.nextDouble();
	}

	/**
	 * 同方法Random.nextFloat()
	 * @return float 返回的随机数
	 */
	public static float nextFloat() {
		return RANDOM.nextFloat();
	}

	/**
	 * 同方法Random.nextLong()
	 * @return long 返回的随机数
	 */
	public static long nextLong() {
		return RANDOM.nextLong();
	}

	/**
	 * 获取指定位数的随机数
	 * @return 字符串格式的随机数
	 */
	public static String getRandom(int num) {
		// 声明字符缓存
		StringBuilder veryfy = new StringBuilder();
		// 循环位数
		for (int i = 0; i < num; i++) {
			// 添加到字符缓存中
			veryfy.append(nextInt(10));
		}
		// 返回
		return veryfy.toString();
	}

	/**
	 * 私有构造
	 */
	private RandomUtil() {}
}
