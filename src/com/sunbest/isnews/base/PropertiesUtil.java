package com.sunbest.isnews.base;

import android.annotation.SuppressLint;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;

public class PropertiesUtil {

	private static Properties properties;
	private static PropertiesUtil propertiesUtil = new PropertiesUtil();
	private List<String> keyList = new ArrayList<String>();

	static InputStream in = PropertiesUtil.class.getClassLoader()
			.getResourceAsStream("weather.properties");

	private PropertiesUtil() {
	}

	@SuppressLint("NewApi")
	public static PropertiesUtil getInstance() {

		if (null != propertiesUtil) {
			propertiesUtil = new PropertiesUtil();
			properties = new Properties();
			Reader reader = null;
			try {
				reader = new BufferedReader(new InputStreamReader(in, "GBK"));
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				properties.load(reader);

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return propertiesUtil;
	}

	public String getValue(String key) {
		return properties.getProperty(key);
	}

	// 通过value 获取Key
	public String getKey(String value) {
		String val = "";
		Set<Object> keys = properties.keySet();
		for (Object key : keys) {
			val = getValue(key.toString());
			if (val.equals(value)) {
				val = key.toString();
				break;
			}
		}
		return val;

	}

}
