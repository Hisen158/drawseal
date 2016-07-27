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
 * Բ��ӡ��
 * 
 * @author yilei@126.com
 *
 */
public class CircleSeal implements SealI {
	
	
	public BufferedImage draw() {
		// ����ͼ��buffer
		BufferedImage buffImg = new BufferedImage(WIDTH, HEIGHT,
				BufferedImage.TYPE_INT_RGB);
		Graphics2D g = buffImg.createGraphics();

		init(g);
		
		// ����Բ
		int radius = HEIGHT / 2;// �ܰ뾶
		int CENTERX = WIDTH / 2;// ��ͼ����λ��
		int CENTERY = HEIGHT / 2;// ��ͼ����λ��

		drawCicle(g, radius, CENTERX, CENTERY);
		
		
		//drawEmblemByText(g, "��",radius, CENTERX, CENTERY);
		
		drawEmblemByImg(g, "gh.png", radius, CENTERX, CENTERY);

		drawUnitName(g, radius, CENTERX, CENTERY);
		
		drawSealCode(g, radius, CENTERX, CENTERY);

		
		drawExt1(g,ext1, radius, CENTERX, CENTERY);
		
		
		

		// ������
		//g.setFont(new Font("����", Font.LAYOUT_LEFT_TO_RIGHT, 20));// д��ǩ��
		//g.drawString(sealCode, CENTERX - (60), CENTERY + (30 + 80));

		return buffImg;
	}

	private static final int WIDTH = 650;// ͼƬ���
	private static final int HEIGHT = 650;// ͼƬ�߶�
	private String unitName;// ��Ȧ
	private String ext1;
	private String sealCode;

	public CircleSeal(String unitName, String ext1, String sealCode) {
		this.unitName = unitName;
		this.ext1 = ext1;
		this.sealCode = sealCode;
	}

