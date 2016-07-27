package com.yil.seal;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;

import com.yil.seal.util.Constants;

public class TestY {
	public static void main(String[] args) {
		int canvasWidth = 400;
		int canvasHeight = 400;
		double lineArc = 80 * (Math.PI / 180);// �Ƕ�ת����
		String savepath = Constants.IMG_OUTPUT_DIR
				+ File.separator +"seal.png";
		SimpleDateFormat format = new SimpleDateFormat("yyyy'��'MM'��'dd'��'");
		String head = "�й���ѧԺ��Ϣ�����о���";
		String foot = "����ר����";
		String center = format.format(new Date());
		BufferedImage image = GraphicsUtil.getSeal(head, center, foot,
				canvasWidth, canvasHeight, lineArc);

		try {
			ImageIO.write(image, "PNG", new File(savepath));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
