<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script src="//code.jquery.com/jquery.min.js"></script>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath}"/>
	<div class="content">
	
		<p class="tbTopTxt"><span><b class="star" title="필수항목">*</b> 필수입력</span></p>
		<form method="post" id="writeform" name="writeform" action="/SpringTiles/board/free_board_insert.do"> 
			<input type="hidden" name="id" value="slr2">
			<input type="hidden" name="writer" value="작성자">
			
			<!-- boardWrite -->
			<div class="boardWrite">
				<table>
					<tr>
						<th>카테코리<b class="star" title="필수항목">*</b>
							<br><span id="scate_chk"></span></th>
						<td>
							<select name="cate_chk" id="cate_chk">
									<option value="">--선택하세요--</option>
								<c:forEach var="fb_article" items="${fbmap.fblist}">
									<option value="${fb_article.cate_chk}">${fb_article.cate_name}</option>
								</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<th>제목<b class="star" title="필수항목">*</b>
							<br><span id="ssubject"></span>
						</th>
						<td><input type="text" id="subject" name="subject" title="제목" class="w100" value="">
						<br>
						</td>
					</tr>
					<tr>
						<th>내용<b class="star" title="필수항목">*</b>
							<br><span id="scontent"></span>
						</th>
						<td>
							<textarea name="content" id="bcontent" class="textarea" title="내용입력"></textarea>
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
			
					<input class="btnList" type="button" value="목록" onclick="location.href='/SpringTiles/board/free_board_list.do'">
				
					<button class="btnSubmit" type="button" id="btnSave" onclick="write_submit('free_board');" >확인</button>
					<button class="btnReset" type="reset" >취소</button>
				
			</div>
			<!-- //btnArea -->
			
		</form>
	</div>

