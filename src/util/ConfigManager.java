package util;
import java.io.InputStream;
import java.util.Properties;


public class ConfigManager {
	// 01.��������ľ�̬����
		private static ConfigManager manager = new ConfigManager();// ����
		// ������ȡproperties�ļ��Ķ���
		private static Properties properties;
		// 02.˽�л�����
		private ConfigManager() {
			// �����ǵ�jdbc.propertiesת����������
			properties = new Properties();
			InputStream stream = ConfigManager.class.getClassLoader()
					.getResourceAsStream("properties");
			try {
				// �����ļ�
				properties.load(stream);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					stream.close(); // �ͷ���Դ
				} catch (Exception e) {
				e.printStackTrace();
				}
			}
		}
		// 03.�����ṩ���ʱ���Ľӿ�
		public static synchronized ConfigManager getInstance() {
		return manager;
		}
		// 04. ����Key��ȡvalue
		public static String getValue(String key) {
			return properties.getProperty(key);
		}
}