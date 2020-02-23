package l12;
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

public class Check extends HttpServlet {

	public void destroy() {
		super.destroy();
	}

	public void doGet(HttpServletRequest request, 
					  HttpServletResponse response)
					  throws ServletException, IOException {
		doPost(request,response);
	}

	public void doPost(HttpServletRequest request, 
					   HttpServletResponse response)
			  		   throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		
		String sql = "SELECT * FROM USERDATA";
		String [] logined = new String[20];
		int a=0;			
		try {
			Connection conn = DBUtil.getConnection();
			Statement stmt = conn.createStatement();				
			ResultSet rs =   stmt.executeQuery(sql);
			while (rs.next()) {				
				String user  = rs.getString("username");
				logined [a]= user;
				a++;
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(logined[0]);
		System.out.println(logined[1]);
		System.out.println(logined[2]);

//		for (int i=0;i<logined.length;i++){
//			//������ע���û��б���������ύ��ע�����Ѵ��ڣ����޸���Ӧ����
//			System.out.print(logined[i]);
//		}
//		
				
		//���մӿͻ����ύ��loginName����
		String loginName = request.getParameter("loginName");
		//����һ�������Ӧ���ݵ��ַ���
		String responseContext = "true";
		for (int i=0;i<20;i++){
			//������ע���û��б���������ύ��ע�����Ѵ��ڣ����޸���Ӧ����
			if (loginName.equals(logined[i]))responseContext = "false";
		}
		//�����������ظ��ͻ���
		out.println(responseContext);
		out.flush();
		out.close();
	}

	public void init() throws ServletException {}

}
