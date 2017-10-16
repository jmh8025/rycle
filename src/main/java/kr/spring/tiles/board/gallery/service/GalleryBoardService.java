package kr.spring.tiles.board.gallery.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import kr.spring.tiles.board.gallery.model.dto.Gallery_boardVO;
import kr.spring.tiles.board.gallery.model.dto.Gallery_commentVO;
import kr.spring.tiles.board.gallery.model.dto.Gallery_fileVO;

public interface GalleryBoardService {

	// 01. 게시글 작성
	public void create(Gallery_boardVO vo, Gallery_fileVO fvo) throws Exception;
	
	// 02. 게시글 상세보기
	public Gallery_boardVO read(int bno) throws Exception;
	// 02-3 파일 가져오기
	public List<Gallery_fileVO> fread(int bno) throws Exception;
	// 02-4 댓글 가져오기
	public List<Gallery_commentVO> getCommentList(int bno) throws Exception;
	// 02-5 넣고 댓글 가져오기
	public Gallery_commentVO getComment(int bno, Gallery_commentVO cvo) throws Exception;
	// 02-6 수정하고 댓글 가져오기
	public Gallery_commentVO getUpComment(int cno, Gallery_commentVO cvo) throws Exception;
	// 02-7 댓글 삭제
	public void getDelComment(int cno) throws Exception;
	
	// 03. 게시글 수정
	public void update(Gallery_boardVO vo, Gallery_fileVO fvo) throws Exception;
	// 03-2 file 이미지 갯수
	public int cnt_img(int bno) throws Exception;
	// 03-3 file 이미지 아닌것 갯수
	public int cnt_nimg(int bno) throws Exception;
	/*// 04. 게시글 삭제
	public void delete(int bno) throws Exception;*/
	// 05. 게시글 전체 목록
	public List<Gallery_fileVO> listAll(int page_cnt) throws Exception;
// 06. 게시글 조회
	public void increaseViewcnt(int no, HttpSession session) throws Exception;
	/*		// 07. 게시글 레코드 갯수
*/	public int countArticle() throws Exception;
	// 09. 게시글 첨부파일 삭제
	public void deleteFile(String fullname) throws Exception;
	//10. 게시판 삭제
	public void delete(int bno) throws Exception;
}
