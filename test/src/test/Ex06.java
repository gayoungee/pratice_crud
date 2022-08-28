package test;
//데이터 delete ( 삭제 ) 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Ex06 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement pstmt = null; 
		int result = 0; 
		try {
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:xe",
					"jspuser",
					"qwer1234"
					);
			System.out.println("db 접속 성공 !");
			
			StringBuffer query = new StringBuffer();
			query.append("delete \"MEMBER\" ");
			query.append("where \"NUM\"=? ");
			
			pstmt = conn.prepareStatement(query.toString());
			pstmt.setInt(1, 5);
			
			result = pstmt.executeUpdate(); //쿼리 실제 전송 후 결과 받음 
			System.out.println(result+"행이 삭제되었습니다.");
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			
			if(pstmt!=null) {
				try {
					pstmt.close();
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

}
