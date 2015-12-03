package Peder.MySearch.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import Peder.MySearch.bean.Data;
import Peder.MySearch.bean.Word;
import Peder.MySearch.dao.DataDao;
import Peder.MySearch.dao.WordDao;
import Peder.MySearch.utils.API;
import Peder.MySearch.utils.Arithmetic;
import Peder.MySearch.utils.Utils;

public class SlaveService {

	private WordDao wd = new WordDao();
	private DataDao dd = new DataDao();

	public void creatIndex(Data data) {

		Map<String, Double> map = new HashMap<String, Double>();

		if (null != data.getKeys()) {
			for (String key : data.getKeys()) {
				map.put(key,  50.0);
			}
		}
		// 设置权重进行评分
		Utils.setScore(data.getTitle(), map, 100.0,data.getScore());
		Utils.setScore(data.getDescription(), map, 20.0,data.getScore());
		

		for (String key : map.keySet()) {

			Word word=wd.find(key);
			
			if (null == word) {
				Word temp=new Word();
				LinkedHashMap<String, Double> index =new LinkedHashMap<String,Double>();
				double num = map.get(key);
				index.put(data.getId(), num);
				temp.setKey(key);
				temp.setValue(index);
				wd.save(temp);
			} else {
				LinkedHashMap<String, Double> index =  word.getValue();
				double score = map.get(key);
				SlaveService ss=new SlaveService();
				index=ss.updateIndex(index, data.getId(), score);
				Word temp=new Word();
				temp.setKey(key);
				temp.setValue(index);
				wd.update(temp);
			}
		}

	}

	public List<Data> getResult(List<String> dataids) {
		List<Data> result = new ArrayList<Data>();
		for (String dataid : dataids) {
			Data data = dd.find(dataid);
			result.add(data);
		}
		return result;
	}
	
	private LinkedHashMap<String,Double> updateIndex(LinkedHashMap<String,Double> index,String id,double score){
		List<Map.Entry<String, Double>> list = new LinkedList<Map.Entry<String, Double>>(
				index.entrySet());
		int length=list.size();
		
		
		if(score<list.get(length-1).getValue()&&length<API.INDEX_SIZE){
			index=new LinkedHashMap<String,Double>();
			for(int i=0;i<length;i++){
				index.put(list.get(i).getKey(), list.get(i).getValue());
			}
			index.put(id, score);
		}
		
		else if(score<list.get(length-1).getValue()&&length>=API.INDEX_SIZE){
			return index;
		}
		
		else if(score>list.get(0).getValue()){
			index=new LinkedHashMap<String,Double>();
			index.put(id, score);
			for(int i=0;i<length-1;i++){
				index.put(list.get(i).getKey(), list.get(i).getValue());
			}
		}
		else{
			int point=Arithmetic.sort(list, score, 0,length);
			index=new LinkedHashMap<String,Double>();
			for(int i=0;i<point;i++){
				index.put(list.get(i).getKey(), list.get(i).getValue());
			}
			index.put(id, score);
			for(int i=point;i<length-1;i++){
				index.put(list.get(i).getKey(), list.get(i).getValue());
			}
		}
		return index;
	}
	
}
