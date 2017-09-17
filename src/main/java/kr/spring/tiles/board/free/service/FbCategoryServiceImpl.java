package kr.spring.tiles.board.free.service;

import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.spring.tiles.board.free.model.dao.Fb_categoryDAOImpl;
import kr.spring.tiles.board.free.model.dto.Fb_categoryVO;

// 현재 클래스를 스프링에서 관리하는 service bean으로 등록
@Service
public class FbCategoryServiceImpl implements FbCategoryService {
	
	// MemberDAOImpl 객체를 스프링에서 생성하여 주입시킴
	@Inject
	Fb_categoryDAOImpl fbcategoryDao;
	
	// 01. 카테고리 가져오기
	public List<Fb_categoryVO> listAll() throws Exception{
	    return fbcategoryDao.listAll();
	}

}
