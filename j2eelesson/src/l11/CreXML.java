package l11;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreXML extends HttpServlet {

	public CreXML() {
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
		String selected = request.getParameter("selected");
		PrintWriter out = response.getWriter();
		out.println("<response>");
		//下面分别为两个省份创建地市
		if (selected.equals("1")){//如果选择的是“湖南省”
			out.println("<city>");
			out.println("<cityname>长沙</cityname>");
			out.println("<cityvalue>1</cityvalue>");
			out.println("</city>");
			out.println("<city>");
			out.println("<cityname>娄底</cityname>");
			out.println("<cityvalue>2</cityvalue>");
			out.println("</city>");
			out.println("<city>");
			out.println("<cityname>常德</cityname>");
			out.println("<cityvalue>3</cityvalue>");
			out.println("</city>");
		}else{//如果选择的是“广东省”
			out.println("<city>");
			out.println("<cityname>广州</cityname>");
			out.println("<cityvalue>1</cityvalue>");
			out.println("</city>");
			out.println("<city>");
			out.println("<cityname>深圳</cityname>");
			out.println("<cityvalue>2</cityvalue>");
			out.println("</city>");
			out.println("<city>");
			out.println("<cityname>佛山</cityname>");
			out.println("<cityvalue>3</cityvalue>");
			out.println("</city>");
		}
		out.println("</response>");
		out.flush();
		out.close();
	}

	public void init() throws ServletException {}

}
