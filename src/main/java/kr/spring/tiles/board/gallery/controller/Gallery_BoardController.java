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
import org.springframework.web.bind.annotation.ResponseBody;
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
	public String gallery_Board_List(Model model, @RequestParam(value = "gpage", defaultValue = "0") int gpage)
			throws Exception {

		logger.info("Gallery_boardController_page" + gpage);
		// 레코드의 갯수 계산
		int count = galleryBoardService.countArticle();

		int page_cnt = gpage * 10;

		List<Gallery_fileVO> list = galleryBoardService.listAll(page_cnt);
		// 데이터를 맵에 저장
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("list", list); // list
		map.put("count", count); // 레코드의 갯수

		map.put("board_name", "gallery_board"); // 게시물명

		model.addAttribute("map", map);

		return "board/gallery/gallery_board_list";

	}

	@RequestMapping(value = "/board/gallery_board_list_infinite.do", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> gallery_Board_List2(Model model,
			@RequestParam(value = "gpage", defaultValue = "0") int gpage) throws Exception {

		logger.info("Gallery_boardController_page" + gpage);
		// 레코드의 갯수 계산
		int count = galleryBoardService.countArticle();

		int page_cnt = gpage * 20;

		List<Gallery_fileVO> list = galleryBoardService.listAll(page_cnt);
		// 데이터를 맵에 저장
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list); // list

		return map;
	}

	@RequestMapping("/board/gallery_board_write.do")
	public String gallery_Board_Write(Model model) throws Exception {

		Map<String, Object> fbmap = new HashMap<String, Object>();

		return "board/gallery/gallery_board_write";
	}

	// 게시글 작성처리
	@RequestMapping(value = "/board/gallery_board_insert.do", method = RequestMethod.POST)
	public String gallery_Board_insert(@ModelAttribute Gallery_boardVO galleryboard,
			@ModelAttribute Gallery_fileVO galleryfile, HttpSession session) throws Exception {

		// session에 저장된 userId를 writer에 저장
		String session_id = (String) session.getAttribute("id");
		String session_writer = (String) session.getAttribute("name");

		// vo에 writer를 세팅
		galleryboard.setId(session_id);
		galleryboard.setWriter(session_writer);

		logger.info("galleryboard 값 체크 [ " + "galleryboard.getId()" + galleryboard.getId() + "galleryboard.Writer="
				+ galleryboard.getWriter() + ", subject=" + galleryboard.getSubject());

		// 파일 e

		galleryBoardService.create(galleryboard, galleryfile);

		return "redirect:/board/gallery_board_list.do";
	}

	// 03. 게시글 상세내용 조회, 게시글 조회수 증가 처리
	// @RequestParam : get/post방식으로 전달된 변수 1개
	// HttpSession 세션객체
	@RequestMapping("/board/gallery_board_view.do")
	public String gallery_Board_View(@RequestParam int bno, HttpSession session, Model model) throws Exception {
		// 조회수 증가 처리
		galleryBoardService.increaseViewcnt(bno, session);

		// 데이터를 맵에 저장
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("dto", galleryBoardService.read(bno)); // view

		map.put("fdto", galleryBoardService.fread(bno));

		// 댓글
		// 게시글 번호를 이용하여 해당 글에 있는 댓글 목록을 가져온다.
		map.put("cmtdto", galleryBoardService.getCommentList(bno));

		model.addAttribute("map", map);

		return "board/gallery/gallery_board_view";
	}

	// 04. 게시글 수정
	@RequestMapping(value = "/board/gallery_board_update.do", method = RequestMethod.GET)
	public String gallery_Board_Update(int bno, Model model) throws Exception {

		// 데이터를 맵에 저장
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("dto", galleryBoardService.read(bno)); // view
		map.put("fdto", galleryBoardService.fread(bno));
		map.put("uheight", galleryBoardService.cnt_img(bno) * 180 + galleryBoardService.cnt_nimg(bno) * 23 + 60);

		model.addAttribute("map", map);

		return "board/gallery/gallery_board_update";
	}

	// 04. 게시글 수정
	@RequestMapping(value = "/board/gallery_board_update.do", method = RequestMethod.POST)

	public String gallery_Board_Update(@RequestParam int bno, @ModelAttribute Gallery_boardVO galleryboard,
			@ModelAttribute Gallery_fileVO galleryfile, HttpSession session, Model model) throws Exception {

		logger.info("gallery_Board_Update실행[ bno=" + bno + "]");

		String session_id = (String) session.getAttribute("id");
		String session_writer = (String) session.getAttribute("name");

		// vo에 writer를 세팅
		galleryboard.setId(session_id);
		galleryboard.setWriter(session_writer);

		galleryboard.setNo(bno);
		logger.info("galleryboard 값 체크 [ " + "galleryboard.getId()" + galleryboard.getId() + "galleryboard.Writer="
				+ galleryboard.getWriter() + ", subject=" + galleryboard.getSubject());

		galleryBoardService.update(galleryboard, galleryfile);

		gallery_Board_View(bno, session, model);

		return "board/gallery/gallery_board_view";
	}

	// 05. 게시글 삭제
	@RequestMapping(value = "/board/gallery_board_delete.do", method = RequestMethod.GET)
	public String free_Board_Delete(@RequestParam int bno) throws Exception {
		galleryBoardService.delete(bno);
		return "redirect:/board/gallery_board_list.do";
	}

}