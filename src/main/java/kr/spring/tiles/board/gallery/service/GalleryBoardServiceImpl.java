package kr.spring.tiles.board.gallery.service;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.spring.tiles.board.gallery.controller.Gallery_BoardController;
import kr.spring.tiles.board.gallery.model.dao.Gallery_boardDAOImpl;
import kr.spring.tiles.board.gallery.model.dao.Gallery_commentDAOImpl;
import kr.spring.tiles.board.gallery.model.dao.Gallery_fileDAOImpl;
import kr.spring.tiles.board.gallery.model.dto.Gallery_boardVO;
import kr.spring.tiles.board.gallery.model.dto.Gallery_commentVO;
import kr.spring.tiles.board.gallery.model.dto.Gallery_fileVO;

// 현재 클래스를 스프링에서 관리하는 service bean으로 등록
@Service
public class GalleryBoardServiceImpl implements GalleryBoardService {
	
	private static final Logger logger = LoggerFactory.getLogger(Gallery_BoardController.class);

	// MemberDAOImpl 객체를 스프링에서 생성하여 주입시킴
	@Inject
	Gallery_boardDAOImpl galleryboardDao;
	
	
	@Inject
	Gallery_fileDAOImpl galleryfileDao;
	
	@Inject
	Gallery_commentDAOImpl gallerycommentDao;
	
	// 01. 게시글쓰기
	@Transactional // 트랜잭션 처리 메서드로 설정
	@Override
	public void create(Gallery_boardVO vo, Gallery_fileVO fvo) throws Exception {
		
		String title = vo.getSubject();
		// *태그문자 처리 (< ==> &lt; > ==> &gt;)
		// replace(A, B) A를 B로 변경
		
		title = title.replace("<", "&lt;");
		title = title.replace("<", "&gt;");
		
		// *공백문자 처리  
		title = title.replace("  ",	"&nbsp;&nbsp;");

		// *줄바꿈 문자처리
		vo.setSubject(title);
		
		// 게시물 등록
    	logger.info("GalleryBoardServiceImple에서 불러서 create 실행한다");
    	galleryboardDao.create(vo);
		
		//최근 게시글 번호 얻어오기
		int bno = galleryboardDao.recent_bno();
		
		// 게시물의 첨부파일 정보 등록
	    String[] files = fvo.getFile_name2(); // 첨부파일 배열
	    String[] ufiles = fvo.getUfile_name2(); // 첨부파일 업로드명 배열
	    
	    if(files == null) {
	    	logger.info("없어서 리턴한다");
	    	return; // 첨부파일이 없으면 메서드 종료

	    }	else {

	    // 첨부파일들의 정보를 tbl_attach 테이블에 insert
		    for(int i=0; i<files.length; i++){ 
		    	logger.info("GalleryService_files"+i+":"+files[i]);
		    	logger.info("GalleryService_ufiles"+i+":"+ufiles[i]);
		    	logger.info("GalleryService_bno:"+bno);
		    }
		    
		    for(int i=0; i<files.length; i++){ 
		    	galleryfileDao.addAttach(files[i], ufiles[i], bno);
		    }
	    }
	}
		// 02. 게시글 상세보기
	@Override
	public Gallery_boardVO read(int bno) throws Exception {
		return galleryboardDao.read(bno);
	}
	  
	@Override
	public List<Gallery_fileVO> fread(int bno) throws Exception {
		return galleryfileDao.fread(bno);
	} 
	
	@Override
	public List<Gallery_commentVO> getCommentList(int bno) throws Exception {
		return gallerycommentDao.clread(bno);
	}
	
	@Override
	public Gallery_commentVO getComment(int bno, Gallery_commentVO cvo) throws Exception {
		
		gallerycommentDao.cinsert(cvo);
		logger.info("getComment_bno : "+bno);
		return gallerycommentDao.cread(bno);
		
	}
	
	// 02-6 수정하고 댓글 가져오기
	@Override
	public Gallery_commentVO getUpComment(int cno, Gallery_commentVO cvo) throws Exception {
		
		gallerycommentDao.cupdate(cvo);
/*		logger.info("getUpComment_cno : "+cno);*/
		return gallerycommentDao.curead(cno);
		
	}
	
	// 02-7 댓글 삭제
	@Override
	public void getDelComment(int cno) throws Exception {
		gallerycommentDao.cdelete(cno);
	}

	// 03. 게시글 수정
	@Transactional
	@Override
	public void update(Gallery_boardVO vo) throws Exception {
		galleryboardDao.update(vo);

		/*		// 첨부파일들의 정보를 tbl_attach 테이블에 insert
		for(String name : files){
			galleryboardDao.updateAttach(name, vo.getBno());
		}*/
	}
		
	/*// 04. 게시글 삭제
	@Override
	public void delete(int bno) throws Exception {
		boardDao.delete(bno);
	}*/
	
	// 05. 게시글 전체 목록
	@Override
	public List<Gallery_fileVO> listAll(int page_cnt) throws Exception {
		return galleryfileDao.flistAll(page_cnt);
	}
	
	// 06. 게시글 조회수 증가
	@Override
	public void increaseViewcnt(int no, HttpSession session) throws Exception {

		galleryboardDao.increaseViewcnt(no);
			
	}
	
	// 07. 게시글 레코드 갯수
	@Override
	public int countArticle() throws Exception {
		return galleryboardDao.countArticle();
	}
	
	@Override
	public int cnt_img(int bno) throws Exception {
		return galleryfileDao.cnt_img(bno);
	}
	@Override
	public int cnt_nimg(int bno) throws Exception {
		return galleryfileDao.cnt_nimg(bno);

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
		galleryfileDao.deleteFile(fullname);
	}
	
	@Override
	public void delete(int bno) throws Exception {
		// TODO Auto-generated method stub
		//파일 삭제 시 게시판, 파일, 댓글 삭제
		galleryboardDao.bdelete(bno);
		galleryfileDao.bdeleteFile(bno); 
		gallerycommentDao.bdeleteCmt(bno);
	}
}
