<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath}" />




<header id="header">
	<ul class="header-inner">
		<li class="pull-left" id="sidebar_menu"><a href="#"><i
				class="icon-menu"></i> <span class="hidden-xs">MENU</span></a></li>
		<li class="pull-left" id="top-search"><a href="#"><i
				class="icon-search"></i></a></li>
		<li class="logo"><a href="${path}/index.do">LYCLE</a></li>
		<c:choose>
			<c:when test="${sessionScope.id == null}">
				<li class="pull-right"><a href="#" data-toggle="modal"
					data-target="#myModal">JOIN</a> &nbsp;/&nbsp; <a href="#" data-toggle="modal"
					data-target="#Login">LOGIN</a></li>
			</c:when>
			<c:otherwise>
				<li class="pull-right">
				${sessionScope.name}님이로그인중입니다.
				&nbsp;/&nbsp;<a href="${path}/member/pwcheck.do">정보보기</a>
					&nbsp;/&nbsp; <a href="${path}/member/logout.do">로그아웃</a>
				</li>
			</c:otherwise>
		</c:choose>
	</ul>

	<!-- Top Search Content -->
	<div id="top-search-wrap">
		<input type="text"> <i id="top-search-close"
			class="icon-cancel"></i>
	</div>
</header>

<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="js-title-step"></h4>
			</div>
			<div class="modal-body">

				<!-- 1스텝 -->
				<div class="row hide" data-step="1" data-title="약관을 읽어주세요!">
					<div class="well">이용약관</div>
				</div>

				<!-- 2스텝 -->
				<div class="row hide" data-step="2" data-title="회원님을 알고싶어요!!!">
					<div class="well">
						<form role="form" name="reg" id="reg" method="post">
							<div class="form-group" id="divName">
								<label for="name">이름</label> 
								<input type="text"class="form-control onlyHangul" name="name" id="name"
									placeholder="이름">
							</div>
							<div class="form-group" id="divId">
								<label for="id">아이디</label> <input type="text"
									class="form-control onlyAlphabetAndNumber" name="id" id="id"
									placeholder="아이디">
									<span class="help-block"
									id="idhelp"></span>
							</div>
							<div class="form-group" id="divPassword">
								<label for="InputPassword1">비밀번호</label> <input type="password"
									class="form-control" name="pw" id="password" placeholder="비밀번호">
									<span class="help-block"
									id="pwhelp"></span>
							</div>
							<div class="form-group" id="divPasswordCheck">
								<label for="InputPassword2">비밀번호 확인</label> <input
									type="password" class="form-control" id="passwordCheck"
									placeholder="비밀번호 확인"> <span class="help-block"
									id="pwckhelp"></span>
							</div>
							<br>
							<!-- 이메일 -->
							<label for="inputEmail">이메일</label>
							<div class="input-group" id="divEmail">
								<input type="email" class="form-control" id="inputEmail" name="email"
									placeholder="이메일을 입력해주세요" /> <span class="input-group-btn">
									<button class="btn btn-primary" type="button" id="email">
										인증번호 전송<i class="fa fa-mail-forward spaceLeft"></i>
									</button>
								</span>
							</div>
							<p class="help-block" id="emailmsg"></p>
							<!-- 이메일 입력 체크 메시지 -->
							<!-- 이메일 끝 -->
							<!-- 이메일 체크 -->
							<div class="input-group" id="hidecheckmail">
								<span class="input-group-addon">인증번호 확인</span> <input
									class="form-control" id="inputEmailCheck" type="text"
									placeholder="인증번호"> <span class="input-group-btn">
									<button class="btn btn-primary" type="button" id="emailCheck">
										인증번호 확인<i class="fa fa-edit spaceLeft"></i>
									</button>
								</span>
							</div>
							<p class="help-block" id="emailcheckmsg"></p>
							<!-- 인증번호 체크 메시지 -->
							<p class="help-block" id="emailsend">
								<!-- 이메일을 보내고 난뒤 노출 -->
								<a href="" id="emaillink" target="_blank">메일을 보낸사이트 새창띄우기!</a> <span
									class="msg" id="emailtime"></span>
							</p>
							<!-- 이메일을 보내고난뒤 노출 끝 -->
							<!-- 이메일 체크 끝 -->
					</div>
				</div>

				<!-- 3스텝 -->
				<div class="row hide" data-step="3" data-title="거의 다 오셨습니다!">
					<div class="well">
						<!-- 성별 -->
							<label>성별</label>										
<div class="btn-group" data-toggle="buttons" id="divgender">
  <label class="btn btn-primary">
    <input type="radio" name="gender" id="gender" autocomplete="off" value="m" > 남자
  </label>
  <label class="btn btn-primary">
    <input type="radio" name="gender" id="gender" autocomplete="off" value="f"> 여자
  </label>
