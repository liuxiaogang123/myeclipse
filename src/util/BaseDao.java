package util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class BaseDao {
	/**
	 * ����dao�й���������
	 */
	protected static Scanner input = new Scanner (System.in);
	protected static Connection conn=null;//���Ӷ���
	protected static PreparedStatement stmt=null;//ִ��sql���Ķ���
	protected static ResultSet rs = null; // ���صĽ����
	/**
	 *  ����dao�й������������ݿ�ķ���
	 */
	protected static boolean getConnection(){
		try {
			//1.ʹ�÷�����Ƽ���������
			Class.forName(ConfigManager.getInstance().getValue("jdbc.driver"));   
			try {
				//2.���������ݿ������
				conn = DriverManager.getConnection(ConfigManager.getInstance().getValue("jdbc.url"),
						ConfigManager.getInstance().getValue("jdbc.userName"),
						ConfigManager.getInstance().getValue("jdbc.password"));
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}




