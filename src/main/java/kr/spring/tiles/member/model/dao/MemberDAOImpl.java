package kr.spring.tiles.member.model.dao;

import java.util.HashMap;


import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import kr.spring.tiles.member.controller.MemberController;
import kr.spring.tiles.member.model.dto.MemberVO;

//현재 클래스를 DAO bean으로 등록시킴
@Repository
public class MemberDAOImpl implements MemberDAO {
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	// SqlSession 객체를 스프링에서 생성하여 주입시켜준다.
	// 의존관계 주입(Dependency Injection, DI)
	// 느스한 결함
	// IoC(Inversion of Control, 제어의 역전)
	@Inject
	// Inject애노테이션이 없으면 sqlSession은 null상태이지만
	// Inject애노테이션이 있으면 외부에서 객체를 주입시켜주게 된다. 
	// try catch문, finally문, 객체를 close할 필요가 없어졌다.
	SqlSession sqlSession;
	
	// 01. 전체 회원 목록 조회
	@Override
	public List<MemberVO> memberList() {
		return sqlSession.selectList("member.memberList");
	}
	// 02. 회원 등록
	@Override
	public void insertMember(MemberVO vo) {
		sqlSession.insert("member.insertMember", vo);
	}

	// 04.회원 정보 삭제 처리
	@Override
	public void deleteMember(String id) {
		sqlSession.delete("member.deleteMember",id);
	}
	// 05.  회원 정보 수정 처리
	@Override
	public void updateMember(MemberVO vo) {
		sqlSession.update("member.updateMember", vo);

	}
	// 06. 회원 정보 수정 및 삭제를 위한 비밀번호 체크
	@Override
	public boolean checkPw(MemberVO vo) {
		boolean result = false;
		Map<String, String> map = new HashMap<String, String>();
		String id = vo.getId();
		String pw = vo.getPw();
		map.put("id", id);
		map.put("pw", pw);
		int count = sqlSession.selectOne("member.checkPw", map);
		if(count == 1) result= true;
		return result;
	}
	  // 01_01. 회원 로그인체크
    @Override
    public boolean loginCheck(MemberVO vo) {
        String name = sqlSession.selectOne("member.loginCheck", vo);
        return (name == null) ? false : true;
    }
    // 01_02. 회원 로그인 정보
    @Override
    public MemberVO viewMember(MemberVO vo) {
        return sqlSession.selectOne("member.viewMember", vo);
    }
    // 02. 회원 로그아웃
    @Override
    public void logout(HttpSession session) {
    }
    
    //아이디 중복체크
	@Override
	public boolean idcheck(String id) {
		// TODO Auto-generated method stub
		String regidcheck = sqlSession.selectOne("member.idcheck",id);
		logger.info("아이디"+regidcheck);
		return (regidcheck == null) ? false : true;
	}
	
	// 이메일 중복체크
		@Override
		public boolean emailCheck(String email) {
			boolean result = false;
			Map<String, String> map = new HashMap<String, String>();
			map.put("email", email);
			int count = sqlSession.selectOne("member.emailCheck", map);
			if(count == 1) result= true;
			return result;
		}
		@Override
		public Map<String, Integer> cycleyn() {
			int bike_y =  sqlSession.selectOne("member.cycley");
			int bike_n =  sqlSession.selectOne("member.cyclen");
			int cycletype_pixy =  sqlSession.selectOne("member.cycletype_pixy");
			int cycletype_load =  sqlSession.selectOne("member.cycletype_load");
			int cycletype_mini =  sqlSession.selectOne("member.cycletype_mini");
			int cycletype_tour =  sqlSession.selectOne("member.cycletype_tour");
			int cycletype_mtb =  sqlSession.selectOne("member.cycletype_mtb");
			int cycletype_hib =  sqlSession.selectOne("member.cycletype_hib");
			int cycletype_default =  sqlSession.selectOne("member.cycletype_default");
			int cycle_how_01 =  sqlSession.selectOne("member.cycle_how_01");
			int cycle_how_23 =  sqlSession.selectOne("member.cycle_how_23");
			int cycle_how_45 =  sqlSession.selectOne("member.cycle_how_45");
			int cycle_how_67 =  sqlSession.selectOne("member.cycle_how_67");
		
			Map<String, Integer> result = new HashMap<String, Integer>();
			result.put("y", bike_y);
			result.put("n", bike_n);
			result.put("pixy", cycletype_pixy);
			result.put("load", cycletype_load);
			result.put("mini", cycletype_mini);
			result.put("tour", cycletype_tour);
			result.put("mtb", cycletype_mtb);
			result.put("hib", cycletype_hib);
			result.put("default", cycletype_default);
			result.put("how_01", cycle_how_01);
			result.put("how_23", cycle_how_23);
			result.put("how_45", cycle_how_45);
			result.put("how_67", cycle_how_67);
			return result;
		}
		@Override
		public int todayuser() {
			// TODO Auto-generated method stub
			return sqlSession.selectOne("member.todayuser");
		}
		@Override
		public int totaluser() {
			// TODO Auto-generated method stub
			return  sqlSession.selectOne("member.totaluser");
		}
		@Override
		public void inserttoday() {
			sqlSession.insert("member.inserttoday");
		}
	

}
