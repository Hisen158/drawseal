package com.yil.seal;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;

import com.yil.seal.util.Constants;

/**
 * 圆形
 * 
 * @author SongFei
 * @date 2015年8月6日
 */
public class Oval {

	public static void main(String[] args) throws IOException {
		final BufferedImage targetImg = new BufferedImage(200, 300,
				BufferedImage.TYPE_INT_RGB);// 获得一个image对象
		final Graphics2D g2d = targetImg.createGraphics();// 获得一个图形类
		g2d.drawOval(100, 100, 100, 100);// 绘制图形
		final OutputStream out = new FileOutputStream(new File(
				Constants.IMG_OUTPUT_DIR + File.separator + "aa.jpeg"));// 打开输出流
		ImageIO.write(targetImg, "JPEG", out);// 保存成图片
	}
}