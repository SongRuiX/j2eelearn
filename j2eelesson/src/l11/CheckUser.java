package l11;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CheckUser extends HttpServlet {

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
		//这里假设已经注册过了四位用户
		String[] logined = {"admin","users","dywdyw","liubin"};
		//接收从客户端提交的loginName参数
		String loginName = request.getParameter("loginName");
		//创建一个存放响应内容的字符串
		String responseContext = "true";
		for (int i=0;i<logined.length;i++){
			//遍历已注册用户列表，如果发现提交的注册名已存在，则修改响应内容
			if (loginName.equals(logined[i]))responseContext = "false";
		}
		//将处理结果返回给客户端
		out.println(responseContext);
		out.flush();
		out.close();
	}

	public void init() throws ServletException {}

}
