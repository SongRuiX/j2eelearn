package l4;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class loginServlet implements javax.servlet.Servlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws Exception  {
		String username = request.getParameter("username");// ȡ���û���
		String password = request.getParameter("password");// ȡ������
		System.out.println("ȡ���û���������");
		 
		LoginSuccess db = new LoginSuccess(); // ������½����
		boolean canLogin = db.LoginSuccess(username, password);// ȡ���û���������
		if (canLogin) {// ���ݵ�½�������תҳ��
			System.out.println("�û�����������ȷ");
			response.sendRedirect("main.html");
		} else {
			response.sendRedirect("register.html");
			System.out.println("�û��������������ע��");
		}
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
