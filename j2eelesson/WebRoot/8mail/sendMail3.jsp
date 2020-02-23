<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%@ page import="l8.SendAttachMail"%>
<%@ page import="com.jspsmart.upload.*"%>
<jsp:useBean id="mySend" class="l8.SendAttachMail"></jsp:useBean>
<%
	// �½�һ��SmartUpload����,�����Ǳ����
	SmartUpload myupload = new SmartUpload();		
	// ��ʼ��,�����Ǳ����
	myupload.initialize(pageContext);		
	// ����ÿ���ϴ��ļ�����󳤶�
	myupload.setMaxFileSize(10*1024*1024);
	// �趨��ֹ�ϴ����ļ���ͨ����չ�����ƣ�
	myupload.setDeniedFilesList("exe,bat,jsp,htm,html");		
	try{			
		// �ϴ��ļ�,�����Ǳ����
		myupload.upload();			
		// ͳ���ϴ��ļ�������
		int count = myupload.getFiles().getCount();			
		// ȡ��Request����
		Request myRequest = myupload.getRequest();		
		//��ȡ��һ���ϴ��ļ�
		File file = myupload.getFiles().getFile(0);				
		// ���ļ����,��WEBӦ�õĸ�Ŀ¼��Ϊ�ϴ��ļ��ĸ�Ŀ¼
		if (file.getFileName()!=null && file.getFileName().trim().length()>1){
			file.saveAs("/upload/" + file.getFileName(),myupload.SAVE_VIRTUAL);
			//���ø������������·��
			String realpath = pageContext.getServletContext().getRealPath("/");
			mySend.setFilename(realpath+"upload/" + file.getFileName());	
		}
		//�����ʼ����͵���������
		mySend.setSMTPHost(myRequest.getParameter("SMTPHost"));
		mySend.setUser(myRequest.getParameter("user"));
		mySend.setPassword(myRequest.getParameter("password"));
		mySend.setFrom(myRequest.getParameter("from"));
		mySend.setTo(myRequest.getParameter("to"));
		mySend.setSubject(myRequest.getParameter("subject"));
		mySend.setContent(myRequest.getParameter("content"));
	}catch(Exception ex){
		out.println("�����ļ������������������ϴ�ʧ��!<br>");
		out.println("����ԭ��<br>"+ex.toString());
	}	
	boolean status = mySend.send();
	if (status){
		out.println("��ϲ�����ʼ����ͳɹ���");
	}else{
		out.println("�Բ����ʼ�����ʧ�ܣ�");
	}
%>
