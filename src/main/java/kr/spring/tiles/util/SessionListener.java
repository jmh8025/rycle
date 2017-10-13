package kr.spring.tiles.util;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

public class SessionListener implements HttpSessionBindingListener 
{

ServletContext context;

public SessionListener (ServletContext context) {
this.context = context;
}

// listenter 가 session에 binding 될 때 호출
public void valueBound(HttpSessionBindingEvent event) 
{
System.out.println("세션호출");
}

// listenter 가 session에 unbinding 될 때 호출
public void valueUnbound(HttpSessionBindingEvent event) 
{
// 해당 DB 에서 정보를 삭제하세요 
}
}