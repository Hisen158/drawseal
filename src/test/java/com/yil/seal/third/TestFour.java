package com.yil.seal.third;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;

public class TestFour {
	/**

	* 输出图片的公用方法

	* 

	* @param message

	*            弧度上的字 （基本都是从数据库读取）

	* @param centerName

	*            中间要显示的名字

	* @param width

	*            中间字体的宽度

	* @param height

	*            中间字体的高度

	* @return

	* @throws FileNotFoundException

	* @throws IOException

	*/

	public static BufferedImage bufferedImageUtil(String message,

	String centerName, int width, int height)

	throws FileNotFoundException, IOException {

	BufferedImage image = null;

	if (message != null) {

	final int CENTERX = 90;

	final int CENTERY = 90;

	final int radius = 76;

	// 获取get_png文件夹

	ActionContext ac = ActionContext.getContext();

	ServletContext sc = (ServletContext) ac

	.get(ServletActionContext.SERVLET_CONTEXT);

	String filePath = sc.getRealPath(File.separator + "style"

	+ File.separator + "imgs");

	String path = filePath;

	image = ImageIO.read(new FileInputStream(path + File.separator

	+ "newIcon.jpg"));

	Graphics2D g2 = image.createGraphics();// 得到图形上下文

	g2.setColor(Color.RED); // 设置画笔颜色

	// 设置字体

	g2.setFont(new Font("宋体", Font.LAYOUT_LEFT_TO_RIGHT, 15));// 写入签名

	if (centerName != null) {

	g2.drawString(centerName, width, height);

	}

	// 根据输入字符串得到字符数组

	String[] messages2 = message.split("", 0);

	String[] messages = new String[messages2.length - 1];

	System.arraycopy(messages2, 1, messages, 0, messages2.length - 1);

	// 输入的字数

	int ilength = messages.length;

	// 设置字体属性

	int fontsize = 16;

	Font f = new Font("", Font.BOLD, fontsize);

	FontRenderContext context = g2.getFontRenderContext();

	Rectangle2D bounds = f.getStringBounds(message, context);

	// 字符宽度＝字符串长度/字符数

	double char_interval = (bounds.getWidth() / ilength);

	// 上坡度

	double ascent = -bounds.getY();

	int first = 0, second = 0;

	boolean odd = false;

	if (ilength % 2 == 1) {

	first = (ilength - 1) / 2;

	odd = true;

	} else {

	first = (ilength) / 2 - 1;

	second = (ilength) / 2;

	odd = false;

	}

	double radius2 = radius - ascent;

	double x0 = CENTERX;

	double y0 = CENTERY - radius + ascent;

	// 旋转角度

	double a = 2 * Math.asin(char_interval / (2 * radius2));

	if (odd) {

	g2.setFont(f);

	g2.drawString(messages[first],

	(float) (x0 - char_interval / 2), (float) y0);

	// 中心点的右边

	for (int i = first + 1; i < ilength; i++) {

	double aa = (i - first) * a;

	double ax = radius2 * Math.sin(aa);

	double ay = radius2 - radius2 * Math.cos(aa);

	AffineTransform transform = AffineTransform

	.getRotateInstance(aa);// ,x0 + ax, y0 + ay);

	Font f2 = f.deriveFont(transform);

	g2.setFont(f2);

	g2

	.drawString(messages[i],

	(float) (x0 + ax - char_interval / 2

	* Math.cos(aa)),

	(float) (y0 + ay - char_interval / 2

	* Math.sin(aa)));

	}

	// 中心点的左边

	for (int i = first - 1; i > -1; i--) {

	double aa = (first - i) * a;

	double ax = radius2 * Math.sin(aa);

	double ay = radius2 - radius2 * Math.cos(aa);

	AffineTransform transform = AffineTransform

	.getRotateInstance(-aa);// ,x0 + ax, y0 + ay);

	Font f2 = f.deriveFont(transform);

	g2.setFont(f2);

	g2

	.drawString(messages[i],

	(float) (x0 - ax - char_interval / 2

	* Math.cos(aa)),

	(float) (y0 + ay + char_interval / 2

	* Math.sin(aa)));

	}

	} else {

	// 中心点的右边

	for (int i = second; i < ilength; i++) {

	double aa = (i - second + 0.5) * a;

	double ax = radius2 * Math.sin(aa);

	double ay = radius2 - radius2 * Math.cos(aa);

	AffineTransform transform = AffineTransform

	.getRotateInstance(aa);// ,x0 + ax, y0 + ay);

	Font f2 = f.deriveFont(transform);

	g2.setFont(f2);

	g2

	.drawString(messages[i],

	(float) (x0 + ax - char_interval / 2

	* Math.cos(aa)),

	(float) (y0 + ay - char_interval / 2

	* Math.sin(aa)));

	}

	// 中心点的左边

	for (int i = first; i > -1; i--) {

	double aa = (first - i + 0.5) * a;

	double ax = radius2 * Math.sin(aa);

	double ay = radius2 - radius2 * Math.cos(aa);

	AffineTransform transform = AffineTransform

	.getRotateInstance(-aa);// ,x0 + ax, y0 + ay);

	Font f2 = f.deriveFont(transform);

	g2.setFont(f2);

	g2

	.drawString(messages[i],

	(float) (x0 - ax - char_interval / 2

	* Math.cos(aa)),

	(float) (y0 + ay + char_interval / 2

	* Math.sin(aa)));

	}

	}

	g2.dispose();

	}

	return image;

	}



