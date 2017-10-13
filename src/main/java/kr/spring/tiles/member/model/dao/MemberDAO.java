package kr.spring.tiles.member.model.dao;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import kr.spring.tiles.member.model.dto.MemberVO;

public interface MemberDAO {
	// 회원 목록 
	public List<MemberVO> memberList();
	// 회원 입력
	public void insertMember(MemberVO vo);
	// 회원삭제
	public void deleteMember(String id);
	// 회원정보 수정
	public void updateMember(MemberVO vo);
	// 비밀번호 체크
	public boolean checkPw(MemberVO vo);
	
	  // 01_01. 회원 로그인 체크
    public boolean loginCheck(MemberVO vo);
    // 01_02. 회원 로그인 정보
    public MemberVO viewMember(MemberVO vo);
    // 02. 회원 로그아웃
    public void logout(HttpSession session);
    
    //아이디 중복확인
    public boolean idcheck(String id);
 // 이메일 체크
 	public boolean emailCheck(String email);
 	
 	//자전거유무
 	public Map<String, Integer> cycleyn();
	
}
