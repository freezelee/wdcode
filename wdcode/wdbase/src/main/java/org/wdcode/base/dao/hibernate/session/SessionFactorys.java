package org.wdcode.base.dao.hibernate.session;

import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.hibernate.persister.entity.SingleTableEntityPersister;
import org.springframework.stereotype.Component;
import org.wdcode.base.context.Context;
import org.wdcode.base.entity.Entity;
import org.wdcode.common.interfaces.Close;
import org.wdcode.common.lang.Maps;

/**
 * SessionFactory包装类
 * @author WD
 * @since JDK7
 * @version 1.0 2013-11-19
 */
@Component
public final class SessionFactorys implements Close {
	// Context
	@Resource
	private Context							context;
	// 表名
	private Map<Class<?>, String>			tables;
	// 类对应SessionFactory
	private Map<Class<?>, SessionFactory>	factorys;

	/**
	 * 初始化
	 */
	@PostConstruct
	protected void init() {
		// 实例化表列表
		tables = Maps.getConcurrentMap();
		factorys = Maps.getConcurrentMap();
		// 获得所有SessionFactory
		Map<String, SessionFactory> map = context.getBeans(SessionFactory.class);
		// 循环获得表名
		for (Class<? extends Entity> c : context.getEntitys()) {
			// 循环获得SessionFactory
			for (SessionFactory sessionFactory : map.values()) {
				try {
					tables.put(c, ((SingleTableEntityPersister) sessionFactory.getClassMetadata(c)).getTableName());
					factorys.put(c, sessionFactory);
				} catch (Exception e) {}
			}
		}
	}

	/**
	 * 根据实体类获得SessionFactory
	 * @param entity 实体类
	 * @return SessionFactory
	 */
	public SessionFactory getSessionFactory(Class<?> entity) {
		return factorys.get(entity);
	}

	/**
	 * 根据实体类获得表名
	 * @param entity 实体类
	 * @return 表名
	 */
	public String getTables(Class<?> entity) {
		return tables.get(entity);
	}

	@Override
	public void close() {
		for (SessionFactory factory : factorys.values()) {
			factory.close();
		}
	}
}
