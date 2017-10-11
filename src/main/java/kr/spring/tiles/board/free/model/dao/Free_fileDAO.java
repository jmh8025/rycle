package kr.spring.tiles.board.free.model.dao;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import kr.spring.tiles.board.free.model.dto.Fb_categoryVO;
import kr.spring.tiles.board.free.model.dto.Free_fileVO;
  
public interface Free_fileDAO {
	
	//파일 업로드
	public void addAttach(String file, String ufile, int bno) throws Exception;
	
	//해당 게시판 파일 목록
	public List<Free_fileVO> fread(int bno) throws Exception;
	
	//파일 목록 중 img인것 갯수
	public int cnt_img(int bno) throws Exception;
	
	//파일 목록 중 img 아닌 것 갯수
	public int cnt_nimg(int bno) throws Exception;
	
	//파일이름을 체크하여 삭제
	public void deleteFile(String ufile_name) throws Exception;
	
	//파일 게시판 번호 체크하여 삭제
	public void bdeleteFile(int bno) throws Exception;
	
	
}
