package l11;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetPhoto extends HttpServlet {

	public GetPhoto() {
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

		response.setContentType("text/html");
		response.setCharacterEncoding("gb2312");
		PrintWriter out = response.getWriter();
		String selected = request.getParameter("selected");
		//定义相册
		String[] photos = {"photo/photo1.jpg",
						   "photo/photo2.jpg",
						   "photo/photo3.jpg",
						   "photo/photo4.jpg",
						   "photo/photo5.jpg"};
		int index = Integer.parseInt(selected);
		//返回相片给客户端
		out.println(photos[index]);
		out.flush();
		out.close();
	}

	public void init() throws ServletException {}

}
