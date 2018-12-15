package TestDom4j;

import java.io.File;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

public class Test {
	public static void main(String[] args) throws Exception {
		SAXReader saxReader=new SAXReader();
		Document document=saxReader.read(new File("books.xml"));
		//��ȡ��ǰ�ĵ��еĵ�һ��name�ڵ���Ϣ
		Node node=document.selectSingleNode("//name");
		System.out.println("�ڵ����ƣ�"+node.getName()+"\t�ı�����"+node.getText());
		//��ȡ��ǰ�ĵ��е�����author�ڵ�
		List<Node> list=document.selectNodes("//author");
		for (Node node2 : list) {
			System.out.println(node2.getName()+"\t"+node2.getText());
		}
		List<Node> list2=document.selectNodes("//@id");
		for (Node node2 : list2) {
			System.out.println(node2.getName()+"\t"+node2.getText());
		}
		
	}
}
