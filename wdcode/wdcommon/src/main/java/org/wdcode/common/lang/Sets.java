package org.wdcode.common.lang;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

import org.wdcode.common.util.EmptyUtil;

/**
 * Set的帮助类,获得Set的一些操作
 * @author WD
 * @since JDK7
 * @version 1.0 2009-09-08
 */
public final class Sets {
	/**
	 * 判断是否Set
	 * @param obj 对象
	 * @return 是否Set
	 */
	public static boolean isSet(Object obj) {
		return !EmptyUtil.isEmpty(obj) && obj instanceof Set<?>;
	}

	/**
	 * 获得Set实例 实现类是HashSet 默认初始化大小为10
	 * @return Set
	 */
	public static <E> Set<E> getSet() {
		return getHashSet();
	}

	/**
	 * 获得Set实例 实现类是HashSet
	 * @param size 初始化大小
	 * @return Set
	 */
	public static <E> Set<E> getSet(int size) {
		return getHashSet(size);
	}

	/**
	 * 获得Set实例 实现类是HashSet
	 * @param c 初始化的集合
	 * @return Set
	 */
	public static <E> Set<E> getSet(Collection<E> c) {
		return getHashSet(c);
	}

	/**
	 * 获得同步Set实例 实现类是HashSet 默认初始化大小为10
	 * @return Set
	 */
	public static <E> Set<E> getSynchronizedSet() {
		return getSynchronizedSet();
	}

	/**
	 * 获得Set实例 实现类是HashSet
	 * @param size 初始化大小
	 * @return Set
	 */
	public static <E> Set<E> getSynchronizedSet(int size) {
		return Collections.synchronizedSet(new HashSet<E>(size));
	}

	/**
	 * 获得Set实例 实现类是HashSet
	 * @param c 初始化的集合
	 * @return Set
	 */
	public static <E> Set<E> getSynchronizedSet(Collection<E> c) {
		return Collections.synchronizedSet(getHashSet(c));
	}

	/**
	 * 获得并发的List实例 实现类是CopyOnWriteArrayList
	 * @return 同步的List
	 */
	public static <E> CopyOnWriteArrayList<E> getConcurrenrSet() {
		return new CopyOnWriteArrayList<E>();
	}

	/**
	 * 获得并发的Set实例 实现类是CopyOnWriteArraySet
	 * @param c 初始化的集合
	 * @return 同步的List
	 */
	public static <E> CopyOnWriteArraySet<E> getConcurrenrSet(Collection<E> c) {
		return new CopyOnWriteArraySet<E>(c);
	}

	/**
	 * 获得Set实例 实现类是HashSet 默认初始化大小为10
	 * @return Set
	 */
	public static <E> HashSet<E> getHashSet() {
		return getHashSet(10);
	}

	/**
	 * 获得Set实例 实现类是HashSet
	 * @param size 初始化大小
	 * @return Set
	 */
	public static <E> HashSet<E> getHashSet(int size) {
		return new HashSet<E>(size);
	}

	/**
	 * 获得Set实例 实现类是HashSet
	 * @param c 初始化的集合
	 * @return Set
	 */
	public static <E> HashSet<E> getHashSet(Collection<E> c) {
		return new HashSet<E>(c);
	}

	/**
	 * 获得一个不可变的空Set
	 * @return 一个不可变的空Set
	 */
	public static <E> Set<E> emptyList() {
		return Collections.emptySet();
	}

	/**
	 * 私有构造
	 */
	private Sets() {}
}
