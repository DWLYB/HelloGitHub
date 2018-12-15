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
		//1.创建SAXReader对象
		SAXReader saxReader=new SAXReader();
		//2.调用SAXReader对象的read()方法，将XML文件转化为Document对象
		Document document =saxReader.read(new File("books.xml"));
		//3.获取XML中的根节点
		Element root=document.getRootElement();
		//4.获取根节点的子节点
		Iterator<Element> bookIter=root.elementIterator();
		while(bookIter.hasNext()) {
			Element book=bookIter.next();//获取到book节点的信息
			System.out.println(book.getName());
//			Iterator<Attribute> attIter=book.attributeIterator();
//			while(attIter.hasNext()) {
//				Attribute attribute=attIter.next();//获取book节点属性信息
//				System.out.println("属性名"+attribute.getName()+"属性值"+attribute.getValue());
//				
//			}
			String id=book.attributeValue("id");//获取book属性为id的值
			System.out.println("id="+id);
			//获取book节点的子节点
				Iterator<Element> eleIter=book.elementIterator();
				while(eleIter.hasNext()) {
					Element element=eleIter.next();
					System.out.println("\t"+element.getName()+"="+element.getText());
				}
			
		}
		
	}
}
