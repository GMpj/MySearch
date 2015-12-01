package Peder.MySearch.memory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Peder.MySearch.utils.Utils;

public class Index {

	private MyMap dictionary = new MyMap();

	/**
	 * 建立索引
	 * 
	 * @param filePath
	 *            源数据文件位置
	 */
	public void creatIndex(String filePath) {
		// 记时
		Long startTime = System.currentTimeMillis();
		try {
			// 从文件中读取源数据
			File file = new File(filePath);
			InputStream is = new FileInputStream(file);
			BufferedReader read = new BufferedReader(new InputStreamReader(is));
			String temp;
			int line = 1;
			while ((temp = read.readLine()) != null) {
				// 对每行数据进行分词
				List<String> words = Ansj.analysis(temp.trim());

				for (String word : words) {
					// 去除空格
					if (!word.equals(" ")) {
						Map<Integer, Integer> value = new HashMap<Integer, Integer>();
						// 当前行计数
						value.put(line, 1);
						// 将信息放到索引表中
						dictionary.put(word, value);
					}
				}
				line++;
			}
			read.close();
			is.close();

			Long endTime = System.currentTimeMillis();
			System.out.println("构建索引所花费的时间为：" + (endTime - startTime) / 1000
					+ "s");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 索引检索
	 * 
	 * @param str
	 *            检索的关键词
	 * @throws Exception
	 */
	public void find(String str) throws Exception {
		Long startTime = System.currentTimeMillis();

		// 将检索内容进行分词
		List<String> words = Ansj.analysis(str.trim());
		for (String word : words) {
			if (!word.equals(" ")) {
				// 获得检索表中的信息
				Map<Integer, Integer> value = dictionary.get(word);
				if (null != value) {
					//进行降序排序
					List<Map.Entry<Integer, Integer>> list = Utils.sortInt(value);
					
					System.out.print(word + ":");
					// 将检索得到的值进行输出
					for (int i = 0; i < list.size(); i++) {
						System.out.print(list.get(i).getKey() + "["
								+ list.get(i).getValue() + "] ");
					}
					System.out.println();

				} else {
					String temp = word + "not found";
					System.out.println(temp);
				}
			}
		}
		Long endTime = System.currentTimeMillis();
		System.out.println("查询所花费的时间为：" + (endTime - startTime) + "ms");
	}

}
