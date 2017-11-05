import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.apache.log4j.Logger;;

public class Test1 {
	private static Logger logger=Logger.getLogger(Test1.class.getName());
   public static void main(String[] args) {
	   Connection conn=null;
	//1.加载驱动
	   try {
		Class.forName("com.mysql.jdbc.Driver");
	} catch (ClassNotFoundException e) {
		logger.error(e);
	}
	   //2.建立链接
	   try {
		conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/epet","root","");
	   System.out.println("建立链接成功！");
	   } catch (SQLException e) {
		   logger.error(e);
	}finally{
		//3.关闭链接
		try {
			if(conn!=null){
			conn.close();
			System.out.println("关闭链接成功！");
			}
		} catch (SQLException e) {
			logger.error(e);
		}
	}	   
}
}
