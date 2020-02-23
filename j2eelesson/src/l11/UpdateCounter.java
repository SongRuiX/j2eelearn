package l11;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.*;

public class UpdateCounter extends HttpServlet {

	public UpdateCounter() {
		super();
	}

	public void destroy() {
		super.destroy(); 

	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/xml");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		Random rnd = new Random();//创建一个随机数发生器
		//创建返回客户端的统计数据XML文档
		out.println("<response>");
		//产生六个随机数做为实时统计数据
		for (int i=0;i<6;i++){
			out.println("<counter>"+rnd.nextInt(100)+"</counter>");
		}
		out.println("</response>");
		out.flush();
		out.close();
	}

	public void init() throws ServletException {}

}
