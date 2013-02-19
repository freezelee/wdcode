package org.wdcode.shop.po;

import javax.persistence.Entity;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.wdcode.base.entity.EntityIp;
import org.wdcode.base.entity.base.BaseEntityIdTime;

/**
 * 商品评论
 * @author WD
 * @since JDK7
 * @version 1.0 2012-06-29
 */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Entity
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public final class Comment extends BaseEntityIdTime implements EntityIp {
	// 序列化ID
	private static final long	serialVersionUID	= 4483229854905409081L;
	// 商品ID
	private Integer				goodsId;
	// IP
	private String				ip;
	// 内容
	private String				content;
	// 评论ID
	private Integer				commentId;
	// 联系方式
	private String				contact;
	// 名称
	private String				name;

	/**
	 * 获得名称
	 */
	public String getName() {
		return name;
	}

	/**
	 * 设置名称
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 获得联系方式
	 * @return 联系方式
	 */
	public String getContact() {
		return contact;
	}

	/**
	 * 设置联系方式
	 * @param contact 联系方式
	 */
	public void setContact(String contact) {
		this.contact = contact;
	}

	/**
	 * 获得商品ID
	 * @return 商品ID
	 */
	public Integer getGoodsId() {
		return goodsId;
	}

	/**
	 * 设置商品ID
	 * @param goodsId 商品ID
	 */
	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}

	/**
	 * 获得IP
	 * @return IP
	 */
	public String getIp() {
		return ip;
	}

	/**
	 * 设置IP
	 * @param ip IP
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}

	/**
	 * 获得内容
	 * @return 内容
	 */
	public String getContent() {
		return content;
	}

	/**
	 * 设置内容
	 * @param content 内容
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * 获得评论ID
	 * @return 评论ID
	 */
	public Integer getCommentId() {
		return commentId;
	}

	/**
	 * 设置评论ID
	 * @param commentId 评论ID
	 */
	public void setCommentId(Integer commentId) {
		this.commentId = commentId;
	}
}
