package Peder.MySearch.memory;

import java.util.HashMap;
import java.util.Map;

public class Test {
public static void main(String[] args) {
	//源数据路径
	String src="/Users/MPJ/Desktop/new.txt";
	Index index=new Index();
	//建立索引
	index.creatIndex(src);
	try {
		//进行检索
		index.find("长江沉船");
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	/**
	 * 参考文章：
	 * 倒排索引的简单实现 http://eriol.iteye.com/blog/1166989
	 * 搜索引擎-倒排索引基础知识 http://blog.csdn.net/hguisu/article/details/7962350
	 * Hash表分析以及Java实现 http://java-mzd.iteye.com/blog/827523
	 */
}
}
