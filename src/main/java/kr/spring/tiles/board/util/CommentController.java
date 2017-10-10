package kr.spring.tiles.board.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import kr.spring.tiles.board.free.model.dto.Free_boardVO;
import kr.spring.tiles.board.free.model.dto.Free_commentVO;
import kr.spring.tiles.board.free.service.FreeBoardService;
import kr.spring.tiles.board.util.MediaUtils;
import kr.spring.tiles.board.util.UploadFileUtils;

@Controller
public class CommentController {

	private static final Logger logger = LoggerFactory.getLogger(CommentController.class);

	// xml에 설정된 리소스 참조
	// bean의 id가 uploadPath인 태그를 참조
	@Resource(name = "uploadPath")
	String uploadPath;

	@Inject
	FreeBoardService boardService;

	// 업로드 흐름 : 업로드 버튼클릭 => 임시디렉토리에 업로드=> 지정된 디렉토리에 저장 => 파일정보가 file에 저장

	/******************************
	 * # ajax 방식의 업로드 처리
	 *********************************/

	// 4. Ajax업로드 페이지 매핑
	@RequestMapping(value = "/comment/insertAjax", method = RequestMethod.GET)
	public void insertAjax() {

	}

	// 5. Ajax업로드 처리 매핑
	// 파일의 한글처리 : produces="text/plain;charset=utf-8"
	@ResponseBody // view가 아닌 data리턴
	@RequestMapping(value = "/comment/insertAjax", method = RequestMethod.POST)	
	public Map<String, Object> insertAjax(@RequestParam("bno") int bno, @RequestParam("cid") String cid,
			@RequestParam("cname") String cname, @RequestParam("ccontent") String ccontent,
			@RequestParam(value="cpno", required=false, defaultValue="0") int cpno , @RequestParam(value="clevel", required=false, defaultValue="1") int clevel ) throws Exception {
		
		Map<String, Object> map = new HashMap<String, Object>();
		

		Free_commentVO cvo = new Free_commentVO();
		
		cvo.setBno(bno);
		cvo.setCid(cid);
		cvo.setCname(cname);
		cvo.setCcontent(ccontent);
		cvo.setCpno(cpno);
		cvo.setClevel(clevel);

		map.put("cvo", boardService.getComment(bno, cvo)); // 게시물명
		
		return map;
	}

}
