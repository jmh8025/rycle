package kr.spring.tiles.board.free.service;

import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Service;
import kr.spring.tiles.board.free.model.dao.Free_boardDAOImpl;
import kr.spring.tiles.board.free.model.dto.Free_boardVO;

// 현재 클래스를 스프링에서 관리하는 service bean으로 등록
@Service
public class FreeBoardServiceImpl implements FreeBoardService {
	
	// MemberDAOImpl 객체를 스프링에서 생성하여 주입시킴
	@Inject
	Free_boardDAOImpl freeboardDao;
	
	// 01. 전체 회원 목록 조회
	@Override
	public List<Free_boardVO> boardList() {
		return freeboardDao.boardList();
	}

}
