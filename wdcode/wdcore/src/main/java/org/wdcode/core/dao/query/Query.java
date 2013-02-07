package org.wdcode.core.dao.query;

import java.util.List;

import org.wdcode.core.dao.jdbc.DataBase;

/**
 * JDBC查询统一接口 所有需要分页的查询都继承着个接口,所有操作都调用这个接口.以便统一规则
 * @author WD
 * @since JDK7
 * @version 1.0 2009-05-02
 */
public interface Query<E> {
	/**
	 * 获得数据库查询对象
	 * @return DataBase
	 */
	DataBase getDataBase();

	/**
	 * 设置数据库查询查询对象
	 * @param db DataBase
	 */
	void setDataBase(DataBase db);

	/**
	 * 返回查询条件实体
	 * @return 查询条件实体
	 */
	E getEntity();

	/**
	 * 设置查询条件实体
	 * @param entity 查询条件实体
	 */
	void setEntity(E entity);

	/**
	 * 获得分页查询的数据
	 * @return 返回的分页数据
	 */
	List<E> queryByPage();

	/**
	 * 查询回所有记录
	 * @return 所有数据记录
	 */
	List<E> queryByAll();
}
