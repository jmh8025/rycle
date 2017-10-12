<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath}" />


<div class="content_inner">
	<!-- boardSearch -->
	<div class="boardSearch">
		<p class="result">
			<i class="icon-doc-text"></i> 총 <strong>${map.count}</strong> 건
		</p>
		         <!-- padding -->
         <%@ include file="../include/list_page_foot.jsp" %>
         <!-- btnArea -->
	</div>
	<!-- boardList -->
	<div class="boardList">
		<script src="${path}/js/RGInfiniteScroll.js">
			
		</script>

		<c:forEach var="article" items="${map.list}" varStatus="status">
			<div id="gitem" style="float:left"><a href="/SpringTiles/board/gallery_board_view.do?bno=${article.bno}"><img src="/SpringTiles/upload/displayFile.do?fileName=${article.ufile_name}" style="margin: 5px;"></a></div>
		</c:forEach>
	</div>

</div>






