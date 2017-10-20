<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 

<c:set var="path" value="${pageContext.request.contextPath}" />


<input type="hidden" name="bno" id="bno" value="${map.dto.no}">
<input type="hidden" name="sid" id="sid" value="${sessionScope.id}">
<input type="hidden" name="sname" id="sname" value="${sessionScope.name}">

<div class="content_inner">
	<div class="content">

		<p class="tbTopTxt">
			<span><b class="star" title="필수항목"></b> 자유게시판</span>
		</p>
		<input type="hidden" id="bno" value="${map.dto.no}" />
		<!-- boardWrite -->
		<div class="boardWrite">
			<table>
				<tr>
					<th>id</th>
					<td>${map.dto.id}</td>
					<th>조회수</th>
					<td>${map.dto.readcount}</td>
				</tr>
				<tr>
					<th>writer</th>
					<td colspan="3">${map.dto.writer}</td>
				</tr>

				<tr>
					<th>제목</th>
					<td colspan="3">${map.dto.subject}</td>
				</tr>
				<tr>
					<th>첨부파일</th>
					<td>
						<!-- 첨부파일 목록 -->
						<div id="uploadedList">
							<c:if test="${map.fdto != null}">
								<c:forEach var="farticle" items="${map.fdto}" varStatus="status">
								<c:set var="wfile_name" value="${fn:replace(farticle.ufile_name, '/s_', '/')}" />

								<a href='/upload/displayFile.do?fileName=${wfile_name}'>
								<img id="gitem" src="/upload/displayFile.do?fileName=${farticle.ufile_name}" style="margin: 3px;"></a>

									<br>
								</c:forEach>
							</c:if>
						</div>
					</td>
				</tr>

			</table>
		</div>

	</div>

	<!-- 댓글 부분 -->
	<div id="comment">
		<!-- 댓글 목록 -->
		<c:if test="${map.cmtdto != null}">
			<table border="1" id="cmt_tb1" bordercolor="#333">
				<input type="hidden" id="last_idx" name="last_idx" value="${fn:length(map.cmtdto)-1}">
				<c:forEach var="comment" items="${map.cmtdto}" varStatus="status">
					<div>
					<tr id="tr_${comment.cno}">
					<!-- 
						<input type="hidden" id="clevel_${status.index}" value="${comment.clevel}">
						<input type="hidden" id="cno_${status.index}" value="${comment.cno}">
						 -->
						<!-- 아이디, 작성날짜 -->
						<td width="150">
							<div>
								<c:if test="${comment.clevel > 1}">
							&nbsp;&nbsp;&nbsp;&nbsp; <!-- 답변글일경우 아이디 앞에 공백을 준다. -->
									<img src="img/reply_icon.gif">
								</c:if>
								${comment.cid}<br> <font size="2" color="lightgray">${comment.cdate}</font>
							</div>
						</td>
						
						
						<!-- 본문내용 -->
						<div id="div_${comment.cno}">
							<td width="550">
								<div class="text_wrapper"><input type="hidden" id="ccontent_${comment.cno}" value="${comment.ccontent}">
								${comment.ccontent}</div>
							</td>
						<!-- 버튼 -->
								<td width="420">
							<c:if test="${sessionScope.id != null && sessionScope.id != '' && comment.cid == sessionScope.id}">
									<div id="btn" style="text-align: center;">
										<%-- <a onclick="cmReplyOpen(${status.index});">[답변]</a>&nbsp; --%>
										<!-- 댓글 작성자만 수정, 삭제 가능하도록 -->
											<span class="cmt_sp"  onclick="cmUpdateOpen(${comment.cno})">[수정]</span>&nbsp;
											<span class="cmt_sp" onclick="cmDeleteOpen(${comment.cno})">[삭제]</span>
									</div>
							</c:if>
								</td>
						</div>
					</tr>
					</div>
					<tr><td colspan=3>
							<ul class="cmt_gubun"><li></li></ul>
					</td></tr>
				</c:forEach>
			</table>
		</c:if>
		<!-- 로그인 했을 경우만 댓글 작성가능 -->
		<c:if test="${sessionScope.id !=null}">
			<table border="1" id="cmt_tb2" bordercolor="#333">
				<tr bgcolor="#F5F5F5">

					<!-- 아이디-->
					<td width="150" class="vmiddle">
						${sessionScope.id}
					</td>
					<!-- 본문 작성-->
					<td width="550">
						<div>
							<textarea name="ccontent" id="ccontent" rows="4" cols="70"></textarea>
						</div>
					</td>
					<!-- 댓글 등록 버튼 -->
					<td width="420" class="center vmiddle">
							<p class="cmt_sp" id="writeCmt">[댓글등록]</p>
					</td>
				</tr>
			</table>
		</c:if>
	</div>

	<!-- btnArea -->
	<div class="btnArea">
		<button class="btnSubmit" type="button" onclick="location.href='/board/gallery_board_list.do">목록</button>

		<c:if test="${map.dto.id == sessionScope.id}">
			<button class="btnSubmit" type="button" onclick="location.href='/board/gallery_board_update.do?bno=${map.dto.no}' ">수정</button>
			<button class="btnSubmit" type="button" onclick="location.href='/board/gallery_board_delete.do?bno=${map.dto.no}'">삭제</button>
		</c:if>
			
	</div>
	<!-- //btnArea -->

</div>
