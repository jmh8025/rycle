<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath}" />

<ul class="cards">
    <li>
        <h2>Like + Bicycle <small>BY <span>LYCLE</span> FROM KIC CAMPUS</small></h2>
        <div class="survey">설문 그래프</div>
    </li>
    <li>
        <div class="weather">
        <h3>${today.day_week}, ${today.month}</h3><br>
        <h3><i class="icon-plus-circled"></i>Seoul, ${today.sky}</h3>
        <p><span>${today.temp}˚</span><img src="${path}/${today.skyicon}" width="115px"></p><br>
        <h3></h3>
        </div>
    </li>
    <li>
        <div class="gallary">갤러리</div>
        <div class="today">접속자수</div>
    </li>
    <li><div class="notice">공지사항</div></li>
    <li><div class="route">경로추천</div></li>
</ul>