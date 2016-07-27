package com.yil.seal;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import junit.framework.Assert;

import org.apache.commons.configuration.BaseConfiguration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.junit.Test;

import com.yil.seal.util.ConfigProperties;
import com.yil.seal.util.Constants;

public class CreateSealTest {

	@Test
	public void testCreateCicleSeal() {
		SealI circleSeal = new CircleSeal("广西某高科技术有限公司", "单位专用章", "4566011002243");
		BufferedImage image = circleSeal.draw();
		try {
			String filePath = Constants.IMG_OUTPUT_DIR + File.separator
					+ "circleseal.png";
			ImageIO.write(image, "png", new File(filePath)); 
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	@Test
	public void testCreateEllipseSeal() {
		SealI seal = new EllipseSeal("广西某高科技术有限公司", "单位专用章", "4566011002243");
		BufferedImage image = seal.draw();
		try {
			String filePath = Constants.IMG_OUTPUT_DIR + File.separator
					+ "Ellipseseal.png";
			ImageIO.write(image, "png", new File(filePath)); 
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
