
import java.sql.SQLException;
import org.apache.log4j.Logger;
import util.BaseDao;
/**
 * �Ż�
 * @author Lenovo
 *
 */
public class jdbcTest3 extends BaseDao{
  static Logger logger=Logger.getLogger(jdbcTest3.class);
    public static void main(String[] args) throws ClassNotFoundException {
    	boolean flag = loainUser();
    	//��ȡ�û�����
    	if(flag){
    	System.out.print("1.��ѯ�����û���Ϣ\t");
    	System.out.print("2.�����û���Ϣ\t");
    	System.out.print("3.ɾ���û���Ϣ\t");
    	System.out.print("4.�޸��û���Ϣ\t\n");
    	System.out.println("����������ѡ��:");
    	int choose = input.nextInt();
		switch (choose) {
		case 1:
			selectAllUsers();// ��ѯ�����û���Ϣ
			break;
		case 2:
			insertUser();// �����û���Ϣ
			break;
		case 3:
			deleteUser();// ɾ���û���Ϣ
			break;
		case 4:
			updateUser();// �޸��û���Ϣ
			break;
		}
    	}else{
    		System.out.println("��½ʧ�ܣ�");
    	}
}
    /**
     * �û���¼�ķ���
     * @return
     */
    private static boolean loainUser() {
    	// ��ȡ�û�������
    	System.out.println("���������¼���ƣ�");
    	String loainName = input.next();
    	System.out.println("���������¼���룺");
    	String password = input.next();
    	boolean flag = false;
    	//1.��������
    	   try {
    		   getConnection(); // ���ø������ӵķ���
    	// ��дsql ����dbms������һ��
    	String sql = "SELECT loainName,password FROM easybuy_user where loainName=? and password=?";
    	// ִ��sql
    	stmt = conn.prepareStatement(sql);
    	// ��ռλ����ֵ
    	stmt.setString(1, loainName);
    	stmt.setString(2, password);
    	// �õ��Ľ����
    	rs = stmt.executeQuery();
    	// ��������
    	if(rs.next()){ // ֤�����û�
    		flag = true;
    	}
    	   } catch (SQLException e) {
    		   logger.error(e);
    	}finally{
    		//3.�ر�����
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
     * �޸��û���Ϣ
     */
    private static void updateUser() {
    	// ��ȡ�û�������
    	System.out.println("����������ʵ������");
    	String userName = input.next();
    	System.out.println("�����������ǳƣ�");
    	String loainName = input.next();
    	System.out.println("�������������룺");
    	String password = input.next();
    	//1.��������
    	   try {
    		   getConnection();//���ø������ӵķ���
    	// ��дsql ����dbms������һ��
    	String sql = "UPDATE  easybuy_user SET loainName=? `password`=? WHERE userName=?";
    	// ִ��sql
    	stmt = conn.prepareStatement(sql);
    	// ռλ����ֵ
    	stmt.setString(1, loainName);
    	stmt.setString(2, password);
    	stmt.setString(3, userName);
    	// �õ��Ľ����
    	int rowNum = stmt.executeUpdate();
    	if(rowNum > 0){
    		System.out.println("�޸ĳɹ���");
    	}else{
    		System.out.println("�޸�ʧ�ܣ�");
    	}
    	   } catch (SQLException e) {
    		   logger.error(e);
    	}finally{
    		//3.�ر�����
    		try {
    				stmt.close();
    			    conn.close();
    		} catch (SQLException e) {
    			logger.error(e);
    		}
    	}
	}    
	/**
     * ɾ���û���Ϣ
     */
    private static void deleteUser() {
    	// ��ȡ�û�������
    	System.out.println("����������ʵ������");
    	String userName = input.next();
    	//1.��������
    	   try {
    		   getConnection();//���ø������ӵķ���
    	// ��дsql ����dbms������һ��
    	String sql = "DELETE FROM easybuy_user WHERE userName=?";
    	// ִ��sql
    	stmt = conn.prepareStatement(sql);
    	// ռλ����ֵ
    	stmt.setString(1, userName);
    	// �õ��Ľ����
    	int rowNum = stmt.executeUpdate();
    	if(rowNum > 0){
    		System.out.println("ɾ���ɹ���");
    	}else{
    		System.out.println("ɾ��ʧ�ܣ�");
    	}
    	   } catch (SQLException e) {
    		   logger.error(e);
    	}finally{
    		//3.�ر�����
    		try {
    				stmt.close();
    			    conn.close();
    		} catch (SQLException e) {
    			logger.error(e);
    		}
    	}		
	}
	/**
     * �����û���Ϣ
     */
    private static void insertUser() {
    	// ��ȡ�û�������
    	System.out.println("���������¼���ƣ�");
    	String loainName = input.next();
    	System.out.println("���������¼���룺");
    	String password = input.next();
    	System.out.println("����������ʵ������");
    	String userName = input.next();
    	System.out.println("���������Ա�(1/��   0/Ů)");
    	int sex = input.nextInt();
    	//1.��������
    	   try {
    		   getConnection();//���ø������ӵķ���
    	// ��дsql ����dbms������һ��
    	String sql = "INSERT  INTO easybuy_user(loainName,userName,`password`,sex)"+ " VALUES(?,?,?,?)";
    	// ִ��sql
    	stmt = conn.prepareStatement(sql);
    	// ռλ�� ��ֵ
    	stmt.setString(1, loainName);
    	stmt.setString(2, userName);
    	stmt.setString(3, password);
    	stmt.setInt(4, sex);
    	// �õ��Ľ����
    	int rowNum = stmt.executeUpdate();
    	if(rowNum > 0){
    		System.out.println("�����ɹ���");
    	}else{
    		System.out.println("����ʧ�ܣ�");
    	}
    	   } catch (SQLException e) {
    		   logger.error(e);
    	}finally{
    		//3.�ر�����
    		try {
    				stmt.close();
    			    conn.close();
    		} catch (SQLException e) {
    			logger.error(e);
    		}
    	}	
    }
	/**
	 * ��ѯ�����û���Ϣ
	 */
    private static void selectAllUsers() {
		//1.��������
		   try {
			   getConnection();//���ø������ӵķ���
		// ��дsql ����dbms������һ��
		String sql = "SELECT * FROM easybuy_user";
		// ִ��sql
		stmt = conn.prepareStatement(sql);
		// �õ��Ľ����
		rs = stmt.executeQuery();
		// ��������
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
			System.out.println("���:"+id);
			System.out.println("��¼��:"+loainName);
			System.out.println("��ʵ����:"+userName);
			System.out.println("����:"+password);
			System.out.println("�Ա�:"+sex);
			System.out.println("���֤:"+identityCode);
			System.out.println("����:"+email);
			System.out.println("�ֻ���:"+mobile);
			System.out.println("�û�����:"+type);	
		}
		   } catch (SQLException e) {
			   logger.error(e);
		}finally{
			//3.�ر�����
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
