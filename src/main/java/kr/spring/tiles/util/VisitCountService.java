package kr.spring.tiles.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

public class VisitCountService{


	@Inject
	SqlSession sqlSession;
	
	public void test() {
		System.out.println("정상호출");
	}
	
	public void setVisitTotalCount() {
		sqlSession.insert("visit.insertVisit");
		System.out.println("실행됨");
	}
	public int getVisitTodayCount() {
		int result =  sqlSession.selectOne("visit.selectToday");
		System.out.println(result);
		return result;
	}
	public int getVisitTotalCount() {
		int result = sqlSession.selectOne("visit.selectTotal");
		System.out.println(result);
		return result;
	}
    
 
}