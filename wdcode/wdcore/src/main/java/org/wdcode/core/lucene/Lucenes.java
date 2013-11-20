package org.wdcode.core.lucene;

import java.io.IOException;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.wdcode.common.io.FileUtil;
import org.wdcode.common.util.CloseUtil;

/**
 * Lucene 相关操作
 * @author WD
 * @since JDK7
 * @version 1.0 2012-11-18
 */
public final class Lucenes {
	// Lucene索引目录
	private static Directory	dir;
	// Lucene分词器
	private static Analyzer		analyzer;

	// 索引写入器
	// private static IndexWriter writer;

	public static void init() {

	}

	public static void createIndex() {
		try {
			dir = FSDirectory.open(FileUtil.getFile(""));
			analyzer = new StandardAnalyzer(Version.LUCENE_45);
		} catch (IOException e) {} finally {
			CloseUtil.close(dir, analyzer);
		}
	}

	private Lucenes() {}
}
