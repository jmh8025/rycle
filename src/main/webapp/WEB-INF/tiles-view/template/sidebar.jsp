<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath}"/>
 <!-- sidebar -->
    <aside id="sidebar">
        <div class="sidebar-inner">
            <div id="sidebar_close"><a href="#"><i class="icon-cancel"></i></a></div>
            <ul class="lnb">
                <li><a href="main.html">홈</a></li>                             
                <li class="sub-menu"><a href="#">소개</a>
                    <ul>
                        <li><a href="#">인사말</a></li>
                        <li><a href="#">이용약관</a></li>
                        <li><a href="#">개인정보처리방침</a></li>
                    </ul>
                </li>
                <li><a href="#">경로추천</a></li>
                <li class="sub-menu"><a href="#">날씨</a>
                    <ul>
                        <li><a href="#">오늘날씨</a></li>
                        <li><a href="#">주간날씨</a></li>
                    </ul>
                </li>
                <li class="sub-menu"><a href="sub.html">커뮤니티</a>
                    <ul>
                        <li><a href="#">공지사항</a></li>
                        <li><a href="${path}/board/free_board_list.do">자유게시판</a></li>
                        <li><a href="#">질문과답변</a></li>
                        <li><a href="${path}/board/gallery_board_list.do">갤러리</a></li>
                    </ul>
                </li>
                <li class="sub-menu"><a href="#">이벤트</a>
                    <ul>
                        <li><a href="#">진행중이벤트</a></li>
                        <li><a href="#">완료된이벤트</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </aside>