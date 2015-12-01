package Peder.MySearch.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.ToAnalysis;

import net.sf.json.JSONObject;

public class Utils {
	public static Map<String, String> parseJSON2MapString(String jsonStr) {
		Map<String, String> map = new HashMap<String, String>();
		// 最外层解析
		JSONObject json = JSONObject.fromObject(jsonStr);
		for (Object k : json.keySet()) {
			Object v = json.get(k);
			if (null != v) {
				map.put(k.toString(), v.toString());
			}
		}
		return map;
	}

	public static List<String> analysis(String temp) {
		List<Term> parse = ToAnalysis.parse(temp);
		List<String> list = new ArrayList<String>();
		for (Term t : parse) {
			list.add(t.getName());
		}
		return list;
	}

	public static List<Map.Entry<String, String>> sortString(
			Map<String, String> value) {
		List<Map.Entry<String, String>> list = new ArrayList<Map.Entry<String, String>>(
				value.entrySet());

		Comparator<Map.Entry<String, String>> com = new Comparator<Map.Entry<String, String>>() {

			public int compare(Map.Entry<String, String> left,
					Map.Entry<String, String> right) {

				if (Integer.parseInt(right.getValue()) > (Integer.parseInt(left
						.getValue()))) {
					return 1;
				} else
					return -1;
			}
		};
		Collections.sort(list, com);
		return list;
	}

	public static List<Map.Entry<Integer, Integer>> sortInt(Map<Integer, Integer> value) {
		List<Map.Entry<Integer, Integer>> list = new ArrayList<Map.Entry<Integer, Integer>>(
				value.entrySet());
		
		Comparator<Map.Entry<Integer, Integer>> com = new Comparator<Map.Entry<Integer, Integer>>() {
			
			public int compare(Map.Entry<Integer, Integer> left,
					Map.Entry<Integer, Integer> right) {
				
				return (right.getValue().compareTo(left.getValue()));
			}		
		};
		
		Collections.sort(list, com);
		return list;
	}
	
	public static Map<String,Integer> setScore(String str,Map<String,Integer> map,int weight){
		List<String> words=Utils.analysis(str);
		for(String word:words){
			if(null!=map.get(word)){
				int num=map.get(word);
				map.put(word,num+1*weight);
			}
			else {
				map.put(word,1*weight);
			}
		}
		return map;
	}
}
