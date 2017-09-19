package kr.spring.tiles.member.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import kr.spring.tiles.member.service.MemberJusoutil;

@Controller 
public class MemberJuso {

	 public static final String ZIPCODE_API_KEY = "";
	    // api를 쓰기 위한 주소
	  public static final String ZIPCODE_API_URL = "http://biz.epost.go.kr/KpostPortal/openapi";

	  @RequestMapping(value = "/zip_codeList", method = RequestMethod.POST, produces = "text/planin;charset=UTF-8")
	    public @ResponseBody String zip_codeList(@RequestParam("query") String query) throws Exception {
	        Map<String, Object> paramMap = new HashMap<String, Object>();
	        StringBuilder queryUrl = new StringBuilder();
	        queryUrl.append(ZIPCODE_API_URL);
	        queryUrl.append("?regkey=");
	        queryUrl.append(ZIPCODE_API_KEY);
	        queryUrl.append("&target=");
	        queryUrl.append("postNew");
	        queryUrl.append("&query=");
	        queryUrl.append(query.replaceAll(" ", ""));
	        
	        // document 선언
	        Document document = Jsoup.connect(queryUrl.toString()).get();
	        // errorCode 선언
	        String errorCode = document.select("error_code").text();
	        
	        if(null == errorCode || "".equals(errorCode))
	        {
	            Elements elements = document.select("item");
	            List<MemberJusoutil> list = new ArrayList<MemberJusoutil>();
	            MemberJusoutil zip_codeVO = null;
	            
	            for(Element element : elements){
	                zip_codeVO = new MemberJusoutil();
	                zip_codeVO.setZipcode(element.select("postcd").text());
	                // 지번 검색
	                zip_codeVO.setAddress(element.select("address").text());
	                list.add(zip_codeVO);
	            }
	            // list 결과 put
	            paramMap.put("list", list);
	        }else{
	            String errorMessage = document.select("message").text();
	            paramMap.put("errorCode", errorCode);
	            paramMap.put("errorMessage", errorMessage);
	        }
	        // Gson형태로 paramMap 리턴
	        return (new Gson()).toJson(paramMap);
	    }


	
	
	
}
