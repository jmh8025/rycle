package kr.spring.tiles.board.free.model.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import kr.spring.tiles.board.free.model.dto.Fb_categoryVO;
import kr.spring.tiles.board.free.model.dto.Free_fileVO;
import kr.spring.tiles.board.free.model.dto.Free_commentVO;
  
public interface Free_commentDAO {
	
	//댓글목록 가져오기
	public List<Free_commentVO> clread(int bno) throws Exception;
	
	//insert
	public void cinsert(Free_commentVO vo) throws Exception;
	
	//최근 insert 한 댓글 가져오기
	public Free_commentVO cread(int bno) throws Exception;

}
