package com.zhsj.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


/**
 * 
 * 项目名称：zhsjWeb   
 *
 * 类描述：读取config配置文件
 * 类名称：com.zhsj.util.ConfigUtil     
 * 创建人：xulinchuang
 * 创建时间：2017年2月16日 下午1:34:54
 */
public class ConfigUtil {
    
	public static String filepath;//
	public static String imgpath;//
	public static String FILE_URL;
	
	static Properties properties = null;
	static{
		properties = new Properties();
		InputStream is = null;
		try {
			is = ConfigUtil.class.getResourceAsStream("/config.properties");
			properties.load(is);
			filepath = properties.getProperty("filepath");
			imgpath = properties.getProperty("imgpath");
			FILE_URL= properties.getProperty("FILE_URL");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String getKey(String key){
		return properties.getProperty(key);
	}
	public static void main(String[] args) {
		System.err.println(ConfigUtil.filepath);
		System.err.println(ConfigUtil.imgpath);
		System.err.println(getKey("uploadpath"));
		System.err.println(getKey("zippath"));
	}
}
