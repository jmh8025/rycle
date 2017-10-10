package kr.spring.tiles.board.gallery.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import org.springframework.web.multipart.MultipartHttpServletRequest; //파일 업로드
   
import kr.spring.tiles.board.util.BoardPager;
import kr.spring.tiles.board.gallery.model.dto.Gallery_boardVO;
import kr.spring.tiles.board.gallery.model.dto.Gallery_fileVO;
import kr.spring.tiles.board.gallery.model.dto.Gallery_commentVO;

import kr.spring.tiles.board.gallery.service.GalleryBoardService;

@Controller // 현재의 클래스를 controller bean에 등록시킴
public class Gallery_BoardController {
   
   private static final Logger logger = LoggerFactory.getLogger(Gallery_BoardController.class);
   
   // GalleryBoardService 객체를 스프링에서 생성하여 주입시킴
   @Inject
   GalleryBoardService galleryBoardService;
   
   // 01 회원 목록
   // url pattern mapping
   @RequestMapping("/board/gallery_board_list.do")
   public String gallery_Board_List(Model model, 
         @RequestParam(value="searchOption", defaultValue="all") String searchOption,
            @RequestParam(value="searchKeyword", defaultValue="") String keyword,
            @RequestParam(value="curPage", defaultValue="1") int curPage) throws Exception{

       logger.info("검색값"+searchOption);
       logger.info("키워드값"+keyword);
        
        
       // 레코드의 갯수 계산
       int count = galleryBoardService.countArticle(searchOption, keyword);
   
       // 페이지 나누기 관련 처리
       BoardPager boardPager = new BoardPager(count, curPage);
       int start = boardPager.getPageBegin();
       int end = boardPager.getPageEnd();
       int PAGE_SCALE = boardPager.getPAGE_SCALE();
       logger.info("키워드값2"+keyword);
       List<Gallery_boardVO> list = galleryBoardService.listAll(start, end, searchOption, keyword);
        
       // 데이터를 맵에 저장
       Map<String, Object> map = new HashMap<String, Object>();
       map.put("list", list); // list
       map.put("count", count); // 레코드의 갯수
       map.put("searchOption", searchOption); // 검색옵션
       map.put("keyword", keyword); // 검색키워드
       map.put("boardPager", boardPager);
       map.put("PAGE_SCALE", PAGE_SCALE); //페이지당 게시물 수        
       map.put("board_name", "gallery_board"); //게시물명
       model.addAttribute("map", map);
       
      return "board/gallery/gallery_board_list";
   }
   
   
   @RequestMapping("/board/gallery_board_write.do")
   public String gallery_Board_Write(Model model) throws Exception{
            
      Map<String, Object> fbmap = new HashMap<String, Object>();      
       
      return "board/gallery/gallery_board_write";
   }
  
   
   //게시글 작성처리
   @RequestMapping(value = "/board/gallery_board_insert.do", method= RequestMethod.POST )
   public String gallery_Board_insert( @ModelAttribute Gallery_boardVO galleryboard, @ModelAttribute Gallery_fileVO galleryfile , HttpSession session) throws Exception{


      // session에 저장된 userId를 writer에 저장
/*      String id = (String) session.getAttribute("userId");
      String writer = (String) session.getAttribute("userWriter");*/      
      
      // vo에 writer를 세팅
	  galleryboard.setId("slr2"); 
	  galleryboard.setWriter("홍길동");


      logger.info("galleryboard 값 체크 [ "
            + "galleryboard.getId()"+galleryboard.getId()
            + "galleryboard.Writer="+galleryboard.getWriter()+", subject="+galleryboard.getSubject() 
            );

        //파일 e
        
      galleryBoardService.create(galleryboard, galleryfile);
      
      return "redirect:/board/gallery_board_list.do";
   }
   
   // 03. 게시글 상세내용 조회, 게시글 조회수 증가 처리
   // @RequestParam : get/post방식으로 전달된 변수 1개
   // HttpSession 세션객체
   @RequestMapping("/board/gallery_board_view.do")
   public String gallery_Board_View(@RequestParam int bno, HttpSession session, Model model) throws Exception{
       // 조회수 증가 처리
	   galleryBoardService.increaseViewcnt(bno, session);
	   
       // 데이터를 맵에 저장
       Map<String, Object> map = new HashMap<String, Object>();
       map.put("dto",  galleryBoardService.read(bno)); // view     
              
       map.put("fdto", galleryBoardService.fread(bno));
       
       //댓글
       // 게시글 번호를 이용하여 해당 글에 있는 댓글 목록을 가져온다.
       map.put("cmtdto", galleryBoardService.getCommentList(bno));

       model.addAttribute("map", map);
       
       return "board/gallery/gallery_board_view";
   }
   
   
   // 04. 게시글 수정
   // 폼에서 입력한 내용들은 @ModelAttribute BoardVO vo로 전달됨
   @RequestMapping(value="/board/gallery_board_update.do", method=RequestMethod.GET)
   public String gallery_Board_Update(int bno, Model model) throws Exception{
	   

       // 데이터를 맵에 저장
       Map<String, Object> map = new HashMap<String, Object>();
       map.put("dto",  galleryBoardService.read(bno)); // view    
       map.put("fdto", galleryBoardService.fread(bno));
       
       map.put("uheight", galleryBoardService.cnt_img(bno)*100 + galleryBoardService.cnt_nimg(bno)*20 + 60);
       
       model.addAttribute("map", map);
       
       return "board/gallery/gallery_board_update";
   }
   
   
   // 04. 게시글 수정
   // 폼에서 입력한 내용들은 @ModelAttribute BoardVO vo로 전달됨
   @RequestMapping(value="/board/gallery_board_update.do", method=RequestMethod.POST)
   
   public String gallery_Board_Update(@RequestParam int bno, @ModelAttribute Gallery_boardVO galleryboard, HttpSession session, Model model) throws Exception{
      
	   
	   logger.info("gallery_Board_Update실행[ bno="+bno+"]");
	      // session에 저장된 userId를 writer에 저장
	   /*      String id = (String) session.getAttribute("userId");
	         String writer = (String) session.getAttribute("userWriter");*/      
	         
	         // vo에 writer를 세팅
	   galleryboard.setId("slr2_m"); 
	   galleryboard.setWriter("홍길동_m");
	     
	   galleryboard.setNo(bno); 
	      logger.info("galleryboard 값 체크 [ "
	              + "galleryboard.getId()"+galleryboard.getId()
	              + "galleryboard.Writer="+galleryboard.getWriter()+", subject="+galleryboard.getSubject() 
	              );

	          //파일 e
	          
	      galleryBoardService.update(galleryboard);

	      gallery_Board_View(bno, session, model);
	        
	        return "board/gallery/gallery_board_view";
   }

   
   
}