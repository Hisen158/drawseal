package com.yil.seal;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.yil.seal.util.Constants;

/**
 * 椭圆印章
 * 
 * @author yilei@126.com
 *
 */
public class EllipseSeal implements SealI {
	
	
	public BufferedImage draw() {
		// 定义图像buffer
		BufferedImage buffImg = new BufferedImage(WIDTH, HEIGHT,
				BufferedImage.TYPE_INT_RGB);
		Graphics2D g = buffImg.createGraphics();

		init(g);
		
		// 绘制圆
		int radius = HEIGHT / 2;// 周半径
		int CENTERX = WIDTH / 2;// 画图所出位置
		int CENTERY = HEIGHT / 2;// 画图所处位置

		drawCicle(g, radius, CENTERX, CENTERY);
		
		
		//drawEmblemByText(g, "★",radius, CENTERX, CENTERY);
		
		drawEmblemByImg(g, "gh.png", radius, CENTERX, CENTERY);

		drawUnitName(g, radius, CENTERX, CENTERY);
		
		drawSealCode(g, radius, CENTERX, CENTERY);

		
		drawExt1(g,ext1, radius, CENTERX, CENTERY);
		
		
		

		// 添加年份
		//g.setFont(new Font("宋体", Font.LAYOUT_LEFT_TO_RIGHT, 20));// 写入签名
		//g.drawString(sealCode, CENTERX - (60), CENTERY + (30 + 80));

		return buffImg;
	}

	private static final int WIDTH = 650;// 图片宽度
	private static final int HEIGHT = 650*3/4;// 图片高度
	private String unitName;// 外圈
	private String ext1;
	private String sealCode;

	public EllipseSeal(String unitName, String ext1, String sealCode) {
		this.unitName = unitName;
		this.ext1 = ext1;
		this.sealCode = sealCode;
	}

	public void init(Graphics2D g) {
		// 设置背景
		g.setBackground(Color.WHITE);
		g.clearRect(0, 0, WIDTH, HEIGHT);

		// 设置锯齿圆滑
		// g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
		// RenderingHints.VALUE_ANTIALIAS_ON);
		
		//画两条校正参考线
		if(Constants.DEBUG){
			Stroke dash = new BasicStroke(2.5f, BasicStroke.CAP_BUTT,
					BasicStroke.JOIN_ROUND, 3.5f, new float[] { 15, 10, },
					0f);
			g.setStroke(dash);
			g.setColor(Color.gray);
			g.drawLine(0, 0, WIDTH, HEIGHT);
			g.drawLine(0, HEIGHT/2, WIDTH, HEIGHT/2);
			g.drawLine(0, HEIGHT/4, WIDTH, HEIGHT/4);
			g.drawLine(0, 3*HEIGHT/4, WIDTH, 3*HEIGHT/4);
			g.drawLine(WIDTH/2, 0, WIDTH/2, HEIGHT);
			g.drawLine(WIDTH/4, 0, WIDTH/4, HEIGHT);
			g.drawLine(3*WIDTH/4, 0, 3*WIDTH/4, HEIGHT);
			g.drawLine(0, HEIGHT, WIDTH, 0);
		}
		
	}

	// 画圆圈
	public void drawCicle(Graphics2D g, int radius, int CENTERX, int CENTERY) {
		// 设置颜色
		g.setColor(Color.RED);

		int strokeSize=15;
		// 设置笔画粗细
		g.setStroke(new BasicStroke(strokeSize));
		Ellipse2D circle = new Ellipse2D.Double();
		// 减去粗细值
		//circle.setFrameFromCenter(CENTERX, CENTERY, CENTERX + radius - 9,	CENTERY + radius - 9);
		circle.setFrame(strokeSize/2, strokeSize/2, CENTERX*2-strokeSize, CENTERY*2-strokeSize);
		g.draw(circle);
	}

	// 画文字 徽记 
	public void drawEmblemByText(Graphics2D g,String text, int radius, int CENTERX, int CENTERY) {
		// 绘制中间的五角星
		// 徽记直径
		int size = 230;
		g.setFont(new Font("宋体", Font.BOLD, size));
		g.drawString(text, CENTERX - (size / 2)-5, CENTERY + (size / 3));
	}
	
	// 画图片 徽记 
	
	public void drawEmblemByImg(Graphics2D g,String imgPath, int radius, int CENTERX, int CENTERY) {
		// 绘制中间的五角星
		// 徽记直径
		int size = 230;
		g.setFont(new Font("宋体", Font.BOLD, size));
		//g.drawString(text, CENTERX - (size / 2), CENTERY + (size / 3));
		g.drawImage(transparentImg(imgPath), CENTERX - (size / 2), CENTERY - (size / 2),size,size,null);
		
	}
	
