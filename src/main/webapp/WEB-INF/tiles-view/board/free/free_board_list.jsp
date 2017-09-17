<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<when test="${map.boardPager.curPage} == '' ">
	<c:set var="curPage" value="${curPage}" />
</when>
<otherwise>
	<c:set var="curPage" value="${map.boardPager.curPage}" />
</otherwise>

<when test="${map.searchOption} == '' ">
	<c:set var="searchOption" value="${searchOption}" />
</when>
<otherwise>
	<c:set var="searchOption" value="${map.searchOption}" />
</otherwise>

<when test="${map.keyword} == '' ">
	<c:set var="keyword" value="${keyword}" />
</when>
<otherwise>
	<c:set var="keyword" value="${map.keyword}" />
</otherwise>


<div class="content">

<div class="boardSearch">
	<p class="result">총 <b>${map.count}</b> 건</p>
</div>
<!-- boardList -->
<div class="boardList">
<table width="100%" border="0" cellspacing="0" cellpadding="2">
	
	<tr bgcolor="#F3F3F3">
		<td width="50">번호</td>
		<td>제목</td>
		<td width="70">작성자</td>
		<td width="100">날짜</td>
		<td width="70">조회수</td>
	</tr>
	<!-- 데이터의 유무에 따라서 -->
	<c:if test="${count==0}">
		<tr>
			<td colspan="5" align="center">등록된 게시물이없습니당~</td>
		</tr>
	</c:if>
	<c:forEach var="article" items="${map.list}"  varStatus="status">
		<tr>
			<td align="center">${(map.count) - ( (map.boardPager.curPage - 1)  *  map.PAGE_SCALE + status.index ) }</td>
			<td><a href="/board/free_board_list.do?no=${article.no}&curPage=${map.boardPager.curPage}&searchOption=${map.searchOption}&keyword=${map.keyword}">${article.subject}</a></td>
			<td>${article.writer}</td>
			<td>${article.k_date}</td>
			<td>${article.readcount}</td>
		</tr>	
	</c:forEach>
</table>
</div>


<%@ include file="../include/list_page_foot.jsp" %>


<!-- btnArea -->
<div class="pagingWrap"  style="float:right">
	<div class="btnArea">
		<p class="floatR">
			<a href="/SpringTiles/board/free_board_write.do" class="btn"><span>글쓰기</span></a>
		</p>
	</div>
</div>

<!-- boardSearch -->
<div class="boardSearch"  style="float:right">
	<form method="post" name="test" action="/SpringTiles/board/free_board_list.do">
	<fieldset class="search">
		<legend>공지사항 검색</legend>
		<div class="selectBox">
		<select name="searchOption" title="검색조건 분류 선택하세요.">
			<option value="all" <c:out value="${map.searchOption == 'all'?'selected':''}" /> >제목+본문+작성자+ID</option>
			<option value="subject_content" <c:out value="${map.searchOption == 'subject_content'?'selected':''}" /> >제목+본문</option>
			<option value="subject" <c:out value="${map.searchOption == 'subject'?'selected':''}" /> >제목</option>
			<option value="writer" <c:out value="${map.searchOption == 'writer'?'selected':''}" /> >작성자</option>	
			<option value="id" <c:out value="${map.searchOption == 'id'?'selected':''}" /> >ID</option>	
		</select>
		</div>
		<span class="inText">
			<label for="searchKeyword_" class="hidden">검색어 입력</label>
			<input type="text" name="searchKeyword" value="${map.keyword}">
			<input type="submit" class="btnSch" value="검색">
		</span>
	</fieldset>
	</form>
</div>

<!-- //btnArea -->


</div>