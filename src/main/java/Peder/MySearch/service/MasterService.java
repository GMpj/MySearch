package Peder.MySearch.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

import Peder.MySearch.bean.Data;
import Peder.MySearch.bean.Word;
import Peder.MySearch.dao.DataDao;
import Peder.MySearch.dao.WordDao;
import Peder.MySearch.utils.API;
import Peder.MySearch.utils.Arithmetic;
import Peder.MySearch.utils.RequestClient;
import Peder.MySearch.utils.Utils;



public class MasterService {

	private WordDao wd = WordDao.getInstance();
	private DataDao dd = DataDao.getInstance();

	public void getData() {
		MasterService ms = new MasterService();
		String dataid = dd.findFirstId();
		SlaveService ss = new SlaveService();
		List<Data> list;
		Map<String, Double> map;
		while (true) {
			 list= dd.find(dataid, 100);
			 if(null==list){
				 break;
			 }
			dataid=list.get(list.size()-1).getId();
			for (int i = 0; i < list.size(); i++) {
				 map= ss.creatIndex(list.get(i));
				if (null != map) {
					ms.saveIndex(map, list.get(i).getId());
				}
				map.clear();
			}
			
			
			list.clear();
		}

	}

	// public void toHashLinked(Map<String, Double> index, String dataid,
	// List<Word>list) {
	//
	// for(String key:index.keySet()){
	// for(int i=0;i<list.size();i++){
	// if(key.equals(list.get(i).getKey())){
	// list.get(i).getValue().put(dataid, Double.toString(index.get(key)));
	// }
	// else {
	// Word word=new Word();
	// word.setKey(key);
	// LinkedHashMap<String,String>temp=new LinkedHashMap<String,String>();
	// temp.put(dataid, Double.toString(index.get(key)));
	// word.setValue(temp);
	// list.add(word);
	// }
	// }
	// }
	//
	// }

	public void saveIndex(Map<String, Double> map, String dataid) {

		if(null==map){
			return;
		}
		for (String key : map.keySet()) {

			Word word = wd.find(key);
			LinkedHashMap<String, String> index;
//			System.out.println(key + ":" + map.get(key));
			if (null == word) {
				Word temp = new Word();
				index = new LinkedHashMap<String, String>();
				double score = map.get(key);
				index.put(dataid, Double.toString(score));
				temp.setKey(key);
				temp.setValue(index);
				wd.save(temp);
				index.clear();
			} else {
				index = word.getValue();
//				for (String str : index.keySet()) {
//					System.out.println(str + ":" + index.get(str));
//				}
				double score = map.get(key);
				MasterService ms = new MasterService();
//				index = ms.updateIndex(index, dataid, score);
				index.put(dataid, Double.toString(score));
				Word temp = new Word();
				temp.setKey(key);
				temp.setValue(index);
				wd.update(temp);
				word.getValue().clear();
				index.clear();
			}
			
			
			
		}
		map.clear();
	}

	private LinkedHashMap<String, String> updateIndex(
			LinkedHashMap<String, String> index, String id, double score) {
		List<Map.Entry<String, String>> list = Utils.sortString(index);

		int length = list.size();

		if (score < Double.parseDouble(list.get(length - 1).getValue())
				&& length < API.INDEX_SIZE) {
			index = new LinkedHashMap<String, String>();
			for (int i = 0; i < length; i++) {
				index.put(list.get(i).getKey(), list.get(i).getValue());
			}
			index.put(id, Double.toString(score));
		}

		else if (score < Double.parseDouble(list.get(length - 1).getValue())
				&& length >= API.INDEX_SIZE) {
			return index;
		}

		else if (score > Double.parseDouble(list.get(0).getValue())) {
			index = new LinkedHashMap<String, String>();
			index.put(id, Double.toString(score));
			for (int i = 0; i < length - 1; i++) {
				index.put(list.get(i).getKey(), list.get(i).getValue());
			}
		} else {
			int point = Arithmetic.sort(list, score, 0, length);
			index = new LinkedHashMap<String, String>();
			for (int i = 0; i < point; i++) {
				index.put(list.get(i).getKey(), list.get(i).getValue());
			}
			index.put(id, Double.toString(score));
			for (int i = point; i < length - 1; i++) {
				index.put(list.get(i).getKey(), list.get(i).getValue());
			}
		}
		list.clear();
		return index;
	}
	
	
	public LinkedHashSet<String> getQueryIds(String query){
		Long startTime = System.currentTimeMillis();
		LinkedHashSet<String> result=new LinkedHashSet<String>();
		List<String> keywords=Utils.analysis(query);
		for(String keyword:keywords){
			Word word=wd.find(keyword);
			List<Map.Entry<String, String>> list=Utils.sortString(word.getValue());
			for(int i=0;i<list.size();i++){
				result.add(list.get(i).getKey());
			}
		}
		
		Long endTime = System.currentTimeMillis();
		System.out.println("查询索引所有所花费的时间为：" + (endTime - startTime) + "ms");
		return result;
	}
	
	public Data getData(String id){
		return dd.find(id);
	}

}
