package Peder.MySearch.dao;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bson.types.ObjectId;
import org.junit.Test;

import net.sf.json.JSONObject;
import Peder.MySearch.bean.Data;
import Peder.MySearch.memory.MyMap;
import Peder.MySearch.utils.Utils;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.QueryOperators;
import com.mongodb.util.JSON;

public class DataDao {

	private DB myMongo;
	private DBCollection collection;

	private Mongo mongo;
	
	 
	private static final DataDao dd = new DataDao();

	// 静态工厂方法
	public static DataDao getInstance() {
		return dd;
	}

	private DataDao() {
		
		try {
			mongo = new Mongo();
			myMongo = mongo.getDB("myMongo");
			collection = myMongo.getCollection("data");
			

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
	public void save(Data data) {
		DataDao dd = new DataDao();

		JSONObject json = JSONObject.fromObject(data);
		DBObject dbo = (DBObject) JSON.parse(json.toString());
		collection.insert(dbo);
		
	}

	public Data find(String id) {
		DataDao dd = new DataDao();
		Data temp = new Data();
		DBObject query = new BasicDBObject();
		query.put("_id", new ObjectId(id));
//		System.out.println(query);
		DBCursor cursor = collection.find(query);
		DBObject result = cursor.next();

		System.out.println(result);
//		System.out.println(result.get("_id"));
		temp.setId(result.get("_id").toString());
		temp.setTitle((String) result.get("title"));
		temp.setKeys((List<String>) result.get("keywords"));
		temp.setScore((Double) result.get("priority"));
		temp.setDescription((String) result.get("description"));
		temp.setText((String) result.get("text"));
		return temp;
	}

	public List<Data> findAll() {
		DataDao dd = new DataDao();
		List<Data> list = new ArrayList<Data>();
		DBCursor cursor = collection.find();

		while (cursor.hasNext()) {
			DBObject result = cursor.next();
			System.out.println(result);
			Data temp = new Data();
			System.out.println(result.get("_id"));
		}
		System.out.println(collection.getCount());
		return list;
	}

	public List<Data> find(String id,int limit) {
		List<Data> list = new ArrayList<Data>();
		DBObject query = new BasicDBObject();
		
		query = new BasicDBObject().append("_id",  
				new BasicDBObject("$gt",  new ObjectId(id)));
		DBCursor cursor = collection.find(query).limit(limit);
		
		while (cursor.hasNext()) {
			DBObject result = cursor.next();
//			System.out.println(result);
			Data temp = new Data();
			temp.setId(result.get("_id").toString());
			temp.setTitle((String) result.get("title"));
			temp.setKeys((List<String>) result.get("keywords"));
			temp.setScore((Double) result.get("priority"));
			temp.setDescription((String) result.get("description"));
			temp.setText((String) result.get("text"));
			list.add(temp);

		}
		return list;
	}

	public String findFirstId(){
		DBCursor cursor = collection.find().limit(1);
		DBObject result = cursor.next();
		return  result.get("_id").toString();
	}
	@Override
	protected void finalize() throws Throwable {
		// TODO Auto-generated method stub
		super.finalize();
		mongo.close();
	}
}
