package l8;

import javax.mail.*;

import java.io.*;
import java.text.*;
import java.util.*;

public class GetMail {

	String POP3Host=""; //POP3������
	String user=""; //��¼POP3���������ʺ�
	String password=""; //��¼POP3������������
	
	Session session = null;
	Folder folder = null;
	Store store = null;
	
	//�޲����Ĺ��캯��
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

	//�����ʼ�������
	public void connect() throws Exception{
		//����һ����Ȩ��֤����
		POP3Auth auth = new POP3Auth();
		auth.setAccount(user,password);
		
		//ȡ��һ��Session����
		Properties prop=new Properties();
		prop.put("mail.pop3.host",POP3Host);
		session=Session.getDefaultInstance(prop,auth);
		
		//ȡ��һ��Store����
		store=session.getStore("pop3");
		store.connect(POP3Host,user,password);
		  
		//ȡ��һ��Folder����
		folder=store.getDefaultFolder().getFolder("INBOX");
		folder.open(Folder.READ_ONLY);		
	}	

	//��������ʼ����б�
	public Message[] getAllMail() throws Exception
	 {
		//����POP3����
		connect();
		  
		//ȡ�����е�Message����
		Message[] msg=folder.getMessages();
		  
		FetchProfile profile=new FetchProfile();
		profile.add(FetchProfile.Item.ENVELOPE);
		folder.fetch(msg,profile);

		return msg;
	}	

	//ȡ���ʼ��б����Ϣ
	public List getMailInfo(Message[] msg) throws Exception
	{
		List result = new ArrayList();
		Map map = null;
		Multipart mp = null;
		BodyPart part = null;
		String disp = null;
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy��MM��dd�� hh:mm:ss");
		Enumeration henum = null;
		//ȡ��ÿ���ʼ�����Ϣ
		for (int i=0;i<msg.length;i++){
			map = new HashMap();
			//��ȡ�ʼ�ID
			henum = msg[i].getAllHeaders();
			Header h = null;
			while (henum.hasMoreElements()){
				h = (Header)henum.nextElement();
				if (h.getName().equals("Message-ID")){
					map.put("ID",h.getValue());
				}				
			}
			//��ȡ�ʼ�����
			map.put("subject",msg[i].getSubject());
			//��ȡ������
			map.put("sender",msg[i].getFrom()[0].toString());
			//��ȡ�ʼ���������
			map.put("senddate",fmt.format(msg[i].getSentDate()));
			//��ȡ�ʼ���С
			map.put("size",new Integer(msg[i].getSize()));
			map.put("hasAttach","&nbsp;");
			//�ж��Ƿ��и���
			if(msg[i].isMimeType("multipart/*")){
				mp=(Multipart)msg[i].getContent();
				//����ÿ��Miltipart����
				for (int j=0;j<mp.getCount();j++){
					part = mp.getBodyPart(j);
					disp = part.getDisposition();
					//����и���
					if(disp!=null && (disp.equals(Part.ATTACHMENT)||disp.equals(Part.INLINE))){
						//�����и���������ֵ
						map.put("hasAttach","��");					
					}
				}				
			}
			result.add(map);
		}
		return result;
	}	

	//����ָ���ʼ�
	public Message findMail(String id) throws Exception
	{
		Message[] msg = getAllMail();
		Enumeration henum = null;
		Header h = null;
		for (int i=0;i<msg.length;i++){
			henum = msg[i].getAllHeaders();
			//�����ʼ�ͷ�е�Message-ID��
			while (henum.hasMoreElements()){
				h = (Header)henum.nextElement();
				//���ݴ����message-id������Ŀ���ʼ�
				if ((h.getName().equals("Message-ID"))&&(h.getValue().equals(id))){
					return msg[i];
				}				
			}
		}
		return null;
	}	

	//��ȡ�ʼ�����
	public Map readMail(String basePath,String id) throws Exception{
		Map map = new HashMap();
		//�ҵ�Ŀ���ʼ�
		Message msg = findMail(id);
		//��ȡ�ʼ�����
		map.put("subject",msg.getSubject());
		//��ȡ������
		map.put("sender",msg.getFrom()[0].toString());
		map.put("attach","");
		//ȡ���ʼ�����
		if (msg.isMimeType("text/*")){
			map.put("content",msg.getContent().toString());
		}else{
			StringBuffer result = new StringBuffer();
			Multipart mp=(Multipart)msg.getContent();
			BodyPart part = null;
			String disp = null;
			//����ÿ��Miltipart����
			for (int j=0;j<mp.getCount();j++){
				part = mp.getBodyPart(j);				
				disp = part.getDisposition();
				//����и���
				if(disp!=null && (disp.equals(Part.ATTACHMENT)||disp.equals(Part.INLINE))){
					//ȡ�ø����ļ���
					String filename = part.getFileName();
					filename = new String(filename.getBytes("ISO8859-1"),"gb2312");
					map.put("attach",filename);
					//���ظ���
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
	
	//����һ��POP3��Ȩ��֤��
	static class POP3Auth extends Authenticator{
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
