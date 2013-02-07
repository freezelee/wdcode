package org.wdcode.common.codec;

import org.apache.commons.lang3.StringEscapeUtils;

/**
 * 转码类
 * @author WD
 * @since JDK7
 * @version 1.0 2012-2-17
 */
public final class Escapes {
	/**
	 * html转码
	 * @param html 要转码的html
	 * @return 转码后的html
	 */
	public static String escapeHtml4(String html) {
		return StringEscapeUtils.escapeHtml4(html);
	}

	/**
	 * xml转码
	 * @param xml 要转码的xml
	 * @return 转码后的xml
	 */
	public static String escapeXml(String xml) {
		return StringEscapeUtils.escapeXml(xml);
	}

	/**
	 * csv转码
	 * @param csv 要转码的csv
	 * @return 转码后的csv
	 */
	public static String escapeCsv(String csv) {
		return StringEscapeUtils.escapeCsv(csv);
	}

	/**
	 * java转码
	 * @param java 要转码的java
	 * @return 转码后的java
	 */
	public static String escapeJava(String java) {
		return StringEscapeUtils.escapeJava(java);
	}

	/**
	 * EcmaScript转码
	 * @param input 要转码的EcmaScript
	 * @return 转码后的EcmaScript
	 */
	public static String escapeEcmaScript(String input) {
		return StringEscapeUtils.escapeEcmaScript(input);
	}

	/**
	 * html转码
	 * @param html 要转码的html
	 * @return 转码后的html
	 */
	public static String unescapeHtml4(String html) {
		return StringEscapeUtils.unescapeHtml4(html);
	}

	/**
	 * xml转码
	 * @param xml 要转码的xml
	 * @return 转码后的xml
	 */
	public static String unescapeXml(String xml) {
		return StringEscapeUtils.unescapeXml(xml);
	}

	/**
	 * csv转码
	 * @param csv 要转码的csv
	 * @return 转码后的csv
	 */
	public static String unescapeCsv(String csv) {
		return StringEscapeUtils.unescapeCsv(csv);
	}

	/**
	 * java转码
	 * @param java 要转码的java
	 * @return 转码后的java
	 */
	public static String unescapeJava(String java) {
		return StringEscapeUtils.unescapeJava(java);
	}

	/**
	 * EcmaScript转码
	 * @param input 要转码的EcmaScript
	 * @return 转码后的EcmaScript
	 */
	public static String unescapeEcmaScript(String input) {
		return StringEscapeUtils.unescapeEcmaScript(input);
	}

	/**
	 * 私有构造
	 */
	private Escapes() {}
}
