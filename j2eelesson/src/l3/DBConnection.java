package l3;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
	private static final String driver = "oracle.jdbc.driver.OracleDriver";// ���ݿ�����
	private static final String url = "jdbc:oracle:thin:@localhost:1521:ORCL";   //"login"��ָ������ݿ����� 3306�Ƕ˿�
	private static final String username = "scott";         //  ���ݿ��û���                                 
	private static final String password = "tiger";         //  ���ݿ�����
	private static Connection conn = null;                 //  ���Ӷ���
	// ��̬�����������ݿ�����

	static {
		try {
			Class.forName(driver);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
 
	// ����ģʽ�������ݿ����Ӷ���
	public static Connection getConnection() throws Exception {
		if (conn == null) {
			conn = DriverManager.getConnection(url, username, password);
			return conn;
		}
		return conn;
	}
 
	public static void main(String[] args) {
		try {
			Connection conn = DBConnection.getConnection();
			if (conn != null) {
				System.out.println("���ݿ���������");
			} else {
				System.out.println("���ݿ������쳣");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
