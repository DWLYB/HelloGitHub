package cn.zzsxt.framework;

import java.util.ArrayList;
import java.util.List;


public class Beaninfo {
	private String beanId;
	private String beanClass;
	private List<Propertyinfo>list=new ArrayList<>();
	public Beaninfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Beaninfo(String beanId, String beanClass) {
		super();
		this.beanId = beanId;
		this.beanClass = beanClass;
	}
	public String getBeanId() {
		return beanId;
	}
	public void setBeanId(String beanId) {
		this.beanId = beanId;
	}
	public String getBeanClass() {
		return beanClass;
	}
	public void setBeanClass(String beanClass) {
		this.beanClass = beanClass;
	}
	public List<Propertyinfo> getList() {
		return list;
	}
	public void setList(List<Propertyinfo> list) {
		this.list = list;
	}
	void test() {
		
	}
}
