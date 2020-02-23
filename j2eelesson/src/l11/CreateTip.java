package l11;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreateTip extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//虚拟一个商品列表
		String[][] shop ={{"商品一","90","photo/photo1.jpg"},
						  {"商品二","190","photo/photo2.jpg"},
						  {"商品三","290","photo/photo3.jpg"}	}; 
		
		//获得客户端提交的参数
		int index =Integer.parseInt(request.getParameter("index"));
		
		response.setContentType("text/xml");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		//以XML文档形式返回给客户端
		out.println("<shop>");
		out.println("<name>"+shop[index][0]+"</name>");
		out.println("<price>"+shop[index][1]+"</price>");
		out.println("<photo>"+shop[index][2]+"</photo>");
		out.println("</shop>");		

		out.flush();
		out.close();
	}

}
