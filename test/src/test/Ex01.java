package test;
import java.sql.*;
public class Ex01 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connection conn = null;
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			System.out.println("클래스 로딩!");
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:xe",
					"jspuser",
					"qwer1234"
					);
			System.out.println("db 접속 성공 !");
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			if(conn!=null) {
				try {
					conn.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
