
import java.sql.SQLException;
import org.apache.log4j.Logger;
import util.BaseDao;
/**
 * 优化
 * @author Lenovo
 *
 */
public class jdbcTest3 extends BaseDao{
  static Logger logger=Logger.getLogger(jdbcTest3.class);
    public static void main(String[] args) throws ClassNotFoundException {
    	boolean flag = loainUser();
    	//获取用户输入
    	if(flag){
    	System.out.print("1.查询所有用户信息\t");
    	System.out.print("2.增加用户信息\t");
    	System.out.print("3.删除用户信息\t");
    	System.out.print("4.修改用户信息\t\n");
    	System.out.println("请输入您的选择:");
    	int choose = input.nextInt();
		switch (choose) {
		case 1:
			selectAllUsers();// 查询所有用户信息
			break;
		case 2:
			insertUser();// 增加用户信息
			break;
		case 3:
			deleteUser();// 删除用户信息
			break;
		case 4:
			updateUser();// 修改用户信息
			break;
		}
    	}else{
    		System.out.println("登陆失败！");
    	}
}
    /**
     * 用户登录的方法
     * @return
     */
    private static boolean loainUser() {
    	// 获取用户的输入
    	System.out.println("请您输入登录名称：");
    	String loainName = input.next();
    	System.out.println("请您输入登录密码：");
    	String password = input.next();
    	boolean flag = false;
    	//1.加载驱动
    	   try {
    		   getConnection(); // 调用父类连接的方法
    	// 书写sql 现在dbms中运行一次
    	String sql = "SELECT loainName,password FROM easybuy_user where loainName=? and password=?";
    	// 执行sql
    	stmt = conn.prepareStatement(sql);
    	// 给占位符赋值
    	stmt.setString(1, loainName);
    	stmt.setString(2, password);
    	// 得到的结果集
    	rs = stmt.executeQuery();
    	// 处理结果集
    	if(rs.next()){ // 证明有用户
    		flag = true;
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
		return flag;
	}
	/**
     * 修改用户信息
     */
    private static void updateUser() {
    	// 获取用户的输入
    	System.out.println("请您输入真实姓名：");
    	String userName = input.next();
    	System.out.println("请您输入新昵称：");
    	String loainName = input.next();
    	System.out.println("请您输入新密码：");
    	String password = input.next();
    	//1.加载驱动
    	   try {
    		   getConnection();//调用父类连接的方法
    	// 书写sql 现在dbms中运行一次
    	String sql = "UPDATE  easybuy_user SET loainName=? `password`=? WHERE userName=?";
    	// 执行sql
    	stmt = conn.prepareStatement(sql);
    	// 占位符赋值
    	stmt.setString(1, loainName);
    	stmt.setString(2, password);
    	stmt.setString(3, userName);
    	// 得到的结果集
    	int rowNum = stmt.executeUpdate();
    	if(rowNum > 0){
    		System.out.println("修改成功！");
    	}else{
    		System.out.println("修改失败！");
    	}
    	   } catch (SQLException e) {
    		   logger.error(e);
    	}finally{
    		//3.关闭链接
    		try {
    				stmt.close();
    			    conn.close();
    		} catch (SQLException e) {
    			logger.error(e);
    		}
    	}
	}    
	/**
     * 删除用户信息
     */
    private static void deleteUser() {
    	// 获取用户的输入
    	System.out.println("请您输入真实姓名：");
    	String userName = input.next();
    	//1.加载驱动
    	   try {
    		   getConnection();//调用父类连接的方法
    	// 书写sql 先在dbms中运行一次
    	String sql = "DELETE FROM easybuy_user WHERE userName=?";
    	// 执行sql
    	stmt = conn.prepareStatement(sql);
    	// 占位符赋值
    	stmt.setString(1, userName);
    	// 得到的结果集
    	int rowNum = stmt.executeUpdate();
    	if(rowNum > 0){
    		System.out.println("删除成功！");
    	}else{
    		System.out.println("删除失败！");
    	}
    	   } catch (SQLException e) {
    		   logger.error(e);
    	}finally{
    		//3.关闭链接
    		try {
    				stmt.close();
    			    conn.close();
    		} catch (SQLException e) {
    			logger.error(e);
    		}
    	}		
	}
	/**
     * 增加用户信息
     */
    private static void insertUser() {
    	// 获取用户的输入
    	System.out.println("请您输入登录名称：");
    	String loainName = input.next();
    	System.out.println("请您输入登录密码：");
    	String password = input.next();
    	System.out.println("请您输入真实姓名：");
    	String userName = input.next();
    	System.out.println("请您输入性别：(1/男   0/女)");
    	int sex = input.nextInt();
    	//1.加载驱动
    	   try {
    		   getConnection();//调用父类连接的方法
    	// 书写sql 现在dbms中运行一次
    	String sql = "INSERT  INTO easybuy_user(loainName,userName,`password`,sex)"+ " VALUES(?,?,?,?)";
    	// 执行sql
    	stmt = conn.prepareStatement(sql);
    	// 占位符 赋值
    	stmt.setString(1, loainName);
    	stmt.setString(2, userName);
    	stmt.setString(3, password);
    	stmt.setInt(4, sex);
    	// 得到的结果集
    	int rowNum = stmt.executeUpdate();
    	if(rowNum > 0){
    		System.out.println("新增成功！");
    	}else{
    		System.out.println("新增失败！");
    	}
    	   } catch (SQLException e) {
    		   logger.error(e);
    	}finally{
    		//3.关闭链接
    		try {
    				stmt.close();
    			    conn.close();
    		} catch (SQLException e) {
    			logger.error(e);
    		}
    	}	
    }
	/**
	 * 查询所有用户信息
	 */
    private static void selectAllUsers() {
		//1.加载驱动
		   try {
			   getConnection();//调用父类连接的方法
		// 书写sql 现在dbms中运行一次
		String sql = "SELECT * FROM easybuy_user";
		// 执行sql
		stmt = conn.prepareStatement(sql);
		// 得到的结果集
		rs = stmt.executeQuery();
		// 处理结果集
		while(rs.next()){
			int id = rs.getInt("id");
			String loainName = rs.getString("loainName");
			String userName = rs.getString("userName");
			String password = rs.getString("password");
			int sex = rs.getInt("sex");
			String identityCode = rs.getString("identityCode");
			String email = rs.getString("email");
			String mobile = rs.getString("mobile");
			int type = rs.getInt("type");
			System.out.println("编号:"+id);
			System.out.println("登录名:"+loainName);
			System.out.println("真实姓名:"+userName);
			System.out.println("密码:"+password);
			System.out.println("性别:"+sex);
			System.out.println("身份证:"+identityCode);
			System.out.println("邮箱:"+email);
			System.out.println("手机号:"+mobile);
			System.out.println("用户类型:"+type);	
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
