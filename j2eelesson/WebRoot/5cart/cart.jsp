<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%@ page import="java.sql.*"%>
<%@ page import="l3.DBUtil"%>
<html>
  <head>
    <title>我的购物车</title>
  </head>
  <%
  
  	Connection conn = DBUtil.getConnection();
	//装载MySQL5.5的JDBC驱动
	//Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
	//建立数据库连接
	//String url ="jdbc:oracle:thin://localhost:1521/ORCL?user=scott&password=tiger&useUnicode=true&characterEncoding=gb2312";
	
	//String url ="jdbc:oracle:thin:@localhost:1521:ORCL";
	//Connection conn= DriverManager.getConnection(url);
	//建立一个Statement对象，用于执行SQL语句
	Statement stat = conn.createStatement();
	//执行查询并得到查询结果
	String sql = "select * from cart";
	ResultSet rs = stat.executeQuery(sql);	
  %>   
  <body>
    <h2>我的购物车</h2>
    <hr>
	<table border="1" width="600">
		<tr bgcolor="#dddddd">
			<td align="center" width="80">商品名称</td> 
			<td align="center">商品单价</td>
			<td align="center" width="100">购买数量</td>
			<td align="center" width="100">金额</td>
			<td align="center" width="100">编辑</td>
		</tr>
		<%
			String id,name,price,num,count;
			//将查询结果集中的记录输出到页面上
			while (rs.next()){
				//从当前记录中读取各字段的值
				id = rs.getString("ID").trim();
				name = rs.getString("SP_NAME").trim();
				price = rs.getString("SP_PRICE").trim();
				num = rs.getString("BUY_NUM").trim();
				count = rs.getString("COUNT").trim();
				
				out.println("<tr>");
				out.println("<td>"+ name +"</td>");
				out.println("<td>"+ price +"</td>");
				out.println("<td><input type=text value="+ num +" onChange=\"updateNum('"+id+"',this.value,'"+price+"')\"></td>");
				out.println("<td>"+ count +"</td>");
				out.println("<td><a href='buy.jsp?op=del&id="+id+"'>退回商品架</a></td>");
				out.println("</tr>");
			}
		%>		
	</table>
	<br>
	<a href="index.jsp">继续购物</a>&nbsp;&nbsp;
	<a href="buy.jsp?op=clear">清空购物车</a>	        
  </body>
  <script language="javascript">
  	function updateNum(id,num,price){
  		var url = "buy.jsp?op=update&id="+id+"&num="+num+"&price="+price;
  		window.location = url;
  	}
  </script>  
</html>
