package l9;

import org.dom4j.*;
import org.dom4j.io.*;
import l3.DBUtil;
import java.io.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class CreateXML {

	//�޲������췽��
	public CreateXML() {}
	ResultSet rs = null;
	//����һ��XML�ĵ�������
	public Document create(String filename) throws SQLException{
		
		//ʹ��DocumentHelper�ഴ��һ���ĵ�ʵ��
		Document document=DocumentHelper.createDocument();
		//�������ݿ� 
		Connection conn = DBUtil.getConnection();
		Statement stmt = conn.createStatement();
		String sql = "SELECT * FROM USERDATA";
		rs =   stmt.executeQuery(sql);
		//������Ԫ��
				Element rootElement=document.addElement("users");
		while (rs.next()) {
			String userName = rs.getString("username");
			String passWord = rs.getString("password");
			
			//Ϊ��Ԫ�ش�����Ԫ��
			Element firstElement=rootElement.addElement("user");
			Element element1=firstElement.addElement("username");
			element1.setText(userName);
			Element element2=firstElement.addElement("password");
			element2.setText(passWord);			
		}
		
		
		  
		//��������XML�ĵ�����
		try{
			XMLWriter output = null;
			//����һ����ʽ������
			OutputFormat format = OutputFormat.createPrettyPrint();
			//ʹ��TAB����
			format.setIndent("\t");
			//����һ��XMLWriter����
			output = new XMLWriter(new FileOutputStream(new File(filename)),format);
			//��XML�ĵ����
			output.write(document);
	        output.close();
	    }catch(IOException e){
		   System.out.println(e.getMessage());
		}
	    
	    //����XML�ĵ�����
		return document;
	}	

	//���ı���ת����XML�ĵ�������
	public Document create(String filename,String text) throws Exception{
		
		//ʹ��DocumentHelper�ཫ�ı���ת��ΪXML�ĵ�
		Document document=DocumentHelper.parseText(text);	  

		//��������XML�ĵ�����
		try{
			XMLWriter output = null;
			//����һ����ʽ������
			OutputFormat format = OutputFormat.createPrettyPrint();
			//ʹ��TAB����
			format.setIndent("\t");
			//����һ��XMLWriter����
			output = new XMLWriter(new FileOutputStream(new File(filename)),format);
			//��XML�ĵ����
			output.write(document);
	        output.close();
	    }catch(IOException e){
		   System.out.println(e.getMessage());
		}
	    
	    //����XML�ĵ�����
		return document;
	}
	
	//��ָ�����ļ��У���ָ��Xpath�µ�ָ��Ԫ�ص��ı�ֵ�����滻
	public Document update(String filename,String xpath,String element,String srcText,String tagText) throws Exception{
		
		//��ȡָ����XML�ļ��������ڴ�XML�ĵ�����
        SAXReader reader = new SAXReader();
        Document doc = reader.read(new File(filename));
        
        //��ָ����Xpath��Ԫ���ı�ֵ�����滻
        List list = doc.selectNodes(xpath);
        Iterator it = list.iterator();
        while (it.hasNext()){
        	Element el =(Element)it.next();
        	Iterator it1=el.elementIterator(element);
        	while (it1.hasNext()){
        		Element titleElement=(Element)it1.next();
        		if(titleElement.getText().equals(srcText))
        			titleElement.setText(tagText);
        	}
        }
		//��������XML�ĵ�����
		try{
			XMLWriter output = null;
			//����һ����ʽ������
			OutputFormat format = OutputFormat.createPrettyPrint();
			//ʹ��TAB����
			format.setIndent("\t");
			//����һ��XMLWriter����
			output = new XMLWriter(new FileOutputStream(new File(filename)),format);
			//��XML�ĵ����
			output.write(doc);
	        output.close();
	    }catch(IOException e){
		   System.out.println(e.getMessage());
		}
	    
	    //����XML�ĵ�����
		return doc;
	}	
	
}
