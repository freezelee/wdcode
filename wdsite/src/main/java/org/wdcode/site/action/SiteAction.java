package org.wdcode.site.action;

import javax.annotation.PostConstruct;

import org.wdcode.base.action.SuperAction;
import org.wdcode.base.entity.Entity;
import org.wdcode.site.po.Entitys;

/**
 * 网站Action
 * @author WD
 * @since JDK6
 * @version 1.0 2013-01-14
 */
public class SiteAction<E extends Entity> extends SuperAction<E> {
	// 序列化ID
	private static final long	serialVersionUID	= -7287026951322657992L;

	@PostConstruct
	protected void init() {
		// 父类初始化
		super.init();
		// 如果实体为空 并且 模块名和模式名不同
		if (isEntity && entity == null && !module.equals(mode)) {
			entity = (E) new Entitys(module);
		}
	}
}
