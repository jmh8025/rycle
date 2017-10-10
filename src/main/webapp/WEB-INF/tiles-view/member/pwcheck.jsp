<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script src="//code.jquery.com/jquery.min.js"></script>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath}" />

<div class="pwchk_inner">

	<div class="pwchk_content">

	
		<form method="post" id="writeform" name="writeform"
			action="${path}/member/pwcheck.do">
				<table>
				<tr>
<td><c:choose>
<c:when test="${sessionScope.id == null}">
<script>
history.go(-1)();
</script>
</c:when>
<c:otherwise>
${sessionScope.name}님! 보안을 위해서 비밀번호를 한번 더 입력해주세요.
</c:otherwise>
</c:choose>
</td>
</tr>
<input id="id" name="id" type="hidden" value="${sessionScope.id}">
						<tr><td><input type="password" class="form-control" name="pw" id="pwchk"></td>
							<td><input type="submit" class="sign-in-button btn-primary" id="sign-in"
								name="Sign-in" value="확인" /></td>
							
						</tr>
						<tr>
						<c:choose>
<c:when test="${msg == null}">
</c:when>
<c:otherwise>
<td><p class="help-block chkpw" id="chkpw">비밀번호를 확인해주세요</p></td>
</c:otherwise>
</c:choose>
</tr>
					
				</table>
		</form>
		
	</div>

</div>
