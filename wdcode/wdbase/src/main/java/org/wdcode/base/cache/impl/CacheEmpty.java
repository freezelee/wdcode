package org.wdcode.base.cache.impl;

import java.io.Serializable;
import java.util.List;

import org.wdcode.base.cache.Cache;
import org.wdcode.base.entity.Entity;
import org.wdcode.common.lang.Lists;

/***
 * 空缓存操作
 * @author WD
 * @since JDK7
 * @version 1.0 2012-07-16
 */
public final class CacheEmpty implements Cache<Entity> {
	@Override
	public void clear() {}

	@Override
	public boolean isEmpty() {
		return true;
	}

	@Override
	public Entity set(Entity entity) {
		return entity;
	}

	@Override
	public List<Entity> set(List<Entity> entitys) {
		return entitys;
	}

	@Override
	public Entity remove(Entity entity) {
		return entity;
	}

	@Override
	public List<Entity> remove(List<Entity> entitys) {
		return entitys;
	}

	@Override
	public List<Entity> list() {
		return Lists.emptyList();
	}

	@Override
	public Entity get(Serializable key) {
		return null;
	}

	@Override
	public List<Entity> get(Serializable... keys) {
		return Lists.emptyList();
	}

	@Override
	public List<Entity> get(List<Serializable> keys) {
		return Lists.emptyList();
	}

	@Override
	public Entity remove(Serializable key) {
		return null;
	}

	@Override
	public List<Entity> remove(Serializable... keys) {
		return Lists.emptyList();
	}

	@Override
	public int size() {
		return 0;
	}

	@Override
	public boolean isValid() {
		return false;
	}
}
