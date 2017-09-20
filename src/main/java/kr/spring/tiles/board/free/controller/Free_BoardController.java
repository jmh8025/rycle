
package kr.spring.tiles.board.free.controller;

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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.spring.tiles.board.util.BoardPager;
import kr.spring.tiles.board.free.model.dto.Free_boardVO;
import kr.spring.tiles.board.free.model.dto.Fb_categoryVO;

import kr.spring.tiles.board.free.service.FreeBoardService;

@Controller // 현재의 클래스를 controller bean에 등록시킴
public class Free_BoardController {
	
	private static final Logger logger = LoggerFactory.getLogger(Free_BoardController.class);
	
	// MemberService 객체를 스프링에서 생성하여 주입시킴
	@Inject
	FreeBoardService freeBoardService;
	
    // xml에 설정된 리소스 참조
    // bean의 id가 uploadPath인 태그를 참조
    @Resource(name="uploadPath")
    String uploadPath;
	
	// 01 회원 목록
	// url pattern mapping
	@RequestMapping("/board/free_board_list.do")
	public String free_Board_List(Model model, 
			@RequestParam(value="searchOption", defaultValue="all") String searchOption,
            @RequestParam(value="searchKeyword", defaultValue="") String keyword,
            @RequestParam(value="curPage", defaultValue="1") int curPage) throws Exception{

			logger.info("키워드값"+keyword);
        

		//게시판 목록s
/*		List<Free_boardVO> list = boardService.boardList();
  		model.addAttribute("list", list);*/
  		
	    // 레코드의 갯수 계산
	    int count = freeBoardService.countArticle(searchOption, keyword);
	
	    // 페이지 나누기 관련 처리
	    BoardPager boardPager = new BoardPager(count, curPage);
	    int start = boardPager.getPageBegin();
	    int end = boardPager.getPageEnd();
	    int PAGE_SCALE = boardPager.getPAGE_SCALE();
	    logger.info("키워드값2"+keyword);
	    List<Free_boardVO> list = freeBoardService.listAll(start, end, searchOption, keyword);
  		
	    // 데이터를 맵에 저장
	    Map<String, Object> map = new HashMap<String, Object>();
	    map.put("list", list); // list
	    map.put("count", count); // 레코드의 갯수
	    map.put("searchOption", searchOption); // 검색옵션
	    map.put("keyword", keyword); // 검색키워드
	    map.put("boardPager", boardPager);
	    map.put("PAGE_SCALE", PAGE_SCALE); //페이지당 게시물 수 	    
	    map.put("board_name", "free_board"); //게시물명
	    model.addAttribute("map", map);
	    
		return "free_board_list";
	}
	
	
	@RequestMapping("/board/free_board_write.do")
	public String free_Board_Write(Model model) throws Exception{
		
		List<Fb_categoryVO> fblist = freeBoardService.listAll();
		
		Map<String, Object> fbmap = new HashMap<String, Object>();
		fbmap.put("fblist", fblist);
		
	    model.addAttribute("fbmap", fbmap);
	    
		return "free_board_write";
	}
  
	
	//게시글 작성처리
	@RequestMapping("/board/free_board_insert.do")
	public String insert(@ModelAttribute Free_boardVO freeboard , HttpSession session) throws Exception{
		
		// session에 저장된 userId를 writer에 저장
/*		String id = (String) session.getAttribute("userId");
		String writer = (String) session.getAttribute("userWriter");*/		

		// vo에 writer를 세팅
		freeboard.setId("slr2");
		freeboard.setWriter("홍길동");
		
		freeBoardService.create(freeboard);
		
		return "redirect:/board/free_board_list.do";
	}
}
