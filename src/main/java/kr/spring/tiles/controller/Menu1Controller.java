package kr.spring.tiles.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Menu1Controller {
	
	@RequestMapping("/memr_write.do")
	public String process(){
		return "member_write";		
	}
	
}
