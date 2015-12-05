package Peder.MySearch.bean;

import java.util.List;
import java.util.Map;

public class TT{
	private String key;
	private List<Map.Entry<String, String>> value;
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public List<Map.Entry<String, String>> getValue() {
		return value;
	}
	public void setValue(List<Map.Entry<String, String>> value) {
		this.value = value;
	}
}