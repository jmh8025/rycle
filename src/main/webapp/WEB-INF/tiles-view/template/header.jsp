<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath}"/>




 <header id="header">
        <ul class="header-inner">
            <li class="pull-left" id="sidebar_menu"><a href="#"><i class="icon-menu"></i> <span class="hidden-xs">MENU</span></a></li>
            <li class="pull-left" id="top-search"><a href="#"><i class="icon-search"></i></a></li>
            <li class="logo"><a href="#">LYCLE</a></li>
        <c:choose>
    <c:when test="${sessionScope.userId == null}">
        <li class="pull-right"><a href="#" data-toggle="modal" data-target="#myModal">JOIN</a> &nbsp;/&nbsp; <a href="#">LOGIN</a></li>
        </c:when>
    <c:otherwise>
    <li class="pull-right">${sessionScope.userName}님이 로그인중입니다. &nbsp;/&nbsp; <a href="${path}/member/logout.do">로그아웃</a></li>
     </c:otherwise>
</c:choose>
    </ul>

   <!-- Top Search Content -->
        <div id="top-search-wrap">
            <input type="text">
            <i id="top-search-close" class="icon-cancel"></i>
        </div>
    </header>
    
    <!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h4 class="js-title-step"></h4>
      </div>
      <div class="modal-body">
      
      
       <div class="row hide" data-step="1" data-title="회원가입">
          <div class="well">
			이용약관 넣을예정
          </div>
           </div>
           
           <!--  -->
            <div class="row hide" data-step="2" data-title="회원가입">
          <div class="well">
          
          <form role="form">
          <div class="form-group">
              <label for="userid">아이디</label>
              <input type="text" class="form-control" name="id" id="username" placeholder="아이디">
            </div>
            <div class="form-group">
              <label for="InputPassword1">비밀번호</label>
              <input type="password" class="form-control" id="InputPassword1" placeholder="비밀번호">
            </div>
            <div class="form-group">
              <label for="InputPassword2">비밀번호 확인</label>
              <input type="password" class="form-control" id="InputPassword2" placeholder="비밀번호 확인">
              <p class="help-block">비밀번호 확인을 위해 다시한번 입력 해 주세요</p>
            </div>
            <div class="form-group">
              <label for="InputEmail">이메일 주소</label>
              <input type="email" class="form-control" id="InputEmail" placeholder="이메일 주소">
            </div>
            <div class="form-group">
              <label for="username">이름</label>
              <input type="text" class="form-control" id="username" placeholder="이름을 입력해 주세요">
            </div>
     
			 <div class="form-group">
              <label for="username">휴대폰 인증</label>
              <div class="input-group">
                <input type="tel" class="form-control" id="username" placeholder="- 없이 입력해 주세요">
                <span class="input-group-btn">
                  <button class="btn btn-success">인증번호 전송<i class="fa fa-mail-forward spaceLeft"></i></button>
                </span>
              </div>
            </div>
            <div class="form-group">
              <label for="username">인증번호 입력</label>
              <div class="input-group">
                <input type="text" class="form-control" id="username" placeholder="인증번호">
                <span class="input-group-btn">
                  <button class="btn btn-success">인증번호 입력<i class="fa fa-edit spaceLeft"></i></button>
                </span>
              </div>
            </div>
            <div class="form-group">
                <label>약관 동의</label>
              <div data-toggle="buttons">
              <label class="btn btn-primary active">
                  <span class="fa fa-check"></span>
                  <input id="agree" type="checkbox" autocomplete="off" checked>
              </label>
              <a href="#">이용약관</a>에 동의합니다.
              </div>
            </div>
            <div class="form-group text-center">
              <button type="submit" class="btn btn-info">회원가입<i class="fa fa-check spaceLeft"></i></button>
              <button type="submit" class="btn btn-warning">가입취소<i class="fa fa-times spaceLeft"></i></button>
            </div>
            <div class = "row">
        <div class = "col-md-4">
            <div class = "input-group">
                <span class = "input-group-addon">우편번호</span>
                <input type = "text" class = "form-control" id = "zip_code" name = "zip_code">
                <span class = "input-group-addon"><a href = "#" id = "zip_codeBtn" data-toggle="modal" data-target="#zip_codeModal">검색하기</a></span>                
            </div>
        </div>
    </div>
    <div class = "row">
        <div class = "col-md-4">
            <div class = "input-group">
                <span class = "input-group-addon">주소</span>
                <input type = "text" class = "form-control" id = "address1">                
            </div>
        </div>
    </div>        
    <div class = "row">
        <div class = "col-md-4">
            <div class = "input-group">
                <span class = "input-group-addon">상세주소</span>
                <input type = "text" class = "form-control" id = "address2">        
            </div>
        </div>
    </div>    
          </form>
          	
          	
          </div>
           </div>
           
           
              <!--  -->
        <div class="row hide" data-step="3" data-title="This is the second and last step!">
          <div class="well">
          <table border="1">
          <tr>
				<td>이메일주소</td>
				<td><input name="userEmail"></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="submit" value="확인">
					<input type="reset" value="취소">
				</td>
			</tr>
          	</table>
          </div>
        </div>
           </form>	
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default js-btn-step pull-left" data-orientation="cancel" data-dismiss="modal"></button>
        <button type="button" class="btn btn-warning js-btn-step" data-orientation="previous"></button>
        <button type="button" class="btn btn-success js-btn-step" data-orientation="next"></button>
      </div>
    </div>
  </div>
</div>


<div class="modal fade" id="zip_codeModal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header text-center">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">×</span>
                </button>
                <h3 class="modal-title" id="myModalLabel">우편번호</h3>
            </div>    
            <div class="modal-body text-center">
                 <form id = "zip_codeForm">
                         <div class = "input-group">
                            <span class = "input-group-addon">동 입력</span>
                            <input type="text" class = "form-control" name="query" id="query">
                            <span class = "input-group-btn">                                                
                                <input type="submit" class = "btn btn-warning" value="검색" id="searchBtn" onkeydown="javascript:if(event.keyCode==13)">                                            
                            </span>
                        </div>
                </form>
                <p>
                </p>
                <div>
                <div style="width:100%; height:200px; overflow:auto">
                       <table class = "table text-center">
                        <thead>
                            <tr>
                                <th style="width:150px;">우편번호</th>
                                <th style="width:600px;">주소</th>
                                </tr>
                        </thead>
                        <tbody id="zip_codeList"></tbody>
                    </table>
                </div>
                </div>
            </div>
        </div>
    </div>
</div>







<script>
$('#myModal').modalSteps();
</script>

    
    
    
    
     

