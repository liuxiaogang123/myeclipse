
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.apache.log4j.Logger;;

public class Test4 {
	private static Logger logger=Logger.getLogger(Test1.class.getName());
   public static void main(String[] args) {
	   Connection conn=null;
	   Statement stmt=null;
	   ResultSet rs=null;
	//1.加载驱动
	   try {
		Class.forName("com.mysql.jdbc.Driver");
	} catch (ClassNotFoundException e) {
		logger.error(e);
	}
	   //2.建立链接
	   try {
		conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/epet?useUnicode=true&characterEncoding=utf-8","root","");
	   //3.查询并输出宠物主人信息
		stmt=conn.createStatement();
		rs=stmt.executeQuery("SELECT id,`name`,money FROM `master`;");
		System.out.println("\t主人信息列表");
		System.out.println("编号\t姓名\t元宝数");
		while(rs.next()){
			System.out.print(rs.getInt("id")+"\t");
			System.out.print(rs.getString("name")+"\t");
			System.out.println(rs.getInt("money"));
		}
	   } catch (SQLException e) {
		   logger.error(e);
	}finally{
		//3.关闭链接
		try {
	       rs.close();
		   stmt.close();
		   conn.close();
		} catch (SQLException e) {
			logger.error(e);
		}
	}	   
}
}