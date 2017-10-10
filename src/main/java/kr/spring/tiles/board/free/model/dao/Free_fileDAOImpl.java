package kr.spring.tiles.board.free.model.dao;

import java.util.HashMap;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import kr.spring.tiles.board.free.controller.Free_BoardController;
import kr.spring.tiles.board.free.model.dao.Free_fileDAO;
import kr.spring.tiles.board.free.model.dto.Free_fileVO;

//현재 클래스를 DAO bean으로 등록시킴
@Repository
public class Free_fileDAOImpl implements Free_fileDAO {
	
	private static final Logger logger = LoggerFactory.getLogger(Free_BoardController.class);

	// SqlSession 객체를 스프링에서 생성하여 주입시켜준다.
	// 의존관계 주입(Dependency Injection, DI)
	// 느스한 결함
	// IoC(Inversion of Control, 제어의 역전)
	@Inject
	// Inject애노테이션이 없으면 sqlSession은 null상태이지만
	// Inject애노테이션이 있으면 외부에서 객체를 주입시켜주게 된다. 
	// try catch문, finally문, 객체를 close할 필요가 없어졌다.
	SqlSession sqlSession;

	@Override
	public void addAttach(String file, String ufile, int bno) throws Exception {
		
		logger.info("addAttach_file : "+ file);
		logger.info("addAttach_ufile : "+ ufile);
		logger.info("addAttach_bno : " +bno);
		 
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("file", file);
		map.put("ufile", ufile);
		map.put("bno", bno);
		
		sqlSession.insert("freefile.addAttach", map);		
	}
	
	@Override
	public List<Free_fileVO> fread(int bno) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("freefile.list", bno);
	}
	
	@Override
	public int cnt_img(int bno) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("freefile.cnt_img", bno);
	}
	
	@Override
	public int cnt_nimg(int bno) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("freefile.cnt_nimg", bno);
	}
	
	@Override
	public void deleteFile(String ufile_name) throws Exception {
		sqlSession.delete("freefile.delAttach", ufile_name);		
		
	}
}
