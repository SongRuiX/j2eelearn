<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%@ page import="java.sql.*"%>
<%@ page import="l3.DBUtil"%>
<html>
  <head>
    <title>�ҵĹ��ﳵ</title>
  </head>
  <%
  
  	Connection conn = DBUtil.getConnection();
	//װ��MySQL5.5��JDBC����
	//Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
	//�������ݿ�����
	//String url ="jdbc:oracle:thin://localhost:1521/ORCL?user=scott&password=tiger&useUnicode=true&characterEncoding=gb2312";
	
	//String url ="jdbc:oracle:thin:@localhost:1521:ORCL";
	//Connection conn= DriverManager.getConnection(url);
	//����һ��Statement��������ִ��SQL���
	Statement stat = conn.createStatement();
	//ִ�в�ѯ���õ���ѯ���
	String sql = "select * from cart";
	ResultSet rs = stat.executeQuery(sql);	
  %>   
  <body>
    <h2>�ҵĹ��ﳵ</h2>
    <hr>
	<table border="1" width="600">
		<tr bgcolor="#dddddd">
			<td align="center" width="80">��Ʒ����</td> 
			<td align="center">��Ʒ����</td>
			<td align="center" width="100">��������</td>
			<td align="center" width="100">���</td>
			<td align="center" width="100">�༭</td>
		</tr>
		<%
			String id,name,price,num,count;
			//����ѯ������еļ�¼�����ҳ����
			while (rs.next()){
				//�ӵ�ǰ��¼�ж�ȡ���ֶε�ֵ
				id = rs.getString("ID").trim();
				name = rs.getString("SP_NAME").trim();
				price = rs.getString("SP_PRICE").trim();
				num = rs.getString("BUY_NUM").trim();
				count = rs.getString("COUNT").trim();
				
				out.println("<tr>");
				out.println("<td>"+ name +"</td>");
				out.println("<td>"+ price +"</td>");
				out.println("<td><input type=text value="+ num +" onChange=\"updateNum('"+id+"',this.value,'"+price+"')\"></td>");
				out.println("<td>"+ count +"</td>");
				out.println("<td><a href='buy.jsp?op=del&id="+id+"'>�˻���Ʒ��</a></td>");
				out.println("</tr>");
			}
		%>		
	</table>
	<br>
	<a href="index.jsp">��������</a>&nbsp;&nbsp;
	<a href="buy.jsp?op=clear">��չ��ﳵ</a>	        
  </body>
  <script language="javascript">
  	function updateNum(id,num,price){
  		var url = "buy.jsp?op=update&id="+id+"&num="+num+"&price="+price;
  		window.location = url;
  	}
  </script>  
</html>
