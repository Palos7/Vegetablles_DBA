package Frame;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Function_Select {
	String[][] sample = null;
	List list = new ArrayList();
	com test = new com();
	
	//通过两种id的蔬菜查询
	String[][] vegetables(String model,String searchID){
		try {
			Connection conn = test.getconn();
			
			String sql = new String();
			if(model.equals("")) {
				sql = "select * from vegetables";
			}else {
				sql = "select * from vegetables where "+model+"="+searchID;
				System.out.println(sql);
			}
			// 定义查询的语句
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			// 定义结果集
			ResultSetMetaData md = rs.getMetaData();// 获取键名
			int columnCount = md.getColumnCount();// 获取行的数量
			while (rs.next()) {
				Map rowData = new LinkedHashMap();// 声明Map
				for (int i = 1; i <= columnCount; i++) {
					rowData.put(md.getColumnName(i), rs.getObject(i));// 获取键名及值
				}
				list.add(rowData);
			}
			sample = new String[list.size()][];
			for (int i = 0; i < sample.length; i++) {
				Map m = (Map) list.get(i);
				Set set = m.keySet();
				sample[i] = new String[m.size()];
				Iterator it = set.iterator();
				for (int j = 0; it.hasNext(); j++) {
					String s1 = (String) it.next();
					if (m.get(s1) != null) {
						sample[i][j] = m.get(s1).toString();
					}
				}
			}
			
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sample;
	}

	//当前订单查询
	String[][] myorder(String id,String model){
		try {
			Connection conn = test.getconn();			
			String sql = new String();
			if(model.equals("")) {
				sql = "select Order_ID,weight,Price,Origin,Destination from myorder "
						+ "where PSY_ID=\"x\"";
			}else if(model.equals("jd")){
				sql = "select Order_ID,weight,Price,Origin,Destination from myorder "
						+ "where PSY_ID=\""+id+"\"";
			}else if(model.equals("dd")){
				sql = "select Order_ID,weight,Price,Origin,Destination from myorder "
						+ "where User_ID=\""+id+"\"";
			}
			// 定义查询的语句
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			// 定义结果集
			ResultSetMetaData md = rs.getMetaData();// 获取键名
			int columnCount = md.getColumnCount();// 获取行的数量
			while (rs.next()) {
				Map rowData = new LinkedHashMap();// 声明Map
				for (int i = 1; i <= columnCount; i++) {
					rowData.put(md.getColumnName(i), rs.getObject(i));// 获取键名及值
				}
				list.add(rowData);
			}
			sample = new String[list.size()][];
			for (int i = 0; i < sample.length; i++) {
				Map m = (Map) list.get(i);
				Set set = m.keySet();
				sample[i] = new String[m.size()];
				Iterator it = set.iterator();
				for (int j = 0; it.hasNext(); j++) {
					String s1 = (String) it.next();
					if (m.get(s1) != null) {
						sample[i][j] = m.get(s1).toString();
					}
				}
			}
				
				rs.close();
				ps.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
			return sample;
	}
	
	//历史订单查询
	String[][] hisorder(String id,String model){
		try {
			Connection conn = test.getconn();
				
			String sql = new String();
			if(model.equals("")) {
				sql = "select * from history_order where Psy_ID=\""
			           +id+"\" or User_ID=\""+id+"\"";
			}else if(model.equals("psy")){
				sql = "select * from history_order where Psy_ID=\""+id+"\"";
			}else if(model.equals("yh")){
				sql = "select * from history_order where User_ID=\""+id+"\"";
			}else{
				sql = "select * from history_order where User_ID=\""+id+"\" and Order_ID=\""
						+model+"\"";
			}
			// 定义查询的语句
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			// 定义结果集
			ResultSetMetaData md = rs.getMetaData();// 获取键名
			int columnCount = md.getColumnCount();// 获取行的数量
			while (rs.next()) {
				Map rowData = new LinkedHashMap();// 声明Map
				for (int i = 1; i <= columnCount; i++) {
					rowData.put(md.getColumnName(i), rs.getObject(i));// 获取键名及值
				}
				list.add(rowData);
			}
			sample = new String[list.size()][];
			for (int i = 0; i < sample.length; i++) {
				Map m = (Map) list.get(i);
				Set set = m.keySet();
				sample[i] = new String[m.size()];
				Iterator it = set.iterator();
				for (int j = 0; it.hasNext(); j++) {
					String s1 = (String) it.next();
					if (m.get(s1) != null) {
						sample[i][j] = m.get(s1).toString();
					}
				}
			}
				
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sample;
	}
	//历史订单查询
	String[][] Ghisorder(String id,String model){
		try {
			Connection conn = test.getconn();
					
			String sql = new String();
			if(model.equals("")) {
				sql = "select * from history_order where User_ID=\""
			           +id+"\"";
			}else{
				sql = "select * from history_order where User_ID=\""
			          +id+"\" and Order_ID=\""+model+"\"";
			}
			// 定义查询的语句
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			// 定义结果集
			ResultSetMetaData md = rs.getMetaData();// 获取键名
			int columnCount = md.getColumnCount();// 获取行的数量
			while (rs.next()) {
				Map rowData = new LinkedHashMap();// 声明Map
				for (int i = 1; i <= columnCount; i++) {
					rowData.put(md.getColumnName(i), rs.getObject(i));// 获取键名及值
				}
				list.add(rowData);
			}
			sample = new String[list.size()][];
			for (int i = 0; i < sample.length; i++) {
				Map m = (Map) list.get(i);
				Set set = m.keySet();
				sample[i] = new String[m.size()];
				Iterator it = set.iterator();
				for (int j = 0; it.hasNext(); j++) {
					String s1 = (String) it.next();
					if (m.get(s1) != null) {
						sample[i][j] = m.get(s1).toString();
					}
				}
			}
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sample;
	}
}
