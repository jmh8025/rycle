$(function(){ 
	/*이메일부분*/
	$('#emailmsg').hide()
	$('#emaillink').hide()
	$('#emailsend').hide()
	$('#hidecheckmail').hide()
	var auth;
	var authTime=300; //인증번호 타이머
	$('#email').click(function(){			
		var $email = $('#inputEmail').val()
		var $regEmail = /([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/
		var $emailsite = $email.slice($email.indexOf("@")+1,$email.length)
		//문자열.slice(시작부분,끝부분)
		//$email.indexOf("@") : @부터
		//$email.length : 끝자리
		
		//정규식을 이용하여 올바른 이메일 입력시 전송되도록함
		if(!$regEmail.test($email)){
			$('#emailmsg').show()
			$('#emailmsg').text('올바른 이메일을 입력해주세요.')
		}else{
        $.ajax({
                type : "POST",
                url : "member/sendMail.do", //mv로 전송후
                dataType: "json",
                data : {
                	"email":$email
                },
                success : function(data) {//return값을 String으로 반환받음
                	$('#hidecheckmail').show()
                	$('#emailmsg').show()
               	   	$('#emailmsg').text('인증번호가 발송되었습니다. 메일을 확인해주세요(인증유효시간 5분)')
               	   	$('#emailsend').show()
               	   	$('#emaillink').show()
               	   	$('#emaillink').attr("href","http://www."+$emailsite)
                   if(authTime<300){ //버튼 다시누르면 시간을 초기화합니다.
                	   authTime=300;
                	   clearinterval(authTimerId)
                   } 
            		authTimerId = setInterval(function(){
            			timetime = Math.floor(authTime / 60) + "분 " + (authTime % 60) + "초";
            			  $('#emailtime').text(timetime).css('color','#33A6DC').css('text-align','right');
            			  --authTime;
            			  if(authTime<0){ //타임아웃시 
            				  $('#emailtime').text('인증번호 유효시간이 만료되었습니다. 재발급받으세요');
            				  clearinterval(authTimerId)
            			  }
            		},1000)
                },
                error:function(request,status,error){
                    alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
                   }

        });//ajax
		}//if문끝
	})//email.click

 	$('#emailCheck').click(function(){
		var $EmailCheck2=$('#inputEmailCheck').val()
 		$.ajax({
            type : "POST",
            url : "member/checkMail.do", //mv로 전송후
            data : {
            	"auth":$EmailCheck2 //사용자가입력한 인증번호전송
            },
            success : function(data) {//return값을 String으로 반환받음
            	if(data==true){
        			$('#emailcheckmsg').text('인증되었습니다.').css("color","red");
        			//버튼 비활성화
        			$('#emailCheck').prop("disabled", true);
        			$('#email').prop("disabled", true);
        			//입력텍스트 비활성화
        			$('#inputEmailCheck').attr("readonly",true);
        			$('#inputEmail').attr("readonly",true);
        			//입력시간 비활성화
        			$('#emaillink').hide();
        			$('#emailtime').hide();
        			$('#nextbt').removeAttr('disabled');
        		}else{
        			$('#emailcheckmsg').text('인증키를 확인하여주세요.').css("color","red");
        		} 
            },
            error : function(e) {
                   alert('서버 연결 도중 에러가 났습니다. 다시 시도해 주십시오.');
            }
    });//ajax 
	})//emailCheck.click
	/*이메일끝*/
	
	
	$('.onlyAlphabetAndNumber').keyup(function(event){
                    if (!(event.keyCode >=37 && event.keyCode<=40)) {
                        var inputVal = $(this).val();
                        $(this).val($(this).val().replace(/[^_a-z0-9]/gi,'')); //_(underscore), 영어, 숫자만 가능
                    }
                });
                 
                $(".onlyHangul").keyup(function(event){
                    if (!(event.keyCode >=37 && event.keyCode<=40)) {
                        var inputVal = $(this).val();
                        $(this).val(inputVal.replace(/[a-z0-9]/gi,''));
                    }
                });
             
                $(".onlyNumber").keyup(function(event){
                    if (!(event.keyCode >=37 && event.keyCode<=40)) {
                        var inputVal = $(this).val();
                        $(this).val(inputVal.replace(/[^0-9]/gi,''));
                    }
                });
                 
                //------- 검사하여 상태를 class에 적용
                $('#id').keyup(function(event){
                     
                    var divId = $('#divId');
                     
                    if($('#id').val()==""){
                        divId.removeClass("has-success");
                        divId.addClass("has-error");
                    }else{
                        divId.removeClass("has-error");
                        divId.addClass("has-success");
                    }
                });
                 
                $('#password').keyup(function(event){
                     
                    var divPassword = $('#divPassword');
                     
                    if($('#password').val()==""){
                        divPassword.removeClass("has-success");
                        divPassword.addClass("has-error");
                    }else{
                        divPassword.removeClass("has-error");
                        divPassword.addClass("has-success");
                    }
                });
                 
                $('#passwordCheck').keyup(function(event){
                    var passwordCheck = $('#passwordCheck').val();
                    var password = $('#password').val();
                    var divPasswordCheck = $('#divPasswordCheck');
                     
                    if((passwordCheck=="") || (password!=passwordCheck)){
                        divPasswordCheck.removeClass("has-success");
                        divPasswordCheck.addClass("has-error");
                        $('#pwhelp').text("안맞아요");
                    }else{
                        divPasswordCheck.removeClass("has-error");
                        divPasswordCheck.addClass("has-success");
                        $('#pwhelp').hide();
                    }
                });
                 
                $('#name').keyup(function(event){
                     
                    var divName = $('#divName');
                     
                    if($.trim($('#name').val())==""){
                        divName.removeClass("has-success");
                        divName.addClass("has-error");
                    }else{
                        divName.removeClass("has-error");
                        divName.addClass("has-success");
                    }
                });
                 
        
                 
          
                 
                $('#phoneNumber').keyup(function(event){
                     
                    var divPhoneNumber = $('#divPhoneNumber');
                     
                    if($.trim($('#phoneNumber').val())==""){
                        divPhoneNumber.removeClass("has-success");
                        divPhoneNumber.addClass("has-error");
                    }else{
                        divPhoneNumber.removeClass("has-error");
                        divPhoneNumber.addClass("has-success");
                    }
                });




	
	
	
	
	
	
	
})//jquery