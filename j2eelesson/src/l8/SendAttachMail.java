package l8;

import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
import java.util.*;

public class SendAttachMail {

	String SMTPHost=""; //SMTP������
	String user=""; //��¼SMTP���������ʺ�
	String password=""; //��¼SMTP������������
	 
	String from =""; //����������
	String to =""; //�ռ�������
	String subject =""; //�ʼ�����
	String content =""; //�ʼ�����
	String filename =""; //�����ļ���
	 
	//�޲������췽��	 
	public SendAttachMail() {}
	
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

	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		try{			
			//����ļ�������������
			filename = new String(filename.getBytes("ISO8859-1"),"gb2312");
		}catch(Exception ex){
			ex.printStackTrace();
		}
		//���ļ�·���е�'\'�滻��'/'
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
			Session mailSession = Session.getInstance(props,auth);
			mailSession.setDebug(true);
			
			//����һ��MimeMessage ����
			MimeMessage message=new MimeMessage(mailSession);

			//ָ������������
			message.setFrom(new InternetAddress(from));
			//ָ���ռ�������
			message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
			//ָ���ʼ�����
			message.setSubject(subject);
			//ָ���ʼ���������
			message.setSentDate(new Date());
			//ָ���ʼ����ȼ� 1������ 3����ͨ 5������
			message.setHeader("X-Priority","1");
			message.saveChanges();
			
			//�½�һ��MimeMultipart����������Ŷ��BodyPart����
			Multipart container=new MimeMultipart();
			//�½�һ������ż����ݵ�BodyPart����
			BodyPart textBodyPart=new MimeBodyPart();
			//��BodyPart�����������ݺ͸�ʽ/���뷽ʽ
			textBodyPart.setContent(content,"text/html;charset=gb2312");
			//�������ż����ݵ�BodyPart���뵽MimeMultipart������
			container.addBodyPart(textBodyPart);
			
			//�½�һ������ż�������BodyPart����
			BodyPart fileBodyPart=new MimeBodyPart();
			//�������ļ���Ϊ����
			FileDataSource fds=new FileDataSource(filename);
			fileBodyPart.setDataHandler(new DataHandler(fds));
			//�����ʼ��и����ļ�������������
			String attachName = fds.getName();
			attachName = new String(attachName.getBytes("gb2312"),"ISO8859-1");
			//�趨�����ļ���
			fileBodyPart.setFileName(attachName);
			//��������BodyPart������뵽container��
			container.addBodyPart(fileBodyPart);
			
			//��container��Ϊ��Ϣ���������
			message.setContent(container);
			
			//����һ��Transport����
			Transport transport = mailSession.getTransport("smtp");
			//����SMTP������
			transport.connect(SMTPHost, user, password);
			//�����ʼ�
			transport.send(message, message.getAllRecipients());
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
