package kr.spring.tiles.board.free.service;

import java.util.List;

import javax.servlet.http.HttpSession;
import kr.spring.tiles.board.free.model.dto.Fb_categoryVO;

public interface FbCategoryService {
 
	// 01. 카테고리 가져오기
	public List<Fb_categoryVO> listAll() throws Exception;

}
