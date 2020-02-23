<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%@ page import="l8.SendAttachMail"%>
<%@ page import="com.jspsmart.upload.*"%>
<jsp:useBean id="mySend" class="l8.SendAttachMail"></jsp:useBean>
<%
	// 新建一个SmartUpload对象,此项是必须的
	SmartUpload myupload = new SmartUpload();		
	// 初始化,此项是必须的
	myupload.initialize(pageContext);		
	// 限制每个上传文件的最大长度
	myupload.setMaxFileSize(10*1024*1024);
	// 设定禁止上传的文件（通过扩展名限制）
	myupload.setDeniedFilesList("exe,bat,jsp,htm,html");		
	try{			
		// 上传文件,此项是必须的
		myupload.upload();			
		// 统计上传文件的总数
		int count = myupload.getFiles().getCount();			
		// 取得Request对象
		Request myRequest = myupload.getRequest();		
		//　取得一个上传文件
		File file = myupload.getFiles().getFile(0);				
		// 将文件另存,以WEB应用的根目录作为上传文件的根目录
		if (file.getFileName()!=null && file.getFileName().trim().length()>1){
			file.saveAs("/upload/" + file.getFileName(),myupload.SAVE_VIRTUAL);
			//设置附件的完整存放路径
			String realpath = pageContext.getServletContext().getRealPath("/");
			mySend.setFilename(realpath+"upload/" + file.getFileName());	
		}
		//设置邮件发送的其它属性
		mySend.setSMTPHost(myRequest.getParameter("SMTPHost"));
		mySend.setUser(myRequest.getParameter("user"));
		mySend.setPassword(myRequest.getParameter("password"));
		mySend.setFrom(myRequest.getParameter("from"));
		mySend.setTo(myRequest.getParameter("to"));
		mySend.setSubject(myRequest.getParameter("subject"));
		mySend.setContent(myRequest.getParameter("content"));
	}catch(Exception ex){
		out.println("附件文件超过了限制条件，上传失败!<br>");
		out.println("错误原因：<br>"+ex.toString());
	}	
	boolean status = mySend.send();
	if (status){
		out.println("恭喜您，邮件发送成功！");
	}else{
		out.println("对不起，邮件发送失败！");
	}
%>
