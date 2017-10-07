

$(document).ready(function() {
	
	var path=location.host + location.pathname.slice(0,location.pathname.indexOf("/",2)+1);


/*	var sendMail = "http://"+path+"member/sendMail.do";*/
	$('#havenothave').hide()
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
			$('#emailmsg').text('올바른 이메일을 입력해주세요.').css("color","red");
		}else{
			$('#emailmsg').show()
			$('#emailmsg').text('지금 메일을 보내고있어요. 조금만 기다려주세요..').css("color","blue")
        $.ajax({
                type : "POST",
                url : "http://"+path+"member/sendMail.do", //mv로 전송후
                data : {
                	"email":$email
                },
                success : function(data) {//return값을 String으로 반환받음
                	if(data==false){
                		alert("중복")
                		$('#emailmsg').text('이미있는 이메일이에요.. 다른 이메일을 입력해주세요').css("color","red")
                	}else if(data==true){
                	$('#hidecheckmail').show()
                	$('#emailmsg').show()
               	   	$('#emailmsg').text('인증번호가 발송되었습니다. 메일을 확인해주세요(인증유효시간 5분)').css("color","blue");
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
                }//else
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
            url : "http://"+path+"member/checkMail.do", //mv로 전송후
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
        			$('#divEmail').removeClass("has-error");
                	$('#divEmail').addClass("has-success");
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
	
	//주소 null값 초기화
	$('#divAddress').removeClass("has-success");
   $('#divAddress').addClass("has-error");
   //바이크 유무 초기화
   $('#divBikeyn').removeClass("has-success");
	$('#divBikeyn').addClass("has-error");
	//성별 초기화
	$('#divgender').removeClass("has-success");
	$('#divgender').addClass("has-error");
	
	
	
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
                	if($('#id').val().length<7){
                		$('#idhelp').text("7글자 이상입력해주세요").css("color","red");
                	}else{
                    $.ajax({
                        type : "POST",
                        url : "http://"+path+"member/checkId.do", //mv로 전송후
                        dataType: "json",
                        data : {
                        	"Id":$('#id').val()
                        },
                        success : function(data) {
                        	
                        	if((data==true)&&($('#id').val()!='')){
                        		$('#idhelp').show();
                        		$('#divId').removeClass("has-success");
                        		$('#divId').addClass("has-error");
                        		$('#idhelp').text("있는 아이디에요").css("color","red");
                        	}else if(data==false){
                        		$('#divId').removeClass("has-error");
                        		$('#divId').addClass("has-success");
                        		$('#idhelp').hide();
                        	}
                        }, error:function(request,status,error){
                            alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
                        }
                    });
                	}
               
                });
                 
                $('#password').keyup(function(event){
                     
                    var pwcheck = /^(?=.*[a-zA-Z])(?=.*[!@#$%^~*+=-])(?=.*[0-9]).{10,20}$/;
                	var pw = $('#password');
                	
                    if(!pwcheck.test(pw.val())||pw.val()=="") {
                    	$('#divPassword').removeClass("has-success");
                    	$('#divPassword').addClass("has-error");
                		$('#pwhelp').html('비밀번호는 10글자 이상 영문자+특수문자+숫자 조합해야함').css('color','red');
                    }	else {
                    	$('#divPassword').removeClass("has-error");
                    	$('#divPassword').addClass("has-success");
                        $('#pwhelp').hide();
                    }
                });
                 
                $('#passwordCheck').keyup(function(event){

                     
                     if($('#password').val()!=$('#passwordCheck').val() || $('#passwordCheck').val()==""){
                    	$('#divPasswordCheck').removeClass("has-success");
                    	$('#divPasswordCheck').addClass("has-error");
                        $('#pwckhelp').text("같은 비밀번호를 입력해주세요").css("color","red");
                    }else{
                    	$('#divPasswordCheck').removeClass("has-error");
                    	$('#divPasswordCheck').addClass("has-success");
                        $('#pwckhelp').hide();
                    }
                });
        
                 
                $('#name').keyup(function(event){
                     
                     
                    if($.trim($('#name').val())==""){
                    	$('#divName').removeClass("has-success");
                    	$('#divName').addClass("has-error");
                    }else{
                    	$('#divName').removeClass("has-error");
                    	$('#divName').addClass("has-success");
                    }
                });
                $('#phoneNumber').keyup(function(event){
                    if($.trim($('#phoneNumber').val())==""){
                    	$('#divPhoneNumber').removeClass("has-success");
                    	$('#divPhoneNumber').addClass("has-error");
                    }else{
                    	$('#divPhoneNumber').removeClass("has-error");
                    	$('#divPhoneNumber').addClass("has-success");
                    }
                    
                });
                $('#birth').keyup(function(event){
                    if($.trim($('#birth').val())==""){
                    	$('#divBirth').removeClass("has-success");
                    	$('#divBirth').addClass("has-error");
                    }else{
                    	$('#divBirth').removeClass("has-error");
                    	$('#divBirth').addClass("has-success");
                    }
                    
                });
              

    $('input[name=bike_yn]').change(function(){
    	$('#divBikeyn').removeClass("has-error");
    	$('#divBikeyn').addClass("has-success");
    		if($(this).val()=="y"){
    			$('#havenothave').show()
    		}else if($(this).val()=="n"){
    			$('#havenothave').hide()
    		}
    });
    $('input[name=gender]').change(function(){
    	$('#divgender').removeClass("has-error");
    	$('#divgender').addClass("has-success");
    });




//모달스텝
/* global jQuery */

    'use strict';

    $.fn.modalSteps = function(options){
        var $modal = this;

        var settings = $.extend({
            btnCancelHtml: 'Cancel',
            btnPreviousHtml: 'Previous',
            btnNextHtml: 'Next',
            btnLastStepHtml: 'Complete',
            disableNextButton: false,
            completeCallback: function(){
            //완료버튼누르면 모든걸 확인
            		  $.ajax({
            	            type : "POST",
            	            url : "http://"+path+"member/insert.do", //mv로 전송후
            	            data : $('#reg').serialize(), //폼값전부 전송
            	            dataType: "text",
            	            success : function(data) {
            	            	//얻어온 값을 이용하여, modal 에서 동적으로 바뀌어야 하는 값을 바꾸어 준다..
            	        	    $("#content").html("<h2>"+data+"님 환영해요!!</h2><br>" +
            	        	    		"<img src='img/reg.png'>");

            	        	    //modal을 띄워준다.  
            	        	    $("#myModal2").modal('show');
            	            },
            	            error:function(request,status,error){
            	                alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
            	           }
            	           })//ajax
            },//callback
            callbacks: {}
        }, options);
        var validCallbacks = function(){
            var everyStepCallback = settings.callbacks['*'];

            if (everyStepCallback !== undefined && typeof(everyStepCallback) !== 'function'){
                throw 'everyStepCallback is not a function! I need a function';
            }

            if (typeof(settings.completeCallback) !== 'function') {
                throw 'completeCallback is not a function! I need a function';
            }
            for(var step in settings.callbacks){
                if (settings.callbacks.hasOwnProperty(step)){
                    var callback = settings.callbacks[step];

                    if (step !== '*' && callback !== undefined && typeof(callback) !== 'function'){
                        throw 'Step ' + step + ' callback must be a function';
                    }
                }
            }
        };
        var executeCallback = function(callback){
            if (callback !== undefined && typeof(callback) === 'function'){
                callback();
                return true;
            }
            return false;
        };
        $modal
            .on('show.bs.modal', function(){
                var $modalFooter = $modal.find('.modal-footer'),
                    $btnCancel = $modalFooter.find('.js-btn-step[data-orientation=cancel]'),
                    $btnPrevious = $modalFooter.find('.js-btn-step[data-orientation=previous]'),
                    $btnNext = $modalFooter.find('.js-btn-step[data-orientation=next]'),
                    everyStepCallback = settings.callbacks['*'],
                    stepCallback = settings.callbacks['1'],
                    actualStep,
                    $actualStep,
                    titleStep,
                    $titleStepSpan,
                    nextStep;
                //초기화
                $btnNext.removeAttr('disabled');
                if (settings.disableNextButton){
                    $btnNext.attr('disabled', 'disabled');
                }
                $btnPrevious.attr('disabled', 'disabled');

                validCallbacks();
                executeCallback(everyStepCallback);
                executeCallback(stepCallback);

                // Setting buttons
                $btnCancel.html(settings.btnCancelHtml);
                $btnPrevious.html(settings.btnPreviousHtml);
                $btnNext.html(settings.btnNextHtml);

                $actualStep = $('<input>').attr({
                    'type': 'hidden',
                    'id': 'actual-step',
                    'value': '1',
                });

                $modal.find('#actual-step').remove();
                $modal.append($actualStep);
                actualStep = 1;
                nextStep = actualStep + 1;
                $modal.find('[data-step=' + actualStep + ']').removeClass('hide');
                $btnNext.attr('data-step', nextStep);
                titleStep = $modal.find('[data-step=' + actualStep + ']').data('title');
                $titleStepSpan = $('<span>')
                                    .addClass('label label-primary')
                                    .html(actualStep);
                $modal
                    .find('.js-title-step')
                    .append($titleStepSpan)
                    .append(' ' + titleStep);
            })
            .on('hidden.bs.modal', function(){
                var $actualStep = $modal.find('#actual-step'),
                    $btnNext = $modal.find('.js-btn-step[data-orientation=next]');
                $modal
                    .find('[data-step]')
                    .not($modal.find('.js-btn-step'))
                    .addClass('hide');
                $actualStep
                    .not($modal.find('.js-btn-step'))
                    .remove();
                $btnNext
                    .attr('data-step', 1)
                    .html(settings.btnNextHtml);
                $modal.find('.js-title-step').html('');
            });
        $modal.find('.js-btn-step').on('click', function(){
            var $btn = $(this),
                $actualStep = $modal.find('#actual-step'),
                $btnPrevious = $modal.find('.js-btn-step[data-orientation=previous]'),
                $btnNext = $modal.find('.js-btn-step[data-orientation=next]'),
                $title = $modal.find('.js-title-step'),
                orientation = $btn.data('orientation'),
                actualStep = parseInt($actualStep.val()),
                everyStepCallback = settings.callbacks['*'],
                steps,
                nextStep,
                $nextStep,
                newTitle;
            //버튼 넘기기 초기화
            steps = $modal.find('div[data-step]').length;
            // Callback on Complete
            if ($btn.attr('data-step') === 'complete'){
            	 if(($('#divEmail').hasClass('has-success'))
                         &&($('#divName').hasClass('has-success'))
                         &&($('#divPassword').hasClass('has-success'))
                         &&($('#divPasswordCheck').hasClass('has-success'))
                         &&($('#divBirth').hasClass('has-success'))
                         &&($('#divPhoneNumber').hasClass('has-success'))
                         &&($('#divId').hasClass('has-success'))
                         &&($('#divBikeyn').hasClass('has-success'))
                         &&($('#divgender').hasClass('has-success'))
               		  &&($('#divAddress').hasClass('has-success'))){
                settings.completeCallback();
                $modal.modal('hide');
            	 }else{
            		 alert("입력하신 값을 다시한번 확인해주세요!")
            	 }
                return;
            }
            // Check the orientation to make logical operations with actualStep/nextStep
            if (orientation === 'next'){
                nextStep = actualStep + 1;
                $btnPrevious.attr('data-step', actualStep);
                $actualStep.val(nextStep);
            } else if (orientation === 'previous'){
                nextStep = actualStep - 1;
                $btnNext.attr('data-step', actualStep);
                $btnPrevious.attr('data-step', nextStep - 1);

                $actualStep.val(actualStep - 1);
            } else {
                $modal.modal('hide');
                return;
            }
            if (parseInt($actualStep.val()) === steps){
                $btnNext
                    .attr('data-step', 'complete')
                    .html(settings.btnLastStepHtml);
            } else {
                $btnNext
                    .attr('data-step', nextStep)
                    .html(settings.btnNextHtml);
            }
            if (settings.disableNextButton){
                $btnNext.attr('disabled', 'disabled');
            }
            // Hide and Show steps
            $modal
                .find('[data-step=' + actualStep + ']')
                .not($modal.find('.js-btn-step'))
                .addClass('hide');
            $modal
                .find('[data-step=' + nextStep + ']')
                .not($modal.find('.js-btn-step'))
                .removeClass('hide');
            // Just a check for the class of previous button
            if (parseInt($btnPrevious.attr('data-step')) > 0 ){
                $btnPrevious.removeAttr('disabled');
            } else {
                $btnPrevious.attr('disabled', 'disabled');
            }
            if (orientation === 'previous'){
                $btnNext.removeAttr('disabled');
            }
            // Get the next step
            $nextStep = $modal.find('[data-step=' + nextStep + ']');
            //메일인증받으면 넘어가게하자
            if (parseInt($nextStep.attr('data-step')) == 2 ){
            	if($('#emailCheck').prop("disabled")){
            		$btnNext.removeAttr('disabled');
            	}else{
            		$nextStep.attr('disabled', 'disabled');
            	} 
            }
            // Verify if we need to unlock continue btn of the next step
            if ($nextStep.attr('data-unlock-continue')){
                $btnNext.removeAttr('disabled');
            }
            // Set the title of step
            newTitle = $nextStep.attr('data-title');
            var $titleStepSpan = $('<span>')
                                .addClass('label label-primary')
                                .html(nextStep);
            $title
                .html($titleStepSpan)
                .append(' ' + newTitle);
            var stepCallback = settings.callbacks[$actualStep.val()];
            executeCallback(everyStepCallback);
            executeCallback(stepCallback);
        });
        $('#emailCheck').click(function(){
        });
        return this;
    };
    
	$('#sign-up').click(function(){
		$("#Login").modal('hide');
		$('#myModal').modal('show');
		
	});
	
	//로그인버튼 클릭시
	$('#sign-in').click(function(){
		 $.ajax({
	            type : "POST",
	            url : "http://"+path+"member/loginCheck.do", //mv로 전송후
	            data : $('#loginform').serialize(), //폼값전부 전송
	            dataType: "json",
	            success : function(data) {
	            	//얻어온 값을 이용하여, modal 에서 동적으로 바뀌어야 하는 값을 바꾸어 준다..
	        	  if(data==true){
	        		  $("#Login").modal('hide');
	        		  location.reload();
	        	  }
	        	  else if(data==false){
	        		  $('#loginpwhelp').show();
	        		  $('#user-password').text();
	        		  $('#loginpwhelp').text("아이디 혹은 비밀번호를 확인해 주세요").css("color","red");
	        	  }
	            },
	            error:function(request,status,error){
	                alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
	           }
	           })//ajax
	});
	
	//엔터키를 눌렀을때도 동작하도록
	$('#user-password').keypress(function(event){
	     if ( event.which == 13 ) {
	         $('#sign-in').click();
	         return false;
	     }
	});
	$('#user-name').keypress(function(event){
	     if ( event.which == 13 ) {
	         $('#sign-in').click();
	         return false;
	     }
	});
	
	//아이디나 비밀번호 포커스시
	$('#user-password').focus(function(){
		$('#loginpwhelp').hide();
		});
	$('#user-name').focus(function(){
		$('#loginpwhelp').hide();
		});
	
	$('#pwchk').focus(function(){
		$('#chkpw').hide();
	});

	
	
    //회원수정
    $('#password2').keyup(function(event){
        
        var pwcheck = /^(?=.*[a-zA-Z])(?=.*[!@#$%^~*+=-])(?=.*[0-9]).{10,20}$/;
    	var pw = $('#password2');
    	
        if(!pwcheck.test(pw.val())||pw.val()=="") {
        	$('#divPassword2').removeClass("has-success");
        	$('#divPassword2').addClass("has-error");
    		$('#pwhelp2').html('비밀번호는 10글자 이상 영문자+특수문자+숫자 조합해야함').css('color','red');
        }	else {
        	$('#divPassword2').removeClass("has-error");
        	$('#divPassword2').addClass("has-success");
            $('#pwhelp2').hide();
        }
    });
     
    $('#passwordCheck2').keyup(function(event){
         if($('#password2').val()!=$('#passwordCheck2').val() || $('#passwordCheck2').val()==""){
        	$('#divPasswordCheck2').removeClass("has-success");
        	$('#divPasswordCheck2').addClass("has-error");
            $('#pwckhelp2').text("같은 비밀번호를 입력해주세요").css("color","red");
        }else{
        	$('#divPasswordCheck2').removeClass("has-error");
        	$('#divPasswordCheck2').addClass("has-success");
            $('#pwckhelp2').hide();
        }
    });
	
    $('#membermodify').click(function(){
    	if($('#divPassword2').hasClass('has-success')
    			&& $('#divPasswordCheck2').hasClass('has-success')){//비밀번호일치시
    		document.getElementById('membermodi').submit();
    		alert("정상적으로 수정되었습니다. 다시 로그인해주세요")
            return false;
        	 }else{
        		 alert("입력하신 값을 다시한번 확인해주세요!")
        	 }
    })
    
		
	
	//회원수정끝
	
    
})
