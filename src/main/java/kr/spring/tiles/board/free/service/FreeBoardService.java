package kr.spring.tiles.board.free.service;

import java.util.List;

import javax.servlet.http.HttpSession;
import kr.spring.tiles.board.free.model.dto.Free_boardVO;

public interface FreeBoardService {
	
	// 회원 목록 
	public List<Free_boardVO> boardList();
	
}
