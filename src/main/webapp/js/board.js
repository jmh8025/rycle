
function write_submit(board) {

	//자유게시판은 카테고리명, 내용
	if (board == "free_board") {
		var cate_chk = document.getElementById("cate_chk");
		var scate_chk = document.getElementById("scate_chk");
		
		var content = document.getElementById("bcontent");
		var scontent = document.getElementById("scontent");
		
		if (cate_chk.value.length == 0) {
			scate_chk.innerHTML = "<b style='font-size:15px;color:red'>필수 정보입니다<b>";
		} else {
			scate_chk.innerHTML = "";
		}

		if (content.value.length == 0) {
			scontent.innerHTML = "<b style='font-size:15px;color:red'>필수 정보입니다<b>";
		} else {
			scontent.innerHTML = "";
		}
	}

		var subject = document.getElementById("subject");
		var ssubject = document.getElementById("ssubject");


		if (subject.value.length == 0) {
			ssubject.innerHTML = "<b style='font-size:15px;color:red'>필수 정보입니다<b>";
		} else {
			ssubject.innerHTML = "";
		}

		if (board == "free_board") {
			if (cate_chk.value.length != 0 && subject.value.length != 0 && content.value.length != 0) {
				var form = document.writeform;
				form.submit();
			}
		}	else {
			if (subject.value.length != 0) {
				var form = document.writeform;
				form.submit();
			}
		}

}

//file

// 이미지 파일 여부 판단
function checkImageType(fileName){
	var pattern = /jpg|gif|png|jpeg/i;
	return fileName.match(pattern);
}

// 업로드 파일 정보
function getFileInfo(fullName){
	var fileName, imgsrc, getLink, fileLink; 
	// 이미지 파일일 경우
	if(checkImageType(fullName)){
		// 이미지 파열 경로(썸네일)
		imgsrc = "/SpringTiles/board/upload/displayFile?fileName="+fullName;
		console.log(imgsrc);
		// 업로드 파일명
		fileLink = fullName.substr(14);
		console.log(fileLink);
		// 날짜별 디렉토리 추출
		var front = fullName.substr(0, 12);
		console.log(front);
		// s_를 제거한 업로드이미지파일명
		var end = fullName.substr(14);
		console.log(end);
		// 원본이미지 파일 디렉토리
		getLink = "/SpringTiles/board/upload/displayFile?fileName="+front+end;
		console.log(getLink);
	// 이미지 파일이 아닐경우
	} else {
		// UUID를 제외한 원본파일명
		fileLink = fullName.substr(12);
		console.log(fileLink);
		// 일반파일디렉토리 
		getLink = "/SpringTiles/board/upload/displayFile?fileName="+fullName;
		console.log(getLink);
	}
	// 목록에 출력할 원본파일명
	fileName = fileLink.substr(fileLink.indexOf("_")+1);
	console.log(fileName);
	// { 변수:값 } json 객체 리턴
	return {fileName:fileName, imgsrc:imgsrc, getLink:getLink, fullName:fullName};
}

