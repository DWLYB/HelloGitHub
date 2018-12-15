package TestDom4j;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class TestDom4j2 {
	public static List<Book> parseXML() throws Exception {
		List<Book> list=new ArrayList<>();
		//1.����SAXReader����
		SAXReader saxReader=new SAXReader();
		//2.����SAXReader�����read()��������XML�ļ�ת��ΪDocument����
		Document document =saxReader.read(new File("books.xml"));
		//3.��ȡXML�еĸ��ڵ�
		Element root=document.getRootElement();
		//4.��ȡ���ڵ���ӽڵ�
		Iterator<Element> bookIter=root.elementIterator();
		while(bookIter.hasNext()) {
			Book b=new Book();
			Element book=bookIter.next();//��ȡ��book�ڵ����Ϣ
//			System.out.print(book.getName());
//			Iterator<Attribute> attIter=book.attributeIterator();
//			while(attIter.hasNext()) {
//				Attribute attribute=attIter.next();//��ȡbook�ڵ�������Ϣ
//				System.out.println("������"+attribute.getName()+"����ֵ"+attribute.getValue());
//				
//			}
			String id=book.attributeValue("id");
			b.setId(Integer.parseInt(id));
//			System.out.println("\tid="+id);
				Iterator<Element> eleIter=book.elementIterator();
				while(eleIter.hasNext()) {
					Element element=eleIter.next();
					switch (element.getName()) {
					case "name":
						b.setName(element.getText());
						break;
					case "author":
						b.setAuthor(element.getText());
						break;
					case "price":
						b.setPrice(Double.parseDouble(element.getText()));
						break;
					}
				}
				list.add(b);
		}
		return list;
	}
	public static void main(String[] args) throws Exception {
		List<Book> list=parseXML();
		for (Book book : list) {
			System.out.println(book);
		}
	}
}
