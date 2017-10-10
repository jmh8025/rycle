<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath}" />
<div class="content_inner">
	<div class="content">
		<label for="date">새로운 비밀번호를 입력해주세요</label><br>
		<br> <br>
		<form role="form" name="reg" id="membermodi"
			action="${path}/member/update.do" method="post">
			<label for="inputEmail">이메일</label>
			<div class="input-group" id="divEmail">

				<input type="email" class="form-control" id="inputEmail"
					name="email" value="${sessionScope.email}" readonly />
			</div>
			<br> <br>

			<div class="form-group" id="divPassword2">
				<label for="InputPassword1">비밀번호</label> <input type="password"
					class="form-control" name="pw" id="password2" placeholder="비밀번호">
				<span class="help-block" id="pwhelp2"></span>
			</div>
			<div class="form-group" id="divPasswordCheck2">
				<label for="InputPassword2">비밀번호 확인</label> <input type="password"
					class="form-control" id="passwordCheck2" placeholder="비밀번호 확인">
				<span class="help-block" id="pwckhelp2"></span>
			</div>

		</form>
		<br>
		<center>
			<button type="button" class="btn btn-primary" id="membermodify" >수정</button>
			<button type="button" class="btn btn-default"
				onclick="location.href='${path}/index.do'">나가기</button>
		</center>
	</div>
</div>
