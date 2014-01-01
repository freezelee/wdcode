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

	// // 名称对应SessionFactory
	// private Map<String, SessionFactory> map;
	// // 事务保存Map
	// private Map<String, HibernateTransactionManager> tms;

	/**
	 * 初始化
	 */
	@PostConstruct
	protected void init() {
		// 实例化表列表
		tables = Maps.getConcurrentMap();
		factorys = Maps.getConcurrentMap();
		// tms = Maps.getConcurrentMap();
		// map = Maps.getConcurrentMap();
		// // 生成SessionFactory
		// for (String name : DaoParams.NAMES) {
		// // 获得SessionFactory
		// SessionFactory sf = getSessionFactory(name);
		// // 设置事务管理
		// tms.put(name, new HibernateTransactionManager(sf));
		// // 添加到列表
		// map.put(name, sf);
		// }
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

	// /**
	// * 获得SessionFactory
	// * @param name 名称
	// * @return SessionFactory
	// */
	// private SessionFactory getSessionFactory(String name) {
	// // 获得数据源
	// DataSource ds = getDataSource(name);
	// // 声明SessionFactory
	// LocalSessionFactoryBean factory = new LocalSessionFactoryBean();
	// // 设置数据源
	// factory.setDataSource(ds);
	// // 设置namingStrategy
	// factory.setNamingStrategy(ImprovedNamingStrategy.INSTANCE);
	// // 设置扫描包
	// factory.setPackagesToScan(DaoParams.getPackages(name));
	// // 设置Hibernate属性
	// Properties hp = new Properties();
	// // 方言
	// hp.put("hibernate.dialect", DaoParams.getDialect(name));
	// hp.put("hibernate.show_sql", DaoParams.getSql(name));
	// hp.put("hibernate.format_sql", DaoParams.getSql(name));
	// hp.put("hibernate.release_mode", "auto");
	// hp.put("hibernate.connection.isolation", 1);
	// // 数据库参数
	// hp.put("hibernate.jdbc.batch_size", DaoParams.getBatch(name));
	// hp.put("hibernate.jdbc.fetch_size", DaoParams.getFetch(name));
	// // 事务
	// hp.put("hibernate.current_session_context_class",
	// "org.springframework.orm.hibernate4.SpringSessionContext");
	// // 缓存
	// hp.put("hibernate.cache.use_second_level_cache", DaoParams.getCache(name));
	// hp.put("hibernate.cache.use_query_cache", DaoParams.getCache(name));
	// hp.put("hibernate.cache.region.factory_class",
	// "org.hibernate.cache.ehcache.SingletonEhCacheRegionFactory");
	// hp.put("hibernate.cache.query_cache_factory",
	// "org.hibernate.cache.internal.StandardQueryCacheFactory");
	// // search
	// hp.put("hibernate.search.default.directory_provider", "filesystem");
	// hp.put("hibernate.search.default.indexBase", DaoParams.getIndex(name));
	// hp.put("hibernate.search.lucene_version", "LUCENE_36");
	// hp.put("hibernate.ejb.event.post-insert",
	// "org.hibernate.search.event.FullTextIndexEventListener");
	// hp.put("hibernate.ejb.event.post-update",
	// "org.hibernate.search.event.FullTextIndexEventListener");
	// hp.put("hibernate.ejb.event.post-delete",
	// "org.hibernate.search.event.FullTextIndexEventListener");
	// hp.put("hibernate.search.autoregister_listeners", true);
	// factory.setHibernateProperties(hp);
	// // 初始化
	// try {
	// factory.afterPropertiesSet();
	// } catch (IOException e) {}
	// // 返回SessionFactory
	// return factory.getObject();
	// }

	// /**
	// * 获得数据源
	// * @param name 名称
	// * @return 数据源
	// */
	// private DataSource getDataSource(String name) {
	// // 声明数据源
	// BasicDataSource ds = new BasicDataSource();
	// // 设置属性
	// ds.setParse(DaoParams.getParse(name));
	// ds.setDriver(DaoParams.getDriver(name));
	// ds.setUrl(DaoParams.getUrl(name));
	// ds.setUser(DaoParams.getUser(name));
	// ds.setPassword(DaoParams.getPassword(name));
	// ds.setMaxPoolSize(DaoParams.getMaxPoolSize(name));
	// ds.setMinPoolSize(DaoParams.getMinPoolSize(name));
	// ds.setMaxSize(DaoParams.getMaxSize(name));
	// ds.setTimeout(DaoParams.getTimeout(name));
	// ds.setIdleTimeout(DaoParams.getIdleTime(name));
	// ds.setInitialPoolSize(DaoParams.getInitialPoolSize(name));
	// ds.setMaxIdleTime(DaoParams.getMaxIdleTime(name));
	// // 返回数据源
	// return ds;
	// }
}
