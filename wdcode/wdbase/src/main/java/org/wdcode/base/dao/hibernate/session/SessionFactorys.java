package org.wdcode.base.dao.hibernate.session;

import java.io.IOException;
import java.util.Map;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.ImprovedNamingStrategy;
import org.hibernate.persister.entity.SingleTableEntityPersister;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.stereotype.Component;
import org.wdcode.base.context.Context;
import org.wdcode.base.entity.Entity;
import org.wdcode.base.params.DaoParams;
import org.wdcode.common.interfaces.Close;
import org.wdcode.common.lang.Maps;
import org.wdcode.core.dao.datasource.BasicDataSource;
import org.wdcode.core.dao.datasource.DataSource;

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
	// 是否使用事务
	private Map<SessionFactory, Boolean>	txs;
	// 保存单session工厂 只有一个SessionFactory工厂时使用
	private SessionFactory					factory;
	// 是否使用事务 只有一个SessionFactory工厂时使用
	private boolean							isTx;
	// 名称对应SessionFactory
	private Map<String, SessionFactory>		map;

	/**
	 * 初始化
	 */
	@PostConstruct
	protected void init() {
		// 实例化表列表
		tables = Maps.getConcurrentMap();
		factorys = Maps.getConcurrentMap();
		txs = Maps.getConcurrentMap();
		map = Maps.getConcurrentMap();
		// 生成SessionFactory
		for (String name : DaoParams.NAMES) {
			map.put(name, getSessionFactory(name));
		}
		// 获得所有SessionFactory
		// Map<String, SessionFactory> map = context.getBeans(SessionFactory.class);
		// 如果只有一个SessionFactory
		if (map.size() == 1) {
			factory = map.values().toArray(new SessionFactory[1])[0];
		}
		// 循环获得表名
		for (Class<? extends Entity> c : context.getEntitys()) {
			// 循环获得SessionFactory
			for (SessionFactory sessionFactory : map.values()) {
				try {
					tables.put(c, ((SingleTableEntityPersister) sessionFactory.getClassMetadata(c)).getTableName());
					factorys.put(c, sessionFactory);
					txs.put(sessionFactory, false);
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
		return factory == null ? factorys.get(entity) : factory;
	}

	/**
	 * 获得当前Session
	 * @return Session
	 */
	public Session getSession(Class<?> entity) {
		// 获得sessionFactory
		SessionFactory sessionFactory = getSessionFactory(entity);
		// 声明Session
		Session session = null;
		// 判断是否直接使用 openSession
		if (isTx(entity)) {
			return sessionFactory.openSession();
		}
		try {
			session = sessionFactory.getCurrentSession();
		} catch (Exception e) {} finally {
			if (session == null) {
				if (factory == null) {
					txs.put(sessionFactory, true);
				} else {
					isTx = true;
				}
			} else {
				try {
					if (factory == null) {
						txs.put(sessionFactory, !session.getTransaction().isActive());
					} else {
						isTx = !session.getTransaction().isActive();
					}
				} catch (Exception e) {
					if (factory == null) {
						txs.put(sessionFactory, true);
					} else {
						isTx = true;
					}
				}
			}
		}
		// 判断如果session为空 返回 openSession
		return isTx(entity) ? sessionFactory.openSession() : session;
	}

	/**
	 * 是否自己控制事务
	 * @param entity
	 * @return
	 */
	public boolean isTx(Class<?> entity) {
		return factory == null ? txs.get(getSessionFactory(entity)) : isTx;
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
		if (factory != null) {
			factory.close();
		}
		for (SessionFactory factory : factorys.values()) {
			factory.close();
		}
	}

	/**
	 * 获得SessionFactory
	 * @param name 名称
	 * @return SessionFactory
	 */
	private SessionFactory getSessionFactory(String name) {
		// 获得数据源
		DataSource ds = getDataSource(name);
		// 声明SessionFactory
		LocalSessionFactoryBean factory = new LocalSessionFactoryBean();
		// 设置数据源
		factory.setDataSource(ds);
		// 设置namingStrategy
		factory.setNamingStrategy(ImprovedNamingStrategy.INSTANCE);
		// 设置扫描包
		factory.setPackagesToScan(DaoParams.getPackages(name));
		// 设置Hibernate属性
		Properties hp = new Properties();
		// 方言
		hp.put("hibernate.dialect", DaoParams.getDialect(name));
		hp.put("hibernate.show_sql", DaoParams.getSql(name));
		hp.put("hibernate.format_sql", DaoParams.getSql(name));
		hp.put("hibernate.release_mode", "auto");
		hp.put("hibernate.connection.isolation", 1);
		// 数据库参数
		hp.put("hibernate.jdbc.batch_size", DaoParams.getBatch(name));
		hp.put("hibernate.jdbc.fetch_size", DaoParams.getFetch(name));
		// 事务
		hp.put("hibernate.current_session_context_class", "org.springframework.orm.hibernate4.SpringSessionContext");
		// 缓存
		hp.put("hibernate.cache.use_second_level_cache", DaoParams.getCache(name));
		hp.put("hibernate.cache.use_query_cache", DaoParams.getCache(name));
		hp.put("hibernate.cache.region.factory_class", "org.hibernate.cache.ehcache.SingletonEhCacheRegionFactory");
		hp.put("hibernate.cache.query_cache_factory", "org.hibernate.cache.internal.StandardQueryCacheFactory");
		// search
		hp.put("hibernate.search.default.directory_provider", "filesystem");
		hp.put("hibernate.search.default.indexBase", DaoParams.getIndex(name));
		hp.put("hibernate.search.lucene_version", "LUCENE_36");
		hp.put("hibernate.ejb.event.post-insert", "org.hibernate.search.event.FullTextIndexEventListener");
		hp.put("hibernate.ejb.event.post-update", "org.hibernate.search.event.FullTextIndexEventListener");
		hp.put("hibernate.ejb.event.post-delete", "org.hibernate.search.event.FullTextIndexEventListener");
		hp.put("hibernate.search.autoregister_listeners", true);
		factory.setHibernateProperties(hp);
		// 初始化
		try {
			factory.afterPropertiesSet();
		} catch (IOException e) {}
		// 返回SessionFactory
		return factory.getObject();
	}

	/**
	 * 获得数据源
	 * @param name 名称
	 * @return 数据源
	 */
	private DataSource getDataSource(String name) {
		// 声明数据源
		BasicDataSource ds = new BasicDataSource();
		// 设置属性
		ds.setParse(DaoParams.getParse(name));
		ds.setDriver(DaoParams.getDriver(name));
		ds.setUrl(DaoParams.getUrl(name));
		ds.setUser(DaoParams.getUser(name));
		ds.setPassword(DaoParams.getPassword(name));
		ds.setMaxPoolSize(DaoParams.getMaxPoolSize(name));
		ds.setMinPoolSize(DaoParams.getMinPoolSize(name));
		ds.setMaxSize(DaoParams.getMaxSize(name));
		ds.setTimeout(DaoParams.getTimeout(name));
		ds.setIdleTimeout(DaoParams.getIdleTime(name));
		ds.setInitialPoolSize(DaoParams.getInitialPoolSize(name));
		ds.setMaxIdleTime(DaoParams.getMaxIdleTime(name));
		// 返回数据源
		return ds;
	}
}
