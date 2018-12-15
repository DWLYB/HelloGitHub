package TestDom4j;

import java.io.File;
import java.util.Iterator;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class TestDom4j {
	public static void main(String[] args) throws Exception {
		//1.����SAXReader����
		SAXReader saxReader=new SAXReader();
		//2.����SAXReader�����read()��������XML�ļ�ת��ΪDocument����
		Document document =saxReader.read(new File("books.xml"));
		//3.��ȡXML�еĸ��ڵ�
		Element root=document.getRootElement();
		//4.��ȡ���ڵ���ӽڵ�
		Iterator<Element> bookIter=root.elementIterator();
		while(bookIter.hasNext()) {
			Element book=bookIter.next();//��ȡ��book�ڵ����Ϣ
			System.out.println(book.getName());
//			Iterator<Attribute> attIter=book.attributeIterator();
//			while(attIter.hasNext()) {
//				Attribute attribute=attIter.next();//��ȡbook�ڵ�������Ϣ
//				System.out.println("������"+attribute.getName()+"����ֵ"+attribute.getValue());
//				
//			}
			String id=book.attributeValue("id");//��ȡbook����Ϊid��ֵ
			System.out.println("id="+id);
			//��ȡbook�ڵ���ӽڵ�
				Iterator<Element> eleIter=book.elementIterator();
				while(eleIter.hasNext()) {
					Element element=eleIter.next();
					System.out.println("\t"+element.getName()+"="+element.getText());
				}
			
		}
		
	}
}
