package kr.spring.tiles.board.gallery.model.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import kr.spring.tiles.board.gallery.model.dto.Gallery_fileVO;
import kr.spring.tiles.board.gallery.model.dto.Gallery_commentVO;
  
public interface Gallery_commentDAO {
	
	//댓글목록 가져오기
	public List<Gallery_commentVO> clread(int bno) throws Exception;
	
	//insert
	public void cinsert(Gallery_commentVO vo) throws Exception;
	
	//최근 insert 한 댓글 가져오기
	public Gallery_commentVO cread(int bno) throws Exception;
	
	//update
	public void cupdate(Gallery_commentVO cvo) throws Exception;
	
	//cno통해 댓글 가져오기
	public Gallery_commentVO curead(int cno) throws Exception;	
	
	public void cdelete(int cno) throws Exception;
}
