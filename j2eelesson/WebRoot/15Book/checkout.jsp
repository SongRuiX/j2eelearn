<%@ page contentType="text/html; charset=gb2312"%>
<%@page import="java.util.Vector"%>
<%@page import="l15.BookBean"%>
<html>
<head><title>结帐信息</title></head>
<body>
<h1>结帐信息</h1>
<table>
<tr align="center" valign="middle" bgcolor="#CCCCCC">
    <th width="180" scope="col">书名</th>
    <th width="131" scope="col">作者</th>
    <th width="122" scope="col">出版社</th>
    <th width="85" scope="col">价格</th>
    <th width="65" scope="col">数量</th>
</tr>
<% Vector buyList= (Vector)session.getAttribute("shoppingcart");
  for(int i=0;i<buyList.size();i++){
    BookBean aBook=(BookBean)buyList.elementAt(i);
%>
  <tr>
    <th width="180" scope="col"><%= aBook.getName()%></th>
    <th width="131" scope="col"><%= aBook.getAuthor()%></th>
    <th width="122" scope="col"><%= aBook.getPublisher()%></th>
    <th width="85" scope="col"><%= aBook.getPrice()%></th>
    <th width="65" scope="col"><%= aBook.getQuantity()%></th>
  </tr>
<%}%>
</table>
<h2>总金额为：
<%= session.getAttribute("amount")%></h2>
<form name="form1" method="post" action="shopping.jsp">
  <input type="submit" name="Submit2" value="继续购物">
</form>
<p>&nbsp;</p>
</body>
</html>
