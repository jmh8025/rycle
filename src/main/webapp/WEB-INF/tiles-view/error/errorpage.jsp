<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
  <script>
      function window_onload(){
         setTimeout('go_url()',5000)  
      }
      function go_url(){
         location.href=history.back()
      }
  </script>
<body onload="javascript:window_onload()">
	<div class="content_inner">	
	<div class="errors">
<center>
<h2>맙소사!</h2><br>
	<img src="/img/error.png" width="50%" height="50%">
 <span>
            <h3> 죄송해요 페이지 오류가 발생했어요ㅜㅜ<br>
            <span class="r">5초 뒤에 </span>자동으로 뒤로 돌아가져요.</h3></span>
</center>
    </div>
    </div>
 </body>

