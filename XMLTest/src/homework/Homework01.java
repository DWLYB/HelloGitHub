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
		//1.����List�������ڴ��Bean�ڵ���Ϣ
		List<Beaninfo> list=new ArrayList<>();
		SAXReader saxReader=new SAXReader();
		Document document=saxReader.read(new File("beans.xml"));
		//2.��ȡ���ڵ�
		Element root=document.getRootElement();
		Iterator<Element> beanIter=root.elementIterator();
		while(beanIter.hasNext()) {
			List<Propertyinfo>list2=new ArrayList<>();
			Beaninfo beaninfo=new Beaninfo();
			//3.��ȡbean�ڵ�
			Element bElement=beanIter.next();
			//4.��bean�ڵ����Ϣ��װ��Beaninfo��
			beaninfo.setBeanId(bElement.attributeValue("id"));
			beaninfo.setBeanClass(bElement.attributeValue("class"));
			Iterator<Element> pIterator=bElement.elementIterator();
			while(pIterator.hasNext()) {
				Propertyinfo propertyinfo=new Propertyinfo();
				//5.��ȡproperty�ڵ�
				Element pElement=pIterator.next();
				//6.��property�����Է�װ��propertyinfo��
				propertyinfo.setName(pElement.attributeValue("name"));
				propertyinfo.setValue(pElement.attributeValue("value"));
				//7.��propertyinfo��ӵ�PropertyList��
				list2.add(propertyinfo);	
			}
			//8��propertyList��װ��Beaninfo��
			beaninfo.setPropertyList(list2);	
			list.add(beaninfo);
		}
		return list;
	}
	public static void main(String[] args) throws Exception {
		List<Beaninfo> list=parseXML();
		//1.����bean
		for (Beaninfo beaninfo : list) {
			System.out.println(beaninfo.getBeanId()+"--"+beaninfo.getBeanClass());
			List<Propertyinfo> propertyinfos=beaninfo.getPropertyList();
			//2.����propertyinfo
			for (Propertyinfo propertyinfo : propertyinfos) {
				System.out.println("\t"+propertyinfo.getName()+"---"+propertyinfo.getValue());
			}
		}
	}
}
