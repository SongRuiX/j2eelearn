package l8;

import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
import java.util.*;

public class SendTextMail {
	
	String SMTPHost=""; //SMTP������
	String user=""; //��¼SMTP���������ʺ�
	String password=""; //��¼SMTP������������
	 
	String from =""; //����������
	String to =""; //�ռ�������
	String subject =""; //�ʼ�����
	String content =""; //�ʼ�����	

	//�޲������췽��
	public SendTextMail() {}
	 
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		try{			
			//������ݵ���������
			content = new String(content.getBytes("ISO8859-1"),"gb2312");
		}catch(Exception ex){
			ex.printStackTrace();
		}
		this.content = content;
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
			//����������������
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

	//�����ʼ�
	public boolean send(){
		//����һ�����Զ���
		Properties props = new Properties();
		//ָ��SMTP������
		props.put("mail.smtp.host", SMTPHost);
		//ָ���Ƿ���ҪSMTP��֤		
		props.put("mail.smtp.auth", "true");
		try{
			//����һ����Ȩ��֤����
			SmtpAuth auth = new SmtpAuth();
			auth.setAccount(user,password);
			
			//����һ��Session����
			Session mailSession = Session.getDefaultInstance(props,auth);
			mailSession.setDebug(true);
			
			//����һ��Message����
			Message message=new MimeMessage(mailSession);

			//ָ������������
			message.setFrom(new InternetAddress(from));
			//ָ���ռ�������
			message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
			//ָ���ʼ�����
			message.setSubject(subject);
			//ָ���ʼ�����
			message.setText(content);
			//ָ���ʼ���������
			message.setSentDate(new Date());
			//ָ���ʼ����ȼ� 1������ 3����ͨ 5������
			message.setHeader("X-Priority","1");
			message.saveChanges();
						
			//����һ��Transport����
			Transport transport = mailSession.getTransport("smtp");
			//����SMTP������
			transport.connect(SMTPHost, user, password);
			//�����ʼ�
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
			return true;
		}catch(Exception ex){
			ex.printStackTrace();
			return false;
		}
	}
	
	//����һ��SMTP��Ȩ��֤��
	static class SmtpAuth extends Authenticator{
		String user,password;
		//�����ʺ���Ϣ
		void setAccount(String user,String password){
			this.user = user;
			this.password = password;
		}
		//ȡ��PasswordAuthentication����
		protected PasswordAuthentication getPasswordAuthentication(){
			return new PasswordAuthentication(user,password);
		}
	}
}
