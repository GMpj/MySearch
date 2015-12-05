package Peder.MySearch.master.servlet;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

import Peder.MySearch.bean.Data;
import Peder.MySearch.service.MasterService;
import Peder.MySearch.service.SlaveService;

public class Main {

	public static void main(String[] args) {
		MasterService ms=new MasterService();
		SlaveService ss=new SlaveService();
//		ms.getData();
		Long startTime = System.currentTimeMillis();
		LinkedHashSet<String> temp=ms.getQueryIds("简介");
		for(String str:temp){
			Data data=ms.getData(str);
			
		}
		Long endTime = System.currentTimeMillis();
		System.out.println("查询所有所花费的时间为：" + (endTime - startTime) + "ms");
	}
	
}
