package Peder.MySearch.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Peder.MySearch.bean.Data;
import Peder.MySearch.dao.DataDao;
import Peder.MySearch.dao.WordDao;
import Peder.MySearch.utils.Utils;

public class SlaveService {

	private WordDao wd = new WordDao();
	private DataDao dd = new DataDao();

	public void creatIndex(Data data) {

		Map<String, Integer> map = new HashMap<String, Integer>();

		if (null != data.getKeys()) {
			for (String key : data.getKeys()) {
				map.put(key, (int) (30 * data.getScore()));
			}
		}
		// 设置权重进行评分
		Utils.setScore(data.getTitle(), map, (int) (50 * data.getScore()));
		Utils.setScore(data.getDescription(), map, (int) (20 * data.getScore()));

		for (String key : map.keySet()) {

			Map<String, String> index = wd.find(key);
			if (null == index) {
				index=new HashMap<String,String>();
				int num = map.get(key);
				index.put("id", key);
				index.put(data.getId(), Integer.toString(num));

				wd.save(index);
			} else {
				int num = map.get(key);
				index.put(data.getId(), Integer.toString(num));

				wd.update(index);
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
}
