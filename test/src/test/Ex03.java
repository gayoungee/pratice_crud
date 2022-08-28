package test;
//특정 번호 조회 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Ex03 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement pstmt = null; 
		ResultSet rs = null; 
		try {
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:xe",
					"jspuser",
					"qwer1234"
					);
			System.out.println("db 접속 성공 !");
			pstmt = conn.prepareStatement("select * from \"MEMBER\" where \"NUM\"=?"); //쿼리 준비
			pstmt.setInt(1, 2); //2번 회원 찾기 
			rs = pstmt.executeQuery(); //쿼리 실제 전송 후 결과 받음 
			while(rs.next()) {
				MemberVo vo = new MemberVo(
						rs.getInt(1),
						rs.getString(2),
						rs.getString("MEMBERPW"),
						rs.getString(4)
				);
				vo.setRegdate(rs.getDate("REGDATE"));
				System.out.println(vo);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			if(rs!=null) {
				try {
					rs.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
			
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
