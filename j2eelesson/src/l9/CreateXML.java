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

	//无参数构造方法
	public CreateXML() {}
	ResultSet rs = null;
	//产生一个XML文档并存盘
	public Document create(String filename) throws SQLException{
		
		//使用DocumentHelper类创建一个文档实例
		Document document=DocumentHelper.createDocument();
		//连接数据库 
		Connection conn = DBUtil.getConnection();
		Statement stmt = conn.createStatement();
		String sql = "SELECT * FROM USERDATA";
		rs =   stmt.executeQuery(sql);
		//创建根元素
				Element rootElement=document.addElement("users");
		while (rs.next()) {
			String userName = rs.getString("username");
			String passWord = rs.getString("password");
			
			//为根元素创建子元素
			Element firstElement=rootElement.addElement("user");
			Element element1=firstElement.addElement("username");
			element1.setText(userName);
			Element element2=firstElement.addElement("password");
			element2.setText(passWord);			
		}
		
		
		  
		//将创建的XML文档存盘
		try{
			XMLWriter output = null;
			//创建一个格式化对象
			OutputFormat format = OutputFormat.createPrettyPrint();
			//使用TAB缩进
			format.setIndent("\t");
			//创建一个XMLWriter对象
			output = new XMLWriter(new FileOutputStream(new File(filename)),format);
			//将XML文档输出
			output.write(document);
	        output.close();
	    }catch(IOException e){
		   System.out.println(e.getMessage());
		}
	    
	    //返回XML文档对象
		return document;
	}	

	//将文本串转换成XML文档并存盘
	public Document create(String filename,String text) throws Exception{
		
		//使用DocumentHelper类将文本串转换为XML文档
		Document document=DocumentHelper.parseText(text);	  

		//将创建的XML文档存盘
		try{
			XMLWriter output = null;
			//创建一个格式化对象
			OutputFormat format = OutputFormat.createPrettyPrint();
			//使用TAB缩进
			format.setIndent("\t");
			//创建一个XMLWriter对象
			output = new XMLWriter(new FileOutputStream(new File(filename)),format);
			//将XML文档输出
			output.write(document);
	        output.close();
	    }catch(IOException e){
		   System.out.println(e.getMessage());
		}
	    
	    //返回XML文档对象
		return document;
	}
	
	//在指定的文件中，将指定Xpath下的指定元素的文本值进行替换
	public Document update(String filename,String xpath,String element,String srcText,String tagText) throws Exception{
		
		//读取指定的XML文件，返回内存XML文档对象
        SAXReader reader = new SAXReader();
        Document doc = reader.read(new File(filename));
        
        //将指定的Xpath的元素文本值进行替换
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
		//将创建的XML文档存盘
		try{
			XMLWriter output = null;
			//创建一个格式化对象
			OutputFormat format = OutputFormat.createPrettyPrint();
			//使用TAB缩进
			format.setIndent("\t");
			//创建一个XMLWriter对象
			output = new XMLWriter(new FileOutputStream(new File(filename)),format);
			//将XML文档输出
			output.write(doc);
	        output.close();
	    }catch(IOException e){
		   System.out.println(e.getMessage());
		}
	    
	    //返回XML文档对象
		return doc;
	}	
	
}
