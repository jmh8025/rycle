package kr.spring.tiles.board.gallery.model.dao;

import java.util.HashMap;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import kr.spring.tiles.board.gallery.controller.Gallery_BoardController;
import kr.spring.tiles.board.gallery.model.dto.Gallery_boardVO;
import kr.spring.tiles.board.gallery.model.dto.Gallery_fileVO;

//현재 클래스를 DAO bean으로 등록시킴
@Repository
public class Gallery_boardDAOImpl implements Gallery_boardDAO {
	private static final Logger logger = LoggerFactory.getLogger(Gallery_BoardController.class);
	// SqlSession 객체를 스프링에서 생성하여 주입시켜준다.
	// 의존관계 주입(Dependency Injection, DI)
	// 느스한 결함
	// IoC(Inversion of Control, 제어의 역전)
	@Inject
	// Inject애노테이션이 없으면 sqlSession은 null상태이지만
	// Inject애노테이션이 있으면 외부에서 객체를 주입시켜주게 된다. 
	// try catch문, finally문, 객체를 close할 필요가 없어졌다.
	SqlSession sqlSession;
		
	
	// 01_01. 게시글 작성
	@Override
	public void create(Gallery_boardVO vo) throws Exception {
    	logger.info("GalleryBoardDao에서 불러서 insert 실행한다");
		sqlSession.insert("gallery_board.insert", vo);
	}
	
	/*	// 01_02 게시물 첨부파일 추가
	@Override
	public void addAttach(String fullName) {
		sqlSession.insert("board.addAttach", fullName);
	} */
	
	// 02. 게시글 상세보기
	@Override
	public Gallery_boardVO read(int bno) throws Exception {
		return sqlSession.selectOne("gallery_board.view", bno);
	}	
	
	// 03. 게시글 수정
	@Override
	public void update(Gallery_boardVO vo) throws Exception {
		sqlSession.update("gallery_board.update", vo);
	}
	
	/*// 04. 게시글 삭제
	@Override
	public void delete(int bno) throws Exception {
		sqlSession.delete("board.deleteArticle",bno);

	}*/
	
	// 05. 게시글 전체 목록
	@Override
	public List<Gallery_boardVO> listAll(String searchOption, String keyword) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("searchOption", searchOption);
		map.put("keyword", keyword);
		return sqlSession.selectList("gallery_board.listAll", map);
	}
	
	// 06. 게시글 조회수 증가
	@Override
	public void increaseViewcnt(int no) throws Exception {
		sqlSession.update("gallery_board.increaseViewcnt", no);
	}
	
	// 07. 게시글 레코드 갯수
	@Override
	public int countArticle() throws Exception {
		// 검색옵션, 키워드 맵에 저장
		return sqlSession.selectOne("gallery_board.countArticle");
	}
	
	@Override
	public int recent_bno() throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("gallery_board.getBno");
	}
	
	@Override
	public void bdelete(int bno) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.delete("gallery_board.bdelete" ,bno);
	}
}
