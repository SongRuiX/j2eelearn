package l3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBut 
{
	public final static String URL = "jdbc:oracle:thin:@localhost:1521:sr";
	public final static String DRIVER = "oracle.jdbc.driver.OracleDriver";
	public final static String USERNAME = "scott";
	public final static String PWD = "tiger";

	static 
	{
		try 
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("��ȡ�����ɹ�");
		} 
		catch (ClassNotFoundException e) 
		{
			System.out.println("��ȡ����ʧ��");
			e.printStackTrace();
		}
	}

	public Connection getConnection() throws SQLException 
	{
		Connection conn = null;
		// �Ȼ�����ݿ������
		conn = DriverManager.getConnection(URL, USERNAME, PWD);
		return conn;
	}

	// �����ͷ�������Դ�ķ���
	public void closeAll(ResultSet rst, Statement stmt, Connection conn) 
	{
		if (rst != null) 
		{
			try 
			{
				rst.close();
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
		}

		if (stmt != null) 
		{
			try 
			{
				stmt.close();
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
		}
		if (conn != null) 
		{
			try 
			{
				conn.close();
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
		}

	}
	public static void main(String[] args) 
    {
        Connection conn;
        Statement stmt;
        ResultSet rs;
        String url = "jdbc:oracle:thin:@localhost:1521:ORCL";
        String sql = "select * from STUDENT";
        try
        {

            conn = DriverManager.getConnection(url, "scott", "tiger");
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            while (rs.next())
            {
                String id = rs.getString("Sno");
                String name = rs.getString("Sname");
                String age = rs.getString("Sage");
                System.out.println("Sno:" + id + "\tSame:" + name + "\tSage:" + age);
            }
            if (rs != null)
            {
                rs.close();
                rs = null;
            }
            if (stmt != null)
            {
                stmt.close();
                stmt = null;
            }
            if (conn != null)
            {
                conn.close();
                conn = null;
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            System.out.println("���ݿ�����ʧ��");
        }
    }
	public boolean loginSuccess(String userName,String password){
		boolean returnValue = false;
		String sql = "SELECT * FROM userm";
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try{
		conn = getConnection();
		stmt = conn.createStatement();
		rs = stmt.executeQuery(sql);
		while(rs.next()){
		String userNameInDB = rs.getString("username");
		String passwordInDB = rs.getString("pasword");
		if(userNameInDB.equals(userName)&&
		passwordInDB.equals(password)){
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