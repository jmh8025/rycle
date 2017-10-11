package kr.spring.tiles.util;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

public class VisitCountService{


	@Inject
	SqlSession sqlSession;
	
	public void setVisitTotalCount() {
		sqlSession.insert("visit.insertVisit");
		System.out.println("실행됨");
	}
	public int getVisitTodayCount() {
		return sqlSession.selectOne("visit.selectToday");
	}
	public int getVisitTotalCount() {
		return sqlSession.selectOne("visit.selectTotal");
	}
    
 
}