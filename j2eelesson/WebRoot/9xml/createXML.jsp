<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%@ page import="org.dom4j.*"%>
<%@ page import="l9.CreateXML"%>

<jsp:useBean id="myCreate" class="l9.CreateXML"></jsp:useBean>

<html>
  <head>
    <title>����XML�ļ�</title>
  </head>
  
  <body>
    <h2>����XML�ļ�</h2>
    <hr>
	<%
		//����һ��myfirst.xml�ļ��ľ�������·��
		String fileName1 = "D:/javaworkplace/j2eelesson/WebRoot/9xml/users.xml";		
		//����������myfirst.xml�ļ�,ȡ�ø�XML�ĵ�����
		Document doc = myCreate.create(fileName1);

		//�ݴ��XML�ĵ�������
		String reslut = doc.asXML();
		
		
					
	%> 
	<textarea rows="10" cols="60"><%=reslut%></textarea> 	   
  </body>
</html>
