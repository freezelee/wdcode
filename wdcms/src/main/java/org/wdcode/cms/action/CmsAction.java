package org.wdcode.cms.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.WebApplicationContext;
import org.wdcode.base.entity.Entity;
import org.wdcode.base.entity.EntityLogin;
import org.wdcode.site.action.LoginAction;

/**
 * CMS action
 * @author WD
 * @since JDK6
 * @version 1.0 2013-03-09
 */
@Controller
@Scope(WebApplicationContext.SCOPE_REQUEST)
public class CmsAction extends LoginAction<Entity, EntityLogin> {
	private static final long	serialVersionUID	= 2293781934484601358L;
}
