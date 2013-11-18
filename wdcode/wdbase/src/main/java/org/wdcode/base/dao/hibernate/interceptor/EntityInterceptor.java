package org.wdcode.base.dao.hibernate.interceptor;

import org.hibernate.EmptyInterceptor;
import org.wdcode.base.entity.EntityShards;
import org.wdcode.base.entity.EntityTime;
import org.wdcode.common.constants.DateConstants;
import org.wdcode.common.constants.StringConstants;
import org.wdcode.common.lang.Conversion;
import org.wdcode.common.util.DateUtil;

/**
 * hibernate 实体拦截器
 * @author WD
 * @since JDK7
 * @version 1.0 2013-11-18
 */
public class EntityInterceptor extends EmptyInterceptor {
	private static final long	serialVersionUID	= 2314652107885146870L;

	@Override
	public String onPrepareStatement(String sql) {
		return super.onPrepareStatement(sql);
	}

	@Override
	public String getEntityName(Object entity) {
		// 是否切片实体
		if (entity instanceof EntityShards) {
			// 获得实体名
			String name = entity.getClass().getSimpleName();
			// 按时间切片
			if (entity instanceof EntityTime) {
				// 获得时间
				int time = Conversion.toInt(((EntityTime) entity).getTime());
				// 时间大于0
				if (time > 0) {
					// 按月份切片
					return name + StringConstants.UNDERLINE + DateUtil.toString(time, DateConstants.FORMAT_YYYYMM);
				}
			}
		}
		// 返回实体名
		return super.getEntityName(entity);
	}
}
