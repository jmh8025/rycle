$(document).ready(function() {

	// sidebar toggle
	$('#sidebar_menu a').click(function() {
		$('#header, #sidebar, #content, #footer').toggleClass('toggled');
	});
	$('#sidebar_close a').click(function() {
		$('#header, #sidebar, #content, #footer').removeClass('toggled');
	});

	// top-search(템플릿에서 가져옴)
	(function() {
		$('body').on('click', '#top-search > a', function(e) {
			e.preventDefault();
			$('#header').addClass('search-toggled');
		});
		$('body').on('click', '#top-search-close', function(e) {
			e.preventDefault();
			$('#header').removeClass('search-toggled');
		});
	})();

	// 모달
	$('#myModal').modalSteps();	
});






