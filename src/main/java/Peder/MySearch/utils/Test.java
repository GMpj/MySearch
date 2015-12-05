package Peder.MySearch.utils;

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
import Peder.MySearch.bean.TT;
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
		Data data=dd.find("565f0f0bec4947cf33af412a");
		ss.creatIndex(data);
//		List<Data> list=dd.findAll();
//		for(Data data:list){
//			ss.creatIndex(data);
//		}
	}
	
	public void test(){
		Mongo mongo;
		try {
			TT tt=new TT();
			mongo = new Mongo();
			DB myMongo = mongo.getDB("myMongo");
			DBCollection collection = myMongo.getCollection("test");
//			LinkedHashMap<String, String> map=new LinkedHashMap<String,String>();
//			map.put("565f0f07ec4947cf33af411b", "111");
//			map.put("565f0f0bec4947cf33af412a", "222");
//			List<Map.Entry<String, String>> list = new LinkedList<Map.Entry<String, String>>(
//					map.entrySet());
//			
//			tt.setKey("hihihi");
//			tt.setValue(list);
//			JSONObject json = JSONObject.fromObject(tt);
//			System.out.println(json);
//			DBObject dbo = (DBObject) JSON.parse(json.toString());
//			collection.insert(dbo);
//			DBObject query = new BasicDBObject();
//			query.put("_id", new ObjectId("566165dd0e564371d68764fb"));
//			System.out.println(query);
//			DBCursor cursor = collection.find(query);
//			DBObject result = cursor.next();
//			System.out.println(result);
//			List<Map.Entry<String, String>> list = new LinkedList<Map.Entry<String, String>>();
//			list=(List<Entry<String, String>>) result.get("value");
			
			
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		String str1="565f0f0bec4947cf33af412a";
//		int hash1=str1.hashCode();
//		String str2="565f0f07ec4947cf33af411b";
//		int hash2=str2.hashCode();
//		System.out.println("hash1:"+hash1+",hash2:"+hash2);
//		System.out.println(hash1<hash2);
	}
	
	
}
