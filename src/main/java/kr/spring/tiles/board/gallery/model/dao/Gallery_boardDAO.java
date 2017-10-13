package kr.spring.tiles.board.gallery.model.dao;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import kr.spring.tiles.board.gallery.model.dto.Gallery_boardVO;
import kr.spring.tiles.board.gallery.model.dto.Gallery_fileVO;
import kr.spring.tiles.board.gallery.service.GalleryBoardService;
  
public interface Gallery_boardDAO {
	
	// 01. 게시글 작성
	public void create(Gallery_boardVO vo) throws Exception;
	// 02. 게시글 상세보기
	public Gallery_boardVO read(int bno) throws Exception;
	// 03. 게시글 수정
	public void update(Gallery_boardVO vo) throws Exception;
	/*// 04. 게시글 삭제
	public void delete(int bno) throws Exception;*/
	// 05. 게시글 전체 목록
	public List<Gallery_boardVO> listAll(String searchOption, String keyword) throws Exception;
	
	// 06. 게시글 조회 증가
	public void increaseViewcnt(int no) throws Exception;
	// 07. 게시글 레코드 갯수 메서드 추가
	public int countArticle() throws Exception;
	
	// 08. 최근 게시글 번호 얻어오기
	public int recent_bno() throws Exception;
	
	// 09 게시판 삭제
	public void bdelete(int bno) throws Exception;

	
}
