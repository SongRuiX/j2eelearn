<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%@ page import="l8.SendTextMail"%>

<jsp:useBean id="mySend" class="l8.SendTextMail"></jsp:useBean>
<jsp:setProperty name="mySend" property="*"/>

<%
	//ע����������ͨ��JavaBean����ʡ��������˶������Եĸ�ֵ
		
	boolean status = mySend.send();
	if (status){
		out.println("��ϲ�����ʼ����ͳɹ���");
	}else{
		out.println("�Բ����ʼ�����ʧ�ܣ�");
	}
%>
