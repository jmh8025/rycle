$(document).ready(function(){
	// 파일 업로드 영역에서 기본효과를 제한
	$(".fileDrop").on("dragenter dragover", function(e){
		e.preventDefault(); // 기본효과 제한
	});
	$(".fileDrop").on("drop", function(e){
		e.preventDefault(); // 기본효과 제한
		var files = e.originalEvent.dataTransfer.files; // 드래그한 파일들
		//console.log(files);
		var file = files[0]; // 첫번째 첨부파일
		var formData = new FormData(); // 폼데이터 객체
		formData.append("file", file); // 첨부파일 추가
		$.ajax({
			url: "${path}/upload/uploadAjax",
			type: "post",
			data: formData,
			dataType: "text",
			processData: false, // processType: false - header가 아닌 body로 전달
			contentType: false,
			success: function(data){
				console.log(data);
				// 첨부 파일의 정보
				var fileInfo = getFileInfo(data);
				// 하이퍼링크
				var html = "<a href='"+fileInfo.getLink+"'>"+fileInfo.fileName+"</a><br>";
				// hidden 태그 추가
				html += "<input type='hidden' class='file' value='"+fileInfo.fullName+"'>";
				// div에 추가
				$("#uploadedList").append(html);
			}
		});
	});
	
	$("#btnSave").click(function(){
		//var title = document.form1.title.value; ==> name속성으로 처리할 경우
		//var content = document.form1.content.value;
		//var writer = document.form1.writer.value;
		var subject = $("#subject").val();
		var content = $("#content").val();
		alert (subject);
		alert(content);
/*		var writer = $("#writer").val();
*/		
		if(subject == ""){
			alert("제목을 입력하세요");
			document.writeform.subject.focus();
			return;
		}
		if(content == ""){
			alert("내용을 입력하세요");
			document.writeform.content.focus();
			return;
		}
/*		
		 if(writer == ""){
			alert("이름을 입력하세요");
			document.writeform.writer.focus();
			return;
		} */

		// 폼에 입력한 데이터를 서버로 전송
		document.writeform.submit();
	});
		
});
	