<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%@ page import="java.sql.*"%>
<%@ page import="l3.DBUtil"%>
<body>


<%
	//装载MySQL5.5的JDBC驱动
	//Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
	//建立数据库连接
	//String url ="jdbc:mysql://localhost:3306/eshop?user=root&password=root&useUnicode=true&characterEncoding=gb2312";
	//Connection //conn= DriverManager.getConnection(url);
	Connection conn = DBUtil.getConnection();
	PreparedStatement pstat = null;
	ResultSet rs = null;
	
	String sql = null;
	String op = request.getParameter("op");
	
	//将商品放入购物车
	if (op.equals("add")){
		//从商品库中取出所选购商品的数据
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
		
		//将所选购商品加入到购物车中
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
		
		//关闭数据库连接6
		conn.close();
		
		//重定向到购物车页面
		response.sendRedirect("cart.jsp");
	}
	
	//更改商品的数量
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
			alert("库存不足，默认选择最大库存+<%=n%>+");
		</script>
		<%
		
		System.out.println("库存不足，默认选择最大库存"+n);	  						 
		}

		
		double price=Double.parseDouble(request.getParameter("price"));
		sql = "update cart set BUY_NUM = ?, COUNT = ? where ID=?";
		pstat = conn.prepareStatement(sql);
		pstat.setInt(1,n);
		pstat.setString(2,new Double(price*n).toString());
		pstat.setInt(3,id);
		pstat.executeUpdate();
		pstat.close();
		
		//关闭数据库连接
		conn.close();
		
		//重定向到购物车页面
		response.sendRedirect("cart.jsp");
	}	
	
	//将商品退回到商品架(将商品从购物车中删除)
	if (op.equals("del")){
		int id = Integer.parseInt(request.getParameter("id"));
		sql = "delete from cart where ID=?";
		pstat = conn.prepareStatement(sql);
		pstat.setInt(1,id);
		pstat.executeUpdate();
		pstat.close();
		
		//关闭数据库连接
		conn.close();
		
		//重定向到购物车页面
		response.sendRedirect("cart.jsp");
	}
	
	//清空购物车
	if (op.equals("clear")){
		sql = "delete from cart";
		pstat = conn.prepareStatement(sql);
		pstat.executeUpdate();
		pstat.close();
		
		//关闭数据库连接
		conn.close();
		
		//重定向到购物车页面
		response.sendRedirect("cart.jsp");
	}
%> 
</body>
