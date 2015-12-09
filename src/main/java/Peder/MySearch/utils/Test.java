package Peder.MySearch.utils;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.bson.types.ObjectId;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import Peder.MySearch.bean.Data;
import Peder.MySearch.bean.Word;
import Peder.MySearch.dao.DataDao;
import Peder.MySearch.dao.WordDao;
import Peder.MySearch.service.SlaveService;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.util.JSON;

import junit.framework.TestCase;

public class Test extends TestCase {


	
	public void testDataDao(){
		 WordDao wd = WordDao.getInstance();
		 DataDao dd = DataDao.getInstance();
//		Data data=new Data();
//		data.setId("1231231");
//		data.setDescription("helloword");
//		data.setTitle("hello");
//		data.setScore((float) 2.3);
//		dd.save(data);
//		dd.find("ObjectId(\"565f0eb7ec4947cf33af4027\")");
//		dd.findAll();
//		dd.remove((float) 4.5);
//		wd.save();
//		wd.findAll();
//		wd.find("565d65203aba34da913581b9");
	}
	
	
	public void testCreatIndex(){
		 WordDao wd = WordDao.getInstance();
		 DataDao dd = DataDao.getInstance();
		SlaveService ss=new SlaveService();
//		Data data=dd.find("565f0f0bec4947cf33af412a");
//		ss.creatIndex(data);
//		List<Data> list=dd.findAll();
//		for(Data data:list){
//			ss.creatIndex(data);
//		}
	}
	
	public void testSaveStopWord(){
		try {
			Utils.saveStopWord();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
