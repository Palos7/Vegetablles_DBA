package DBA;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class connect {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String name="root";
		String passward="srx5201314";
		String url="jdbc:mysql://localhost:3306/mysql?characterEncoding=utf8&useSSL=false";
		String driver="com.mysql.jdbc.Driver";
		Connection conn=null;
		try {
			try {
				Class.forName(driver);
				System.out.println("驱动加载成功");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				System.out.println("驱动加失败");
			}
			
			conn=DriverManager.getConnection(url, name, passward);
			if(conn!=null) {
				System.out.println("连接成功");
		}
			conn.close();
			}catch(SQLException e) {
				System.out.println("连接失败");
			}
	}

}
