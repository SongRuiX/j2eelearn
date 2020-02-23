<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%@ page import="l8.SendTextMail"%>

<jsp:useBean id="mySend" class="l8.SendTextMail"></jsp:useBean>
<jsp:setProperty name="mySend" property="*"/>

<%
	//注意这里我们通过JavaBean的自省机制完成了对其属性的赋值
		
	boolean status = mySend.send();
	if (status){
		out.println("恭喜您，邮件发送成功！");
	}else{
		out.println("对不起，邮件发送失败！");
	}
%>
