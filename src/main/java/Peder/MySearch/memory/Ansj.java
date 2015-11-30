package Peder.MySearch.memory;

import java.util.ArrayList;
import java.util.List;

import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.ToAnalysis;

public class Ansj {
	/**
	 * 分词方法，使用ansj分词工具进行分词
	 * @param temp
	 * @return
	 */
public static List<String> analysis(String temp){
	List<Term> parse = ToAnalysis.parse(temp);
	List<String> list=new ArrayList<String>();
	for(Term t:parse){
		list.add(t.getName());
	}
	return list;
}
}
