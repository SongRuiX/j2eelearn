<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%@ page import="l8.GetMail"%>

<jsp:useBean id="receiver" class="l8.GetMail" scope="session"></jsp:useBean>
<jsp:setProperty name="receiver" property="*"/>

<html>
  <head>
    <title>我的收件箱</title>
  </head>
  
  <body>
    <h2>我的收件箱</h2>
    <hr>
    <table border=1 width=700>
	    <tr height=30 bgcolor=#dddddd>
	    	<td align=center>邮件标题</td>
	    	<td width=150 align=center>发件人</td>
	    	<td width=180 align=center>发件时间</td>
	    	<td width=80 align=center>邮件大小</td>
	    	<td width=50 align=center>附件</td>
	    </tr>
		<%
			//取得邮件列表信息
			Iterator it = receiver.getMailInfo(receiver.getAllMail()).iterator();
			Map map = null;
			//将邮件信息列表显示
			while (it.hasNext()){
				map = (Map)it.next();
				out.println("<tr height=30 valign=middle align=center>");	
				out.println("<td><a href='readMail.jsp?id="+map.get("ID")+"' target='_blank'>"+map.get("subject")+"</a></td>");
				out.println("<td>"+map.get("sender")+"</td>");	
				out.println("<td>"+map.get("senddate")+"</td>");	
				out.println("<td>"+map.get("size")+"</td>");	
				out.println("<td>"+map.get("hasAttach")+"</td>");
				out.println("</tr>");					
			}
		%>
	</table>    
  </body>
</html>
