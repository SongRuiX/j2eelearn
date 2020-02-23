package l8;

import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
import java.util.*;

public class SendAttachMail {

	String SMTPHost=""; //SMTP服务器
	String user=""; //登录SMTP服务器的帐号
	String password=""; //登录SMTP服务器的密码
	 
	String from =""; //发件人邮箱
	String to =""; //收件人邮箱
	String subject =""; //邮件标题
	String content =""; //邮件内容
	String filename =""; //附件文件名
	 
	//无参数构造方法	 
	public SendAttachMail() {}
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		try{			
			//解决内容的中文问题
			content = new String(content.getBytes("ISO8859-1"),"gb2312");
		}catch(Exception ex){
			ex.printStackTrace();
		}
		this.content = content;
	}

	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		try{			
			//解决文件名的中文问题
			filename = new String(filename.getBytes("ISO8859-1"),"gb2312");
		}catch(Exception ex){
			ex.printStackTrace();
		}
		//将文件路径中的'\'替换成'/'
		filename = filename.replaceAll("\\\\","/");
		this.filename = filename;
	}

	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String getSMTPHost() {
		return SMTPHost;
	}
	public void setSMTPHost(String host) {
		SMTPHost = host;
	}

	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		try{			
			//解决标题的中文问题
			subject = new String(subject.getBytes("ISO8859-1"),"gb2312");
		}catch(Exception ex){
			ex.printStackTrace();
		}		
		this.subject = subject;
	}

	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}

	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}	

	//发送邮件
	public boolean send(){
		//创建一个属性对象
		Properties props = new Properties();
		//指定SMTP服务器
		props.put("mail.smtp.host", SMTPHost);
		//指定是否需要SMTP验证		
		props.put("mail.smtp.auth", "true");
		try{
			//创建一个授权验证对象
			SmtpAuth auth = new SmtpAuth();
			auth.setAccount(user,password);
			
			//创建一个Session对象
			Session mailSession = Session.getInstance(props,auth);
			mailSession.setDebug(true);
			
			//创建一个MimeMessage 对象
			MimeMessage message=new MimeMessage(mailSession);

			//指定发件人邮箱
			message.setFrom(new InternetAddress(from));
			//指定收件人邮箱
			message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
			//指定邮件主题
			message.setSubject(subject);
			//指定邮件发送日期
			message.setSentDate(new Date());
			//指定邮件优先级 1：紧急 3：普通 5：缓慢
			message.setHeader("X-Priority","1");
			message.saveChanges();
			
			//新建一个MimeMultipart对象用来存放多个BodyPart对象
			Multipart container=new MimeMultipart();
			//新建一个存放信件内容的BodyPart对象
			BodyPart textBodyPart=new MimeBodyPart();
			//给BodyPart对象设置内容和格式/编码方式
			textBodyPart.setContent(content,"text/html;charset=gb2312");
			//将含有信件内容的BodyPart加入到MimeMultipart对象中
			container.addBodyPart(textBodyPart);
			
			//新建一个存放信件附件的BodyPart对象
			BodyPart fileBodyPart=new MimeBodyPart();
			//将本地文件作为附件
			FileDataSource fds=new FileDataSource(filename);
			fileBodyPart.setDataHandler(new DataHandler(fds));
			//处理邮件中附件文件名的中文问题
			String attachName = fds.getName();
			attachName = new String(attachName.getBytes("gb2312"),"ISO8859-1");
			//设定附件文件名
			fileBodyPart.setFileName(attachName);
			//将附件的BodyPart对象加入到container中
			container.addBodyPart(fileBodyPart);
			
			//将container作为消息对象的内容
			message.setContent(container);
			
			//创建一个Transport对象
			Transport transport = mailSession.getTransport("smtp");
			//连接SMTP服务器
			transport.connect(SMTPHost, user, password);
			//发送邮件
			transport.send(message, message.getAllRecipients());
			transport.close();
			return true;
		}catch(Exception ex){
			ex.printStackTrace();
			return false;
		}
	}
	
	//定义一个SMTP授权验证类
	static class SmtpAuth extends Authenticator{
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
