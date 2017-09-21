
function write_submit(board) {

	if (board == "free_board") {
		var cate_chk = document.getElementById("cate_chk");
		var scate_chk = document.getElementById("scate_chk");

		var subject = document.getElementById("subject");
		var ssubject = document.getElementById("ssubject");

		var content = document.getElementById("bcontent");
		var scontent = document.getElementById("scontent");

		if (cate_chk.value.length == 0) {
			scate_chk.innerHTML = "<b style='font-size:15px;color:red'>필수 정보입니다<b>";
		} else {
			scate_chk.innerHTML = "";
		}

		if (subject.value.length == 0) {
			ssubject.innerHTML = "<b style='font-size:15px;color:red'>필수 정보입니다<b>";
		} else {
			ssubject.innerHTML = "";
		}

		if (content.value.length == 0) {
			scontent.innerHTML = "<b style='font-size:15px;color:red'>필수 정보입니다<b>";
		} else {
			scontent.innerHTML = "";
		}

		if (cate_chk.value.length != 0 && subject.value.length != 0 && content.value.length != 0) {
			var form = document.writeform;
			form.submit();
		}

	}
}
