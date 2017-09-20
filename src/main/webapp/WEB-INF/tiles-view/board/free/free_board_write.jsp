<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script src="//code.jquery.com/jquery.min.js"></script>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath}"/>


    
     <form id="fileForm" action="fileUpload" method="post"
        enctype="multipart/form-data">
        <input type="file" id="fileUp" name="fileUp"/><br/><br/>
        <input type="file" id="fileUp2" name="fileUp2"/><br/><br/>
          
        아이디 : <input type="text" name="id" />
        비밀번호 : <input type="password" name="pw" /><br/><br/>
        <input type="button" value="전송하기" id="filestart" >
    </form>
 
<script>
$(function(){ 
	$('#filestart').click(function(){			
        var formData = new FormData($("#fileForm")[0]);
        $.ajax({
            type : 'post',
            url : 'board/fileUpload.do',
            data : formData,
            processData : false,
            contentType : false,
            success : function(html) {
                alert("파일 업로드하였습니다.");
            },
            error:function(request,status,error){
                alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
               }
        });
    });

});//ajax
</script>


