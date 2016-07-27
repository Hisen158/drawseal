package com.yil.seal.third;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;

import com.yil.seal.util.Constants;

/**
 * Բ��
 * 
 * @author SongFei
 * @date 2015��8��6��
 */
public class Oval {

	public static void main(String[] args) throws IOException {
		final BufferedImage targetImg = new BufferedImage(200, 300,
				BufferedImage.TYPE_INT_RGB);// ���һ��image����
		final Graphics2D g2d = targetImg.createGraphics();// ���һ��ͼ����
		g2d.drawOval(100, 100, 100, 100);// ����ͼ��
		final OutputStream out = new FileOutputStream(new File(
				Constants.IMG_OUTPUT_DIR + File.separator + "aa.jpeg"));// �������
		ImageIO.write(targetImg, "JPEG", out);// �����ͼƬ
	}
}