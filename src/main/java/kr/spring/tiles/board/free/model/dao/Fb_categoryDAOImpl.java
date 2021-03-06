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
import kr.spring.tiles.board.free.model.dto.Fb_categoryVO;

//현재 클래스를 DAO bean으로 등록시킴
@Repository
public class Fb_categoryDAOImpl implements Fb_categoryDAO {
	
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

	// 01. 카테고리 가져오기
	@Override
	public List<Fb_categoryVO> listAll() throws Exception {
		return sqlSession.selectList("fb_category.listAll");
	}
	
	// 01. 카테고리 가져오기
	@Override
	public Fb_categoryVO cateName(String cate_chk) throws Exception {
	       logger.info("Fb_categoryDAO->cate_chk="+cate_chk);
		
		return sqlSession.selectOne("fb_category.cateName", cate_chk);
	}

}
