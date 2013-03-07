package org.wdcode.site.action;

import javax.annotation.PostConstruct;

import org.wdcode.base.action.SuperAction;
import org.wdcode.base.entity.Entity;
import org.wdcode.site.entity.EntityFile;
import org.wdcode.site.entity.EntityFiles;
import org.wdcode.site.entity.EntityIp;
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

	/**
	 * 添加实体
	 * @param e
	 * @return
	 */
	protected E add(E e) {
		e = super.add(e);
		// 判断需要上传文件类型
		if (e instanceof EntityFile) {
			((EntityFile) e).setPath(upload());
		}
		// 判断需要上传文件数组类型
		if (e instanceof EntityFiles) {
			((EntityFiles) e).setPaths(uploads());
		}
		// 判断实体类型
		if (e instanceof EntityIp) {
			((EntityIp) e).setIp(getIp());
		}
		// 返回E
		return e;
	}
}
