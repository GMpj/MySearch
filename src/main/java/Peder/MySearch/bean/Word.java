package Peder.MySearch.bean;

import java.util.LinkedHashMap;
import java.util.Map;

public class Word {

	private String key;
	private LinkedHashMap<String,String> value;
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public LinkedHashMap<String, String> getValue() {
		return value;
	}
	public void setValue(LinkedHashMap<String, String> value) {
		this.value = value;
	}
	
}
