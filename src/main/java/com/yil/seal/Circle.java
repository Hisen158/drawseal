package com.yil.seal;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;

import org.apache.commons.io.IOUtils;
import org.apache.commons.math3.exception.MathArithmeticException;

import com.yil.seal.util.Constants;

public class Circle {

	private static final String saveName = "cicle.png";

	public static void main(String[] args) {

		BufferedImage targetImg = new BufferedImage(Constants.IMG_WIDTH,
				Constants.IMG_HEIGHT, BufferedImage.TYPE_INT_RGB);// 获得一个image对象
		final Graphics2D g2d = targetImg.createGraphics();// 获得一个图形类
		g2d.setBackground(Color.WHITE);
		g2d.clearRect(0, 0, Constants.IMG_WIDTH, Constants.IMG_HEIGHT);
		g2d.setPaint(Color.RED);
		g2d.setStroke(new BasicStroke(5));
		g2d.setColor(new Color(255, 0, 0));
		// g2d.setStroke(new BasicStroke(1));
		// g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,
		// 1.0f)); // 1.0f为透明度 ，值从0-1.0，依次变得不透明
		g2d.drawOval(0, 0, Constants.IMG_WIDTH - 5, Constants.IMG_HEIGHT - 5);// 绘制圆形

		Font f = new Font("宋体", Font.BOLD, 24);
		Color[] colors = { Color.ORANGE, Color.LIGHT_GRAY };
		g2d.setFont(f);
		// 平移原点到图形环境的中心
		g2d.translate(Constants.IMG_WIDTH / 2, Constants.IMG_HEIGHT / 2);

		// 利用GeneralPath类来画曲线
		//GeneralPath gp = new GeneralPath();

		//gp.moveTo(0, 0);

		//drawSinx2(gp, g2d);
		
		

		//g2d.drawString("4503000000001", 0, 0);
		drawSealCode("4503000000001",g2d);

		// g2d.draw3DRect(0, 0, Constants.IMG_WIDTH / 2, Constants.IMG_WIDTH /
		// 2, true);

		//g2d.drawArc(-300, (int)Math.sqrt(375 * 375 - 300 * 300), 370, 370, 30, -30);
		g2d.dispose();
		save(targetImg);

	}

	private static void drawSealCode(String sealCode, Graphics2D g2d) {
		GeneralPath sealcodegp = new GeneralPath();
		sealcodegp.moveTo(-300, Math.sqrt(375 * 375 - 300 * 300));
		
		Font f = new Font("宋体", Font.BOLD, 24);

		double x = -300;

		//sealcodegp.moveTo(0,  0);
		double addx = 600 / sealCode.toCharArray().length;
		System.out.println(Math.acos(300/375));
		System.out.println(Math.acos(300/375)*Math.PI/180);
		//g2d.rotate(Math.acos(300/375)*Math.PI/180);
	
		
		FontRenderContext frc = new FontRenderContext(null, true, true);
		GlyphVector glyphVector = f.createGlyphVector(frc, sealCode);
		AffineTransform t = new AffineTransform();
		//t.setToTranslation( x, y );
		t.rotate( 45*Math.PI/180 );
		System.out.println(glyphVector.getNumGlyphs());
		Shape glyph = glyphVector.getGlyphOutline( 0 );
		sealcodegp.append( t.createTransformedShape( glyph ), false );
		//t.rotate( 2 );
		//glyph = glyphVector.getGlyphOutline( 2 );
		//sealcodegp.append( t.createTransformedShape( glyph ), false );
		//t.translate( -px-advance, -py );
		
		
		g2d.draw(sealcodegp);

		for (int i = 0; i < sealCode.toCharArray().length; i++) {
			System.out.println("s:" + String.valueOf(sealCode.charAt(i)));
			
			//旋转
			
			//g2d.rotate(Math.sin((x + addx* i)/375));
			
			
			//g2d.drawString(String.valueOf(sealCode.charAt(i)), (int) (x + addx
					//* i), (int) Math.sqrt(375 * 375 - (x + addx * i)
					//* (x + addx * i)));
			
			

		}

	}

	// 画
	private static void drawSinx2(GeneralPath gp, Graphics2D g2d) {
		Font f = new Font("宋体", Font.BOLD, 16);
		Text te = new Text("4", f);
		// 平移原点到图形环境的中心
		// g2d.translate(-300, Math.sqrt(375*375-300*300));

		// g2d.setStroke(new BasicStroke((float) 0.5));
		gp.moveTo(-300, Math.sqrt(375 * 375 - 300 * 300));
		for (double x = -300; x <= 300; x += 1) {
			// gp.lineTo(x, Math.sqrt(375 * 375 - x * x));
			// gp.intersects(pi, x, y, w, h)
			// gp.contains(p)

		}
		// gp.append(te.createStrokedShape(shape), true);
		// gp.lineTo(-300, 228);
		// gp.lineTo(-280, 235);
		g2d.draw(te.createStrokedShape(gp));
		// g2d.rotate(Math.PI);
		// g2d.draw(gp);
	}

	private static void drawSinx(GeneralPath gp, Graphics2D g2d) {
		for (double i = 0.000001; i <= 8 * Math.PI; i += 0.0001 * Math.PI) {
			gp.lineTo(20 * i, 100 * -Math.sin(i));
		}
		g2d.draw(gp);
		g2d.rotate(Math.PI);
		g2d.draw(gp);
	}

	public static void save(BufferedImage targetImg) {
		OutputStream out = null;
		try {
			out = new FileOutputStream(new File(Constants.IMG_OUTPUT_DIR
					+ File.separator + saveName));
			ImageIO.write(targetImg, "PNG", out);// 保存成图片
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			IOUtils.closeQuietly(out);
		}

	}

}
