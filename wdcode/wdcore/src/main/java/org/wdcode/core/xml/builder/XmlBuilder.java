package org.wdcode.core.xml.builder;

import java.io.File;
import java.io.InputStream;

import org.wdcode.core.params.CoreParams;
import org.wdcode.core.xml.Attribute;
import org.wdcode.core.xml.Document;
import org.wdcode.core.xml.Element;
import org.wdcode.core.xml.impl.dom4j.AttributeDom4J;
import org.wdcode.core.xml.impl.dom4j.DocumentDom4J;
import org.wdcode.core.xml.impl.dom4j.ElementDom4J;
import org.wdcode.core.xml.impl.dom4j.input.XMLReadDom4J;
import org.wdcode.core.xml.impl.dom4j.output.FormatDom4J;
import org.wdcode.core.xml.impl.dom4j.output.XMLWriteDom4J;
import org.wdcode.core.xml.impl.jdom.AttributeJDom;
import org.wdcode.core.xml.impl.jdom.DocumentJDom;
import org.wdcode.core.xml.impl.jdom.ElementJDom;
import org.wdcode.core.xml.impl.jdom.input.XMLReadJDom;
import org.wdcode.core.xml.impl.jdom.output.FormatJDom;
import org.wdcode.core.xml.impl.jdom.output.XMLWriteJDom;
import org.wdcode.core.xml.input.XMLRead;
import org.wdcode.core.xml.output.Format;
import org.wdcode.core.xml.output.XMLWrite;

/**
 * XMLDocument工厂,创建XML解析用对象 <h2>注: 本包功能需要jdom或dom4j依赖包</h2>
 * @author WD
 * @since JDK7
 * @version 1.0 2009-03-18
 */
public final class XmlBuilder {
	// XML解析包
	private final static boolean	DOM4J;

	/**
	 * 静态初始化
	 */
	static {
		DOM4J = "dom4j".equalsIgnoreCase(CoreParams.XML_PARSE);
	}

	/**
	 * 获得Format对象 使用UTF-8编码
	 * @return Format
	 */
	public static Format createFormat() {
		return createFormat(CoreParams.XML_ENCODING);
	}

	/**
	 * 获得Format对象
	 * @param encoding 编码格式
	 * @return Format
	 */
	public static Format createFormat(String encoding) {
		return DOM4J ? new FormatDom4J(encoding) : new FormatJDom(encoding);
	}

	/**
	 * 创建一个空的Document对象
	 * @return Document
	 */
	public static Document createDocument() {
		return DOM4J ? new DocumentDom4J() : new DocumentJDom();
	}

	/**
	 * 创建一个名为root的Document对象
	 * @param root 根节点名
	 * @return Document
	 */
	public static Document createDocument(String root) {
		return createDocument(createElement(root));
	}

	/**
	 * 创建一个e为根节点的Document对象
	 * @param e 根节点
	 * @return Document
	 */
	public static Document createDocument(Element e) {
		return DOM4J ? new DocumentDom4J(e) : new DocumentJDom(e);
	}

	/**
	 * 创建一个名为默认的根节点的Element对象
	 * @return Element
	 */
	public static Element createElement() {
		return createElement(CoreParams.XML_ROOT);
	}

	/**
	 * 创建Element对象
	 * @param name 根节点名称
	 * @return Element
	 */
	public static Element createElement(String name) {
		return DOM4J ? new ElementDom4J(name) : new ElementJDom(name);
	}

	/**
	 * 创建Attribute对象
	 * @param name 名
	 * @param value 值
	 * @return Attribute
	 */
	public static Attribute createAttribute(String name, String value) {
		return DOM4J ? new AttributeDom4J(name, value) : new AttributeJDom(name, value);
	}

	/**
	 * 创建XMLOutput对象
	 * @return XMLOutput
	 */
	public static XMLWrite createXMLOutput() {
		return createXMLOutput(createFormat());
	}

	/**
	 * 创建XMLOutput对象
	 * @param format Format输出格式
	 * @return XMLOutput @ 没有解析包
	 */
	public static XMLWrite createXMLOutput(Format format) {
		return DOM4J ? new XMLWriteDom4J(format) : new XMLWriteJDom(format);
	}

	/**
	 * 创建XMLRead对象
	 * @return XMLRead
	 */
	public static XMLRead createXMLRead() {
		return DOM4J ? new XMLReadDom4J() : new XMLReadJDom();
	}

	/**
	 * 根据文件名生成一个Document
	 * @param xml XML字符串
	 * @return Document
	 */
	public static Document readDocument(String xml) {
		return createXMLRead().build(xml);
	}

	/**
	 * 根据文件生成一个Document
	 * @param file XML文件
	 * @return Document
	 */
	public static Document readDocument(File file) {
		return createXMLRead().build(file);
	}

	/**
	 * 根据输入流生成一个Document
	 * @param in XML文件流
	 * @return Document
	 */
	public static Document readDocument(InputStream in) {
		return createXMLRead().build(in);
	}

	/**
	 * 私有构造
	 */
	private XmlBuilder() {}
}
