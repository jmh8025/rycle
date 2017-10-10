package kr.spring.tiles.member.service;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Service;
import kr.spring.tiles.member.model.dao.MemberDAOImpl;
import kr.spring.tiles.member.model.dto.MemberVO;

// 현재 클래스를 스프링에서 관리하는 service bean으로 등록
@Service
public class MemberServiceImpl implements MemberService {
	
	// MemberDAOImpl 객체를 스프링에서 생성하여 주입시킴
	@Inject
	MemberDAOImpl memberDao;
	
	// 01. 전체 회원 목록 조회
	@Override
	public List<MemberVO> memberList() {
		return memberDao.memberList();
	}
	
	// 02. 회원 등록
	@Override
	public void insertMember(MemberVO vo) {
		memberDao.insertMember(vo);
	}

	// 04. 회원 정보 삭제 처리
	@Override
	public void deleteMember(String userId) {
		memberDao.deleteMember(userId);
	}
	// 05. 회원 정보 수정 처리
	@Override
	public void updateMember(MemberVO vo) {
		memberDao.updateMember(vo);
	}
	// 06. 회원 정보 수정 및 삭제를 위한 비밀번호 체크
	@Override
	public boolean checkPw(MemberVO vo) {
		return memberDao.checkPw(vo);
	}

	   // 01_01. 회원 로그인체크
    @Override
    public boolean loginCheck(MemberVO vo, HttpSession session) {
        boolean result = memberDao.loginCheck(vo);
        if (result) { // true일 경우 세션에 등록
            MemberVO vo2 = viewMember(vo);
            // 세션 변수 등록
            session.setAttribute("id", vo2.getId());
            session.setAttribute("name", vo2.getName());
            session.setMaxInactiveInterval(60*60*12);
            
        } 
        return result;
    }

    // 01_02. 회원 로그인 정보
    @Override
    public MemberVO viewMember(MemberVO vo) {
        return memberDao.viewMember(vo);
    }

    // 02. 회원 로그아웃
    @Override
    public void logout(HttpSession session) {
        // 세션 변수 개별 삭제
        // session.removeAttribute("userId");
        // 세션 정보를 초기화 시킴
        session.invalidate();
    }

	@Override
	public boolean idcheck(String id) {
		// TODO Auto-generated method stub
		//아이디 중복확인
		return memberDao.idcheck(id);
	}
	
	// 06. 회원 정보 수정 및 삭제를 위한 비밀번호 체크
		@Override
		public boolean emailCheck(String email) {
			return memberDao.emailCheck(email);
		}

}
