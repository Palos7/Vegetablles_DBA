package Frame;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

//更新数据功能
public class Function_update {
	//更新订单状态
	public void myorder(String id,String orderID) {
		com test = new com();
		try {
			Connection conn = test.getconn();
			String sql = "UPDATE myorder SET PSY_ID=\""+id
					     +"\" where Order_ID=\""+orderID+"\"";
			//定义sql语句
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.executeUpdate();
			ps.close();
			conn.close();
			//关闭所有打开的对象
		} catch (SQLException e) {
			System.out.println("接单失败");
		}
	}
	
	//为配送员点赞
	public void PsyGood(String PsyID,String model) {
		com test = new com();
		try {
			Connection conn = test.getconn();
			String sql = new String();
			if(model.equals("good")) {
				sql = "UPDATE myorder SET RecGoodnum=RecGoodnum+1,"
						+ "RecRepnum=RecRepnum+1 where Courier_ID=\""+PsyID+"\"";
			}else if(model.equals("bad")) {
				sql = "UPDATE myorder SET RecGoodnum=RecGoodnum+1"
						+ " where Courier_ID=\""+PsyID+"\"";
			}
			//定义sql语句
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.executeUpdate();
			ps.close();
			conn.close();
			//关闭所有打开的对象
		} catch (SQLException e) {
			System.out.println("评价失败");
		}
	}
	
	//为商家点赞
	public void MercGood(String MercID,String model) {
		com test = new com();
		try {
			Connection conn = test.getconn();
			String sql = new String();
			if(model.equals("good")) {
				sql = "UPDATE merchant SET Merc_Goodnum=Merc_Goodnum+1,"
						+ "Merc_Repnum=Merc_Repnum+1 where Merc_ID=\""+MercID+"\"";
			}else if(model.equals("bad")) {
				sql = "UPDATE merchant SET RecGoodnum=RecGoodnum+1"
						+ " where Merc_ID=\""+MercID+"\"";
			}
			//定义sql语句
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.executeUpdate();
			ps.close();
			conn.close();
			//关闭所有打开的对象
		} catch (SQLException e) {
			System.out.println("评价失败");
		}
	}
	
	//更新商家订单数量
	public void MorderNum(String MercID) {
		com test = new com();
		try {
			Connection conn = test.getconn();
			String sql = "UPDATE merchant SET Merc_Ordernum=Merc_Ordernum+1"
					     + " where Merc_ID=\""+MercID+"\"";
			//定义sql语句
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.executeUpdate();
			ps.close();
			conn.close();
			//关闭所有打开的对象
		} catch (SQLException e) {
			System.out.println("评价失败");
		}
	}
	
	//更新配送员接单数量
	public void PorderNum(String psyID) {
		com test = new com();
		try {
			Connection conn = test.getconn();
			String sql = "UPDATE courier SET Rec_Ordernum=Rec_Ordernum+1"
					     + " where Courier_ID=\""+psyID+"\"";
			//定义sql语句
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.executeUpdate();
			ps.close();
			conn.close();
			//关闭所有打开的对象
		} catch (SQLException e) {
			System.out.println("评价失败");
		}
	}
	
}
