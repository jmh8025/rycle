package kr.spring.tiles.board.gallery.model.dao;

import java.util.ArrayList;
import java.util.HashMap;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import kr.spring.tiles.board.gallery.controller.Gallery_BoardController;
import kr.spring.tiles.board.gallery.model.dao.Gallery_fileDAO;
import kr.spring.tiles.board.gallery.model.dto.Gallery_fileVO;
import kr.spring.tiles.board.gallery.model.dto.Gallery_commentVO;

//현재 클래스를 DAO bean으로 등록시킴
@Repository
public class Gallery_commentDAOImpl implements Gallery_commentDAO {
	
	private static final Logger logger = LoggerFactory.getLogger(Gallery_BoardController.class);

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
	public List<Gallery_commentVO> clread(int bno) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList("gallerycomment.clread", bno);
	}
	
	@Override
	public void cinsert(Gallery_commentVO vo) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.insert("gallerycomment.cinsert", vo);
	}
	
	@Override
	public Gallery_commentVO cread(int bno) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("gallerycomment.cread", bno);
	}
	
	@Override
	public void cupdate(Gallery_commentVO cvo) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.update("gallerycomment.cupdate", cvo);
	}
	
	@Override
	public Gallery_commentVO curead(int cno) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("gallerycomment.curead", cno);
	}
	
	@Override
	public void cdelete(int cno) throws Exception {
		sqlSession.delete("gallerycomment.cdelete", cno);
		
	}
}
