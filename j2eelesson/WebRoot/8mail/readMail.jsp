<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%@ page import="l8.GetMail"%>
<%
	//从session中取出receiver
	GetMail receiver = (GetMail)session.getAttribute("receiver");
%>
<html>
  <head>
    <title>阅读邮件</title>
  </head>
  
  <body>
    <h2>阅读邮件</h2>
    <hr>
    <table border=1 width=700 cellpadding=4>
		<%
			//取得请求参数
			String id = request.getParameter("id");
			//取得当前目录的物理路径
			String basePath = request.getRealPath(".");
			//取得指定的邮件内容
			Map result = receiver.readMail(basePath,id);
		%>    
	    <tr>
	    	<td width=100 align=right bgcolor=#dddddd>邮件标题</td>
	    	<td><%=result.get("subject")%></td>
	    </tr>
	    <tr>
	    	<td width=100 align=right bgcolor=#dddddd>发 件 人</td>
	    	<td><%=result.get("sender")%></td>
	    </tr>
	    <%
	    	String attach =result.get("attach").toString();
	    	//有附件时才显示下面的一行
	    	if (attach.trim().length()>0){
	    %>
	    <tr>
	    	<td width=100 align=right bgcolor=#dddddd>附　　件</td>
	    	<td><a href="<%=result.get("attach")%>"><%=result.get("attach")%></a></td>
	    </tr>
	    <%}%>
	    <tr>
	    	<td width=100 valign=top align=right bgcolor=#dddddd>内　　容</td>
	    	<td><BLOCKQUOTE><%=result.get("content")%></BLOCKQUOTE></td>
	    </tr>
	</table>    
  </body>
</html>