	/**背景透明处理**/
	private BufferedImage transparentImg(String  imgPath){
		BufferedImage temp;
		try {
			temp = ImageIO.read(new File(Constants.BMP_IMG_DIR+File.separator+imgPath));
			int imgHeight = temp.getHeight();//取得图片的长和宽
	        int imgWidth = temp.getWidth();
	        int c = temp.getRGB(3, 3);
	        BufferedImage bi = new BufferedImage(imgWidth, imgHeight,
	                BufferedImage.TYPE_4BYTE_ABGR);//新建一个类型支持透明的BufferedImage
	        for(int i = 0; i < imgWidth; ++i)//把原图片的内容复制到新的图片，同时把背景设为透明
	        {
	            for(int j = 0; j < imgHeight; ++j)
	            {
	                if(temp.getRGB(i, j) == c)
	                    bi.setRGB(i, j, c & 0x00ffffff);//这里把背景设为透明
	                else
	                    bi.setRGB(i, j, temp.getRGB(i, j));
	            }
	        }
	        return bi ;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	// 写单位名称
	public void drawUnitName(Graphics2D g, int radius, int CENTERX, int CENTERY) {

		// 半径减少，留出和外圈的间隙
		radius = radius - 20;

		// 根据输入字符串得到字符数组
		String[] charArray = unitName.split("", 0);

		// 字数
		int charLength = charArray.length;

		// 设置字体
		Font f = new Font("宋体", Font.BOLD, 60);

		FontRenderContext context = g.getFontRenderContext();
		Rectangle2D bounds = f.getStringBounds(unitName, context);
		//bounds.setRect(bounds.getX(), bounds.getY(), bounds.getWidth(), bounds.getHeight()+140);

		// 字符宽度＝字符串长度/字符数
		double char_width = (bounds.getWidth() / charLength);
		char_width=char_width-5;

		// 字符高度
		double ascent = -bounds.getY();
		
		//ascent+=5;

		int firstChari = 0, secondChari = 0;

		// 字符数量的奇偶性决定居中排列
		boolean oddFlag = false;
		if (charLength % 2 == 1) {
			firstChari = (charLength - 1) / 2;
			oddFlag = true;
		} else {
			firstChari = (charLength) / 2 - 1;
			secondChari = (charLength) / 2;
			oddFlag = false;
		}

		// 字符圈半径=实际半径-字体高度
		double fontRadius = radius - ascent;
		double aRadius = WIDTH / 2-20;
		double bRadius = 3*aRadius/4;
		
		//3:4 椭圆  离心率= 
		double e = Math.sqrt(7)/4;
		
		System.out.println("e:"+e);
		//焦点的距离
		
		double c = Math.sqrt(aRadius*aRadius-bRadius*bRadius);
		System.out.println("c:"+c);

		// 字符X坐标
		double x0 = CENTERX;
		// 字符Y坐标
		double y0 = CENTERY - fontRadius;
		
		System.out.println("y0:"+y0);
		System.out.println("aRadius:"+aRadius);
		System.out.println("bRadius:"+bRadius);

		// 每个字的 旋转角度(弧度)=2*asin(宽度/直径)
		double per_angle = 2 * Math.asin(char_width / (2 * fontRadius));

		//每次平移X轴的距离
		
		double perX=char_width;
		double perY=5;
		
		// 奇数个字
		if (oddFlag) {
			// 先画中间这个字
			g.setFont(f);
			g.drawString(charArray[firstChari],
					(float) (x0 - char_width / 2), (float) y0);

			// 绘制中心点的右边
			for (int i = firstChari + 1; i < charLength; i++) {
				// 角度递减
				double current_angle = (i - firstChari) * per_angle;
				//double current_x = (i - firstChari)*perX;
				double current_x = aRadius*Math.sin(current_angle)-char_width/2;
				//double current_angle =Math.sin(current_x/aRadius);
				//
				//double current_x = aRadius * Math.sin(current_angle);
				double current_y =  (i - firstChari)*perY;
				//current_y=aRadius*Math.sqrt(3-3*current_x*current_x/4);
				current_y=Math.sqrt(9/16*(aRadius*aRadius-current_x*current_x));
				System.out.println(aRadius*aRadius-current_x*current_x);
				float test =(float) ((9.0/16.0)*(aRadius*aRadius-current_x*current_x));
				
				System.out.println(charArray[i]+" test :"+test);
				//根据X可求得Y
				current_y=Math.sqrt(test);
				
				current_angle=Math.atan(current_x/current_y);
				
				System.out.println(charArray[i]+" current_angle :"+current_angle);
				
				System.out.println(charArray[i]+" current_x :"+current_x);
				
				System.out.println(charArray[i]+" current_y :"+current_y);
				AffineTransform transform = AffineTransform
						.getRotateInstance(current_angle);// ,x0 + current_x, y0
															// + current_y);
				//transform.setToScale(transform.getScaleX(), transform.getScaleX());
				Font f2 = f.deriveFont(transform);
				g.setFont(f2);
				g.drawString(
						charArray[i],
						(float) (x0 - char_width / 2+ current_x),
						(float) (bRadius-current_y +y0));
			}
			// 中心点的左边
			for (int i = firstChari - 1; i > -1; i--) {
				// 角度递减
				double current_angle = (firstChari - i) * per_angle;
				double current_x = fontRadius * Math.sin(current_angle);
				double current_y = fontRadius - fontRadius
						* Math.cos(current_angle);
				AffineTransform transform = AffineTransform
						.getRotateInstance(-current_angle);// ,x0 + current_x,
															// y0 + current_y);
				Font f2 = f.deriveFont(transform);
				g.setFont(f2);
				g.drawString(
						charArray[i],
						(float) (x0 - current_x - char_width / 2
								* Math.cos(current_angle)),
						(float) (y0 + current_y + char_width / 2
								* Math.sin(current_angle)));
			}

		} else {
			// 中心点的右边
			for (int i = secondChari; i < charLength; i++) {
				double current_angle = (i - secondChari + 0.5) * per_angle;
				double current_x = fontRadius * Math.sin(current_angle);
				double current_y = fontRadius - fontRadius
						* Math.cos(current_angle);
				AffineTransform transform = AffineTransform
						.getRotateInstance(current_angle);// ,x0 + current_x, y0
															// + current_y);
				Font f2 = f.deriveFont(transform);
				g.setFont(f2);
				g.drawString(
						charArray[i],
						(float) (x0 + current_x - char_width / 2
								* Math.cos(current_angle)),
						(float) (y0 + current_y - char_width / 2
								* Math.sin(current_angle)));
			}

			// 中心点的左边
			for (int i = firstChari; i > -1; i--) {
				double current_angle = (firstChari - i + 0.5) * per_angle;
				double current_x = fontRadius * Math.sin(current_angle);
				double current_y = fontRadius - fontRadius
						* Math.cos(current_angle);
				AffineTransform transform = AffineTransform
						.getRotateInstance(-current_angle);// ,x0 + current_x,
															// y0 + current_y);
				Font f2 = f.deriveFont(transform);
				g.setFont(f2);
				g.drawString(
						charArray[i],
						(float) (x0 - current_x - char_width / 2
								* Math.cos(current_angle)),
						(float) (y0 + current_y + char_width / 2
								* Math.sin(current_angle)));
			}
		}

	}

	// 写13位印章编码
	public void drawSealCode(Graphics2D g, int radius, int CENTERX, int CENTERY) {

		// 半径减少，留出和外圈的间隙
		//radius = radius - 10;

		// 根据输入字符串得到字符数组
		String[] charArray = sealCode.split("", 0);

		// 字数
		int charLength = charArray.length;

		// 设置字体
		Font f = new Font("Cambria", Font.BOLD, 20);

		FontRenderContext context = g.getFontRenderContext();
		Rectangle2D bounds = f.getStringBounds(sealCode, context);

		// 字符宽度＝字符串长度/字符数
		double char_width = bounds.getWidth() /charLength;
		
		char_width =char_width+18;

		/**
		 * 字符高度 
		 */
		double ascent = -bounds.getY();
		

		int firstChari = 0, secondChari = 0;

		// 字符数量的奇偶性决定居中排列
		boolean oddFlag = false;
		if (charLength % 2 == 1) {
			firstChari = (charLength - 1) / 2;
			oddFlag = true;
		} else {
			firstChari = (charLength) / 2 - 1;
			secondChari = (charLength) / 2;
			oddFlag = false;
		}

		/**
		 *  字符圈半径=实际半径-字体高度
		 */
		double fontRadius = radius - ascent-3;

		//原点在哪里？
		//字符X坐标
		double x0 = CENTERX;
		// 字符Y坐标 =直径-外圈宽度-间隙
		double y0 =2*CENTERY- ascent-3;

		// 每个字的 旋转角度(弧度)=2*asin(宽度/半径) 等腰直角三角形弧度计算公式
		double per_angle = 2* Math.asin((char_width/2)/ fontRadius);

		// 奇数个字
		if (oddFlag) {
			// 先画中间这个字
			g.setFont(f);
			g.drawString(charArray[firstChari],
					(float) (x0 - char_width / 2), (float) y0);

			// 绘制中心点的右边
			for (int i = firstChari + 1; i < charLength; i++) {
				// 角度递减
				double current_angle = (i - firstChari) * per_angle;
				//x坐标根据角度的变化产生的位移=半径*正弦角度
				double current_x = fontRadius * Math.sin(current_angle); //a= c*sin(.)
				//current_x =2*fontRadius*Math.tan(per_angle)/(1-Math.tan(per_angle));
				double current_y = fontRadius - fontRadius
						* Math.cos(current_angle);
				//反方向旋转
				AffineTransform transform = AffineTransform
						.getRotateInstance(-current_angle);// ,x0 + current_x, y0
															// + current_y);
				Font f2 = f.deriveFont(transform);
				g.setFont(f2);
				
				//实际X坐标=X0坐标+旋转位移x1-字符宽度旋转位移x2
				//实际Y坐标=y0坐标-旋转位移Y1-字符宽度旋转位移x2
				g.drawString(
						charArray[i],
						(float) (x0 + current_x - char_width / 2
								* Math.cos(current_angle)),
						(float) (y0 - current_y + char_width / 2
								* Math.sin(current_angle)));
			}
			// 中心点的左边
			for (int i = firstChari - 1; i > -1; i--) {
				// 角度递减
				double current_angle = (firstChari - i) * per_angle;
				double current_x = fontRadius * Math.sin(current_angle);
				double current_y = fontRadius - fontRadius
						* Math.cos(current_angle);
				AffineTransform transform = AffineTransform
						.getRotateInstance(current_angle);// ,x0 + current_x,
															// y0 + current_y);
				Font f2 = f.deriveFont(transform);
				g.setFont(f2);
				g.drawString(
						charArray[i],
						(float) (x0 - current_x - char_width / 2
								* Math.cos(current_angle)),
						(float) (y0 - current_y - char_width / 2
								* Math.sin(current_angle)));
			}

		} else {
			// 中心点的右边
			for (int i = secondChari; i < charLength; i++) {
				double current_angle = (i - secondChari + 0.5) * per_angle;
				double current_x = fontRadius * Math.sin(current_angle);
				double current_y = fontRadius - fontRadius
						* Math.cos(current_angle);
				AffineTransform transform = AffineTransform
						.getRotateInstance(-current_angle);// ,x0 + current_x, y0
															// + current_y);
				Font f2 = f.deriveFont(transform);
				g.setFont(f2);
				g.drawString(
						charArray[i],
						(float) (x0 + current_x - char_width / 2
								* Math.cos(current_angle)),
						(float) (y0 - current_y + char_width / 2
								* Math.sin(current_angle)));
			}

			// 中心点的左边
			for (int i = firstChari; i > -1; i--) {
				double current_angle = (firstChari - i + 0.5) * per_angle;
				double current_x = fontRadius * Math.sin(current_angle);
				double current_y = fontRadius - fontRadius
						* Math.cos(current_angle);
				AffineTransform transform = AffineTransform
						.getRotateInstance(current_angle);// ,x0 + current_x,
															// y0 + current_y);
				Font f2 = f.deriveFont(transform);
				g.setFont(f2);
				g.drawString(
						charArray[i],
						(float) (x0 - current_x - char_width / 2
								* Math.cos(current_angle)),
						(float) (y0 - current_y - char_width / 2
								* Math.sin(current_angle)));
			}
		}

	}

	
/**写如后段值**/
	private void drawExt1(Graphics2D g,String content, int radius, int cENTERX, int cENTERY) {
		// 添加姓名
		Font f = new Font("宋体", Font.BOLD, 80);
		g.setFont(f);
		
		FontRenderContext context = g.getFontRenderContext();
		Rectangle2D bounds = f.getStringBounds(content, context);

		// 字符宽度＝字符串长度/字符数
		//double char_width = bounds.getWidth() /charLength;
		
		int x = (int) ((2*cENTERX -bounds.getWidth())/2);
				
		g.drawString(content, x, cENTERY+200);
		
	}
}