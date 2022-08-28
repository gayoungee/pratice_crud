package examjdbc02;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MemberDao {
	private JdbcConnectionUtil util; 
	public MemberDao() {
		util=JdbcConnectionUtil.getInstance();
	}
	
	//C
	public void insertMember(MemberVo vo) {
		Connection conn = null;
		PreparedStatement pstmt = null; 
		int result = 0; 
		try {
			conn = util.getConnection();
			System.out.println("db 접속 성공 !");
			
			StringBuffer query = new StringBuffer();
			query.append("insert into \"MEMBER\" ");
			query.append("(\"NUM\", \"MEMBERID\", \"MEMBERPW\", \"NICKNAME\", \"REGDATE\") ");
			query.append("values (\"MEMBER_SEQ\".nextval, ? , ? , ? , sysdate)");
			
			pstmt = conn.prepareStatement(query.toString());
			pstmt.setString(1, vo.getMemberId());
			pstmt.setString(2, vo.getMemberPw());
			pstmt.setString(3, vo.getNickName());
			
			result = pstmt.executeUpdate(); //쿼리 실제 전송 후 결과 받음 
			System.out.println(result+"행이 삽입되었습니다.");
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			util.close(pstmt);
			util.close(conn);
		}
	}
	
	//R-1 (전체조회)
	public List<MemberVo> selectMemberAll() {
		Connection conn = null;
		PreparedStatement pstmt = null; 
		ResultSet rs = null; 
		List<MemberVo> result = new ArrayList<>();
		try {
			conn = util.getConnection();
			System.out.println("db 접속 성공 !");
			pstmt = conn.prepareStatement("select * from \"MEMBER\""); //쿼리 준비
			rs = pstmt.executeQuery(); //쿼리 실제 전송 후 결과 받음 
			while(rs.next()) {
				MemberVo vo = new MemberVo(
						rs.getInt(1),
						rs.getString(2),
						rs.getString("MEMBERPW"),
						rs.getString(4)
				);
				vo.setRegdate(rs.getDate("REGDATE"));
				
				result.add(vo);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			util.close(rs);
			util.close(pstmt);
			util.close(conn);
		}
		return result; //전체 조회 결과 리스트 반환 
	}
	
	//R-2 (번호별 조회)
	public MemberVo selectMember(int num) {
		Connection conn = null;
		PreparedStatement pstmt = null; 
		ResultSet rs = null; 
		MemberVo result = null; 
		try {
			conn = util.getConnection();
			System.out.println("db 접속 성공 !");
			pstmt = conn.prepareStatement("select * from \"MEMBER\" where \"NUM\"=?"); //쿼리 준비
			pstmt.setInt(1, num); //바인딩 변수 회원 찾기 
			rs = pstmt.executeQuery(); //쿼리 실제 전송 후 결과 받음 
			while(rs.next()) {
				result = new MemberVo(
						rs.getInt(1),
						rs.getString(2),
						rs.getString("MEMBERPW"),
						rs.getString(4)
				);
				result.setRegdate(rs.getDate("REGDATE"));
				
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			util.close(rs);
			util.close(pstmt);
			util.close(conn);
		}
		return result;
	}
	
	//U
	public void updateMember(MemberVo vo) {
		Connection conn = null;
		PreparedStatement pstmt = null; 
		int result = 0; 
		try {
			conn = util.getConnection();
			System.out.println("db 접속 성공 !");
			
			StringBuffer query = new StringBuffer();
			query.append("update \"MEMBER\" ");
			query.append("set \"MEMBERPW\"=?, \"NICKNAME\"=? ");
			query.append("where \"NUM\"=? ");
			
			pstmt = conn.prepareStatement(query.toString());
			pstmt.setString(1, vo.getMemberPw());
			pstmt.setString(2, vo.getNickName());
			pstmt.setInt(3, vo.getNum());
			
			result = pstmt.executeUpdate(); //쿼리 실제 전송 후 결과 받음 
			System.out.println(result+"행이 수정되었습니다.");
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			util.close(pstmt);
			util.close(conn);
		}
	}
	
	//D
	public void deleteMemeber(int num) {
		Connection conn = null;
		PreparedStatement pstmt = null; 
		int result = 0; 
		try {
			conn = util.getConnection();
			System.out.println("db 접속 성공 !");
			
			StringBuffer query = new StringBuffer();
			query.append("delete \"MEMBER\" ");
			query.append("where \"NUM\"=? ");
			
			pstmt = conn.prepareStatement(query.toString());
			pstmt.setInt(1, num); //바인딩 변수 삭제 
			
			result = pstmt.executeUpdate(); //쿼리 실제 전송 후 결과 받음 
			System.out.println(result+"행이 삭제되었습니다.");
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			util.close(pstmt);
			util.close(conn);
		}
	}
}
