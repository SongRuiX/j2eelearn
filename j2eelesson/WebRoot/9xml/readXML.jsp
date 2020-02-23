<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%@ page import="l9.ParseXML"%>

<jsp:useBean id="myParse" class="l9.ParseXML"></jsp:useBean>

<html>
  <head>
    <title>读取并解析XML文件</title>
  </head>
  
  <body>
    <h2>读取并解析XML文件</h2>
    <hr>
	<%
		//取得test.xml文件的绝对物理路径
		String fileName = "D:/javaworkplace/j2eelesson/WebRoot/9xml/users.xml";
		//读取XML文件，生成内存中的XML文档
		myParse.read(fileName);
		//递归遍历内存中的XML文档
		myParse.treeWalk();
		//输出解析结果
		out.println(myParse.getResult());
	%>     
  </body>
</html>
