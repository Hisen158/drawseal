package com.yil.seal.util;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

public class ConfigProperties {

	private static PropertiesConfiguration config;

	private ConfigProperties() {

	}

	public static PropertiesConfiguration getInstance() {
		if (null == config) {
			try {
				config = new PropertiesConfiguration("config.properties");
			} catch (ConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return config;
	}
}
