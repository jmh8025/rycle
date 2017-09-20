<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="pagingWrap">
	<div class="paging">
		<!-- 페이징 -->
		
				<!-- 처음페이지로 이동 : 현재 페이지가 1보다 크면  [처음]하이퍼링크를 화면에 출력-->
				<c:if test="${map.boardPager.curBlock > 1}">
					<span class="btn_arr"><a href="/SpringTiles/board/free_board_list.do?curPage=1&searchOption=${map.searchOption}&keyword=${map.keyword}">[처음]</a></span>
				</c:if>
				
				<!-- 이전페이지 블록으로 이동 : 현재 페이지 블럭이 1보다 크면 [이전]하이퍼링크를 화면에 출력 -->
				<c:if test="${map.boardPager.curBlock > 1}">
					<span class="btn_arr"><a href="/SpringTiles/board/free_board_list.do?curPage=${map.boardPager.prevPage}&searchOption=${map.searchOption}&keyword=${map.keyword}">[이전]</a></span>
				</c:if>
				
				<!-- **하나의 블럭 시작페이지부터 끝페이지까지 반복문 실행 -->
				<c:forEach var="num" begin="${map.boardPager.blockBegin}" end="${map.boardPager.blockEnd}">
					<!-- 현재페이지이면 하이퍼링크 제거 -->
					<c:choose>
						<c:when test="${num == map.boardPager.curPage}">
							<span class="num">${num}</span>&nbsp;
						</c:when>
						<c:otherwise>
							<span class="num"><a href="/SpringTiles/board/free_board_list.do?curPage=${num}&searchOption=${map.searchOption}&keyword=${map.keyword}">${num}</a></span>&nbsp;
						</c:otherwise>
					</c:choose>
				</c:forEach>
				
				<!-- 다음페이지 블록으로 이동 : 현재 페이지 블럭이 전체 페이지 블럭보다 작거나 같으면 [다음]하이퍼링크를 화면에 출력 -->
				<c:if test="${map.boardPager.curBlock <= map.boardPager.totBlock}">
					<span class="btn_arr"><a href="/SpringTiles/board/free_board_list.do?curPage=${map.boardPager.nextPage}&searchOption=${map.searchOption}&keyword=${map.keyword}">[다음]</a></span>
				</c:if>
				
				<!-- 끝페이지로 이동 : 현재 페이지가 전체 페이지보다 작거나 같으면 [끝]하이퍼링크를 화면에 출력 -->
				<c:if test="${map.boardPager.curPage <= map.boardPager.totPage}">
					<span class="btn_arr"><a href="/SpringTiles/board/free_board_list.do?curPage=${map.boardPager.totPage}&searchOption=${map.searchOption}&keyword=${map.keyword}">[끝]</a>
				</c:if>
				
				<input class="btnWrite" type="button" value="글쓰기" onclick="location.href='/SpringTiles/board/${map.board_name}_write.do'">
				
	</div>

</div>