	public void init(Graphics2D g) {
		// ���ñ���
		g.setBackground(Color.WHITE);
		g.clearRect(0, 0, WIDTH, HEIGHT);

		// ���þ��Բ��
		// g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
		// RenderingHints.VALUE_ANTIALIAS_ON);
		
		//������У���ο���
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

	// ��ԲȦ
	public void drawCicle(Graphics2D g, int radius, int CENTERX, int CENTERY) {
		// ������ɫ
		g.setColor(Color.RED);

		// ���ñʻ���ϸ
		g.setStroke(new BasicStroke(18));
		Ellipse2D circle = new Ellipse2D.Double();
		// ��ȥ��ϸֵ
		circle.setFrameFromCenter(CENTERX, CENTERY, CENTERX + radius - 9,
				CENTERY + radius - 9);
		g.draw(circle);
	}

	// ������ �ռ� 
	public void drawEmblemByText(Graphics2D g,String text, int radius, int CENTERX, int CENTERY) {
		// �����м�������
		// �ռ�ֱ��
		int size = 230;
		g.setFont(new Font("����", Font.BOLD, size));
		g.drawString(text, CENTERX - (size / 2)-5, CENTERY + (size / 3));
	}
	
	// ��ͼƬ �ռ� 
	
	public void drawEmblemByImg(Graphics2D g,String imgPath, int radius, int CENTERX, int CENTERY) {
		// �����м�������
		// �ռ�ֱ��
		int size = 230;
		g.setFont(new Font("����", Font.BOLD, size));
		//g.drawString(text, CENTERX - (size / 2), CENTERY + (size / 3));
		g.drawImage(transparentImg(imgPath), CENTERX - (size / 2), CENTERY - (size / 2),size,size,null);
		
	}
	
	/**����͸������**/
	private BufferedImage transparentImg(String  imgPath){
		BufferedImage temp;
		try {
			temp = ImageIO.read(new File(Constants.BMP_IMG_DIR+File.separator+imgPath));
			int imgHeight = temp.getHeight();//ȡ��ͼƬ�ĳ��Ϳ�
	        int imgWidth = temp.getWidth();
	        int c = temp.getRGB(3, 3);
	        BufferedImage bi = new BufferedImage(imgWidth, imgHeight,
	                BufferedImage.TYPE_4BYTE_ABGR);//�½�һ������֧��͸����BufferedImage
	        for(int i = 0; i < imgWidth; ++i)//��ԭͼƬ�����ݸ��Ƶ��µ�ͼƬ��ͬʱ�ѱ�����Ϊ͸��
	        {
	            for(int j = 0; j < imgHeight; ++j)
	            {
	                if(temp.getRGB(i, j) == c)
	                    bi.setRGB(i, j, c & 0x00ffffff);//����ѱ�����Ϊ͸��
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

	// д��λ����
	public void drawUnitName(Graphics2D g, int radius, int CENTERX, int CENTERY) {

		// �뾶���٣���������Ȧ�ļ�϶
		radius = radius - 30;

		// ���������ַ����õ��ַ�����
		String[] charArray = unitName.split("", 0);

		// ����
		int charLength = charArray.length;

		// ��������
		Font f = new Font("����", Font.BOLD, 80);

		FontRenderContext context = g.getFontRenderContext();
		Rectangle2D bounds = f.getStringBounds(unitName, context);
		//bounds.setRect(bounds.getX(), bounds.getY(), bounds.getWidth(), bounds.getHeight()+140);

		// �ַ���ȣ��ַ�������/�ַ���
		double char_width = (bounds.getWidth() / charLength);
		char_width=char_width-5;

		// �ַ��߶�
		double ascent = -bounds.getY();
		
		//ascent+=5;

		int firstChari = 0, secondChari = 0;

		// �ַ���������ż�Ծ�����������
		boolean oddFlag = false;
		if (charLength % 2 == 1) {
			firstChari = (charLength - 1) / 2;
			oddFlag = true;
		} else {
			firstChari = (charLength) / 2 - 1;
			secondChari = (charLength) / 2;
			oddFlag = false;
		}

		// �ַ�Ȧ�뾶=ʵ�ʰ뾶-����߶�
		double fontRadius = radius - ascent;

		// �ַ�X����
		double x0 = CENTERX;
		// �ַ�Y����
		double y0 = CENTERY - fontRadius;

		// ÿ���ֵ� ��ת�Ƕ�(����)=2*asin(���/ֱ��)
		double per_angle = 2 * Math.asin(char_width / (2 * fontRadius));

		// ��������
		if (oddFlag) {
			// �Ȼ��м������
			g.setFont(f);
			g.drawString(charArray[firstChari],
					(float) (x0 - char_width / 2), (float) y0);

			// �������ĵ���ұ�
			for (int i = firstChari + 1; i < charLength; i++) {
				// �Ƕȵݼ�
				double current_angle = (i - firstChari) * per_angle;
				double current_x = fontRadius * Math.sin(current_angle);
				double current_y = fontRadius - fontRadius
						* Math.cos(current_angle);
				AffineTransform transform = AffineTransform
						.getRotateInstance(current_angle);// ,x0 + current_x, y0
															// + current_y);
				//transform.setToScale(transform.getScaleX(), transform.getScaleX());
				Font f2 = f.deriveFont(transform);
				g.setFont(f2);
				g.drawString(
						charArray[i],
						(float) (x0 + current_x - char_width / 2
								* Math.cos(current_angle)),
						(float) (y0 + current_y - char_width / 2
								* Math.sin(current_angle)));
			}
			// ���ĵ�����
			for (int i = firstChari - 1; i > -1; i--) {
				// �Ƕȵݼ�
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
			// ���ĵ���ұ�
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

			// ���ĵ�����
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

	// д13λӡ�±���
	public void drawSealCode(Graphics2D g, int radius, int CENTERX, int CENTERY) {

		// �뾶���٣���������Ȧ�ļ�϶
		//radius = radius - 10;

		// ���������ַ����õ��ַ�����
		String[] charArray = sealCode.split("", 0);

		// ����
		int charLength = charArray.length;

		// ��������
		Font f = new Font("Cambria", Font.BOLD, 20);

		FontRenderContext context = g.getFontRenderContext();
		Rectangle2D bounds = f.getStringBounds(sealCode, context);

		// �ַ���ȣ��ַ�������/�ַ���
		double char_width = bounds.getWidth() /charLength;
		
		char_width =char_width+18;

		/**
		 * �ַ��߶� 
		 */
		double ascent = -bounds.getY();
		

		int firstChari = 0, secondChari = 0;

		// �ַ���������ż�Ծ�����������
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
		 *  �ַ�Ȧ�뾶=ʵ�ʰ뾶-����߶�
		 */
		double fontRadius = radius - ascent-3;

		//ԭ�������
		//�ַ�X����
		double x0 = CENTERX;
		// �ַ�Y���� =ֱ��-��Ȧ���-��϶
		double y0 =2*CENTERY- ascent-3;

		// ÿ���ֵ� ��ת�Ƕ�(����)=2*asin(���/�뾶) ����ֱ�������λ��ȼ��㹫ʽ
		double per_angle = 2* Math.asin((char_width/2)/ fontRadius);

		// ��������
		if (oddFlag) {
			// �Ȼ��м������
			g.setFont(f);
			g.drawString(charArray[firstChari],
					(float) (x0 - char_width / 2), (float) y0);

			// �������ĵ���ұ�
			for (int i = firstChari + 1; i < charLength; i++) {
				// �Ƕȵݼ�
				double current_angle = (i - firstChari) * per_angle;
				//x������ݽǶȵı仯������λ��=�뾶*���ҽǶ�
				double current_x = fontRadius * Math.sin(current_angle); //a= c*sin(.)
				//current_x =2*fontRadius*Math.tan(per_angle)/(1-Math.tan(per_angle));
				double current_y = fontRadius - fontRadius
						* Math.cos(current_angle);
				//��������ת
				AffineTransform transform = AffineTransform
						.getRotateInstance(-current_angle);// ,x0 + current_x, y0
															// + current_y);
				Font f2 = f.deriveFont(transform);
				g.setFont(f2);
				
				//ʵ��X����=X0����+��תλ��x1-�ַ������תλ��x2
				//ʵ��Y����=y0����-��תλ��Y1-�ַ������תλ��x2
				g.drawString(
						charArray[i],
						(float) (x0 + current_x - char_width / 2
								* Math.cos(current_angle)),
						(float) (y0 - current_y + char_width / 2
								* Math.sin(current_angle)));
			}
			// ���ĵ�����
			for (int i = firstChari - 1; i > -1; i--) {
				// �Ƕȵݼ�
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
			// ���ĵ���ұ�
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

			// ���ĵ�����
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

	
/**д����ֵ**/
	private void drawExt1(Graphics2D g,String content, int radius, int cENTERX, int cENTERY) {
		// �������
		Font f = new Font("����", Font.BOLD, 80);
		g.setFont(f);
		
		FontRenderContext context = g.getFontRenderContext();
		Rectangle2D bounds = f.getStringBounds(content, context);

		// �ַ���ȣ��ַ�������/�ַ���
		//double char_width = bounds.getWidth() /charLength;
		
		int x = (int) ((2*cENTERX -bounds.getWidth())/2);
				
		g.drawString(content, x, cENTERY+200);
		
	}
}