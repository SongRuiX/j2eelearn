package l4;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import l3.DBUtil;

public class LoginSuccess {
	public boolean LoginSuccess(String username, String password) throws Exception {
		boolean returnValue = false;
		String sql = "SELECT * FROM USERDATA";
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
 
		try {
			conn = DBUtil.getConnection();
			stmt = conn.createStatement();
			rs =   stmt.executeQuery(sql);
			while (rs.next()) {
				String userName = rs.getString("username");
				String passWord = rs.getString("password");
				if (userName.equals(username) && passWord.equals(password)) {
					//   如果用户名和密码都和数据库的一样，就返回true
					returnValue = true;
					break;
				}
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return returnValue;
 
	}

}
