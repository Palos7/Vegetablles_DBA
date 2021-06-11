package Frame;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class com {
	public Connection getconn() throws SQLException{
		String name="root";
		String passward="srx5201314";
		String url="jdbc:mysql://localhost:3306/test?characterEncoding=utf-8&useSSL=false";
		String driver="com.mysql.jdbc.Driver";
		Connection conn =DriverManager.getConnection(url, name, passward);
		return conn;
	}
}
