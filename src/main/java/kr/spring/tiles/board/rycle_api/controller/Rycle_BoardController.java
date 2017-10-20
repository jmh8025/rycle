
package kr.spring.tiles.board.rycle_api.controller;

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
import kr.spring.tiles.board.free.model.dto.Free_boardVO;
import kr.spring.tiles.board.free.model.dto.Free_fileVO;
import kr.spring.tiles.board.free.model.dto.Fb_categoryVO;
import kr.spring.tiles.board.free.model.dto.Free_commentVO;

import kr.spring.tiles.board.free.service.FreeBoardService;
import kr.spring.tiles.board.gallery.model.dto.Gallery_fileVO;

@Controller // 현재의 클래스를 controller bean에 등록시킴
public class Rycle_BoardController {

	private static final Logger logger = LoggerFactory.getLogger(Rycle_BoardController.class);

	// FreeBoardService 객체를 스프링에서 생성하여 주입시킴
	@Inject
	FreeBoardService freeBoardService;

	// 01 회원 목록
	// url pattern mapping
	@RequestMapping("/board/rycle_api_list.do")
	public String free_Board_List(Model model) throws Exception {

		return "board/rycle_api/rycle_api_list";
	}


}