$(document).ready(function() {
	   var path = window.location.pathname;
	   var gall_chk = "N";
	   
	   if(path.indexOf("gallery") != -1) {
	      gall_chk = "Y";
	   }
	
	// 1. 드래그 영역 기본 효과(바로보기)를 제한
	$("#fileDrop").on("dragenter dragover", function(event) {
		event.preventDefault(); // 기본효과를 제한
	});
	
	// 2. 파일 업로드
	// event : jQuery 이벤트, originalEvent : javascript 이벤트
	$("#fileDrop").on("drop", function(event) {
		event.preventDefault(); // 기본효과를 제한
		// 드래그된 파일의 정보
		var files = event.originalEvent.dataTransfer.files;
		// 첫번째 파일
		var file = files[0];
				// 콘솔에서 파일정보 확인
		console.log(file);
		// ajax로 전달할 폼 객체
		var formData = new FormData();
		// 폼 객체에 파일추가, append("변수명", 값)
		formData.append("file", file);
		
		$.ajax({
			type: "post",
			url: "/SpringTiles/upload/uploadAjax.do",
			data: formData,
			dataType: "text",
			// processData: true=> get방식, false => post방식
			processData: false,
			contentType: false,
			success: function(data) {
				//alert(data);
				console.log(data);

				var hadd = 20;
				var str = "";
				var file_name = "";

				alert(data);
				// 이미지파일이면 썸네일 이미지 출력
					if(checkImageType(data)){ 
					str = "<div><a href='/SpringTiles/upload/displayFile.do?fileName="+getImageLink(data)+"'>";
					str += "<img src='/SpringTiles/upload/displayFile.do?fileName="+data+"'></a>";
					hadd = 100;
				// 이미지 파일이 아니면 다운로드
				} else { 
					if(gall_chk == "Y"){ alert("이미지만 등록 가능합니다"); return false; }
					str = "<div><a href='/SpringTiles/upload/displayFile.do?fileName="+data+"'>"+getOriginalName(data)+"</a>";	

				}
					str += "<span data-src="+data+">[삭제]</span>";
					str += "<input type='hidden' name='file_name2' value='"+ getOriginalName(data) +"'>"; //원본파일명
					str += "<input type='hidden' name='ufile_name2' value='"+ data +"'></div>";					 //업로드명
					
				var height = document.getElementById('fileDrop' ).offsetHeight;
				
				$('#fileDrop').height( height + hadd );

				$("#fileDrop").append(str);
			}
		});
	});
		// 2. 파일 삭제
		// 태그.on("이벤트", "자손태그", "이벤트핸들러")
	$("#fileDrop").on("click", "span", function(event){
		alert("게시판명"+$("#board_file").val());
		
		//게시판명으로 파일 삭제 체크
		var board_file = $("#board_file").val();
		
		var that = $(this); // 여기서 this는 클릭한 span태그
		$.ajax({
			url: "/SpringTiles/upload/deleteFile.do",
			type: "post",
			// data: "fileName="+$(this).attr("date-src") = {fileName:$(this).attr("data-src")}
			// 태그.attr("속성")
			data: {fileName:$(this).attr("data-src"), board_file:board_file}, // json방식
			dataType: "text",
			success: function(result){
				if( result == "deleted" || result == "deleted_img" ){
					// 클릭한 span태그가 속한 div를 제거
					that.parent("div").remove();
					
					var height = document.getElementById('fileDrop' ).offsetHeight;
					
					if(result == "deleted_img") {
						$('#fileDrop').height( height - 100 );
					}	else {
						$('#fileDrop').height( height - 20 );
					}
					
				}
			}
		});
	});

});
	
// 원본파일이름
function getOriginalName(fileName) {
	// 이미지 파일이면
	if(checkImageType(fileName)) {
		var idx = fileName.indexOf("_",14)+1;
		return fileName.substr(idx);
	}
	// uuid를 제외한 원래 파일 이름을 리턴
	var idx = fileName.indexOf("_")+1;
	return fileName.substr(idx);
}

// 이미지파일 링크
function getImageLink(fileName) {
	// 이미지파일이 아니면
	if(!checkImageType(fileName)) { 
		return; // 함수 종료 
	}
	// 이미지 파일이면
	var front = fileName.substr(0, 12); // 연원일경로 추출
	var end = fileName.substr(14); // s_ 제거
	console.log(front);
	console.log(end);
	return front+end;
}

// 이미지파일 형식 체크
function checkImageType(fileName) {
	// i : ignore case(대소문자 무관)
	var pattern = /jpg|gif|png|jpeg/i; // 정규표현식
	return fileName.match(pattern); // 규칙이 맞으면 true
}

//*************************************댓글********************************************************//

