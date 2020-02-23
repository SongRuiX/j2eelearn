package l11;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.net.*;
import java.io.*;

public class YahooSearch extends HttpServlet {

	public YahooSearch() {
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

		//定义Yahoo的搜索Web Service的访问URL的基本部分
		String baseUrl = "http://api.search.yahoo.com/WebSearchService/V1/webSearch?appid=YahooDemo&ei=UTF-8&type=all";
		//获得客户的查询关键字
		String query = request.getParameter("query");
		//对query进编码转换
		query = new String(query.getBytes("ISO8859-1"),"gb2312");
		//对query进URL编码
		query = URLEncoder.encode(query,"UTF-8");
		//构造出查询关键字参数
		query = "&query="+query;
		//获得客户的记录数
		String results = request.getParameter("results");
		//构造出记录数参数
		results = "&results="+results;	
		//构造出完整的URL
		String url = baseUrl+query+results;
		//建立一个HttpURLConnection对象进行搜索
		HttpURLConnection con = (HttpURLConnection)new URL(url).openConnection();
		//设置HttpURLConnection对象的访问方法
		con.setRequestMethod("GET");
		
		//将查询的结果以XML文档返回给客户端
		response.setContentType("text/xml");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		//建立一个BufferedReader对象读取查询结果
		BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String line = null;
		//一行一行地读取查询结果
		while ((line = reader.readLine())!=null){
			//对读取的数据进行编码转换
			line = new String(line.getBytes(),"UTF-8");
			out.println(line);
		}
		out.flush();
		out.close();
	}

	public void init() throws ServletException {}

}
