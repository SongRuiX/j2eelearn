<%@ page contentType="text/html; charset=gb2312" %>
<%@ page import="java.sql.*"%>
<%@ page import="l3.DBUtil"%>
<html><head>
<title>欢迎光临网上书店</title>
  <%  
  	Connection conn = DBUtil.getConnection();
  	Statement stat = conn.createStatement();
	//执行查询并得到查询结果
	String sql = "select * from SHOPPING";
	ResultSet rs = stat.executeQuery(sql);	
  %>   
</head>
<body><center>
  <h1 align="center">网上书店</h1>
  <table width="706" border="1">
	<tr align="center" valign="middle" bgcolor="#CCCCCC">
        <th width="180" scope="col">书名</th>
        <th width="131" scope="col">作者</th>
        <th width="122" scope="col">出版社</th>
        <th width="85" scope="col">价格</th>
        <th width="65" scope="col">数量</th>
        <th width="83" scope="col">&nbsp;</th>
    </tr>  
        <%
			String Name,Author,Publisher;
			int Price=0;
			//将查询结果集中的记录输出到页面上
			while (rs.next()){
				//从当前记录中读取各字段的值				
				Name = rs.getString("SP_NAME").trim();
				Author = rs.getString("SP_AUTHOR").trim();
				Publisher = rs.getString("SP_PUBLISHER").trim();
				Price = rs.getInt("SP_PRICE");
				
				out.println("<form name='shoppingForm' method='post' action='ShoppingServlet'>");
				out.println("<tr align='center' valign='middle'>");
				out.println("<td>"+ Name +"</td>");
				out.println("<td>"+ Author +"</td>");
				out.println("<td>"+ Publisher +"</td>");
				out.println("<td>"+ Price +"</td>");
				out.println("<td><input type='textfield' name='quantity' value='1' size='3'></td>");
				out.println("<td><input type='submit' name='Submit' value='购买'></td>");								
				out.println("</tr>");								
				out.println("<input type='hidden' name='name' value="+Name+">");
				out.println("<input type='hidden' name='author' value="+Author+">");
				out.println("<input type='hidden' name='publisher' value="+Publisher+">");
				out.println("<input type='hidden' name='price' value="+Price+">");
				out.println("<input type='hidden' name='action' value='ADD'>");				
				out.println("</form>");							
			}
		%>		                                              
		</table>
<form name="checkoutForm" method="post" action="ShoppingServlet">
    <input type="submit" name="submit" value="结帐">
    <input type="hidden" name="action" value="CHECKOUT">
</form>
<center></body></html>
        