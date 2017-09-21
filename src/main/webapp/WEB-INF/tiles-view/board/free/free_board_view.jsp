<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script src="//code.jquery.com/jquery.min.js"></script>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath}"/>

<div class="content_inner">
	<div class="content">
	
		<p class="tbTopTxt"><span><b class="star" title="필수항목"></b> 자유게시판</span></p>
			
			<!-- boardWrite -->
			<div class="boardWrite">
				<table>
					<tr>
						<th>카테코리</th>
						<td>
								${map.cdto.cate_name}
						</td>
						<th>조회수</th>
						<td>
								${map.dto.readcount}
						</td>
					</tr>
					<tr> 
						<th>id</th>
						<td colspan="3">${map.dto.id}</td>
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
						<th>내용	</th>
						<td height="300px;" colspan="3">${map.dto.content}</td>
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
					<button class="btnSubmit" type="button" id="btnSave" onclick="location.href='/SpringTiles/board/free_board_update.do?bno=${map.dto.no}' ">수정</button>
					<button class="btnSubmit" type="button" id="btnSave" onclick="location.href='/SpringTiles/board/free_board_delete.do'">삭제</button>
			</div>
			<!-- //btnArea -->
			
		</form>
	</div>
</div>
