<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath}"/>

	<div class="content">
		<p class="tbTopTxt"><span><b class="star" title="필수항목">*</b> 필수입력</span></p>
		<form method="post" id="writeform" name="writeform" action="/board/gallery_board_insert.do"> 
			<input type="hidden" id="board_file" value="gallery_file">
			
			<!-- boardWrite -->
			<div class="content_inner">
			<div class="boardWrite">
				<table>
					<tr>
						<th>제목<b class="star" title="필수항목">*</b>
							<br><span id="ssubject"></span>
						</th>
						<td><input type="text" id="subject" name="subject" title="제목" class="w100" value="">
						<br>
						</td>
					</tr>
					<tr>
						<th>첨부파일</th>
						<td>
							<div>
							    <span class="btxt">※첨부할 파일을 아래 영역에 드래그 해주세요</span>
							    <!-- 첨부파일 등록영역 -->
							    <div id="fileDrop" class="gfileDrop"></div>
							    <!-- 첨부파일의 목록 출력영역 
							    <div class="uploadedList">첨부파일의 목록 출력영역</div>-->
							</div>

						</td>
					</tr>

				</table>
			</div>
			
			
			
			<!-- btnArea -->
			<div class="btnArea">
					<input class="btnList" type="button" value="목록" onclick="location.href='/board/gallery_board_list.do'">
					<button class="btnSubmit" type="button" id="btnSave" onclick="write_submit('gallery_board');" >확인</button>
					<button class="btnReset" type="reset" >취소</button>
				
			</div>
			<!-- //btnArea -->
			
		</div>
	</form>
</div>

