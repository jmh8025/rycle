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
        <h3><br>${today.day_week}, ${today.month}</h3><br>
        <h3><i class="icon-plus-circled"></i>Seoul, ${today.sky}</h3><br>
        <p><span>${today.temp}˚</span><img src="${path}/${today.skyicon}" width="115px"></p><br>
        </div>
    </li>
    <li>
        <div class="gallary">갤러리</div>
        <div class="today">dd ${totalCount} </div>
    </li>
    <li><div class="notice">
    
      <div class="boardList">
                <table>
                <colgroup>
                    <col style="width:10%;">
                    <col style="width:10%;">
                    <col style="width:10%;">
                </colgroup>
                <thead>
                    <tr class="boardListtr">
                        <th scope="col">제목</th>
                        <th scope="col">작성자</th>
                        <th scope="col">작성일</th>
                    </tr>
                </thead>
                <tbody>
	<c:forEach var="article" items="${map.list}"  varStatus="status">
		<tr>
			<td><a href="/SpringTiles/board/free_board_view.do?bno=${article.no}&cate_chk=${article.cate_chk}${board_link}">${article.subject}</a></td>
			<td>${article.writer}</td>
			<td>${article.k_date}</td>
		</tr>	
	</c:forEach>
                </tbody>
                </table>
            </div>


</div>

</li>
    <li><div class="route">경로추천</div></li>
</ul>