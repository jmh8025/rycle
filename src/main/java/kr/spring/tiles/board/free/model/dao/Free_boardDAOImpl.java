package kr.spring.tiles.board.free.model.dao;

import java.util.HashMap;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import kr.spring.tiles.board.free.controller.Free_BoardController;
import kr.spring.tiles.board.free.model.dto.Free_boardVO;

//현재 클래스를 DAO bean으로 등록시킴
@Repository
public class Free_boardDAOImpl implements Free_boardDAO {
	private static final Logger logger = LoggerFactory.getLogger(Free_BoardController.class);
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
	public void create(Free_boardVO vo) throws Exception {
    	logger.info("FreeBoardDao에서 불러서 insert 실행한다");
		sqlSession.insert("free_board.insert", vo);
	}
	
	/*	// 01_02 게시물 첨부파일 추가
	@Override
	public void addAttach(String fullName) {
		sqlSession.insert("board.addAttach", fullName);
	} */
	
	// 02. 게시글 상세보기
	@Override
	public Free_boardVO read(int bno) throws Exception {
		return sqlSession.selectOne("free_board.view", bno);
	}	
	
	// 03. 게시글 수정
	@Override
	public void update(Free_boardVO vo) throws Exception {
		sqlSession.update("free_board.update", vo);
	}
	
	/*// 04. 게시글 삭제
	@Override
	public void delete(int bno) throws Exception {
		sqlSession.delete("board.deleteArticle",bno);

	}*/
	
	// 05. 게시글 전체 목록
	@Override
	public List<Free_boardVO> listAll(int start, int end, String searchOption, String keyword) throws Exception {
		// 검색옵션, 키워드 맵에 저장
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("searchOption", searchOption);
		map.put("keyword", keyword);
		// BETWEEN #{start}, #{end}에 입력될 값을 맵에 
		map.put("start", start);
		map.put("end", end);
		return sqlSession.selectList("free_board.listAll", map);
	}
	
	// 06. 게시글 조회수 증가
	@Override
	public void increaseViewcnt(int no) throws Exception {
		sqlSession.update("free_board.increaseViewcnt", no);
	}
	
	// 07. 게시글 레코드 갯수
	@Override
	public int countArticle(String searchOption, String keyword) throws Exception {
		// 검색옵션, 키워드 맵에 저장
		Map<String, String> map = new HashMap<String, String>();
		map.put("searchOption", searchOption);
		map.put("keyword", keyword);
		return sqlSession.selectOne("free_board.countArticle", map);
	}
	
	@Override
	public int recent_bno() throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("free_board.getBno");
	}
	
	@Override
	public void bdelete(int bno) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.delete("free_board.bdelete" ,bno);
	}
/*	
	// 08. 게시글 첨부파일 목록
	@Override
	public List<String> getAttach(int bno) {
		return sqlSession.selectList("board.getAttach", bno);
	}
	
	// 09. 게시글 첨부파일 삭제처리
	@Override
	public void deleteFile(String fullname) {
		sqlSession.delete("board.deleteAttach", fullname);
	}
	// 10. 게시글 첨부파일 업데이트 처리
	@Override
	public void updateAttach(String fullName, int bno) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("fullName", fullName);
		map.put("bno", bno);
		sqlSession.insert("board.updateAttach", map);
		
	}*/
}
