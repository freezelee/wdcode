package org.wdcode.base.cache.impl;

import java.io.Serializable;
import java.util.List;

import org.wdcode.base.cache.base.BaseCache;
import org.wdcode.base.entity.Entity;

/**
 * 基于memcached的缓存
 * @author WD
 * @since JDK6
 * @version 1.0 2013-09-06
 */
public final class CacheMemcached<E extends Entity> extends BaseCache<E> {

	@Override
	public E set(E entity) {

		return null;
	}

	@Override
	public E get(Serializable key) {

		return null;
	}

	@Override
	public List<E> get(List<Serializable> keys) {

		return null;
	}

	@Override
	public E remove(Serializable key) {

		return null;
	}

	@Override
	public List<E> remove(Serializable... keys) {
		return null;
	}

	@Override
	public List<E> list() {
		return null;
	}

	@Override
	public int size() {
		return 0;
	}

	@Override
	public void clear() {}

	@Override
	public boolean isEmpty() {
		return false;
	}
}
