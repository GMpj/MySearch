package Peder.MySearch.dao;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import net.sf.json.JSONObject;
import Peder.MySearch.bean.Data;
import Peder.MySearch.utils.Utils;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.util.JSON;

public class WordDao {

	
	/**
	 * 设置
	 * @return
	 */
	public DBCollection confg(){
		try {
			Mongo mongo = new Mongo();
			DB myMongo = mongo.getDB("myMongo");
			DBCollection collection = myMongo.getCollection("word");
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
	public void save(Map<String,String> index)  {
		WordDao wd=new WordDao();
		DBCollection collection=wd.confg();
		
		JSONObject json=JSONObject.fromObject(index);
		DBObject dbo =(DBObject) JSON.parse(json.toString());
		collection.insert(dbo);
	}

	/**
	 * 更新
	 * @param user
	 * @throws UnknownHostException
	 */
	public void update(Map<String,String> newIndex)  {
		WordDao wd=new WordDao();
		DBCollection collection=wd.confg();

		BasicDBObject baseDBO = new BasicDBObject();
		baseDBO.put("_id", newIndex.get("_id"));
		
		JSONObject json=JSONObject.fromObject(newIndex);
		DBObject newDBO =(DBObject) JSON.parse(json.toString());
		
		collection.update(baseDBO, newDBO);
	}
	
	/**
	 * 查询
	 * @return
	 * @throws UnknownHostException 
	 */
	public Map<String,String> find(String word) {
		WordDao wd=new WordDao();
		DBCollection collection=wd.confg();
		
		DBObject query=(DBObject) JSON.parse("{\"_id\":\""+word+"\"}");
		DBCursor cursor=collection.find(query);
		if(cursor.hasNext()){
		String result = cursor.next().toString();
		System.out.println(result);
		Map<String, String> temp = Utils.parseJSON2MapString(result);
		
		return temp;
		}
		else return null;
	}
	
	public void findAll(){
		WordDao wd=new WordDao();
		DBCollection collection=wd.confg();
		DBCursor cursor=collection.find();
		
		while(cursor.hasNext()){
			DBObject result = cursor.next();
			System.out.println(result.get("_id"));
			System.out.println(result);
		}
		System.out.println(collection.getCount());
		
	}

	

}
