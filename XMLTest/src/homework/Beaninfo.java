package homework;

import java.util.List;

public class Beaninfo {
	private String beanId;
	private String beanClass;
	private List<Propertyinfo> propertyList;
	public Beaninfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Beaninfo(String beanId, String beanClass, List<Propertyinfo> propertyList) {
		super();
		this.beanId = beanId;
		this.beanClass = beanClass;
		this.propertyList = propertyList;
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
	public List<Propertyinfo> getPropertyList() {
		return propertyList;
	}
	public void setPropertyList(List<Propertyinfo> propertyList) {
		this.propertyList = propertyList;
	}
	@Override
	public String toString() {
		return "Beaninfo [beanId=" + beanId + ", beanClass=" + beanClass + ", propertyList=" + propertyList + "]";
	}

}
