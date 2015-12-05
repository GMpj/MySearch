package Peder.MySearch.master.servlet;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

import Peder.MySearch.bean.Data;
import Peder.MySearch.dao.WordDao;
import Peder.MySearch.service.MasterService;
import Peder.MySearch.service.SlaveService;

public class Main {

	public static void main(String[] args) {
		MasterService ms=new MasterService();
		SlaveService ss=new SlaveService();
		WordDao wd=WordDao.getInstance();
		Long startTime = System.currentTimeMillis();
//		ms.getData();
		List<String> temp=ms.getQueryIds("口才的故事简介（中高级）贾学堂");
		System.out.println(temp.size());
		for(String str:temp){
			Data data=ms.getData(str);
			
		}
//		wd.find("简介");
		Long endTime = System.currentTimeMillis();
		System.out.println("所有所花费的时间为：" + (endTime - startTime) + "ms");
	}
	
}
