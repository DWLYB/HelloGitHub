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
		//获取当前文档中的第一个name节点信息
		Node node=document.selectSingleNode("//name");
		System.out.println("节点名称："+node.getName()+"\t文本内容"+node.getText());
		//获取当前文档中的所有author节点
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
