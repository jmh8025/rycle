package kr.spring.tiles.member.controller;

import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.spring.tiles.member.model.dto.MemberVO;
import kr.spring.tiles.member.service.MemberService;
import kr.spring.tiles.util.MailService;

@Controller // 현재의 클래스를 controller bean에 등록시킴
@RequestMapping("/member/*") // 모든맵핑은 /member/를 상속
public class MemberController {
	
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	// MemberService 객체를 스프링에서 생성하여 주입시킴
	@Inject
	MemberService memberService;
	
	 @Inject
	 MailService mailService;

	// 01 회원 목록
	// url pattern mapping
	@RequestMapping("/member/list.do")
	public String memberList(Model model){
	// controller => service => dao 요청
		List<MemberVO> list = memberService.memberList();
		model.addAttribute("list", list);
		return "index";
	}


	@RequestMapping(value ="/member/insert.do",method = RequestMethod.POST, produces = "application/json; charset=utf8")
	@ResponseBody
	// * 폼에서 입력한 데이터를 받아오는 법 3가지 
	//public String memberInsert(HttpServlet request){
	//public String memberInsert(String userId, String userPw, String userName, String userEmail){
	public String memberInsert(@ModelAttribute MemberVO vo){
		// 테이블에 레코드 입력
		memberService.insertMember(vo);
		// * (/)의 유무에 차이
		// /member/list.do : 루트 디렉토리를 기준
		// member/list.do : 현재 디렉토리를 기준
		// member_list.jsp로 리다이렉트
		logger.info(vo.getName());
		return vo.getName();
	}

    // 01. 로그인 처리
    @RequestMapping(value ="/member/loginCheck.do" , method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public boolean loginCheck(@ModelAttribute MemberVO vo, HttpSession session){
        boolean result = memberService.loginCheck(vo, session);
        return result;
    }
    
    // 02. 로그아웃 처리
    @RequestMapping("/member/logout.do")
    public ModelAndView logout(HttpSession session){
        memberService.logout(session);
        ModelAndView mav = new ModelAndView();
        mav.setViewName("redirect:/index.do");
       /* mav.addObject("msg", "logout");*/
        return mav;
    }
	
	
    @RequestMapping(value = "/member/sendMail.do", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    private boolean sendMail(HttpSession session, @RequestParam String email) {
    	
        int randomCode = new Random().nextInt(10000) + 1000;
        String joinCode = String.valueOf(randomCode);
        session.setAttribute("joinCode", joinCode);
        session.setMaxInactiveInterval(60*5);
        String subject = "회원가입 승인 번호 입니다.";
        StringBuilder sb = new StringBuilder();
        sb.append("회원가입 승인번호는 ").append(joinCode).append(" 입니다.");
        return mailService.send(subject, sb.toString(), "rycleproject@gmail.com", email);
        }
    
    
    //메일인증번호확인
    @RequestMapping(value = "/member/checkMail.do", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    private boolean checkMail(HttpSession session, @RequestParam String auth) {
    
    	String authNum = (String) session.getAttribute("joinCode");
    	 logger.info("답"+authNum);
    	 logger.info("요청값"+auth);
      
        return mailService.check(authNum,auth);
        }
    
  //아이디중복확인
    @RequestMapping(value = "/member/checkId.do", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    private boolean checkId(HttpSession session, @RequestParam String Id) {  
        return memberService.idcheck(Id);
        }
    
    //회원정보 수정페이지
    	@RequestMapping(value="/member/pwcheck.do",method=RequestMethod.GET)
    	public String process(){
    		return "member/pwcheck";		
    	}
    
    
    
    //회원정보 수정을 위한 비밀번호 확인
    @RequestMapping(value="/member/pwcheck.do",method=RequestMethod.POST)
    private ModelAndView pwcheckupdate(@ModelAttribute MemberVO vo) {
    	ModelAndView mav = new ModelAndView();
        boolean result = memberService.checkPw(vo);
        if(result) {
        	MemberVO vo2 = memberService.viewMember(vo);
        	mav.setViewName("member/detail");
        	mav.addObject("msg", "fail");
        	mav.addObject("user", vo2);
        	return mav;
        }else{
        	mav.setViewName("member/pwcheck");
        	mav.addObject("msg", "fail");
        	return mav;
        }
        }
    
    
    /*	// 04. 회원 정보 수정 처리
	@RequestMapping("member/update.do")
	public String memberUpdate(@ModelAttribute MemberVO vo, Model model){
		// 비밀번호 체크
		boolean result = memberService.checkPw(vo.getUserId(), vo.getUserPw());
		if(result){ // 비밀번호가 일치하면 수정 처리후, 전체 회원 목록으로 리다이렉트
			memberService.updateMember(vo);
			return "redirect:/member/list.do";
		} else { // 비밀번호가 일치하지 않는다면, div에 불일치 문구 출력, viwe.jsp로 포워드
			// 가입일자, 수정일자 저장
			MemberVO vo2 = memberService.viewMember(vo.getUserId());
			vo.setUserRegdate(vo2.getUserRegdate());
			vo.setUserUpdatedate(vo2.getUserUpdatedate());
			model.addAttribute("dto", vo);
			model.addAttribute("message", "비밀번호 불일치");
			return "member/member_view";
		}
		
	}*/
	// 05. 회원정보 삭제 처리
	// @RequestMapping : url mapping
	// @RequestParam : get or post방식으로 전달된 변수값
/*	@RequestMapping("member/delete.do")
	public String memberDelete(@RequestParam String userId, @RequestParam String userPw, Model model){
		// 비밀번호 체크
		boolean result = memberService.checkPw(userId, userPw);
		if(result){ // 비밀번호가 맞다면 삭제 처리후, 전체 회원 목록으로 리다이렉트
			memberService.deleteMember(userId);
			return "redirect:/member/list.do";
		} else { // 비밀번호가 일치하지 않는다면, div에 불일치 문구 출력, viwe.jsp로 포워드
			model.addAttribute("message", "비밀번호 불일치");
			model.addAttribute("dto", memberService.viewMember(userId));
			return "member/member_view";
		}
	}*/

}
