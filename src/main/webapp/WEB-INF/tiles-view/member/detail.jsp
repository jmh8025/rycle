<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<div class="content_inner">
	<div class="content">
	<form role="form" name="reg" id="membermodi" action="${path}/member/update.do" method="post">
	<div class="form-group" >
								<label for="date">마지막 수정일자 : ${user.userUpdatedate}</label> 
							</div>
	<div class="form-group" id="divName">
								<label for="name">이름</label> 
								<input type="text"class="form-control onlyHangul" name="name" id="name"
									value="${user.name}" readonly>
							</div>
							<div class="form-group" id="divId">
								<label for="id">아이디</label> <input type="text"
									class="form-control readonly" name="id" id="id"
									value="${user.id}" readonly>
							</div>
							<div class="form-group" id="divPassword2">
								<label for="InputPassword1">비밀번호</label> <input type="password"
									class="form-control" name="pw" id="password2" placeholder="비밀번호">
										<span class="help-block"
									id="pwhelp2"></span>
							</div>
							<div class="form-group" id="divPasswordCheck2">
								<label for="InputPassword2">비밀번호 확인</label> <input
									type="password" class="form-control" id="passwordCheck2"
									placeholder="비밀번호 확인"> <span class="help-block"
									id="pwckhelp2"></span>
							</div>
							<!-- 이메일 -->
							<label for="inputEmail">이메일</label>
							<div class="input-group" id="divEmail">
								<input type="email" class="form-control" id="inputEmail" name="email"
									value="${user.email}" readonly/> 
							</div>
							<!-- 이메일끝 -->
						<!-- 생일 -->
						<div class="form-group" id="divBirth">
							<label for="birth">생일</label> <input type="text"
								class="form-control readonly" name="birth" id="birth"
								placeholder="${user.birth}" readonly>
						</div>
						<!-- /생일 -->
						<!-- 전화번호 -->
						<div class="form-group" id="divPhoneNumber">
							<label for="tel">전화번호</label> <input type="text"
								class="form-control " name="tel"
								id="phoneNumber" placeholder="${user.tel}" readonly>
						</div>
						<!-- 전화번호 -->
						<!-- 주소 -->
						<label for="postcode">주소</label>
						<div class="input-group" id="divAddress">
							<input type="text" class="form-control" id="postcode"
								name="postcode" placeholder="${user.postcode}" readonly> <input type="text"
								class="form-control" id="address" name="address"
								placeholder="${user.address}" readonly> 
						</div>
		</form><br><center>
		 <button type="button" class="btn btn-primary" id="membermodify" >수정</button>
		  <button type="button" class="btn btn-default" onclick="location.href='${path}/index.do'" >나가기</button>
	</center>
	</div>
</div>
