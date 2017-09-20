package kr.spring.tiles.board.free.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import kr.spring.tiles.board.free.model.dto.Fb_categoryVO;
import kr.spring.tiles.board.free.model.dto.Free_boardVO;

public interface FreeBoardService {
	
	// 카테고리 가져오기
	public List<Fb_categoryVO> listAll() throws Exception;
	
	// 01. 게시글 작성
	public void create(Free_boardVO vo) throws Exception;
	/*	// 02. 게시글 상세보기
	public Free_boardVO read(int bno) throws Exception;
	// 03. 게시글 수정
	public void update(Free_boardVO vo) throws Exception;
	// 04. 게시글 삭제
	public void delete(int bno) throws Exception;*/
	// 05. 게시글 전체 목록
	public List<Free_boardVO> listAll(int start, int end, String searchOption, String keyword) throws Exception;
// 06. 게시글 조회
	public void increaseViewcnt(int no, HttpSession session) throws Exception;
	/*		// 07. 게시글 레코드 갯수
*/	public int countArticle(String searchOption, String keyword) throws Exception;
/*	// 08. 게시글 첨부파일 목록
	public List<String> getAttach(int bno);
	// 09. 게시글 첨부파일 삭제
	public void deleteFile(String fullname);*/
	
}
