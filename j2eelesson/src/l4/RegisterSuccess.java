package l4;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import l3.DBUtil;

public class RegisterSuccess {
	public  RegisterSuccess(String username, String password) throws ClassNotFoundException  {
		
		String sql = "insert into USERDATA(USERNAME, PASSWORD)  values (?,?)";
		Connection conn = null;
		PreparedStatement pstmt  = null;
		ResultSet rs = null;
 
		try {
			conn = DBUtil.getConnection();
			pstmt  = conn.prepareStatement(sql);			
			pstmt .setString(1, username);
			pstmt .setString(2, password);
			pstmt .executeUpdate();		
		   
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
				} 
			catch (SQLException e) {		
									}
		}

	}
}
