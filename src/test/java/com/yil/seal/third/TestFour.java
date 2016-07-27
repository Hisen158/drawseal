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

	* ���ͼƬ�Ĺ��÷���

	* 

	* @param message

	*            �����ϵ��� ���������Ǵ����ݿ��ȡ��

	* @param centerName

	*            �м�Ҫ��ʾ������

	* @param width

	*            �м�����Ŀ��

	* @param height

	*            �м�����ĸ߶�

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

	// ��ȡget_png�ļ���

	ActionContext ac = ActionContext.getContext();

	ServletContext sc = (ServletContext) ac

	.get(ServletActionContext.SERVLET_CONTEXT);

	String filePath = sc.getRealPath(File.separator + "style"

	+ File.separator + "imgs");

	String path = filePath;

	image = ImageIO.read(new FileInputStream(path + File.separator

	+ "newIcon.jpg"));

	Graphics2D g2 = image.createGraphics();// �õ�ͼ��������

	g2.setColor(Color.RED); // ���û�����ɫ

	// ��������

	g2.setFont(new Font("����", Font.LAYOUT_LEFT_TO_RIGHT, 15));// д��ǩ��

	if (centerName != null) {

	g2.drawString(centerName, width, height);

	}

	// ���������ַ����õ��ַ�����

	String[] messages2 = message.split("", 0);

	String[] messages = new String[messages2.length - 1];

	System.arraycopy(messages2, 1, messages, 0, messages2.length - 1);

	// ���������

	int ilength = messages.length;

	// ������������

	int fontsize = 16;

	Font f = new Font("", Font.BOLD, fontsize);

	FontRenderContext context = g2.getFontRenderContext();

	Rectangle2D bounds = f.getStringBounds(message, context);

	// �ַ���ȣ��ַ�������/�ַ���

	double char_interval = (bounds.getWidth() / ilength);

	// ���¶�

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

	// ��ת�Ƕ�

	double a = 2 * Math.asin(char_interval / (2 * radius2));

	if (odd) {

	g2.setFont(f);

	g2.drawString(messages[first],

	(float) (x0 - char_interval / 2), (float) y0);

	// ���ĵ���ұ�

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

	// ���ĵ�����

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

	// ���ĵ���ұ�

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

	// ���ĵ�����

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

	* ����������

	* 

	* @param message

	*            Ҫ�̵�����

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

	Graphics2D g2 = image.createGraphics();// �õ�ͼ��������

	g2.setColor(Color.RED); // ���û�����ɫ

	// ��������

	g2.setFont(new Font("����", Font.LAYOUT_LEFT_TO_RIGHT, 80));// д��ǩ��

	if (message != null) {

	String newMessage = null;

	// ������� �� XXX�� ��������� XX֮�� ����3�� �� ȡǰ��

	if (message.length() == 2) {

	newMessage = message + "֮��";

	} else if (message.length() == 3) {

	newMessage = message + "��";

	} else if (message.length() > 3) {

	newMessage = message.substring(0, 3) + "��";

	}

	char[] charArray = newMessage.toCharArray();

	g2.drawString(new String(new char[] { charArray[0] }), 90, 70);  // д����

	g2.drawString(new String(new char[] { charArray[1] }), 90, 160); // д����

	g2.drawString(new String(new char[] { charArray[2] }), 10, 70);  // д����

	g2.drawString(new String(new char[] { charArray[3] }), 10, 160); // д����

	}

	}

	return image;

	}



	/**

	* ��ȡ����ӡ��

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

	// �������������(Բ�Σ�

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

	BufferedImage image = OutPicture.bufferedImageUtil("�������������", null,

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
