package l4;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import l3.DBUtil;


public class RegisterServlet implements javax.servlet.Servlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws Exception  {
		String username = request.getParameter("username");// ȡ���û���
		String password = request.getParameter("password1");// ȡ������
		
		
		System.out.println("ȡ���û���������");
		RegisterSuccess db=new RegisterSuccess(username, password);		
			System.out.println("ע��ɹ������¼");
		response.sendRedirect("login.html");
		
	}
		
		
		
		
		public void destroy() {
		}
		public ServletConfig getServletConfig() {
			return null;
		}
		public String getServletInfo() {
			return null;
		}
		public void init(ServletConfig arg0) throws ServletException {
		}
	 
		public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
			HttpServletRequest rq = (HttpServletRequest) request;
			HttpServletResponse rs = (HttpServletResponse) response;
			try {
				doPost(rq, rs);
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
}