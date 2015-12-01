package Peder.MySearch.dao;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import net.sf.json.JSONObject;
import Peder.MySearch.bean.Data;
import Peder.MySearch.bean.Product;
import Peder.MySearch.memory.MyMap;
import Peder.MySearch.utils.Utils;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.util.JSON;

public class DataDao {

	
	/**
	 * 设置
	 * @return
	 */
	public DBCollection confg(){
		Mongo mongo;
		try {
			mongo= new Mongo();
			DB myMongo = mongo.getDB("myMongo");
			DBCollection collection = myMongo.getCollection("data");
			
			return collection;
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
	
	/**
	 * 保存
	 * @param user
	 * @throws UnknownHostException
	 */
	@Test
	public void save(Data data)  {
//		DataDao dd=new DataDao();
//		DBCollection collection=dd.confg();
		
		Mongo mongo;
		try {
			mongo = new Mongo();
		
		DB myMongo = mongo.getDB("myMongo");
		DBCollection collection = myMongo.getCollection("data");
		JSONObject json=JSONObject.fromObject(data);
		DBObject dbo =(DBObject) JSON.parse(json.toString());
		collection.insert(dbo);
		mongo.close();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	/**
	 * 查询所有
	 * @return
	 * @throws UnknownHostException 
	 */
	public Data find(String id) {
		DataDao dd=new DataDao();
		DBCollection collection=dd.confg();
		Data temp=new Data();
		
		DBObject query=(DBObject) JSON.parse("{\"_id\":\""+id+"\"}");
		DBCursor cursor=collection.find(query);
		DBObject result = cursor.next();
		
		temp.setId((String) result.get("_id"));
		temp.setTitle((String) result.get("title"));
		temp.setKeys((String[]) result.get("keys"));
		temp.setScore((Float) result.get("score"));
		temp.setDescription((String) result.get("description"));
		
		return temp;
	}
	
	public List<Data> findAll(){
		DataDao dd=new DataDao();
		DBCollection collection=dd.confg();
		List<Data> list=new ArrayList<Data>();
		DBCursor cursor=collection.find();
		
		while(cursor.hasNext()){
			DBObject result = cursor.next();
			System.out.println(result);
			Data temp=new Data();
//			JSONObject json=JSONObject.fromObject(result);
//			System.out.println(json.getString("title"));
			System.out.println(result.get("_id"));
			temp.setId( (String) result.get("id"));
			temp.setTitle((String) result.get("title"));
//			temp.setKeys((String[]) result.get("keys"));
			temp.setScore((Double) result.get("score"));
			temp.setDescription((String) result.get("description"));
			
			list.add(temp);
		}
		System.out.println(collection.getCount());
		return list;
	}
	

	public void remove(float id) {  
        
		DataDao dd=new DataDao();
		DBCollection collection=dd.confg();
        
        BasicDBObject baseDBO =new BasicDBObject();  
        baseDBO.put("score", id);  
  
        //删除某一条记录 
       
        collection.remove(baseDBO);  
    }  

}
