package Peder.MySearch.memory;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class Utils {
	public static List<Map.Entry<Integer, Integer>> sort(Map<Integer, Integer> value) {
		List<Map.Entry<Integer, Integer>> list = new ArrayList<Map.Entry<Integer, Integer>>(
				value.entrySet());
		Comparator<Map.Entry<Integer, Integer>> com = new Comparator<Map.Entry<Integer, Integer>>() {
			public int compare(Map.Entry<Integer, Integer> left,
					Map.Entry<Integer, Integer> right) {
				return (right.getValue()).compareTo(left.getValue());
			}
		};
		Collections.sort(list, com);
		return list;
	}
	
}