	/**

	* 方形名字章

	* 

	* @param message

	*            要刻的名字

	* @return

	* @throws FileNotFoundException

	* @throws IOException

	*/

	public static BufferedImage getSquarePng(String message)

	throws FileNotFoundException, IOException {

	BufferedImage image = null;

	if (message != null) {

	ActionContext ac = ActionContext.getContext();

	ServletContext sc = (ServletContext) ac

	.get(ServletActionContext.SERVLET_CONTEXT);

	String filePath = sc.getRealPath(File.separator + "style"

	+ File.separator + "imgs");

	String path = filePath;

	image = ImageIO.read(new FileInputStream(path + File.separator

	+ "squarePng.png"));

	Graphics2D g2 = image.createGraphics();// 得到图形上下文

	g2.setColor(Color.RED); // 设置画笔颜色

	// 设置字体

	g2.setFont(new Font("宋体", Font.LAYOUT_LEFT_TO_RIGHT, 80));// 写入签名

	if (message != null) {

	String newMessage = null;

	// 如果三个 则 XXX章 如果两个则 XX之章 大于3个 则 取前三

	if (message.length() == 2) {

	newMessage = message + "之章";

	} else if (message.length() == 3) {

	newMessage = message + "章";

	} else if (message.length() > 3) {

	newMessage = message.substring(0, 3) + "章";

	}

	char[] charArray = newMessage.toCharArray();

	g2.drawString(new String(new char[] { charArray[0] }), 90, 70);  // 写文字

	g2.drawString(new String(new char[] { charArray[1] }), 90, 160); // 写文字

	g2.drawString(new String(new char[] { charArray[2] }), 10, 70);  // 写文字

	g2.drawString(new String(new char[] { charArray[3] }), 10, 160); // 写文字

	}

	}

	return image;

	}



	/**

	* 获取方形印章

	* 

	* @return

	*/

	public String getSquarePngByUserName() {

	String info = null;

	BufferedImage image = null;

	try {

	ActionContext ac = ActionContext.getContext();

	HttpServletResponse response = (HttpServletResponse) ac

	.get(ServletActionContext.HTTP_RESPONSE);

	response.setContentType("image/JPEG");

	OutputStream output = response.getOutputStream();

	JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(output);

	Users user = (Users) SiteAction.getEntity("loginUser");

	if (user != null) {

	String userName = user.getUName();

	if (userName != null) {

	image = OutPicture.getSquarePng(userName);

	}

	}

	if (image != null) {

	encoder.encode(image);

	}

	output.flush();

	output.close();

	} catch (Exception e) {

	return ERROR;

	}

	return info;

	}

	// 工商行政管理局(圆形）

	)

	public String noticeOfRegistrationPng() {

	String info = null;

	try {

	ActionContext ac = ActionContext.getContext();

	HttpServletResponse response = (HttpServletResponse) ac

	.get(ServletActionContext.HTTP_RESPONSE);

	response.setContentType("image/JPEG");

	OutputStream output = response.getOutputStream();

	JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(output);

	BufferedImage image = OutPicture.bufferedImageUtil("工商行政管理局", null,

	48, 150);

	if (image != null) {

	encoder.encode(image);

	}

	output.flush();

	output.close();

	} catch (Exception e) {

	info = Action.ERROR;

	}

	return info;

	}
}
