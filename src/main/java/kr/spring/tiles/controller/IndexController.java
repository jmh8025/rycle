package kr.spring.tiles.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import kr.spring.tiles.board.free.model.dto.Free_boardVO;
import kr.spring.tiles.board.free.service.FreeBoardService;
import kr.spring.tiles.util.VisitCountService;
import kr.spring.tiles.util.WeatherService;

@Controller
public class IndexController {
	
	private static final Logger logger = LoggerFactory.getLogger(IndexController.class);
	
	@Inject
	WeatherService weatherService;
	@Inject
	FreeBoardService freeBoardService;
	
	
	@RequestMapping("/index.do")
	public ModelAndView proIndex() throws Exception{
		ModelAndView mav = new ModelAndView();
		
		
		HashMap<String, Object> today = weatherService.todayWeather("서울");
		mav.setViewName("index");
		mav.addObject("today",today);
		
		List<Free_boardVO> list = freeBoardService.listAll(0, 5,"all","");
	    // 데이터를 맵에 저장
		// 데이터를 맵에 저장
	    Map<String, Object> map = new HashMap<String, Object>();
	    map.put("list", list); // list    
	    map.put("board_name", "free_board"); //게시물명
	    mav.addObject("map", map);
		return mav;
	}
}
