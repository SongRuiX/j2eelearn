package l9;

import org.dom4j.*;
import org.dom4j.io.*;
import java.io.*;

public class ParseXML {

	StringBuffer result = new StringBuffer();
	Document doc = null;
	
	//�޲������췽��
	public ParseXML() {}
	
	//��ȡ�����XML�ļ�������XML�ĵ�����
	public void read(String fileName) throws Exception{
        SAXReader reader = new SAXReader();
        doc = reader.read(new File(fileName));
    }	

    //�����Ԫ�������ݹ����
	public void treeWalk() {	
	     treeWalk(doc.getRootElement());	
	}
	
	//�ݹ����XML�ĵ�
	public void treeWalk(Element element) {
        for (int i = 0, size = element.nodeCount(); i < size; i++) {
            Node node = element.node(i);
            //����ӽڵ�����Ԫ��,�����ݹ�
            if (node instanceof Element) {
            	if (node.getText().trim().length()>0)result.append(node.getName()+" = ");
            	else result.append("<br>�û���Ϣ<br>");
                treeWalk((Element) node);                
            } else {
            	//���������ӽڵ���ı�ֵ
            	if (node.getText().trim().length()>0)result.append(node.getText()+"<br>");
            }
        }
    }
	
	//ȡ��XML�Ľ������
	public String getResult() {
		return result.toString();
	}	
	
}
