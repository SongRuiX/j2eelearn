<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%@ page import="l9.ParseXML"%>

<jsp:useBean id="myParse" class="l9.ParseXML"></jsp:useBean>

<html>
  <head>
    <title>��ȡ������XML�ļ�</title>
  </head>
  
  <body>
    <h2>��ȡ������XML�ļ�</h2>
    <hr>
	<%
		//ȡ��test.xml�ļ��ľ�������·��
		String fileName = "D:/javaworkplace/j2eelesson/WebRoot/9xml/users.xml";
		//��ȡXML�ļ��������ڴ��е�XML�ĵ�
		myParse.read(fileName);
		//�ݹ�����ڴ��е�XML�ĵ�
		myParse.treeWalk();
		//����������
		out.println(myParse.getResult());
	%>     
  </body>
</html>
