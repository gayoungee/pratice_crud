package examjdbc02;
import java.util.List;

public class Main {

	public static void main(String[] args) { //main이 사용자와 소통하고 있는 부분 
		// TODO Auto-generated method stub
		MemberService memberService = new MemberService(new MemberDao());
		
		//C(insert) 
		MemberVo vo1 = new MemberVo(4,"test4","1234","nick4");
		MemberVo vo2 = new MemberVo(5,"test5","1234","nick5");
		MemberVo vo3 = new MemberVo(6,"test6","1234","nick6");
		
		memberService.regist(vo1);
		memberService.regist(vo2);
		memberService.regist(vo3);
		System.out.println("저장 완료!"); 
		
		//R(select)
		List<MemberVo> ls = memberService.listAll();
		for(MemberVo tmp : ls) {
			System.out.println(tmp); 
		}
		System.out.println("전체 조회 완료!"); 
		
		//R-2 선택 조회 
		MemberVo vo = null; 
		vo = memberService.read(2); 
		System.out.println(vo); 
		System.out.println("특정 번호 조회 완료! ");
		
		//U 수정 
		vo = memberService.read(2); 
		if(vo!=null) {
			vo.setMemberPw("4321");
			vo.setNickName("1nick");
			memberService.edit(vo);
		}
		vo = memberService.read(2);
		System.out.println(vo);
		System.out.println("수정 완료!");
		
		
		//D 삭제 
		vo = memberService.read(2); 
		memberService.remove(vo.getNum());
		System.out.println("삭제 완료!");
	}

}
