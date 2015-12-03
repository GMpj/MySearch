package Peder.MySearch.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import Peder.MySearch.bean.Data;
import Peder.MySearch.dao.DataDao;
import Peder.MySearch.dao.WordDao;
import Peder.MySearch.utils.Utils;

public class MasterService {

	private WordDao wd = new WordDao();
	private DataDao dd = new DataDao();

	public List<String> getDataid(String word) {
		List<String> dataids = new ArrayList<String>();
//		LinkedHashMap<String, Double> index = wd.find(word);

		return dataids;
	}

	public List<Data> getData() {
		List<Data> result = new ArrayList<Data>();
		result = dd.findAll();
		return result;
	}
}
