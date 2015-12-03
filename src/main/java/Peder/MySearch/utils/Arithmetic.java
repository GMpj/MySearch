package Peder.MySearch.utils;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Arithmetic {

	public static int sort(List<Map.Entry<String, Double>> list,double score,int start,int end){
		if(start==end-1){
			return end;
		}
		else{
			int middle=(start+end)/2;
			if(score<list.get(middle).getValue()){
				sort(list,score,middle,end);
			}
			else{
				sort(list,score,start,middle);
			}
			
			return 0;
		}
	}
}
