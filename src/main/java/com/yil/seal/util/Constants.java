package com.yil.seal.util;

import java.io.File;

public class Constants {

	public static String IMG_OUTPUT_DIR = System.getProperty("user.dir")
			+ File.separator
			+ ConfigProperties.getInstance().getString("output.dir", "output");

	public static final Integer IMG_WIDTH = ConfigProperties.getInstance()
			.getInteger("img.width", 800);

	public static final Integer IMG_HEIGHT = ConfigProperties.getInstance()
			.getInteger("img.height", 800);

}
