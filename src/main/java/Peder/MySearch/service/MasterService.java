package Peder.MySearch.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import Peder.MySearch.bean.Data;
import Peder.MySearch.dao.DataDao;
import Peder.MySearch.dao.WordDao;
import Peder.MySearch.utils.Utils;

public class MasterService {
	
	private WordDao wd=new WordDao();
	private DataDao dd=new DataDao();
	
	public List<String> getDataid(String word){
		List<String> dataids=new ArrayList<String>();
		Map<String,String>index=wd.find(word);
		
		index.remove(word);
		List<Map.Entry<String, String>> list=Utils.sortString(index);
		
		for(int i=0;i<list.size();i++){
			String dataid=list.get(i).getKey();
			dataids.add(dataid);
		}
		return dataids;
	}
	
	public List<Data> getData(){
		List<Data> result=new ArrayList<Data>();
		result=dd.findAll();
		return result;
	}
}
