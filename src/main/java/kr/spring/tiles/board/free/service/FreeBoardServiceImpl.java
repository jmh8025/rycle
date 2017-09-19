package kr.spring.tiles.board.free.service;

import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.spring.tiles.board.free.controller.Free_BoardController;
import kr.spring.tiles.board.free.model.dao.Fb_categoryDAOImpl;
import kr.spring.tiles.board.free.model.dao.Free_boardDAOImpl;
import kr.spring.tiles.board.free.model.dto.Fb_categoryVO;
import kr.spring.tiles.board.free.model.dto.Free_boardVO;

// 현재 클래스를 스프링에서 관리하는 service bean으로 등록
@Service
public class FreeBoardServiceImpl implements FreeBoardService {
	
	private static final Logger logger = LoggerFactory.getLogger(Free_BoardController.class);

	// MemberDAOImpl 객체를 스프링에서 생성하여 주입시킴
	@Inject
	Free_boardDAOImpl freeboardDao;
	
	@Inject
	Fb_categoryDAOImpl fbcategoryDao;
	
	// 카테고리 가져오기
	public List<Fb_categoryVO> listAll() throws Exception{
	    return fbcategoryDao.listAll();
	}
	
	// 01. 게시글쓰기
	@Transactional // 트랜잭션 처리 메서드로 설정
	@Override
	public void create(Free_boardVO vo) throws Exception {
		
		String cate_chk = vo.getCate_chk();
		String title = vo.getSubject();
		String content = vo.getContent();
		// *태그문자 처리 (< ==> &lt; > ==> &gt;)
		// replace(A, B) A를 B로 변경
		
		title = title.replace("<", "&lt;");
		title = title.replace("<", "&gt;");
		content = content.replace("<", "&lt;");
		content = content.replace("<", "&gt;");
		
		// *공백문자 처리  
		title = title.replace("  ",	"&nbsp;&nbsp;");

		// *줄바꿈 문자처리
		content = content.replace("\n", "<br>");
		vo.setSubject(title);
		vo.setContent(content);
		vo.setContent(content);
		
		// 게시물 등록
		freeboardDao.create(vo);
		// 게시물의 첨부파일 정보 등록
		
	}
	/*	// 02. 게시글 상세보기
	@Override
	public Free_boardVO read(int bno) throws Exception {
		return boardDao.read(bno);
	}
	// 03. 게시글 수정
	@Transactional
	@Override
	public void update(Free_boardVO vo) throws Exception {
		boardDao.update(vo);
		// 첨부파일 정보 등록
		String[] files = vo.getFiles(); // 첨부파일 배열
		// 첨부파일이 없으면 종료
		if(files == null) return;
		// 첨부파일들의 정보를 tbl_attach 테이블에 insert
		for(String name : files){
			boardDao.updateAttach(name, vo.getBno());
		}
	}
	// 04. 게시글 삭제
	@Override
	public void delete(int bno) throws Exception {
		boardDao.delete(bno);
	}*/
	
	// 05. 게시글 전체 목록
	@Override
	public List<Free_boardVO> listAll(int start, int end, String searchOption, String keyword) throws Exception {
		 logger.info("키워드값333"+keyword);
		return freeboardDao.listAll(start, end, searchOption, keyword);
	}
	
	// 06. 게시글 조회수 증가
	@Override
	public void increaseViewcnt(int no, HttpSession session) throws Exception {

		freeboardDao.increaseViewcnt(no);
			
	}
	
	// 07. 게시글 레코드 갯수
	@Override
	public int countArticle(String searchOption, String keyword) throws Exception {
		return freeboardDao.countArticle(searchOption, keyword);
	}
/*	
	// 08. 게시글의 첨부파일 목록
	@Override
	public List<String> getAttach(int bno) {
		return boardDao.getAttach(bno);
	}
	// 09. 게시글의 첨부파일 삭제 처리
	@Override
	public void deleteFile(String fullname) {
		boardDao.deleteFile(fullname);
	}*/

}