</div>
						<!-- /성별 -->

						<!-- 생일 -->
						<div class="form-group" id="divBirth">
							<label for="birth">생일</label> <input type="text"
								class="form-control onlyNumber" name="birth" id="birth"
								placeholder="ex)930608">
						</div>
						<!-- /생일 -->

						<!-- 전화번호 -->
						<div class="form-group" id="divPhoneNumber">
							<label for="tel">전화번호</label> <input type="text"
								class="form-control onlyNumber" name="tel"
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
						<!--  주소끝 -->
					</div>
				</div>
				<div class="row hide" data-step="4" data-title="설문조사에 참여해주세요!">
					<div class="well">
					
		<label>집에 자전거 있나요?</label>										
<div class="btn-group" data-toggle="buttons" id="divBikeyn">
  <label class="btn btn-primary">
    <input type="radio" name="bike_yn" id="bike_yn" autocomplete="off" value="y" > 자전거가 있어요
  </label>
  <label class="btn btn-primary">
    <input type="radio" name="bike_yn" id="bike_yn2" autocomplete="off" value="n"> 자전거가 없어요
  </label>
</div>
<br><br><br>
<div id="havenothave">
<label>자전거의 종류를 알려주세요!</label>
<select class="form-control" name="bike_type">
   <option value="default" selected>자전거 종류</option>
   <option value="로드">로드</option>
   <option value="MTB">MTB</option>
   <option value="하이브리드">하이브리드</option>
   <option value="미니벨로">미니벨로</option>
   <option value="픽시">픽시</option>
   <option value="투어링바이크">투어링바이크</option>
   <option value="none">기타</option>
</select> 
<br><br><br>
<label>혹시 일주일에 자전거를 몇번 타세요?</label>
<div class="btn-group" data-toggle="buttons">
  <label class="btn btn-primary">
    <input type="radio" name="how_many" id="option1" autocomplete="off" value="01" checked="checked" >0~1번
  </label>
  <label class="btn btn-primary">
    <input type="radio" name="how_many" id="option1" autocomplete="off" value="23" >2~3번
  </label>
  <label class="btn btn-primary">
    <input type="radio" name="how_many" id="option1" autocomplete="off" value="45" >4~5번
  </label>
  <label class="btn btn-primary">
    <input type="radio" name="how_many" id="option1" autocomplete="off" value="67">6~7번
  </label>
</div>
<br><br><br>
<label>가입하신 동호회가 있으신가요?</label>
 <label class="btn btn-primary">
    <input type="radio" name="club_yn" id="option2" autocomplete="off" value="y" checked="checked"> 가입한 동호회가 있어요
  </label>
  <label class="btn btn-primary">
    <input type="radio" name="club_yn" id="option2" autocomplete="off" value="n"> 가입한 동호회가 없어요
  </label>
  </div>
<br><br><br>
<div class="form-group">
<input type="text" class="form-control" name="join_root" id="name" placeholder="저희 사이트의 가입경로를 알려주세요">
</div>
<!-- 끝 ㅜㅜ -->						
</div>
</div>
</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default js-btn-step pull-left"
					data-orientation="cancel" data-dismiss="modal"></button>
				<button type="button" class="btn btn-warning js-btn-step"
					data-orientation="previous"></button>
				<button type="button" class="btn btn-primary js-btn-step"
					data-orientation="next" id="nextbt"></button>
			</div>
		</div>
	</div>
</div>
<!-- 회원가입모달끝 -->

<!-- 회원가입완료모달 -->
  <!-- Modal -->
  <div class="modal fade" id="myModal2" role="dialog" >
    <div class="modal-dialog modal-center" style="width:350px">
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-body">
          <p id="content"></p>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        </div>
      </div>
    </div>
  </div>  
<!-- 회원가입완료모달 끝 -->

<!-- 로그인 모달 -->
  <!-- Modal -->
  <div class="modal fade" id="Login" role="dialog">
    <div class="modal-dialog">
      <!-- Modal content-->
        <div class="modal-body">
          <!-- 본문 -->
<div class="login-form">
        <div class="login-top">
            <h1 class="login-header">SIGN IN</h1>
            <form method="post" id="loginform">
                <input type="text" id="user-name" name="id" />
                <label for="user-name" class="input-prefix">Id</label>
                
                <input type="password" id="user-password" name="pw" />
                <label for="password" class="input-prefix">Password</label>
                <span class="help-block" id="loginpwhelp"></span>
            <span>
                <input type="button" class="sign-in-button" id="sign-in" name="Sign-in" value="로그인" /> 
            </form>
           <input type="button" class="sign-in-button"  id="sign-up" name="Sign-up" value="가입할래요" /> 
              </span>      
        </div>
        <div class="login-bottom">
            <a href="${path}/member/findPw.do" class="forgot-password">비밀번호가 뭐였죠?</a>
        </div>
    </div>
<!-- 본문끝 -->
        </div>
      </div>
    </div>
<!-- 로그인 모달끝 -->





<script src="/SpringTiles/js/regfooter.js"></script>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>








