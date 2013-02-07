package org.wdcode.core.dao.query.base;

import java.util.List;
import java.util.Map;

import org.wdcode.core.dao.jdbc.DataBase;
import org.wdcode.core.dao.query.Query;
import org.wdcode.common.lang.Conversion;
import org.wdcode.common.util.ArrayUtil;
import org.wdcode.common.util.BeanUtil;
import org.wdcode.common.util.SqlUtil;
import org.wdcode.common.util.StringUtil;

/**
 * JDBC实现分页抽象父类,所有的实现类都继承这个抽象类,实现这个类以便统一规则
 * @author WD
 * @since JDK7
 * @version 1.0 2009-05-02
 */
public abstract class BaseQuery<E> implements Query<E> {
	// 定义数据库连接，
	protected DataBase	db;
	// 查询所用的实体
	protected E			entity;
	// 是否附值两遍
	protected boolean	isTwoParam;

	/**
	 * 获得分页查询的数据
	 * @return 返回的分页数据
	 */
	public final List<E> queryByPage() {
		// 获得分页查询语句
		String sql = getSQLByPage();
		// 判断是否使用双参
		Object[] parame = isTwoParam ? twoParam() : getParame();
		// 返回分页查询结果
		return conversion(db.queryMultiRowMultiCol(sql, parame));
	}

	/**
	 * 查询回所有记录
	 * @return 所有数据记录
	 */
	public final List<E> queryByAll() {
		return conversion(db.queryMultiRowMultiCol(getSQL(), getParame()));
	}

	/**
	 * 获得数据库查询对象
	 * @return DataBase
	 */
	public final DataBase getDataBase() {
		return db;
	}

	/**
	 * 设置数据库查询查询对象
	 * @param db DataBase
	 */
	public final void setDataBase(DataBase db) {
		this.db = db;
	}

	/**
	 * 返回查询条件实体
	 * @return 查询条件实体
	 */
	public final E getEntity() {
		return entity;
	}

	/**
	 * 设置查询条件实体
	 * @param entity 查询条件实体
	 */
	public final void setEntity(E entity) {
		this.entity = entity;
	}

	/**
	 * 是否使用双参数附值 分页查询时 当参数需要附值两遍使用true 默认为false 一般用于SQLServer2000查询
	 * @param isTwoParam 是否使用双参
	 */
	protected final void setTwoParam(boolean isTwoParam) {
		this.isTwoParam = isTwoParam;
	}

	/**
	 * 获得MySQL分页查询的语句
	 * @return String 分页查询语句
	 */
	protected final String getSQLByPageForMySQL() {
		// 截取数据库记录中某段记录的开始记录行号
		int sum = 0;// (page.getCurrentPage() - 1) * page.getPageSize();
		// 声明分页查询SQL
		StringBuilder sql = new StringBuilder("SELECT * FROM (");
		sql.append(getSQL());
		sql.append(") as temp LIMIT ");
		sql.append(sum);
		sql.append(" ,");
		sql.append(1);
		// sql.append(page.getPageSize());
		// 返回SQL
		return sql.toString();
	}

	/**
	 * 获得SQLServer分页查询的语句 id列是必须的 <br/>
	 * <h2>注: 使用此类需要显示常量列row,如果实体里没有这个字段将报异常,但不影响使用</h2>
	 * @param id 列名
	 * @return String 分页查询语句
	 */
	protected final String getSQLByPageForSQLServer2005(String id) {
		// 获得获得最大的行数
		int sum = 0;// (page.getCurrentPage() - 1) * page.getPageSize();
		// 获得sql查询语句
		String sql = getSQL();
		// 声明分页查询SQL
		StringBuilder buf = new StringBuilder("SELECT TOP ");
		buf.append(1);
		// buf.append(page.getPageSize());
		buf.append(" * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY ");
		buf.append(id);
		buf.append(" ) AS row,");
		buf.append(StringUtil.subString(sql.toLowerCase(), "select"));
		buf.append(" ) AS temp WHERE ");
		buf.append("row > ");
		buf.append(sum);
		// 返回分页查询语句
		return buf.toString();
	}

	/**
	 * 获得SQLServer分页查询的语句 id列是必须的<br/>
	 * <h2>注: 本方法返回的语句基于双参实现, 内部已实现 不需要在做其它处理</h2>
	 * @param id 列名
	 * @return String 分页查询语句
	 */
	protected final String getSQLByPageForSQLServer(String id) {
		// 获得获得最大的行数
		int sum = 0;// (page.getCurrentPage() - 1) * page.getPageSize();
		// 获得sql查询语句
		String sql = getSQL();
		// 声明分页查询SQL
		StringBuilder buf = new StringBuilder("SELECT TOP ");
		buf.append(1);
		// buf.append(page.getPageSize());
		buf.append(" * FROM ( ");
		buf.append(sql);
		buf.append(" ) AS temp WHERE ");
		buf.append(id);
		buf.append(" NOT IN (SELECT TOP ( ");
		buf.append(sum);
		buf.append(" ) ");
		buf.append(id);
		buf.append(" FROM ");
		buf.append(StringUtil.subString(sql.toLowerCase(), "from"));
		buf.append(" )");
		// 使用双参数
		isTwoParam = true;
		// 返回分页查询语句
		return buf.toString();
	}

	/**
	 * 查询总数量 此方法可重写但必须保证准确
	 * @return 数据总数量
	 */
	protected int getCount() {
		// 返回查询出的数量
		return Conversion.toInt(db.querySnglRowSnglCol(SqlUtil.getCountSQL(getSQL()), getParame()));
	}

	/**
	 * 把List<Map<String, String>>列表转换成你要返回的实体
	 * @param list 查询回的List<Map<String, String>>数据列表
	 * @return 转换后的数据列表
	 */
	protected List<E> conversion(List<Map<String, Object>> list) {
		return BeanUtil.copyProperties(getEntityClass(), list);
	}

	/**
	 * 获得分页查询的语句,查询返回的数据数应与每页显示的数据相同<br/>
	 * <h2>注: 默认提供的是MySQL分页 其它分页程序请重写此方法</h2>
	 * @return String 分页查询语句
	 */
	protected String getSQLByPage() {
		return getSQLByPageForMySQL();
	}

	/**
	 * 返回双参数
	 * @return 双参
	 */
	private Object[] twoParam() {
		// 获得 参数
		Object[] parame = getParame();
		// 返回双参
		return ArrayUtil.add(parame, parame);
	}

	/**
	 * 获取查询SQL语句,写你查询全部数据的SQL
	 * @return SQL语句
	 */
	protected abstract String getSQL();

	/**
	 * 获取查询SQL语句,写需要的参数,如果无返回null
	 * @return 参数
	 */
	protected abstract Object[] getParame();

	/**
	 * 获得实体Class
	 * @return 实体Class
	 */
	protected abstract Class<E> getEntityClass();
}
