package Peder.MySearch.service;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import Peder.MySearch.bean.Data;
import Peder.MySearch.dao.WordDao;

/**
 * 建立线程，进行分词，建立索引，并且将结果插入到数据库
 * @author MPJ
 *
 */
class PostDataThread implements Runnable{
	private static Logger logger = Logger.getLogger(PostDataThread.class);
	private List<Data> list;
	private WordDao wd = WordDao.getInstance();
	private MasterService ms = new MasterService();
	private SlaveService ss = new SlaveService();
	private Map<String, Double> map;
	private int num;
	
	public PostDataThread(List<Data> list,int num){
		this.list=list;
		this.num=num;
	}
	

	public PostDataThread(List<Data> list){
		this.list=list;
	}
	
	public void run() {
		// TODO Auto-generated method stub
		logger.info("线程"+num+"启动");
		for (int i = 0; i < list.size(); i++) {
			 map= ss.creatIndex(list.get(i));
			if (null != map) {
				ss.saveIndex(map, list.get(i).getId());
			}
			map.clear();
		}
		logger.info("线程"+num+"结束");
	}
	
}