package Frame;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.text.SimpleDateFormat;

public class Function_add {
	SimpleDateFormat ymd1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
	SimpleDateFormat ymd2 = new SimpleDateFormat("yyyyMMddHHmmss");
	float weight,price;
	String oAddress,eAddress;
	String MercID;
	
	//用户下单功能
	//通过读取文本框类容的方式来读取下单信息
	public void myOrder(String UserID,String content) {
		com test = new com();
		getInfo(UserID,content);
		try {
			Connection conn = test.getconn();
			
			String sql = "insert into myOrder values (\""
			              +ymd2.format(new Date())+"\",\""+
			              UserID+"\",\""+"x"+"\",\""+content+"\",\""+weight+"\",\""+
			              price+"\",\""+oAddress+"\",\""+eAddress+"\",\""+
			              ymd1.format(new Date())+"\");";
			//定义sql语句
			System.out.println(sql);
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.executeUpdate();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			System.out.println("失败");
		}
	}
	//辅助函数
	//对文本框类容经行格式化处理
	public void getInfo(String UserID,String content) {
		weight=0;price=0;
		for(int i=0;i<content.split(" ").length;i++) {
			String temp = content.split(" ")[i];
			weight=weight+Float.parseFloat(temp.split(",")[2]);
			com test = new com();
			String vegid = temp.split(",")[0];
			String mercid = temp.split(",")[1];
			MercID=mercid;
			try {
				Connection conn = test.getconn();
				String sql = new String();
				sql = "select Veg_Price from vegetables where Veg_ID=\""+vegid+
						"\" and Merc_ID=\""+mercid+"\"";
				PreparedStatement stmt = conn.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery(sql);
				float p=0;
				while(rs.next()) {
					p = rs.getFloat("Veg_Price");
				}
	            price = price + Float.parseFloat(temp.split(",")[2])*p;
	            // 完成后关闭
	            rs.close();
	            stmt.close();
	            conn.close();
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}
		//取出地址
		System.out.println("=====getAddress-----");
		com test1 = new com();
		try {
			Connection conn = test1.getconn();
			String sql = new String();
			sql = "select * from user where User_ID=\""+UserID+"\"";
			System.out.println(sql);
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				eAddress = rs.getString("User_Addr");
				System.out.println(eAddress+"   eddddd");
			}
            // 完成后关闭
            rs.close();
            stmt.close();
            conn.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		System.out.println("=====getAddress-----");
		com test2 = new com();
		try {
			Connection conn = test2.getconn();
			String sql = new String();
			sql = "select * from merchant where Merc_ID=\""+MercID+"\"";
			System.out.println(sql);
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				oAddress = rs.getString("Merc_Addr");
				System.out.println(oAddress+"   oddddd");
			}
            // 完成后关闭
            rs.close();
            stmt.close();
            conn.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//完成订单函数
	public void FinishOrder(String orderID) {
		com test = new com();
		String mercID = getMercID(orderID);
		
		try {
			Connection conn = test.getconn();
			String sql = "insert into history_order(Order_ID,User_ID,Psy_ID,Merc_ID,\r\n"
					+ "Content,weight,Price,Origin,Destination,Place_Time) \r\n"
					+ "SELECT Order_ID,User_ID,PSY_ID,\""+mercID+"\",Content,weight,Price,\r\n"
					+ "Origin,Destination,Place_Time FROM myorder where Order_ID=\""+orderID+"\";";
			//定义sql语句
			System.out.println(sql);
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.executeUpdate();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			System.out.println("失败");
		}
	}
	
	//辅助函数
	//取出冲connect中取出mercID
	public String getMercID(String orderid) {
		String mercid = new String();
		com test = new com();
		try {
			Connection conn = test.getconn();
			
			String sql = new String();
			sql = "select Content from myorder where Order_ID=\""+orderid+"\"";
			// 定义查询的语句
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				mercid = rs.getString("Content");
				mercid = mercid.split(" ")[0].split(",")[1];
			}
			
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return mercid;
	}
}
