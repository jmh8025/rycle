package kr.spring.tiles.board.gallery.model.dao;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import kr.spring.tiles.board.gallery.model.dto.Gallery_fileVO;
  
public interface Gallery_fileDAO {
	
	//목록 뿌리기
	public List<Gallery_fileVO> flistAll(int page_cnt) throws Exception;
	//파일 업로드
	public void addAttach(String file, String ufile, int bno) throws Exception;
	
	//해당 게시판 파일 목록
	public List<Gallery_fileVO> fread(int bno) throws Exception;
	
	//파일 목록 중 img인것 갯수
	public int cnt_img(int bno) throws Exception;
	
	//파일 목록 중 img 아닌 것 갯수
	public int cnt_nimg(int bno) throws Exception;
	
	//파일 삭제
	public void deleteFile(String ufile_name) throws Exception;
}
