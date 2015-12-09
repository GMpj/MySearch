package Peder.MySearch.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import Peder.MySearch.bean.Data;
import Peder.MySearch.bean.Entry;
import Peder.MySearch.bean.Result;
import Peder.MySearch.bean.Word;
import Peder.MySearch.dao.DataDao;
import Peder.MySearch.dao.WordDao;
import Peder.MySearch.utils.API;
import Peder.MySearch.utils.RequestClient;
import Peder.MySearch.utils.Utils;

public class MasterService {
	private static Logger logger = Logger.getLogger(MasterService.class);
	private WordDao wd = WordDao.getInstance();
	private DataDao dd = DataDao.getInstance();

	/**
	 * 从数据库中获取数据资源，并将获取到得数据分发给各个线程
	 */
	public void getData() {
		MasterService ms = new MasterService();
		String dataid = dd.findFirstId();
		SlaveService ss = new SlaveService();
		List<Data> list;
		Map<String, Double> map;
		int num = 0;// 线程计数器

		while (true) {
			num++;
			list = dd.find(dataid, 1000);
			// 如果资源全部获取就停止
			if (list.size() == 0) {
				break;
			}
			// 获取上次查询的最后一条记录的id
			dataid = list.get(list.size() - 1).getId();
			// 建立线程，分发任务
			PostDataThread pd = new PostDataThread(list, num);
			new Thread(pd).start();
		}

	}

	/**
	 * 将查询语句进行分词，并将各个关键词的索引结果返回
	 * 
	 * @param query
	 * @return
	 */
	public List<Result> getResults(String query) {

		List<Map.Entry<String, String>> list = new ArrayList<Map.Entry<String, String>>();
		List<Result> result = new ArrayList<Result>();
		Long startTime = System.currentTimeMillis();
		// 将查询语句进行分词
		List<String> keywords = Utils.analysis(query);
		// 对每个关键词进行索引
		for (String keyword : keywords) {
			Word word = wd.find(keyword);
			if (null != word) {
				// 将查询到得结果按照分数进行排序
				List<Map.Entry<String, String>> temp = Utils.sortString(word
						.getValue());
				// 按照顺序获取其中的dataid
				List<String> value = new ArrayList<String>();
				for (int i = 0; i < temp.size(); i++) {
					value.add(temp.get(i).getKey());
				}
				// 放入结果对象
				Result r = new Result();
				r.setKey(keyword);
				r.setValue(value);
				// 加入结果队列中返回
				result.add(r);
			}

		}
		Long endTime = System.currentTimeMillis();
		logger.info("关键词：" + query + " 查询索引所花费的时间为：" + (endTime - startTime)
				+ "ms");

		return result;
	}

	/**
	 * 将查询的结果整合到一起输出
	 * 
	 * @param query
	 * @return
	 */
	public List<String> getQueryIds(String query) {
		Long startTime = System.currentTimeMillis();

		List<Entry> list = new ArrayList<Entry>();
		List<String> result = new ArrayList<String>();
		List<String> keywords = Utils.analysis(query);

		for (String keyword : keywords) {
			Word word = wd.find(keyword);

			if (null == word) {
				return null;
			}

			Map<String, String> value = word.getValue();
			for (String key : value.keySet()) {
				int i = 0;
				for (; i < list.size(); i++) {
					if (key.equals(list.get(i).getKey())) {
						double temp = list.get(i).getValue();

						list.get(i).setValue(
								temp + Double.parseDouble(value.get(key)));

					}
				}
				if (i == list.size()) {
					Entry e=new Entry();
					e.setKey(key);
					e.setValue(Double.parseDouble(value.get(key)));
					list.add(e);
				}
			}

		}

		for (int i = 0; i < list.size(); i++) {
			result.add(list.get(i).getKey());
		}
		
		System.out.println(list.size());
		Long endTime = System.currentTimeMillis();
		logger.info("关键词：" + query + " 查询索引所花费的时间为：" + (endTime - startTime)
				+ "ms");
		return result;
	}

	

	/**
	 * 通过id获取资源
	 * 
	 * @param id
	 * @return
	 */
	public Data getData(String id) {
		return dd.find(id);
	}

}
