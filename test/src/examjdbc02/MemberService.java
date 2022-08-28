package examjdbc02;

import java.util.List;

public class MemberService {
	
	private MemberDao memberDao; 
	
	public MemberService(MemberDao memberDao) {
		this.memberDao = memberDao;
	}
	
	//등록하기 
	public boolean regist(MemberVo vo) {
		if(memberDao.selectMember(vo.getNum())==null) {
			memberDao.insertMember(vo);
			return true;
		}
		return false;
	}
	
	//조회하기 
	public MemberVo read(int num) {
		return memberDao.selectMember(num);
	}
	
	//전체조회
	public List<MemberVo> listAll(){
		return memberDao.selectMemberAll();
	}
	
	//업데이트 
	public void edit(MemberVo vo) {
		memberDao.updateMember(vo);
	}
	
	//탈퇴 
	public void remove(int num) {
		memberDao.deleteMemeber(num);
	}
}
