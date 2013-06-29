package org.wdcode.common.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.OutputStream;

import javax.imageio.ImageIO;

import org.wdcode.common.constants.ImageConstants;

import org.wdcode.common.io.FileUtil;
import org.wdcode.common.log.Logs;

/**
 * 对普通图片处理。
 * @author WD
 * @since JDK7
 * @version 1.0 2011-05-01
 */
public final class ImageUtil {
	// 字体名
	private final static String	fontName;
	// 字体
	private static Font			font;
	// 颜色
	private static Color		color;
	// 图片格式名
	private static String		formatName;

	static {
		fontName = "宋体";
		font = new Font(fontName, Font.PLAIN, 15);
		color = Color.WHITE;
		formatName = ImageConstants.JPEG;
	}

	/**
	 * 抓屏保存图片
	 * @param formatName 保存格式
	 * @param out 输出流
	 */
	public static void captureScreen(String formatName, OutputStream out) {
		try {
			ImageIO.write(new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize())), formatName, out);
		} catch (Exception ex) {}
	}

	/**
	 * 添加文字到图片
	 * @param text 要添加的文字
	 * @param file 添加文字的图片文件
	 */
	public static void writeString(String text, File file) {
		writeString(text, file, -1, -1);
	}

	/**
	 * 添加文字到图片
	 * @param text 要添加的文字
	 * @param file 添加文字的图片文件
	 * @param x 添加位置的X轴
	 * @param y 添加位置的Y轴
	 * @param isClose 是否关闭流
	 */
	public static void writeString(String text, File file, int x, int y) {
		try {
			// 添加文字
			writeString(text, ImageIO.read(file), FileUtil.getOutputStream(file), x, y, true);
		} catch (Exception e) {
			Logs.error(e);
		}
	}

	/**
	 * 添加文字到图片
	 * @param text 要添加的文字
	 * @param image 添加文字的图片对象
	 * @param out 输出流
	 * @param 是否关闭流
	 */
	public static void writeString(String text, BufferedImage image, OutputStream out, boolean isClose) {
		writeString(text, image, out, -1, -1, isClose);
	}

	/**
	 * 添加文字到图片
	 * @param text 要添加的文字
	 * @param image 添加文字的图片对象
	 * @param out 输出流 把图片输出到这个流上
	 * @param x 添加位置的X轴
	 * @param y 添加位置的Y轴
	 * @param isClose 是否关闭流
	 */
	public static void writeString(String text, BufferedImage image, OutputStream out, int x, int y, boolean isClose) {
		// 获得Graphics 用于写图片
		Graphics g = image.getGraphics();
		// 设置字体
		g.setFont(font);
		// 设置颜色
		g.setColor(color);

		// 如果x==-1
		if (x == -1) {
			// 把X设置为图片中央
			x = (image.getWidth(null) - getStringWidth(text, g.getFontMetrics())) / 2;
		}
		// 如果y==-1
		if (y == -1) {
			// 把X设置为图片中央
			y = (image.getHeight(null) + font.getSize()) / 2;
		}

		// 写内容
		g.drawString(text, x, y);
		// 释放资源使图片生效
		g.dispose();
		// 写图片
		write(image, formatName, out, isClose);
	}

	/**
	 * 写图片
	 * @param image 图片对象
	 * @param formatName 图片类型
	 * @param out 输出流
	 * @param isClose 是否关闭流
	 */
	public static void write(BufferedImage image, String formatName, OutputStream out, boolean isClose) {
		try {
			// 写图片
			ImageIO.write(image, formatName, out);
		} catch (Exception e) {
			Logs.error(e);
		} finally {
			// 关闭输出流
			if (isClose) {
				CloseUtil.close(out);
			}
		}
	}

	/**
	 * 添加图片到图片上
	 * @param draw 要添加的图片
	 * @param image 写到的图片
	 * @param isClose 是否关闭流
	 */
	public static void writeImage(File draw, File image) {
		writeImage(draw, image, -1, -1);
	}

	/**
	 * 添加图片到图片上
	 * @param draw 要添加的图片
	 * @param image 写到的图片
	 * @param x X坐标
	 * @param y Y坐标
	 * @param isClose 是否关闭流
	 */
	public static void writeImage(File draw, File image, int x, int y) {
		try {
			writeImage(ImageIO.read(draw), ImageIO.read(image), FileUtil.getOutputStream(image), x, y, true);
		} catch (Exception e) {
			Logs.error(e);
		}
	}

	/**
	 * 添加图片到图片上
	 * @param draw 要添加的图片
	 * @param image 写到的图片
	 * @param out 输出流
	 * @param 是否关闭流
	 */
	public static void writeImage(Image draw, BufferedImage image, OutputStream out, boolean isClose) {
		writeImage(draw, image, out, -1, -1, isClose);
	}

	/**
	 * 添加图片到图片上
	 * @param draw 要添加的图片
	 * @param image 写到的图片
	 * @param out 输出流
	 * @param x 添加位置的X轴
	 * @param y 添加位置的Y轴
	 * @param isClose 是否关闭流
	 */
	public static void writeImage(Image draw, BufferedImage image, OutputStream out, int x, int y, boolean isClose) {
		// 获得Graphics 用于写图片
		Graphics g = image.getGraphics();
		// 设置字体
		g.setFont(font);
		// 设置颜色
		g.setColor(color);
		// 如果x==-1
		if (x == -1) {
			// 把X设置为图片中央
			x = (image.getWidth(null) - draw.getWidth(null)) / 2;
		}
		// 如果y==-1
		if (y == -1) {
			// 把X设置为图片中央
			y = (image.getHeight(null) - draw.getHeight(null)) / 2;
		}
		// 写内容
		g.drawImage(draw, x, y, null);
		// 释放资源使图片生效
		g.dispose();
		// 写图片
		write(image, formatName, out, isClose);
	}

	/**
	 * 获得图片格式名
	 * @return 图片格式名
	 */
	public static String getFormatName() {
		return formatName;
	}

	/**
	 * 设置图片格式名
	 * @param formatName 图片格式名
	 */
	public static void setFormatName(String formatName) {
		ImageUtil.formatName = formatName;
	}

	/**
	 * 获得字体
	 * @return 字体
	 */
	public static Font getFont() {
		return font;
	}

	/**
	 * 设置字体
	 * @param font 字体
	 */
	public static void setFont(Font font) {
		ImageUtil.font = font;
	}

	/**
	 * 设置字体
	 * @param name 字体名称
	 * @param style 字体样式常量
	 * @param size 字体大小
	 */
	public static void setFont(String name, int style, int size) {
		ImageUtil.font = new Font(name, style, size);
	}

	/**
	 * 获得颜色
	 * @return 颜色
	 */
	public static Color getColor() {
		return color;
	}

	/**
	 * 设置颜色
	 * @param color 颜色
	 */
	public static void setColor(Color color) {
		ImageUtil.color = color;
	}

	/**
	 * 设置颜色
	 * @param r 红色分量 0-255
	 * @param g 绿色分量 0-255
	 * @param b 蓝色分量 0-255
	 */
	public static void setColor(int r, int g, int b) {
		ImageUtil.color = new Color(r, g, b);
	}

	/**
	 * 获得文字高度
	 * @param text 文字内容
	 * @param fm FontMetrics对象
	 * @return 宽度
	 */
	private static int getStringWidth(String text, FontMetrics fm) {
		// 初始化宽度值
		int intReturn = 0;
		// 获得文字的字节
		char[] chars = text.toCharArray();
		// 循环字节
		for (int i = 0; i < chars.length; i++) {
			// 添加到宽度中
			intReturn += fm.charWidth(chars[i]);
		}
		// 返回宽度
		return intReturn;
	}

	/**
	 * 私有构造
	 */
	private ImageUtil() {}
}
