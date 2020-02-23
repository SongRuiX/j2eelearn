package l10;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jspsmart.upload.*;

import l3.DBUtil;

import java.text.*;
import java.util.*;

/*******************************************************/
/*    该实例中尽可能多地用到了一些方法，在实际应用中            */
/*    我们可以根据自己的需要进行取舍！                                               */
/*******************************************************/

public class UploadServlet extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 新建一个SmartUpload对象,此项是必须的
		SmartUpload myupload = new SmartUpload();		
		// 初始化,此项是必须的
		ServletConfig config = getServletConfig();
		myupload.initialize(config,request,response);		

		response.setContentType("text/html");
		response.setCharacterEncoding("gb2312");
		PrintWriter out = response.getWriter();
		out.println("<h2>处理上传的文件</h2>");
		out.println("<hr>");
		
		
		String sql = "insert into UPLOAD(PATH)  values (?)";
		Connection conn;
		
		
		
		try{			
			// 限制每个上传文件的最大长度
			myupload.setMaxFileSize(1024*1024);		
			// 限制总上传数据的长度
			myupload.setTotalMaxFileSize(5*1024*1024);		
			// 设定允许上传的文件（通过扩展名限制）
			 myupload.setAllowedFilesList("doc,txt,jpg,gif");	
			// 设定禁止上传的文件（通过扩展名限制）
			 myupload.setDeniedFilesList("exe,bat,jsp,htm,html,,");			
			// 上传文件,此项是必须的
			myupload.upload();			
			// 统计上传文件的总数
			int count = myupload.getFiles().getCount();			
			// 取得Request对象
			Request myRequest = myupload.getRequest();		
			String rndFilename,fileExtName,fileName,filePathName,memo;
			Date dt = null; 
			SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMddHHmmssSSS"); 			
			int a=0;
			// 逐一提取上传文件信息，同时可保存文件	
			for (int i=0;i<count;i++)
			{
				//　取得一个上传文件
				File file = myupload.getFiles().getFile(i);				
				// 若文件不存在则继续
				if (file.isMissing()) continue;		
				// 取得文件名
				fileName = file.getFileName();
				// 取得文件全名
				filePathName = file.getFilePathName();
				// 取得文件扩展名
				fileExtName = file.getFileExt();
				// 取得随机文件名
				dt = new Date(System.currentTimeMillis()); 
				
				a=i+1;
				rndFilename= "photo"+a+"."+fileExtName;			
				memo = myRequest.getParameter("memo"+i);
	
				// 显示当前文件信息
				out.println("第"+(i+1)+"个文件的文件信息：<br>");
				out.println(" 文件名为："+fileName+"<br>");
				out.println(" 文件扩展名为："+fileExtName+"<br>");
				out.println(" 文件全名为："+filePathName+"<br>");
				out.println(" 文件大小为："+file.getSize()+"字节<br>");
				out.println(" 文件备注为："+memo+"<br>");
				out.println(" 文件随机文件名为："+rndFilename+"<br><br>");
								
				
				
				// 将文件另存,以WEB应用的根目录作为上传文件的根目录
				file.saveAs("/10load/upload/" + rndFilename,SmartUpload.SAVE_VIRTUAL);
				try {
					conn = DBUtil.getConnection();
					PreparedStatement stmt = conn.prepareStatement(sql);				
					stmt .setString(1, "D:/javaworkplace/.metadata/.me_tcat85/webapps/j2eelesson/10load/upload/" + rndFilename);
					stmt .executeUpdate();	
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}
						
			out.println(count+"个文件上传成功！<br>");			
		}catch(Exception ex){
			out.println("上传文件超过了限制条件，上传失败!<br>");
			out.println("错误原因：<br>"+ex.toString());
		}
		out.flush();
		out.close();
	}

}
