package kr.spring.tiles.board.free.service;

import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.spring.tiles.board.free.controller.Free_BoardController;
import kr.spring.tiles.board.free.model.dao.Free_boardDAOImpl;
import kr.spring.tiles.board.free.model.dto.Free_boardVO;

// 현재 클래스를 스프링에서 관리하는 service bean으로 등록
@Service
public class FreeBoardServiceImpl implements FreeBoardService {
	
	// MemberDAOImpl 객체를 스프링에서 생성하여 주입시킴
	@Inject
	Free_boardDAOImpl freeboardDao;
	private static final Logger logger = LoggerFactory.getLogger(Free_BoardController.class);
	// 01. 전체 회원 목록 조회

	
	// 01. 게시글쓰기
	@Transactional // 트랜잭션 처리 메서드로 설정
	@Override
	public void create(Free_boardVO vo) throws Exception {
/*		String title = vo.getTitle();
		String content = vo.getContent();
		String writer = vo.getWriter();
		// *태그문자 처리 (< ==> &lt; > ==> &gt;)
		// replace(A, B) A를 B로 변경
		title = title.replace("<", "&lt;");
		title = title.replace("<", "&gt;");
		writer = writer.replace("<", "&lt;");
		writer = writer.replace("<", "&gt;");
		// *공백문자 처리  
		title = title.replace("  ",	"&nbsp;&nbsp;");
		writer = writer.replace("  ",	"&nbsp;&nbsp;");
		// *줄바꿈 문자처리
		content = content.replace("\n", "<br>");
		vo.setTitle(title);
		vo.setContent(content);
		vo.setWriter(writer);
		// 게시물 등록
		boardDao.create(vo);
		// 게시물의 첨부파일 정보 등록
		String[] files = vo.getFiles(); // 첨부파일 배열
		if(files == null) return; // 첨부파일이 없으면 메서드 종료
		// 첨부파일들의 정보를 tbl_attach 테이블에 insert
		for(String name : files){ 
			boardDao.addAttach(name);
		}*/
		
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
	
/*	// 06. 게시글 조회수 증가
	@Override
	public void increaseViewcnt(int bno, HttpSession session) throws Exception {
		long update_time = 0;
		// 세션에 저장된 조회시간 검색
		// 최초로 조회할 경우 세션에 저장된 값이 없기 때문에 if문은 실행X
		if(session.getAttribute("update_time_"+bno) != null){
								// 세션에서 읽어오기
			update_time = (long)session.getAttribute("update_time_"+bno);
		}
		// 시스템의 현재시간을 current_time에 저장
		long current_time = System.currentTimeMillis();
		// 일정시간이 경과 후 조회수 증가 처리 24*60*60*1000(24시간)
		 // 시스템현재시간 - 열람시간 > 일정시간(조회수 증가가 가능하도록 지정한 시간)
		if(current_time - update_time > 5*1000){
			boardDao.increaseViewcnt(bno);
			// 세션에 시간을 저장 : "update_time_"+bno는 다른변수와 중복되지 않게 명명한 것
			session.setAttribute("update_time_"+bno, current_time);
			
		}
	}*/
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
