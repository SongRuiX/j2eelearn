<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%@ page import="java.sql.*"%>
<%@ page import="l3.DBUtil"%>

<html>
  <head>
    <title>���߹���</title>
  </head>
  <%
	//װ��MySQL5.5��JDBC����
	//Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
	//�������ݿ�����
	//String url ="jdbc:mysql://localhost:3306/eshop?user=root&password=root&useUnicode=true&characterEncoding=gb2312";
	//Connection conn= DriverManager.getConnection(url);
	//����һ��Statement��������ִ��SQL���
	Connection conn = DBUtil.getConnection();
	Statement stat = conn.createStatement();
	//ִ�в�ѯ���õ���ѯ���
	String sql = "select * from shop";
	ResultSet rs = stat.executeQuery(sql);	
  %>   
  <body>
    <h2>���߹���</h2>
    <hr> 
	<table border="1" width="600">
		<tr bgcolor="#dddddd">
			<td align="center" width="80">��Ʒ��ͼ</td> 
			<td align="center">��ƷժҪ</td>
			<td align="center" width="100">���߹���</td>
		</tr>
		<%
			String bm,name,num,price,info,img;
			//����ѯ������еļ�¼�����ҳ����
			while (rs.next()){
				//�ӵ�ǰ��¼�ж�ȡ���ֶε�ֵ
				img = rs.getString("SP_PIC").trim();
				bm = rs.getString("SP_NO").trim();
				name = rs.getString("SP_NAME").trim();
				num = rs.getString("SP_NUM").trim();
				price = rs.getString("SP_PRICE").trim();
				info = rs.getString("SP_INFO").trim();
				
				out.println("<tr>");
				out.println("<td><img src='"+ img +"'  border=0 height=60 width=60 /></td>");
				out.println("<td>");
				out.println("��Ʒ��ţ�"+ bm +"<br>");
				out.println("��Ʒ���ƣ�"+ name +"<br>");
				out.println("��Ʒ������"+ num +"<br>");
				out.println("��Ʒ�۸�"+ price +"Ԫ<br>");
				out.println("��Ʒ��飺"+ info +"<br>");
				out.println("</td>");
				out.println("<td><a href='buy.jsp?op=add&bm="+bm+"'>���빺�ﳵ</a></td>");
				out.println("</tr>");
			}
		%>		
	</table>
	<br>
	<a href="cart.jsp">�鿴���ﳵ</a>&nbsp;&nbsp;
	<a href="buy.jsp?op=clear">��չ��ﳵ</a>	       
  </body>
</html>
