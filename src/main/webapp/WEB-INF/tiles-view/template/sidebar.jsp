<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath}"/>
 <!-- sidebar -->
    <aside id="sidebar">
        <div class="sidebar-inner">
            <div id="sidebar_close"><a href="#"><i class="icon-cancel"></i></a></div>
            <ul class="lnb">
                <li><a href="main.html">홈</a></li>                             
                <li class="sub-menu"><a href="sub.html">커뮤니티</a>
                    <ul>
                        <li><a href="${path}/board/rycle_api_list.do">매장_식수대</a></li>
                        <li><a href="${path}/board/free_board_list.do">자유게시판</a></li>
                        <li><a href="${path}/board/gallery_board_list.do">갤러리 </a></li>
                    </ul>
                </li>
             
            </ul>
        </div>
    </aside>