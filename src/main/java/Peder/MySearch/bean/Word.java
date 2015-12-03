package Peder.MySearch.bean;

import java.util.LinkedHashMap;
import java.util.Map;

public class Word {

	private String key;
	private LinkedHashMap<String,Double> value;
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public LinkedHashMap<String, Double> getValue() {
		return value;
	}
	public void setValue(LinkedHashMap<String, Double> value) {
		this.value = value;
	}
	
}
