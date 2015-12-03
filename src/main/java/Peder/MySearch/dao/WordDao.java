package Peder.MySearch.dao;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import net.sf.json.JSONObject;
import Peder.MySearch.bean.Data;
import Peder.MySearch.bean.Word;
import Peder.MySearch.utils.Utils;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.util.JSON;

public class WordDao {
	DB myMongo;
	DBCollection collection;

	public WordDao() {
		try {
			Mongo mongo = new Mongo();
			myMongo = mongo.getDB("myMongo");
			collection = myMongo.getCollection("word");
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 保存
	 * 
	 * @param user
	 * @throws UnknownHostException
	 */
	@Test
	public void save(Word word) {

		JSONObject json = JSONObject.fromObject(word);
		System.out.println(json);
		DBObject dbo = (DBObject) JSON.parse(json.toString());
		collection.insert(dbo);
	}

	/**
	 * 更新
	 * 
	 * @param user
	 * @throws UnknownHostException
	 */
	public void update(Word word) {

		BasicDBObject baseDBO = new BasicDBObject();
		baseDBO.put("key", word.getKey());

		JSONObject json = JSONObject.fromObject(word);
		DBObject newDBO = (DBObject) JSON.parse(json.toString());

		collection.update(baseDBO, newDBO);
	}

	/**
	 * 查询
	 * 
	 * @return
	 * @throws UnknownHostException
	 */
	public Word find(String word) {

		DBObject query = (DBObject) JSON
				.parse("{\"key\":\"" + word + "\"}");
		DBCursor cursor = collection.find(query);
		if (cursor.hasNext()) {
			DBObject result = cursor.next();
			String key =(String) result.get("key");
			LinkedHashMap map=(LinkedHashMap) result.get("value");
			
			Word temp=new Word();
			temp.setKey(key);
			temp.setValue(map);
			return temp;
		} else
			return null;
	}

	public void findAll() {
		DBCursor cursor = collection.find();

		while (cursor.hasNext()) {
			DBObject result = cursor.next();
			System.out.println(result.get("_id"));
			System.out.println(result);
		}
		System.out.println(collection.getCount());

	}

}
