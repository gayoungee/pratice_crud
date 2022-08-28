package examjdbc02;
//데이터 insert (삽입) 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Ex04 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement pstmt = null; 
		int result = 0; 
		JdbcConnectionUtil util = JdbcConnectionUtil.getInstance();
		try {
			conn = util.getConnection();
			System.out.println("db 접속 성공 !");
			
			StringBuffer query = new StringBuffer();
			query.append("insert into \"MEMBER\" (\"NUM\", \"MEMBERID\", \"MEMBERPW\", \"NICKNAME\", \"REGDATE\") values (\"MEMBER_SEQ\".nextval, ? , ? , ? , sysdate)");
			
			pstmt = conn.prepareStatement(query.toString());
			pstmt.setString(1, "tester4");
			pstmt.setString(2, "1234");
			pstmt.setString(3, "nick4");
			

			result = pstmt.executeUpdate(); //쿼리 실제 전송 후 결과 받음 
			System.out.println(result+"행이 삽입되었습니다.");
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			util.close(pstmt);
			util.close(conn);
		}
	}

}
