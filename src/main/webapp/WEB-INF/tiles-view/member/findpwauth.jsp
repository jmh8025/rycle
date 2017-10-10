<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath}" />

<div class="pwchk_inner">

	<div class="pwchk_content">


		<form method="post" id="writeform" name="writeform"
			action="${path}/member/findPwAuth.do">
			<table>
				<tr> 
					<td>회원님의 이메일로 인증번호를 전송해드렸어요. 이메일을 확인해서 인증번호를 알려주세요.</td>
				</tr>
				<tr>
					<td><input type="text" class="form-control" name="auth"></td>
					<td><input type="submit" class="sign-in-button btn-primary" id="findpwbutton"
						name="Sign-in" value="확인" /></td>

				</tr>
				<tr>
					<c:choose>
						<c:when test="${msg == null}">
						</c:when>
						<c:otherwise>
							<td><p class="help-block chkpw" id="chkemail">인증번호를 확인해주세요</p></td>
						</c:otherwise>
					</c:choose>
				</tr>

			</table>
		</form>

	</div>

</div>
