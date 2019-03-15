package myblog.util;
import java.sql.*;
public class DBUtil {
	static String user="root";
	static String pass="yh1903721789";
	static String url="jdbc:mysql://localhost:3306/test_db?characterEncoding=utf-8";
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			//Connection 
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	public static Connection getConnection() {
		Connection conn=null;
		try {
			conn=DriverManager.getConnection(url, user,pass);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	public static void closeJDBC(ResultSet rs,Statement stmt,Connection conn) {
		if(rs!=null) {
		  try {
			rs.close();
		  }catch(SQLException e) {
			e.printStackTrace();
		  }
		}
		if(stmt!=null) {
			  try {
				  stmt.close();
			  }catch(SQLException e) {
				e.printStackTrace();
			  }
		}
		if(conn!=null) {
			  try {
				  conn.close();
			  }catch(SQLException e) {
				e.printStackTrace();
			  }
		}
	}
}
