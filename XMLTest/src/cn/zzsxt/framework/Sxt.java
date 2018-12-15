package cn.zzsxt.framework;

import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import cn.zzsxt.entity.Userinfo;


public class Sxt {
	private static final String methods = null;
	private List<Beaninfo> bList=new ArrayList<>();
	private Map<String, Object> map=new HashMap<>();
	public  void parseXML()  {
		try {
			SAXReader saxReader=new SAXReader();
			Document document=saxReader.read("beans.xml");
			Element root=document.getRootElement();
			Iterator<Element> beans=root.elementIterator();
			while(beans.hasNext()) {
				Element bean=beans.next();
				String beanId=bean.attributeValue("id");
				String beanClass=bean.attributeValue("class");
				Beaninfo beaninfo=new Beaninfo(beanId,beanClass);
				Iterator<Element> pIterator=bean.elementIterator();
				while(pIterator.hasNext()) {
					Element property=pIterator.next();
					String propertyName=property.attributeValue("name");
					String propertyValue=property.attributeValue("value");
					Propertyinfo propertyinfo=new Propertyinfo(propertyName, propertyValue);
					beaninfo.getList().add(propertyinfo);
				}
				bList.add(beaninfo);
			}
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void getObject() {
		try {
			for(Beaninfo beaninfo:bList) {
				String beanId=beaninfo.getBeanId();
				String beanClass=beaninfo.getBeanClass();
				Class class1=Class.forName(beanClass);
				Object obj=class1.newInstance();
				map.put(beanId, obj);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void injecObject() {
		try {
			for (Beaninfo beaninfo : bList) {
				String beanId=beaninfo.getBeanId();//获取bean节点的id属性值
				Object obj=map.get(beanId);//根据id获取创建对象
				Class clazz=obj.getClass();
				for(Propertyinfo propertyinfo:beaninfo.getList()) {
					String propertyName=propertyinfo.getName();
					String propertyValue=propertyinfo.getValue();
					String setterMethodName=makeSetter(propertyName);//获取对应的setter方法名称
					Method[] methods=clazz.getDeclaredMethods();
					for (Method method : methods) {
						if(method.getName().equals(setterMethodName)) {
							method.invoke(obj, propertyValue);
						}
					}
				}
			}
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public String makeSetter(String fieldName) {
		return "set"+fieldName.substring(0, 1).toUpperCase()+fieldName.substring(1);
	}

	public static void main(String[] args) throws DocumentException {
		Sxt sxt=new Sxt();
		sxt.parseXML();
		sxt.getObject();
		sxt.injecObject();
		Map<String, Object> map=sxt.map;
		//System.out.println(map);
		Userinfo user=(Userinfo)map.get("user");
		//System.out.println(user);
		System.out.println(user.getUserId()+"--"+user.getUserName()+"--"+user.getUserPass());
	}
}