$(document).ready(function() {
	
	// 1. 큰댓글
	$("#writeCmt").on("click", function(event) {
		event.preventDefault(); // 기본효과를 제한
		
		var bno = $("#bno").val();
		var cid = $("#sid").val();
		var cname = $("#sname").val();
		var ccontent = $("#ccontent").val();
		
		if(ccontent!=null){
			ccontent = ccontent.replace("<","&lt;");
			ccontent = ccontent.replace(">","&gt;");
		}
		alert(ccontent);
		
		$("#ccontent").val("");
		
		console.log("board_bno:"+bno);
		console.log("board_cid:"+cid);
		console.log("board_cname:"+cname);
		console.log("board_ccontent:"+ccontent);
		
		// ajax로 전달할 폼 객체
	    var allData = { "bno": bno, "cid": cid, "cname": cname, "ccontent": ccontent  };
		
		$.ajax({
			type: "post",
			url: "/SpringTiles/comment/insertAjax.do",
			data: allData,
			success: function(data) {
				
	            if(!data) {
	            	return alert("실패");
	            }

	            var cmtvo = data.cvo;
	            var html = '';
	            	            
	            html += '<tr id="tr_'+cmtvo.cno+'"><td width="150"><div>';
	            if (cmtvo.clevel > 1) {
	            	html += '&nbsp;&nbsp;&nbsp;&nbsp;<img src="img/reply_icon.gif">';
	            }
	            html += cmtvo.cid + '<br> <font size="2" color="lightgray">'+ cmtvo.cdate +'</font></div></td>';
	            html += '<td width="550"><div class="text_wrapper"><input type="hidden" id="ccontent_'+cmtvo.cno+'" value="'+cmtvo.ccontent+'">'+cmtvo.ccontent+'</div></td>';
	            
	            if(cid != null){
	            	html += '<td width="420"><div id="btn" style="text-align: center;">';		            
	            	if (cid == cmtvo.cid) {
		            	html += '<span class="cmt_sp" onclick="cmUpdateOpen('+cmtvo.cno+')">[수정]</span>&nbsp;<span class="cmt_sp" onclick="cmDeleteOpen('+cmtvo.cno+')">[삭제]</a>';
		       }
	            	
	            }
	            
	            html += '</div></td></tr>';  
	            html += '	<tr><td colspan=3><ul class="cmt_gubun"><li></li></ul></td></tr>';
	            $("#cmt_tb1").append(html);

			}
		});
	});

});


function cmUpdateOpen(cno){
	
	//내용 담기
	var cid = $("#sid").val();
	var ccontent = $("#ccontent_"+cno).val();
	$("#tr_"+cno).empty(); //empty 자식만 제거
	
	
	var html ='<td width="150" class="vmiddle"><p class="p_center">'+cid+'</p></td>';
	html +='<td width="550"><div><textarea name="ccontent" id="ccontent_'+cno+'" rows="4" cols="70">'+ccontent+'</textarea></div></td>';
	html +='<td width="100" class="center vmiddle"><p><p class="p_center cmt_sp" onclick="cmUpdateOK('+cno+')">[댓글등록]</p></td>';
	
	$("#tr_"+cno).append(html);
	
}

// 댓글 수정
function cmUpdateOK(cno){
	alert("cmUpdateOK실행!");
	
	var cid = $("#sid").val();
	var ccontent_cno = $("#ccontent_"+cno).val();
	
	alert("내용:"+ccontent_cno);
	
	// ajax로 전달할 폼 객체
    var allData = { "cno": cno, "ccontent": ccontent_cno };
	
	$.ajax({
		type: "post",
		url: "/SpringTiles/comment/updateAjax.do",
		data: allData,
		success: function(data) {

			if(!data) {
            	return alert("실패");
            }
			
			$("#tr_"+cno).empty(); //empty 자식만 제거
            var cmtvo = data.cvo;
            
            var html = "";
            
            html += '<td>'+cmtvo.cid+ '<br> <font size="2" color="lightgray">'+ cmtvo.cdate +'</font></div></td>';
            html += '<td width="550"><div class="text_wrapper"><input type="hidden" id="ccontent_'+cmtvo.cno+'" value="'+cmtvo.ccontent+'">'+cmtvo.ccontent+'</div></td>';
            html += '<td width="420">';
            if(cid != null){
            	html += '<div id="btn" style="text-align: center;">';		            
            	if (cid == cmtvo.cid) {
	            	html += '<span class="cmt_sp" onclick="cmUpdateOpen('+cmtvo.cno+')">[수정]</span>&nbsp;<span class="cmt_sp" onclick="cmDeleteOpen('+cmtvo.cno+')">[삭제]</a>';
            	}
            	
           }
            
        	$("#tr_"+cno).append(html);
            
		}
	});
	
}

