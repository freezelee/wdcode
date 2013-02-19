package org.wdcode.core.xml.impl.jdom;

import org.wdcode.common.util.EmptyUtil;
import org.wdcode.core.xml.Document;
import org.wdcode.core.xml.Element;

/**
 * Document接口 JDom实现
 * @author WD
 * @since JDK7
 * @version 1.0 2009-03-23
 */
public final class DocumentJDom implements Document {
	// JDom Document对象
	private org.jdom2.Document	document;
	// Element接口对象 根节点
	private Element				root;

	/**
	 * 构造方法
	 */
	public DocumentJDom() {
		// 创建Document
		document = new org.jdom2.Document();
		root = new ElementJDom(document.getRootElement());
	}

	/**
	 * 构造方法
	 * @param document org.jdom2.Document
	 */
	public DocumentJDom(org.jdom2.Document document) {
		this.document = document;
		root = new ElementJDom(document.getRootElement());
	}

	/**
	 * 构造方法
	 * @param root 根节点
	 */
	public DocumentJDom(Element root) {
		// 创建Document
		document = new org.jdom2.Document();
		// 判断传入的根节点不为空
		// 创建根节点
		this.root = root == null ? new ElementJDom("root") : root;
		// 设置根
		setRoot();
	}

	/**
	 * 获得根接点
	 * @return 根接点
	 */
	public Element getRootElement() {
		return root;
	}

	/**
	 * 设置根节点
	 * @param root 根节点
	 */
	public void setRootElement(Element root) {
		this.root = root;
		// 设置根
		setRoot();
	}

	/**
	 * 判断是否空对象
	 * @return true 空对象 false 非空对象
	 */
	public boolean isEmpty() {
		return EmptyUtil.isEmpty(document);
	}

	/**
	 * 获得JDom Document
	 * @return Document
	 */
	public org.jdom2.Document getDocument() {
		return document;
	}

	/**
	 * 设置JDom Document
	 * @param doc
	 */
	public void setDocument(org.jdom2.Document doc) {
		document = doc;
	}

	/*
	 * 设置根
	 */
	private void setRoot() {
		// 设置根节点
		document.setRootElement(((ElementJDom) root).getElement());
		// 设置Document
		root.setDocument(this);
	}

	/**
	 * 返回对象字符串
	 */
	public String toString() {
		return document.toString();
	}
}
