package kr.spring.tiles.board.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.imgscalr.Scalr;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.FileCopyUtils;

public class UploadFileUtils {

	private static final Logger logger = LoggerFactory.getLogger(UploadController.class);
	
	public static String uploadFile(String uploadPath, String originalName, byte[] fileData) throws Exception {
		
		// UUID 발급
		UUID uuid = UUID.randomUUID();
		// 저장할 파일명 = UUID + 원본이름
		String savedName = uuid.toString() + "_" + originalName;
		// 업로드할 디렉토리(날짜별 폴더) 생성 
		String savedPath = calcPath(uploadPath);
		// 파일 경로(기존의 업로드경로+날짜별경로), 파일명을 받아 파일 객체 생성		
		File target = new File(uploadPath + savedPath, savedName);
		
		// 임시 디렉토리에 업로드된 파일을 지정된 디렉토리로 복사
		FileCopyUtils.copy(fileData, target);
		// 썸네일을 생성하기 위한 파일의 확장자 검사
		// 파일명이 aaa.bbb.ccc.jpg일 경우 마지막 마침표를 찾기 위해
		String formatName = originalName.substring(originalName.lastIndexOf(".")+1);
		String uploadedFileName = null;
		// 이미지 파일이면 썸네일 생성
		if (MediaUtils.getMediaType(formatName) != null) {
			// 썸네일 생성
			uploadedFileName = makeThumbnail(uploadPath, savedPath, savedName);
		// 이미지 파일이 아니면 아이콘을 생성
		} else {
			// 아이콘 생성
			uploadedFileName = makeIcon(uploadPath, savedPath, savedName);
		}
		
/*	    Map<String, Object> fmap = new HashMap<String, Object>();

		
		String fdata = uploadedFileName;
		
	    fmap.put("file_name", originalName);
	    fmap.put("ufile_name", fdata.substring(12));
	    fmap.put("file_date", fdata.substring(0, 12));
	    fmap.put("fdate", fdata);
	     
	    logger.info("file_name: "+ originalName);
	    logger.info("ufile_name: "+ fdata.substring(12));
	    logger.info("file_date: "+ fdata.substring(0, 12));
	    logger.info("fdate: "+ fdata);		*/
	    
		return uploadedFileName;
	}
	
	// 날짜별 디렉토리 추출
	private static String calcPath(String uploadPath) {
		Calendar cal = Calendar.getInstance();
		// File.separator : 디렉토리 구분자(\\)
		// 연도, ex) \\2017 
		String yearPath = File.separator + cal.get(Calendar.YEAR);
		System.out.println(yearPath);
		// 월, ex) \\2017\\03
		String monthPath = yearPath + File.separator + new DecimalFormat("00").format(cal.get(Calendar.MONTH) + 1);
		System.out.println(monthPath);
		// 날짜, ex) \\2017\\03\\01
		String datePath = monthPath + File.separator + new DecimalFormat("00").format(cal.get(Calendar.DATE));
		System.out.println(datePath);
		// 디렉토리 생성 메서드 호출
		makeDir(uploadPath, yearPath, monthPath, datePath);
		return datePath;
	}
	
	// 디렉토리 생성
	private static void makeDir(String uploadPath, String... paths) {
		// 디렉토리가 존재하면
		if (new File(paths[paths.length - 1]).exists()){
			return;
		}
		
		logger.info("UploadFileUtil_makeDir_uploadPath:"+uploadPath);
		File dirPath2 = new File(uploadPath);
		
		if(!dirPath2.exists()) {
			logger.info(uploadPath);
			dirPath2.mkdir(); //디렉토리 생성
		}
		
		// 디렉토리가 존재하지 않으면
		for (String path : paths) {
			// 
			File dirPath = new File(uploadPath + path);
			// 디렉토리가 존재하지 않으면
			if (!dirPath.exists()) {
				dirPath.mkdir(); //디렉토리 생성
			}
		}
	}	
	
	// 썸네일 생성
	private static String makeThumbnail(String uploadPath, String path, String fileName) throws Exception {
		//이미지를 읽기 위한 버퍼
		BufferedImage sourceImg = ImageIO.read(new File(uploadPath + path, fileName));
		
		//100픽셀 단위의 썸네일 생성
		
		//BufferedImage destImg = Scalr.resize(sourceImg, Scalr.Method.AUTOMATIC, Scalr.Mode.FIT_TO_HEIGHT, 250);
		
		BufferedImage destImg = Scalr.resize(sourceImg, Scalr.Method.AUTOMATIC, Scalr.Mode.FIT_TO_WIDTH, 270);
		
		//썸네일의 이름을 생성(기존의파일명에 s_를 붙임)
		String thumbnailName = uploadPath + path + File.separator + "s_" + fileName;
		File newFile = new File(thumbnailName);
		String formatName = fileName.substring(fileName.lastIndexOf(".") + 1);
		//썸네일 생성
		ImageIO.write(destImg, formatName.toUpperCase(), newFile);
		//썸네일의 이름을 리턴함
		return thumbnailName.substring(uploadPath.length()).replace(File.separatorChar, '/');
	}
	
	// 아이콘 생성
	private static String makeIcon(String uploadPath, String path, String fileName) throws Exception {
		// 아이콘의 이름
		String iconName = uploadPath + path + File.separator + fileName;
		// 아이콘 이름을 리턴
		// File.separatorChar : 디렉토리 구분자
		// 윈도우 \ , 유닉스(리눅스) / 		
		return iconName.substring(uploadPath.length()).replace(File.separatorChar, '/');
	}
}