//댓글 삭제
function cmDeleteOpen(cno){
	
	// ajax로 전달할 폼 객체
    var allData = { "cno": cno };
	
	$.ajax({
		type: "post",
		url: "/SpringTiles/comment/deleteAjax.do",
		data: allData,
		success: function(data) {
			
            $("#tr_"+data).next().remove();
            $("#tr_"+data).remove();
		}
	});
	
}



//답글창
function cmReplyOpen(val) {

	var idx = val;

	var cpno = $("#cno_"+idx).val(); 
	var clevel = parseInt($("#clevel_"+idx).val())+1; //부모 level +1
	
	alert ("cpno:"+cpno+", clevel:"+clevel);
	
	var html = '';
    var nbsp = '';
    
    $("#tr_"+last_idx).remove();
    
    html += '<tr id="tr_"'+last_idx+'><td width="150">';
    
    for(var i=1; i<clevel; i++){ nbsp += "&nbsp;&nbsp;&nbsp;&nbsp"; }
    
    if (clevel > 1) {
    	html += nbsp+'ㄴ<img src="img/reply_icon.gif">';
    }
    
    html += cid + '<br> </td>';
    html += '<td width="550"><div class="text_wrapper"></div>';
    html += '<textarea id="ccontent_'+last_idx+'" name="ccontent_'+last_idx+'" rows="4" cols="70"></textarea>';
    html += '<input type="hidden" name="cpno_'+last_idx+'" id="cpno_'+last_idx+'" value="'+cpno+'" >';
    html += '<input type="hidden" name="clevel_'+last_idx+'" id="clevel_'+last_idx+'" value="'+clevel+'" ></td>';
    html += '<td width="150"><div id="btn" style="text-align: center;"><a onclick="writeReCmt('+last_idx+');">[댓글등록]</a>&nbsp;';
    
    html += '</div></td></tr>';  
    
    $("#tr_"+idx).after(html);
 
}

function writeReCmt(idx) {
	
	var cpno = $("#cpno_"+idx).val();
	var ccontent = $("#ccontent_"+idx).val();
	var clevel = $("#clevel_"+idx).val();
	
	alert ("cpno:"+cpno+", ccontent:"+ccontent+", clevel:"+clevel);

	// ajax로 전달할 폼 객체
    var allData = { "bno": bno, "cid": cid, "cname": cname, "ccontent": ccontent, "cpno":cpno, "clevel" : clevel  };
	
	$.ajax({
		type: "post",
		url: "/SpringTiles/comment/insertAjax.do",
		data: allData,
		
		success: function(data) {
			
            if(!data) {
            	return alert("실패");
            }

            var cmtvo = data.cvo;
            var html = '';

            $("#last_idx").val(parseInt(last_idx)+1);
            
            html += '<tr id="tr_'+last_idx+'"><td width="150"><div>';
            if (cmtvo.clevel > 1) {
            	html += '&nbsp;&nbsp;&nbsp;&nbsp;<img src="img/reply_icon.gif">';
            }
            html += cmtvo.cid + '<br> <font size="2" color="lightgray">'+ cmtvo.cdate +'</font></div></td>';
            html += '<td width="550"><div class="text_wrapper">'+ cmtvo.ccontent+ '</div></td>';
            
            if(cid != null){																	
            	html += '<td width="150"><div id="btn" style="text-align: center;"><a onclick="cmReplyOpen('+last_idx+');">[답변]</a>&nbsp;';
	            
            	if (cid == cmtvo.cid) {
	            	html += '<span >[수정]</span>&nbsp;<a onclick="cmDeleteOpen('+last_idx+');">[삭제]</a>';
	            }
            }
            
            html += '</div></td></tr>';  
            
            $("#cmt_tb1").append(html);

		}
	});
}