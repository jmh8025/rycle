package kr.spring.tiles.util;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import kr.spring.tiles.util.VisitCountService;

public class SessionListener implements HttpSessionListener {

	 @Override
	    public void sessionCreated(HttpSessionEvent arg0) {
	 
	        // DAO 객체 생성
		 VisitCountService dao = new VisitCountService();
	         
	        // 전체 방문자 수 +1
	        dao.setVisitTotalCount();
	         System.out.println("접속");

	    }
	 
	    @Override
	    public void sessionDestroyed(HttpSessionEvent arg0) {
	 
	    }
	
	
}