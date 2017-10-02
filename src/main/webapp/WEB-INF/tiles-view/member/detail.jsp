<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script src="//code.jquery.com/jquery.min.js"></script>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath}"/>

<div class="content_inner">
	<div class="content">
	<div class="form-group" id="divName">
								<label for="name">이름</label> 
								<input type="text"class="form-control onlyHangul" name="name" id="name"
									placeholder="이름">
							</div>
							<div class="form-group" id="divId">
								<label for="id">아이디</label> <input type="text"
									class="form-control readonly" name="id" id="id"
									placeholder="아이디">
							</div>
							<div class="form-group" id="divPassword">
								<label for="InputPassword1">비밀번호</label> <input type="password"
									class="form-control" name="pw" id="password" placeholder="비밀번호">
							</div>
						
							<!-- 이메일 -->
							<label for="inputEmail">이메일</label>
							<div class="input-group" id="divEmail">
								<input type="email" class="form-control readonly" id="inputEmail" name="email"
									placeholder="이메일을 입력해주세요" /> 
							</div>
							<!-- 이메일끝 -->


						<!-- 생일 -->
						<div class="form-group" id="divBirth">
							<label for="birth">생일</label> <input type="text"
								class="form-control readonly" name="birth" id="birth">
						</div>
						<!-- /생일 -->

						<!-- 전화번호 -->
						<div class="form-group" id="divPhoneNumber">
							<label for="tel">전화번호</label> <input type="text"
								class="form-control " name="tel"
								id="phoneNumber" placeholder="-빼고 입력하세요">
						</div>
						<!-- 전화번호 -->

						<!-- 주소 -->
						<label for="postcode">주소</label>
						<div class="input-group" id="divAddress">
							<input type="text" class="form-control" id="postcode"
								name="postcode" placeholder="우편번호" readonly> <input type="text"
								class="form-control" id="address" name="address"
								placeholder="한글주소" > <span class="input-group-btn">
								<button class="btn btn-primary" type="button"
									onclick="execDaumPostcode()" >주소검색</button>
							</span>
						</div>
						<!-- 주소 -->
						<!-- iOS에서는 position:fixed 버그가 있음, 적용하는 사이트에 맞게 position:absolute 등을 이용하여 top,left값 조정 필요 -->
						<div id="wrap"
							style="display: none; border: 1px solid; width: 500px; height: 300px; margin: 5px 0; position: relative">
							<img
								src="//t1.daumcdn.net/localimg/localimages/07/postcode/320/close.png"
								id="btnFoldWrap"
								style="cursor: pointer; position: absolute; right: 0px; top: -1px; z-index: 1"
								onclick="foldDaumPostcode()" alt="접기 버튼">
						</div>
		
	</div>
</div>