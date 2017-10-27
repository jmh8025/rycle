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

<!-- 링크페이지 -->
<c:set var="board_link" value="&curPage=${map.boardPager.curPage}&searchOption=${map.searchOption}&keyword=${map.keyword}" />

<div class="content_inner">
            <!-- boardSearch -->
            <div class="boardSearch">
                <p class="result"><i class="icon-doc-text"></i> 총 <strong>${map.count}</strong> 건</p>
                <form method="post" name="boardForm" action="/board/free_board_list.do">    
                    <fieldset class="search">
                        <div class="selectBox">
                         <select id="searchOption" name="searchOption" title="검색조건 분류 선택하세요.">
			<option value="all" <c:out value="${map.searchOption == 'all'?'selected':''}" /> >전체</option>
			<option value="content" <c:out value="${map.searchOption == 'content'?'selected':''}" /> >본문</option>
			<option value="subject" <c:out value="${map.searchOption == 'subject'?'selected':''}" /> >제목</option>
			<option value="writer" <c:out value="${map.searchOption == 'writer'?'selected':''}" /> >작성자</option>	
			<option value="id" <c:out value="${map.searchOption == 'id'?'selected':''}" /> >ID</option>	
		</select>
                        </div>
                        <span class="inText">
                            <input type="text" id="searchKeyword" name="searchKeyword" value="${map.keyword}">
                            <input type="submit" class="btnSch" value="검색" >
                        </span>
                    </fieldset> 
                </form>
            </div>
            <!-- boardList -->
            <div class="boardList">
                <table>
                <colgroup>
                    <col style="width:6%;">
                    <col style="width:15%;">
                    <col>
                    <col style="width:10%;">
                    <col style="width:10%;">
                    <col style="width:10%;">
                    <col style="width:10%;">
                </colgroup>
                <thead>
                    <tr>
                        <th scope="col">번호</th>
                        <th scope="col">카테고리명</th>
                        <th scope="col">제목</th>
                        <th scope="col">ID</th>
                        <th scope="col">작성자</th>
                        <th scope="col">작성일</th>
                        <th scope="col">조회</th>
                    </tr>
                </thead>
                <tbody>
                 <c:if test="${count==0}">
		<tr>
			<td colspan="5" align="center">등록된 게시물이없습니당~</td>
		</tr>
	</c:if>
	<c:forEach var="article" items="${map.list}"  varStatus="status">
		<tr>
			<td align="center">${(map.count) - ( (map.boardPager.curPage - 1)  *  map.PAGE_SCALE + status.index ) }</td>
			<td>${article.cate_name}</td>
			<td><a href="/board/free_board_view.do?bno=${article.no}&cate_chk=${article.cate_chk}${board_link}">${article.subject}</a></td>
			<td>${article.id}</td>
			<td>${article.writer}</td>
			<td>${article.k_date}</td>
			<td>${article.readcount}</td>
		</tr>	
	</c:forEach>
                </tbody>
                </table>
            </div>
            <!-- padding -->
         <%@ include file="../include/list_page_foot.jsp" %>
         <!-- btnArea -->

</div>






