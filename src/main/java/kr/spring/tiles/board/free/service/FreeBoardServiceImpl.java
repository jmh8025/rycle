package kr.spring.tiles.board.free.service;

import java.util.ArrayList;
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
import kr.spring.tiles.board.free.model.dao.Free_commentDAOImpl;
import kr.spring.tiles.board.free.model.dao.Free_fileDAOImpl;
import kr.spring.tiles.board.free.model.dto.Fb_categoryVO;
import kr.spring.tiles.board.free.model.dto.Free_boardVO;
import kr.spring.tiles.board.free.model.dto.Free_commentVO;
import kr.spring.tiles.board.free.model.dto.Free_fileVO;

// 현재 클래스를 스프링에서 관리하는 service bean으로 등록
@Service
public class FreeBoardServiceImpl implements FreeBoardService {
	
	private static final Logger logger = LoggerFactory.getLogger(Free_BoardController.class);

	// MemberDAOImpl 객체를 스프링에서 생성하여 주입시킴
	@Inject
	Free_boardDAOImpl freeboardDao;
	
	@Inject
	Fb_categoryDAOImpl fbcategoryDao;
	
	@Inject
	Free_fileDAOImpl freefileDao;
	
	@Inject
	Free_commentDAOImpl freecommentDao;
	
	// 카테고리 가져오기
	public List<Fb_categoryVO> listAll() throws Exception{
	    return fbcategoryDao.listAll();
	}
	
	// 01. 게시글쓰기
	@Transactional // 트랜잭션 처리 메서드로 설정
	@Override
	public void create(Free_boardVO vo, Free_fileVO fvo) throws Exception {
		
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
    	logger.info("FreeBoardServiceImple에서 불러서 create 실행한다");
		freeboardDao.create(vo);
		
		//최근 게시글 번호 얻어오기
		int bno = freeboardDao.recent_bno();
		
		// 게시물의 첨부파일 정보 등록
	    String[] files = fvo.getFile_name2(); // 첨부파일 배열
	    String[] ufiles = fvo.getUfile_name2(); // 첨부파일 업로드명 배열
	    
	    if(files == null) {
	    	logger.info("없어서 리턴한다");
	    	return; // 첨부파일이 없으면 메서드 종료

	    }	else {

	    // 첨부파일들의 정보를 tbl_attach 테이블에 insert
		    for(int i=0; i<files.length; i++){ 
		    	logger.info("FreeService_files"+i+":"+files[i]);
		    	logger.info("FreeService_ufiles"+i+":"+ufiles[i]);
		    	logger.info("FreeService_bno:"+bno);
		    }
		    
		    for(int i=0; i<files.length; i++){ 
		    	freefileDao.addAttach(files[i], ufiles[i], bno);
		    }
	    }
	}
		// 02. 게시글 상세보기
	@Override
	public Free_boardVO read(int bno) throws Exception {
		return freeboardDao.read(bno);
	}
	
	@Override
	public Fb_categoryVO cateName(String cate_chk) throws Exception {
		// TODO Auto-generated method stub
		return fbcategoryDao.cateName(cate_chk);
	} 
	  
	@Override
	public List<Free_fileVO> fread(int bno) throws Exception {
		return freefileDao.fread(bno);
	} 
	
	@Override
	public List<Free_commentVO> getCommentList(int bno) throws Exception {
		return freecommentDao.clread(bno);
	}
	
	@Override
	public Free_commentVO getComment(int bno, Free_commentVO cvo) throws Exception {

		
		freecommentDao.cinsert(cvo);
		
		logger.info("getComment_bno : "+bno);
		
		return freecommentDao.cread(bno);
		
	}

	// 03. 게시글 수정
	@Transactional
	@Override
	public void update(Free_boardVO vo) throws Exception {
		freeboardDao.update(vo);

		/*		// 첨부파일들의 정보를 tbl_attach 테이블에 insert
		for(String name : files){
			freeboardDao.updateAttach(name, vo.getBno());
		}*/
	}
	
	/*// 04. 게시글 삭제
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
	
	@Override
	public int cnt_img(int bno) throws Exception {
		return freefileDao.cnt_img(bno);
	}
	@Override
	public int cnt_nimg(int bno) throws Exception {
		return freefileDao.cnt_nimg(bno);

	}
/*	
	// 08. 게시글의 첨부파일 목록
	@Override
	public List<String> getAttach(int bno) {
		return boardDao.getAttach(bno);
	}*/
	
	// 09. 게시글의 첨부파일 삭제 처리
	@Override
	public void deleteFile(String fullname) throws Exception{
		// TODO Auto-generated method stub
		freefileDao.deleteFile(fullname);
	}

}
