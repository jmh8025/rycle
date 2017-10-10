<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath}" />

<div class="pwchk_inner">

	<div class="pwchk_content">


		<form method="post" id="writeform" name="writeform"
			action="${path}/member/findPw.do">
			<table>
				<tr>
					<td>제가 회원님의 비밀번호찾기를 도와드릴게요.<br>가입하실때 입력하신 이메일을 알려주시겠어요?</td>
				</tr>
				<tr>
					<td><input type="email" class="form-control" name="email"
						id="findpwemail"></td>
					<td><input type="submit" class="sign-in-button" id="findpwbutton"
						name="Sign-in" value="확인" /></td>

				</tr>
				<tr>
					<c:choose>
						<c:when test="${msg == null}">
						</c:when>
						<c:otherwise>
							<td><p class="help-block chkpw" id="chkemail">이메일을 확인해주세요</p></td>
						</c:otherwise>
					</c:choose>
				</tr>

			</table>
		</form>

	</div>

</div>
