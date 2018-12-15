package homework;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class Homework01 {
	public static List<Beaninfo> parseXML() throws Exception {
		//1.创建List集合用于存放Bean节点信息
		List<Beaninfo> list=new ArrayList<>();
		SAXReader saxReader=new SAXReader();
		Document document=saxReader.read(new File("beans.xml"));
		//2.获取根节点
		Element root=document.getRootElement();
		Iterator<Element> beanIter=root.elementIterator();
		while(beanIter.hasNext()) {
			List<Propertyinfo>list2=new ArrayList<>();
			Beaninfo beaninfo=new Beaninfo();
			//3.获取bean节点
			Element bElement=beanIter.next();
			//4.将bean节点的信息封装到Beaninfo中
			beaninfo.setBeanId(bElement.attributeValue("id"));
			beaninfo.setBeanClass(bElement.attributeValue("class"));
			Iterator<Element> pIterator=bElement.elementIterator();
			while(pIterator.hasNext()) {
				Propertyinfo propertyinfo=new Propertyinfo();
				//5.获取property节点
				Element pElement=pIterator.next();
				//6.将property的属性封装到propertyinfo中
				propertyinfo.setName(pElement.attributeValue("name"));
				propertyinfo.setValue(pElement.attributeValue("value"));
				//7.将propertyinfo添加到PropertyList中
				list2.add(propertyinfo);	
			}
			//8将propertyList封装到Beaninfo中
			beaninfo.setPropertyList(list2);	
			list.add(beaninfo);
		}
		return list;
	}
	public static void main(String[] args) throws Exception {
		List<Beaninfo> list=parseXML();
		//1.遍历bean
		for (Beaninfo beaninfo : list) {
			System.out.println(beaninfo.getBeanId()+"--"+beaninfo.getBeanClass());
			List<Propertyinfo> propertyinfos=beaninfo.getPropertyList();
			//2.遍历propertyinfo
			for (Propertyinfo propertyinfo : propertyinfos) {
				System.out.println("\t"+propertyinfo.getName()+"---"+propertyinfo.getValue());
			}
		}
	}
}
