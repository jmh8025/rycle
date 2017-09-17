<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script src="//code.jquery.com/jquery.min.js"></script>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath}"/>

<h2>게시글 작성</h2>
<form name="form1" id="form1" method="post" action="${path}/board/insert.do">


	<div class="content">
		<p class="tbTopTxt"><span><b class="star" title="필수항목">*</b> 필수입력</span></p>
		<form method="post" name="writeform" action="/SpringTiles/board/free_board_write_Pro.do">
			<input type="hidden" name="id" value="${id}">
			
			<!-- boardWrite -->
			<div class="boardWrite">
				<table>
					<tr>
						<th>카테코리</th>
						<td>
							<select name="cate_chk">
								<c:forEach var="fb_article" items="${fbmap.fblist}">
									<option value="${fb_article.cate_chk}">${fb_article.cate_name}</option>
								</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<th>제목<b class="star" title="필수항목">*</b></th>
						<td><input type="text" name="subject" title="제목" class="w100" value=""></td>
					</tr>
					<tr>
						<th>내용<b class="star" title="필수항목">*</b></th>
						<td>
							<textarea name="content" class="textarea" title="내용입력"></textarea>
							<span class="cmt">2.000 Bytes 이내로 작성 하세요.</span>
						</td>
					</tr>
<!-- 					<tr>
						<th>첨부파일</th>
						<td>
							<div class="fileBox">
								<input type="text" id="ATTCH_FILE_1" name="ATTCH_FILE_1" class="addFile" readonly="">
								<span class="btnFile"><input type="file" id="ATTCH_FILEid1" name="ATTCH_FILE_1" title="첨부파일 선택" class="addFile02"></span>
							</div>
							<p class="txtCmt">총 10MBytes 이하</p>
						</td>
					</tr> -->
				</table>
			</div>
			
			<!-- btnArea -->
			<div class="btnArea">
				<p class="floatR">
					<a href="/SpringTiles/board/free_board_list.do" class="btn"><span>목록</span></a>
				</p>
					<div style="width:650px; text-align: center;">
						<button type="button" id="btnSave">확인</button>
						<button type="reset">취소</button>
					</div>
				
			</div>
			<!-- //btnArea -->
			
		</form>
	</div>
</form>
