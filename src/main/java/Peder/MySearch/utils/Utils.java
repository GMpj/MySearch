package Peder.MySearch.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.ToAnalysis;

import net.sf.json.JSONObject;

public class Utils {

	public static List<String> analysis(String temp) {
		if(null==temp){
			return null;
		}
		List<Term> parse = ToAnalysis.parse(temp);
		List<String> list = new ArrayList<String>();
		for (Term t : parse) {
			if (!API.INGORE.contains(t.getName()))
				list.add(t.getName());
		}
		return list;
	}

	public static Map<String, Double> setScore(String str,
			Map<String, Double> map, double weight, Double score) {
		List<String> words = Utils.analysis(str);
		if(null==words){
			return map;
		}
		for (String word : words) {
			if (null != map.get(word)) {
				double num = map.get(word);
				map.put(word, num + 1 * weight + score * 100);
			} else {
				map.put(word, 1 * weight + score * 100);
			}
		}
		return map;
	}

	public static List<Map.Entry<Integer, Integer>> sortInt(
			Map<Integer, Integer> value) {
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

	public static List<Map.Entry<String, String>> sortString(
			Map<String, String> value) {
		List<Map.Entry<String, String>> list = new ArrayList<Map.Entry<String, String>>(
				value.entrySet());

		Comparator<Map.Entry<String, String>> com = new Comparator<Map.Entry<String, String>>() {

			public int compare(Map.Entry<String, String> left,
					Map.Entry<String, String> right) {

				return (Double.compare(Double.parseDouble(right.getValue()),
						Double.parseDouble(left.getValue())));
			}
		};

		Collections.sort(list, com);
		return list;
	}

	public static Set<String> getIngore() {
		Set<String> ingore = new HashSet<String>();
		ingore.add("的");
		ingore.add("是");
		ingore.add("地");
		ingore.add("得");
		ingore.add("，");
		ingore.add("。");
		ingore.add("！");
		ingore.add("、");
		ingore.add("？");
		ingore.add("“");
		ingore.add("”");
		ingore.add("：");
		ingore.add("；");
		ingore.add(",");
		ingore.add(".");
		ingore.add("!");
		ingore.add("/");
		ingore.add("?");
		ingore.add("'");
		ingore.add("'");
		ingore.add("\"");
		ingore.add("the");
		ingore.add("a");
		ingore.add(" ");
		ingore.add("（");
		ingore.add("）");
		ingore.add("(");
		ingore.add(")");
		return ingore;
	}
}
