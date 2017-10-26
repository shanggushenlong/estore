package cn.itcast.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

//工具类
public class ConfigUtils {
	
	//读取配置文件,在文件加载的时候读取,在静态代码块中读取
	private static Properties prop = null;
	static{
		prop = new Properties();
		try {
			prop.load(new FileInputStream(ConfigUtils.class.getClassLoader().getResource("config.properties").getPath()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//构造方法私有化
	private ConfigUtils(){}
	
	//工具类其他方法全部为静态
	public static Properties getProp(){
		return prop;
	}
	
	public static String getProp(String key){
		return prop.getProperty(key);
	}
}
