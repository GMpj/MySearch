package Peder.MySearch.utils;

import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;
import Peder.MySearch.bean.Data;
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

//	public void testMongoDB() {
//		Mongo mongo;
//		try {
//			mongo = new Mongo();
//
//			DB myMongo = mongo.getDB("myMongo");
//			DBCollection userCollection = myMongo.getCollection("user");
//
//			Map<String, String> map = new HashMap<String, String>();
//			map.put("_id", "你好");
//			map.put("1", "1");
//			map.put("2", "2");
//			JSONObject json = JSONObject.fromObject(map);
//			DBObject dbo = (DBObject) JSON.parse(json.toString());
//			userCollection.insert(dbo);
//
//			DBObject query=(DBObject) JSON.parse("{\"_id\":\"你好\"}");
//			DBCursor cursor = userCollection.find(query);
////			DBCursor cursor = userCollection.find();
//
//			String result = cursor.next().toString();
//			System.out.println(result);
//			Map<String, String> temp = Utils.parseJSON2MapString(result);
//			temp.put("1", "2");
//			temp.put("3", "3");
//			BasicDBObject baseDBO = new BasicDBObject();
//			baseDBO.put("_id", temp.get("_id"));
//			DBObject newDBO = (DBObject) JSON.parse(JSONObject.fromObject(temp)
//					.toString());
//			userCollection.update(baseDBO, newDBO);
//			
//			cursor = userCollection.find();
//			result = cursor.next().toString();
//			System.out.println(result);
//		} catch (UnknownHostException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//	}
	
	public void testDataDao(){
		DataDao dd=new DataDao();
		WordDao wd=new WordDao();
//		Data data=new Data();
//		data.setId("1231231");
//		data.setDescription("helloword");
//		data.setTitle("hello");
//		data.setScore((float) 2.3);
//		dd.save(data);
//		dd.findAll();
//		dd.remove((float) 4.5);
		
		wd.findAll();
//		wd.find("565d65203aba34da913581b9");
	}
	
	
//	public void testCreatIndex(){
//		DataDao dd=new DataDao();
//		SlaveService ss=new SlaveService();
//		List<Data> list=dd.findAll();
//		for(Data data:list){
//			ss.creatIndex(data);
//		}
//	}
	
}
