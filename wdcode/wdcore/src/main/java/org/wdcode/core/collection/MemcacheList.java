package org.wdcode.core.collection;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.wdcode.common.constants.StringConstants;
import org.wdcode.common.lang.Conversion;
import org.wdcode.core.nosql.memcache.Memcache;

/**
 * Memcache实现List
 * @author WD
 * @since JDK7
 * @version 1.0 2013-12-23
 */
public final class MemcacheList<E> implements List<E> {
	// Memcached前缀名
	private String		name;
	// memcached客户端
	private Memcache	memcache;

	@Override
	public int size() {
		return Conversion.toInt(memcache.get(getKeySize()));
	}

	@Override
	public boolean isEmpty() {
		return !memcache.exists(getKey());
	}

	@Override
	public boolean contains(Object o) {
		return false;
	}

	@Override
	public Iterator<E> iterator() {
		return null;
	}

	@Override
	public Object[] toArray() {
		return null;
	}

	@Override
	public <T> T[] toArray(T[] a) {
		return null;
	}

	@Override
	public boolean add(E e) {
		return false;
	}

	@Override
	public boolean remove(Object o) {
		return false;
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		return false;
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		return false;
	}

	@Override
	public boolean addAll(int index, Collection<? extends E> c) {
		return false;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		return false;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		return false;
	}

	@Override
	public void clear() {}

	@Override
	public E get(int index) {
		return null;
	}

	@Override
	public E set(int index, E element) {
		return null;
	}

	@Override
	public void add(int index, E element) {}

	@Override
	public E remove(int index) {
		return null;
	}

	@Override
	public int indexOf(Object o) {
		return 0;
	}

	@Override
	public int lastIndexOf(Object o) {
		return 0;
	}

	@Override
	public ListIterator<E> listIterator() {
		return null;
	}

	@Override
	public ListIterator<E> listIterator(int index) {
		return null;
	}

	@Override
	public List<E> subList(int fromIndex, int toIndex) {
		return null;
	}

	/**
	 * 获得memcached保存主键key
	 * @return memcached使用键
	 */
	private String getKey() {
		return getKey("key");
	}

	/**
	 * 获得memcached保存笨类型的数量
	 * @return memcached使用键
	 */
	private String getKeySize() {
		return getKey("size");
	}

	/**
	 * 根据单个key获得memcached中保存的键
	 * @param key 原实体键
	 * @return memcached使用键
	 */
	private String getKey(Object key) {
		return Conversion.toString(key).startsWith(name) ? Conversion.toString(key) : name + StringConstants.UNDERLINE + key;
	}

	// /**
	// * 根据单个key获得memcached中保存的键
	// * @param key 原实体键
	// * @return memcached使用键
	// */
	// private String[] getKey(String... key) {
	// String[] keys = new String[key.length];
	// for (int i = 0; i < key.length; i++) {
	// keys[i] = getKey(key[i]);
	// }
	// return keys;
	// }

	// /**
	// * 获得本类型全部Keys
	// * @return keys
	// */
	// private String[] getKeys() {
	// return StringUtil.split(Conversion.toString(memcache.get(getKey())), StringConstants.COMMA);
	// }
}
