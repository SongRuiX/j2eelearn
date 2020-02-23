package l3;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
	private static final String driver = "oracle.jdbc.driver.OracleDriver";// 数据库驱动
	private static final String url = "jdbc:oracle:thin:@localhost:1521:ORCL";   //"login"是指你的数据库名称 3306是端口
	private static final String username = "scott";         //  数据库用户名                                 
	private static final String password = "tiger";         //  数据库密码
	private static Connection conn = null;                 //  连接对象
	// 静态代码块加载数据库驱动

	static {
		try {
			Class.forName(driver);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
 
	// 单例模式返回数据库连接对象
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
				System.out.println("数据库连接正常");
			} else {
				System.out.println("数据库连接异常");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
