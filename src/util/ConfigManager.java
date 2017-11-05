package util;
import java.io.InputStream;
import java.util.Properties;


public class ConfigManager {
	// 01.创建本类的静态变量
		private static ConfigManager manager = new ConfigManager();// 饿汉
		// 创建读取properties文件的对象
		private static Properties properties;
		// 02.私有化构造
		private ConfigManager() {
			// 将我们的jdbc.properties转换成输入流
			properties = new Properties();
			InputStream stream = ConfigManager.class.getClassLoader()
					.getResourceAsStream("properties");
			try {
				// 加载文件
				properties.load(stream);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					stream.close(); // 释放资源
				} catch (Exception e) {
				e.printStackTrace();
				}
			}
		}
		// 03.对外提供访问本类的接口
		public static synchronized ConfigManager getInstance() {
		return manager;
		}
		// 04. 根据Key获取value
		public static String getValue(String key) {
			return properties.getProperty(key);
		}
}
