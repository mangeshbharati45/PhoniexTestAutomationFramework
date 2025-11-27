package com.API.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigManagerOld {
	// WAP to read the Properties file from src/test/resources/config/config.properties
	
	private static Properties prop = new Properties(); // Create object of properties class
	
	private ConfigManagerOld() {
		//private Constructor
	}
	
	static {
				// Operation of loading the properties file in the memory!
				// static block it will executed! Once During_ Class Loading Time!............
		
				File configFile = new File(System.getProperty("user.dir")+File.separator+"src"+File.separator+"test"+File.separator+"resources"+File.separator+"config"+File.separator+"config.properties");
				
				FileReader fileReader = null;
				try {
					fileReader = new FileReader(configFile);
					prop.load(fileReader);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
	}
	
	public static String getProperty(String key) {
		return prop.getProperty(key);
	}
}
