<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath}"/>

<div class="content_inner">
	<div class="content">
	
		<p class="tbTopTxt"><span><b class="star" title="필수항목">*</b> 필수입력</span></p>
		<form method="post" id="writeform" name="writeform" action="/SpringTiles/board/free_board_update.do?bno=${map.dto.no}"> 
			<input type="hidden" id="board_file" value="free_file">
			
			<!-- boardWrite -->
			<div class="boardWrite">
				<table>
					<tr>
						<th>카테코리<b class="star" title="필수항목">*</b>
							<br><span id="scate_chk"></span></th>
						<td>
							<select name="cate_chk" id="cate_chk">
									<option value="">--선택하세요--</option>
								<c:forEach var="fb_article" items="${map.fblist}">
									<option value="${fb_article.cate_chk}" 
										<c:out value="${fb_article.cate_chk == map.dto.cate_chk?'selected':''}" /> 
									>${fb_article.cate_name}</option>
								</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<th>제목<b class="star" title="필수항목">*</b>
							<br><span id="ssubject"></span>
						</th>
						<td><input type="text" id="subject" name="subject" title="제목" class="w100" value="${map.dto.subject}">
						<br>
						</td>
					</tr>
					<tr>
						<th>내용<b class="star" title="필수항목">*</b>
							<br><span id="scontent"></span>
						</th>
						<td>
							<textarea name="content" id="bcontent" class="textarea" title="내용입력">${map.dto.content}</textarea>
							<span class="cmt">2.000 Bytes 이내로 작성 하세요.</span>
						
						</td>
					</tr>
					<tr>
						<th>첨부파일</th>
						<td>
							<div>
							    <span class="btxt">※첨부할 파일을 아래 영역에 드래그 해주세요</span>
							    <!-- 첨부파일 등록영역 -->
							    <div id="fileDrop" class="fileDrop" style="height:${map.uheight}px">
								<c:forEach var="farticle" items="${map.fdto}"  varStatus="status">
									<div>
										<a href='/SpringTiles/upload/displayFile.do?fileName=${farticle.ufile_name}'>${farticle.file_name}</a>
										<span data-src="${farticle.ufile_name}">[삭제]</span>
									</div>
								</c:forEach>
							    
							    </div>
							</div>

						</td>
					</tr>

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
</div>