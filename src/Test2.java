
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import org.apache.log4j.Logger;;

public class Test2 {
	private static Logger logger=Logger.getLogger(Test1.class.getName());
   public static void main(String[] args) {
	   Connection conn=null;
	   Statement stmt=null;
	   String name="啦啦";
	   int health=100;
	   int love=0;
	   String strain="酷酷的雪瑞纳";
	//1.加载驱动
	   try {
		Class.forName("com.mysql.jdbc.Driver");
	} catch (ClassNotFoundException e) {
		logger.error(e);
	}
	   //2.建立链接
	   try {
		conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/epet?useUnicode=true&characterEncoding=utf-8","root","");
	   //3.插入狗狗信息到数据库
		stmt=conn.createStatement();
		StringBuffer sbSql=new StringBuffer("insert into dog(name,health,love,strain) value('");
		sbSql.append(name+"',");
		sbSql.append(health+",");
		sbSql.append(love+",'");
		sbSql.append(strain+"');");
		stmt.execute(sbSql.toString());
		logger.info("插入狗狗信息成功！");
	   } catch (SQLException e) {
		   logger.error(e);
	}finally{
		//3.关闭链接
		try {
			if(stmt!=null){
				stmt.close();
			}
			if(conn!=null){
			conn.close();
			}
		} catch (SQLException e) {
			logger.error(e);
		}
	}	   
}
}