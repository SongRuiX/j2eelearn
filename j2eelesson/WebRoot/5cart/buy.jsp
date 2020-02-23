<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%@ page import="java.sql.*"%>
<%@ page import="l3.DBUtil"%>
<body>


<%
	//װ��MySQL5.5��JDBC����
	//Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
	//�������ݿ�����
	//String url ="jdbc:mysql://localhost:3306/eshop?user=root&password=root&useUnicode=true&characterEncoding=gb2312";
	//Connection //conn= DriverManager.getConnection(url);
	Connection conn = DBUtil.getConnection();
	PreparedStatement pstat = null;
	ResultSet rs = null;
	
	String sql = null;
	String op = request.getParameter("op");
	
	//����Ʒ���빺�ﳵ
	if (op.equals("add")){
		//����Ʒ����ȡ����ѡ����Ʒ������
		String bm = request.getParameter("bm");
		String name=null,price=null;
		int num=0;
		sql = "select * from shop where SP_NO=?";
		pstat = conn.prepareStatement(sql);
		pstat.setString(1,bm);
		rs = pstat.executeQuery();
		if (rs.next()){
			name = rs.getString("SP_NAME").trim();
			price = rs.getString("SP_PRICE").trim();
			num=rs.getInt("SP_NUM");
		}
		rs.close();
		pstat.close();
		
		//����ѡ����Ʒ���뵽���ﳵ��
		sql = "insert into cart(ID,SP_NAME,SP_PRICE,BUY_NUM,COUNT) values(?,?,?,?,?)";
		pstat = conn.prepareStatement(sql);
		pstat.setString(1,bm);
		pstat.setString(2,name);
		pstat.setString(3,price);			
		
		if(num>=1)pstat.setInt(4,1);
		else	pstat.setInt(4,0);			
		
		pstat.setString(5,price);
		pstat.executeUpdate();
		pstat.close();
		
		//�ر����ݿ�����6
		conn.close();
		
		//�ض��򵽹��ﳵҳ��
		response.sendRedirect("cart.jsp");
	}
	
	//������Ʒ������
	if (op.equals("update")){
		int id = Integer.parseInt(request.getParameter("id"));
		int num=0,n=0,a=0;
		sql = "select * from shop where SP_NO=?";
		pstat = conn.prepareStatement(sql);
		pstat.setInt(1,id);
		rs = pstat.executeQuery();
		if (rs.next()){			
			num=rs.getInt("SP_NUM");
		}
		a = Integer.parseInt(request.getParameter("num"));
		if(a<=num) n = Integer.parseInt(request.getParameter("num"));
		else {n=num;	
		
		%>
		
		<script type="text/javascript">
			alert(1);
			alert("��治�㣬Ĭ��ѡ�������+<%=n%>+");
		</script>
		<%
		
		System.out.println("��治�㣬Ĭ��ѡ�������"+n);	  						 
		}

		
		double price=Double.parseDouble(request.getParameter("price"));
		sql = "update cart set BUY_NUM = ?, COUNT = ? where ID=?";
		pstat = conn.prepareStatement(sql);
		pstat.setInt(1,n);
		pstat.setString(2,new Double(price*n).toString());
		pstat.setInt(3,id);
		pstat.executeUpdate();
		pstat.close();
		
		//�ر����ݿ�����
		conn.close();
		
		//�ض��򵽹��ﳵҳ��
		response.sendRedirect("cart.jsp");
	}	
	
	//����Ʒ�˻ص���Ʒ��(����Ʒ�ӹ��ﳵ��ɾ��)
	if (op.equals("del")){
		int id = Integer.parseInt(request.getParameter("id"));
		sql = "delete from cart where ID=?";
		pstat = conn.prepareStatement(sql);
		pstat.setInt(1,id);
		pstat.executeUpdate();
		pstat.close();
		
		//�ر����ݿ�����
		conn.close();
		
		//�ض��򵽹��ﳵҳ��
		response.sendRedirect("cart.jsp");
	}
	
	//��չ��ﳵ
	if (op.equals("clear")){
		sql = "delete from cart";
		pstat = conn.prepareStatement(sql);
		pstat.executeUpdate();
		pstat.close();
		
		//�ر����ݿ�����
		conn.close();
		
		//�ض��򵽹��ﳵҳ��
		response.sendRedirect("cart.jsp");
	}
%> 
</body>
