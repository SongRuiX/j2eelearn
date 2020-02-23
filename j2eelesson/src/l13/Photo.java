package l13;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import l3.DBUtil;

public class Photo extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("text/html");
		response.setCharacterEncoding("gb2312");
		PrintWriter out = response.getWriter();
		String selected = request.getParameter("selected");
		//定义相册
		
		String sql = "SELECT PATH FROM UPLOAD";
		
		String[] photos = new String[3];
		int a=0;			
		try {
			Connection conn = DBUtil.getConnection();
			Statement stmt = conn.createStatement();				
			ResultSet rs =   stmt.executeQuery(sql);
			
			while (rs.next()) {	
				String s = rs.getString("PATH");				
				photos [a]=  s;		
				a++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int index = Integer.parseInt(selected);
		//返回相片给客户端
		out.println(photos[index]);
		out.flush();
		out.close();
	}
}
