<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath}"/>
 <header id="header">
        <ul class="header-inner">
            <li class="pull-left" id="sidebar_menu"><a href="#"><i class="icon-menu"></i> <span class="hidden-xs">MENU</span></a></li>
            <li class="pull-left" id="top-search"><a href="#"><i class="icon-search"></i></a></li>
            <li class="logo"><a href="#">LYCLE</a></li>
        <c:choose>
    <c:when test="${sessionScope.userId == null}">
        <li class="pull-right"><a href="#" data-toggle="modal" data-target="#joinModal">JOIN</a> &nbsp;/&nbsp; <a href="#">LOGIN</a></li>
        </c:when>
    <c:otherwise>
    <li class="pull-right">${sessionScope.userName}님이 로그인중입니다. &nbsp;/&nbsp; <a href="${path}/member/logout.do">로그아웃</a></li>
     </c:otherwise>
</c:choose>
    </ul>

   <!-- Top Search Content -->
        <div id="top-search-wrap">
            <input type="text">
            <i id="top-search-close" class="icon-cancel"></i>
        </div>
    </header>
    
    <!-- Modal -->
    <div class="modal fade" id="joinModal" role="dialog">
        <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">회원가입</h4>
            </div>
            <div class="modal-body">
                <p>modal-body안에 내용 넣음 될듯!</p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>

        </div>
    </div>

