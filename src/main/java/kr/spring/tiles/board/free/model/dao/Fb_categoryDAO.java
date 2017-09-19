package kr.spring.tiles.board.free.model.dao;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import kr.spring.tiles.board.free.model.dto.Fb_categoryVO;
  
public interface Fb_categoryDAO {
	
	// 01. 카테고리 가져오기
	public List<Fb_categoryVO> listAll() throws Exception;

}
