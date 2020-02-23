<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%@ page import="org.dom4j.*"%>
<%@ page import="l9.CreateXML"%>

<jsp:useBean id="myCreate" class="l9.CreateXML"></jsp:useBean>

<html>
  <head>
    <title>创建XML文件</title>
  </head>
  
  <body>
    <h2>创建XML文件</h2>
    <hr>
	<%
		//定义一个myfirst.xml文件的绝对物理路径
		String fileName1 = "D:/javaworkplace/j2eelesson/WebRoot/9xml/users.xml";		
		//产生并保存myfirst.xml文件,取得该XML文档对象
		Document doc = myCreate.create(fileName1);

		//暂存该XML文档的内容
		String reslut = doc.asXML();
		
		
					
	%> 
	<textarea rows="10" cols="60"><%=reslut%></textarea> 	   
  </body>
</html>
