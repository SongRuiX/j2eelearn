package l9;

import org.dom4j.*;
import org.dom4j.io.*;
import java.io.*;

public class ParseXML {

	StringBuffer result = new StringBuffer();
	Document doc = null;
	
	//无参数构造方法
	public ParseXML() {}
	
	//读取传入的XML文件，返回XML文档对象
	public void read(String fileName) throws Exception{
        SAXReader reader = new SAXReader();
        doc = reader.read(new File(fileName));
    }	

    //传入根元素启动递归遍历
	public void treeWalk() {	
	     treeWalk(doc.getRootElement());	
	}
	
	//递归遍历XML文档
	public void treeWalk(Element element) {
        for (int i = 0, size = element.nodeCount(); i < size; i++) {
            Node node = element.node(i);
            //如果子节点是子元素,继续递归
            if (node instanceof Element) {
            	if (node.getText().trim().length()>0)result.append(node.getName()+" = ");
            	else result.append("<br>用户信息<br>");
                treeWalk((Element) node);                
            } else {
            	//否则就输出子节点的文本值
            	if (node.getText().trim().length()>0)result.append(node.getText()+"<br>");
            }
        }
    }
	
	//取得XML的解析结果
	public String getResult() {
		return result.toString();
	}	
	
}
