<%@ page language="java" import="com.jspsmart.upload.*" pageEncoding="gb2312"%><%
	
	// �½�һ��SmartUpload����,�����Ǳ����
	SmartUpload myupload = new SmartUpload();		
	// ��ʼ��,�����Ǳ����
	myupload.initialize(pageContext);
	// ȡ���������
	String op = request.getParameter("op");

	try{			
		// �趨contentDispositionΪnull�Խ�ֹ������Զ����ļ���
		myupload.setContentDisposition(null);
		// �����ļ�
		if (op.equals("en")){
			myupload.downloadFile("/WEB-INF/web.xml");
		}else{
			// ��������ļ�����������
			String descFileName = "WebӦ�������ļ�.xml";
			byte[] b = descFileName.getBytes();
			char[] c = new char[b.length];
			for (int x = 0; x < b.length; x++)c[x] = (char) (b[x] & 0x00FF);
			descFileName = new String(c);
			myupload.downloadFile("/WEB-INF/web.xml","text/xml",descFileName);
		}
	}catch(Exception ex){
		System.out.println("�����ļ�ʧ��!<br>");
		out.println("�����ļ�ʧ��!<br>");
		out.println("����ԭ��<br>"+ex.toString());
	}
%>