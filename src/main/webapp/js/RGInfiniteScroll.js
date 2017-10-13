$(window).scroll(function() {
    if ($(window).scrollTop() == $(document).height() - $(window).height()) {
	  	var gpage = $("#gpage").val();
	  	gpage = parseInt(gpage) + 1;
		$("#gpage").val(gpage);
		$.ajax({
			type: "post",
			url : '/SpringTiles/board/gallery_board_list_infinite.do',
			data : { "gpage":gpage },
			success: function(data){
	            if(!data) {
	            	return alert("실패");
	            }	else {
	    			alert (data);
	    			alert (data.list.file_name);
	            }
			}
		});
      $("#boardList").append(data.list.file_name);
    }
});