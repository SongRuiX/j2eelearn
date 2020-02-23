package l8;

import javax.mail.*;

import java.io.*;
import java.text.*;
import java.util.*;

public class GetMail {

	String POP3Host=""; //POP3服务器
	String user=""; //登录POP3服务器的帐号
	String password=""; //登录POP3服务器的密码
	
	Session session = null;
	Folder folder = null;
	Store store = null;
	
	//无参数的构造函数
	public GetMail() {}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String getPOP3Host() {
		return POP3Host;
	}
	public void setPOP3Host(String host) {
		POP3Host = host;
	}

	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}	

	//连接邮件服务器
	public void connect() throws Exception{
		//创建一个授权验证对象
		POP3Auth auth = new POP3Auth();
		auth.setAccount(user,password);
		
		//取得一个Session对象
		Properties prop=new Properties();
		prop.put("mail.pop3.host",POP3Host);
		session=Session.getDefaultInstance(prop,auth);
		
		//取得一个Store对象
		store=session.getStore("pop3");
		store.connect(POP3Host,user,password);
		  
		//取得一个Folder对象
		folder=store.getDefaultFolder().getFolder("INBOX");
		folder.open(Folder.READ_ONLY);		
	}	

	//获得所有邮件的列表
	public Message[] getAllMail() throws Exception
	 {
		//建立POP3连接
		connect();
		  
		//取得所有的Message对象
		Message[] msg=folder.getMessages();
		  
		FetchProfile profile=new FetchProfile();
		profile.add(FetchProfile.Item.ENVELOPE);
		folder.fetch(msg,profile);

		return msg;
	}	

	//取得邮件列表的信息
	public List getMailInfo(Message[] msg) throws Exception
	{
		List result = new ArrayList();
		Map map = null;
		Multipart mp = null;
		BodyPart part = null;
		String disp = null;
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy年MM月dd日 hh:mm:ss");
		Enumeration henum = null;
		//取出每个邮件的信息
		for (int i=0;i<msg.length;i++){
			map = new HashMap();
			//读取邮件ID
			henum = msg[i].getAllHeaders();
			Header h = null;
			while (henum.hasMoreElements()){
				h = (Header)henum.nextElement();
				if (h.getName().equals("Message-ID")){
					map.put("ID",h.getValue());
				}				
			}
			//读取邮件标题
			map.put("subject",msg[i].getSubject());
			//读取发件人
			map.put("sender",msg[i].getFrom()[0].toString());
			//读取邮件发送日期
			map.put("senddate",fmt.format(msg[i].getSentDate()));
			//读取邮件大小
			map.put("size",new Integer(msg[i].getSize()));
			map.put("hasAttach","&nbsp;");
			//判断是否有附件
			if(msg[i].isMimeType("multipart/*")){
				mp=(Multipart)msg[i].getContent();
				//遍历每个Miltipart对象
				for (int j=0;j<mp.getCount();j++){
					part = mp.getBodyPart(j);
					disp = part.getDisposition();
					//如果有附件
					if(disp!=null && (disp.equals(Part.ATTACHMENT)||disp.equals(Part.INLINE))){
						//设置有附件的特征值
						map.put("hasAttach","√");					
					}
				}				
			}
			result.add(map);
		}
		return result;
	}	

	//查找指定邮件
	public Message findMail(String id) throws Exception
	{
		Message[] msg = getAllMail();
		Enumeration henum = null;
		Header h = null;
		for (int i=0;i<msg.length;i++){
			henum = msg[i].getAllHeaders();
			//查找邮件头中的Message-ID项
			while (henum.hasMoreElements()){
				h = (Header)henum.nextElement();
				//根据传入的message-id来查找目标邮件
				if ((h.getName().equals("Message-ID"))&&(h.getValue().equals(id))){
					return msg[i];
				}				
			}
		}
		return null;
	}	

	//读取邮件内容
	public Map readMail(String basePath,String id) throws Exception{
		Map map = new HashMap();
		//找到目标邮件
		Message msg = findMail(id);
		//读取邮件标题
		map.put("subject",msg.getSubject());
		//读取发件人
		map.put("sender",msg.getFrom()[0].toString());
		map.put("attach","");
		//取得邮件内容
		if (msg.isMimeType("text/*")){
			map.put("content",msg.getContent().toString());
		}else{
			StringBuffer result = new StringBuffer();
			Multipart mp=(Multipart)msg.getContent();
			BodyPart part = null;
			String disp = null;
			//遍历每个Miltipart对象
			for (int j=0;j<mp.getCount();j++){
				part = mp.getBodyPart(j);				
				disp = part.getDisposition();
				//如果有附件
				if(disp!=null && (disp.equals(Part.ATTACHMENT)||disp.equals(Part.INLINE))){
					//取得附件文件名
					String filename = part.getFileName();
					filename = new String(filename.getBytes("ISO8859-1"),"gb2312");
					map.put("attach",filename);
					//下载附件
					InputStream in=part.getInputStream();
					filename = basePath + "/"+filename;
					FileOutputStream out=new FileOutputStream(new File(filename));
					byte[] content=new byte[255];
					int read=0;
					while((read=in.read(content))!=-1)
					{
						out.write(content);
					}
					out.close();
					in.close();
				}else{
					result.append(part.getContent().toString());
				}
			}				
			map.put("content",result.toString());
		}		
		return map;		
	}	
	
	//定义一个POP3授权验证类
	static class POP3Auth extends Authenticator{
		String user,password;
		//设置帐号信息
		void setAccount(String user,String password){
			this.user = user;
			this.password = password;
		}
		//取得PasswordAuthentication对象
		protected PasswordAuthentication getPasswordAuthentication(){
			return new PasswordAuthentication(user,password);
		}
	}	
}
