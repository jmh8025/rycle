package kr.spring.tiles.controller;

import java.util.HashMap;

import javax.inject.Inject;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import kr.spring.tiles.util.WeatherService;

@Controller
public class IndexController {
	
	private static final Logger logger = LoggerFactory.getLogger(IndexController.class);
	
	@Inject
	WeatherService weatherService;
	
	
	@RequestMapping("/index.do")
	public ModelAndView proIndex(){
		ModelAndView mav = new ModelAndView();
		HashMap<String, Object> today = weatherService.todayWeather("서울");
		mav.setViewName("index");
		mav.addObject("today",today);
		return mav;
	}
}
