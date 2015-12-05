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
import Peder.MySearch.utils.Arithmetic;
import Peder.MySearch.utils.Utils;

public class SlaveService {

	private WordDao wd = WordDao.getInstance();
	private DataDao dd = DataDao.getInstance();

	public Map<String, Double> creatIndex(Data data) {

		Map<String, Double> map = new HashMap<String, Double>();

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

	public List<Data> getResult(List<String> dataids) {
		List<Data> result = new ArrayList<Data>();
		for (String dataid : dataids) {
			Data data = dd.find(dataid);
			result.add(data);
		}
		return result;
	}
	
	
}
