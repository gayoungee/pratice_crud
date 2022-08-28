package examjdbc02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//싱글톤 적용 
public class JdbcConnectionUtil {
	private static JdbcConnectionUtil instance;
	
	private String url ="jdbc:oracle:thin:@localhost:1521:xe";
	private String user ="jspuser";
	private String password = "qwer1234";
	
	private JdbcConnectionUtil() {}
	public static JdbcConnectionUtil getInstance() {
		if(instance == null) {
			instance = new JdbcConnectionUtil();
		}
		return instance;
	}
	
	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url,user,password);
	}
	
	public void close(ResultSet rs) {
		if(rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void close(Statement stmt) {
		if(stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void close(Connection conn) {
		if(conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
