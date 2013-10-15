package org.wdcode.cms.action;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.WebApplicationContext;
import org.wdcode.base.entity.Entity;
import org.wdcode.cms.po.Entitys;
import org.wdcode.cms.po.User;
import org.wdcode.site.action.UserAction;

/**
 * CMS action
 * @author WD
 * @since JDK6
 * @version 1.0 2013-03-09
 */
@Controller
@Scope(WebApplicationContext.SCOPE_REQUEST)
public class CmsAction extends UserAction<Entity, User> {
	private static final long	serialVersionUID	= 2293781934484601358L;

	@PostConstruct
	protected void init() {
		// 如果实体为空 并且 模块名和模式名不同
		if (isEntity && entity == null && !module.equals(mode)) {
			entity = new Entitys(module);
		}
	}
}
