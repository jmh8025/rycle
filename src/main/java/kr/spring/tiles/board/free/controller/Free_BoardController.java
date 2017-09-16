package kr.spring.tiles.board.free.controller;

import java.util.List;


import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.spring.tiles.board.free.model.dto.Free_boardVO;
import kr.spring.tiles.board.free.service.FreeBoardService;

@Controller // 현재의 클래스를 controller bean에 등록시킴
public class Free_BoardController {
	
	private static final Logger logger = LoggerFactory.getLogger(Free_BoardController.class);
	
	// MemberService 객체를 스프링에서 생성하여 주입시킴
	@Inject
	FreeBoardService boardService;
	
	// 01 회원 목록
	// url pattern mapping
	@RequestMapping("/board/free_board_list.do")
	public String free_Board_List(Model model){
	//	List<Free_boardVO> list = memberService.memberList();
	//	model.addAttribute("list", list);
		
    //    List<Free_boardVO> list = boardService.boardList();
        
    //	model.addAttribute("list", list);
    	
		return "free_board_list";
	}
	
}
