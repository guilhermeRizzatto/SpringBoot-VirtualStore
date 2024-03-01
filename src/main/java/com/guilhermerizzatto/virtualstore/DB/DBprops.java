package com.guilhermerizzatto.virtualstore.DB;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DBprops {

	public static Properties loadProperties() {
		try (FileInputStream fs = new FileInputStream("URL TO DB.PROPERTIES")) {
			Properties props = new Properties();
			props.load(fs);
			return props;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
