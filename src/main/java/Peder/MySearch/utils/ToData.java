package Peder.MySearch.utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import Peder.MySearch.bean.Data;
import Peder.MySearch.dao.DataDao;

public class ToData {
	public static void main(String[] args) {
		String sql = "select weiboid, rt_text, text from dfzx_weibo";
		Connection conn = JdbcUtil.getConnection();
		try {
			if (conn == null) {
				throw new Exception("数据库连接失败！");
			}

			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			DataDao dd=new DataDao();
			while (rs.next()) {
				Data data = new Data();
				data.setId(rs.getString("weiboid"));
				data.setDescription(rs.getString("rt_text"));
				data.setTitle(rs.getString("text"));
				data.setScore((double) 4.5);
				
				
				dd.save(data);
			}
			stmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
