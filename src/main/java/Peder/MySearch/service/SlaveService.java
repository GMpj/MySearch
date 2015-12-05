package Peder.MySearch.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import Peder.MySearch.bean.Data;
import Peder.MySearch.bean.Word;
import Peder.MySearch.dao.DataDao;
import Peder.MySearch.dao.WordDao;
import Peder.MySearch.utils.API;
import Peder.MySearch.utils.Utils;

public class SlaveService {

	private WordDao wd = WordDao.getInstance();
	private DataDao dd = DataDao.getInstance();

	/**
	 * 分词，创建索引
	 * @param data 数据源
	 * @return
	 */
	public Map<String, Double> creatIndex(Data data) {

		Map<String, Double> map = new HashMap<String, Double>();

		//将关键词中的词赋分
		if (null != data.getKeys()) {
			for (String key : data.getKeys()) {
//				System.out.println("关键词"+key);
				map.put(key,  50.0+data.getScore()*100);
			}
		}
		
		// 设置权重进行评分
		Utils.setScore(data.getTitle(), map, 100.0,data.getScore());
		Utils.setScore(data.getDescription(), map, 20.0,data.getScore());
		
		return map;
	}

	/**
	 * 保存索引，将索引写入到数据库中
	 * @param map
	 * @param dataid
	 */
	public void saveIndex(Map<String, Double> map, String dataid) {

		if(null==map){
			return;
		}
		//取出map中的索引词和分数
		for (String key : map.keySet()) {

			//查询数据库中是否有该索引项
			Word word = wd.find(key);
			LinkedHashMap<String, String> index;
			//没有的话插入
			if (null == word) {
				Word temp = new Word();
				index = new LinkedHashMap<String, String>();
				double score = map.get(key);
				index.put(dataid, Double.toString(score));
				temp.setKey(key);
				temp.setValue(index);
				wd.save(temp);
				index.clear();//及时对集合进行清空
			} //有的话更新
			else {
				index = word.getValue();
				double score = map.get(key);
				index.put(dataid, Double.toString(score));
				Word temp = new Word();
				temp.setKey(key);
				temp.setValue(index);
				wd.update(temp);
				word.getValue().clear();
				index.clear();
			}
		}
		map.clear();//清空集合
	}
	
	
	
	
}
