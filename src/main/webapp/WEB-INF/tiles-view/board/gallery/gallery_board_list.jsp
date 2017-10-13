<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath}" />


<div class="content_inner">
	<!-- boardSearch -->
	<div class="boardSearch">
		<p class="result">
			<i class="icon-doc-text"></i> 총 <strong>${map.count}</strong> 건
		</p>
		         <!-- padding -->
         <%@ include file="../include/list_page_foot.jsp" %>
         <!-- btnArea -->
	</div>
	<!-- boardList -->
	<div id="boardList" class="boardList">
		<script>
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
			    			alert ("2"+data.list[1].file_name);
			            }
						
					}
				});
		      $("#boardList").append("<h1>Page</h1><BR/>So<BR/>MANY<BR/>BRS<BR/>YEAHHH~<BR/>So<BR/>MANY<BR/>BRS<BR/>YEAHHH~<BR/>So<BR/>MANY<BR/>BRS<BR/>YEAHHH~<BR/>So<BR/>MANY<BR/>BRS<BR/>YEAHHH~<BR/>So<BR/>MANY<BR/>BRS<BR/>YEAHHH~<BR/>So<BR/>MANY<BR/>BRS<BR/>YEAHHH~<BR/>So<BR/>MANY<BR/>BRS<BR/>YEAHHH~<BR/>So<BR/>MANY<BR/>BRS<BR/>YEAHHH~<BR/>So<BR/>MANY<BR/>BRS<BR/>YEAHHH~<BR/>So<BR/>MANY<BR/>BRS<BR/>YEAHHH~<BR/>So<BR/>MANY<BR/>BRS<BR/>YEAHHH~<BR/>So<BR/>MANY<BR/>BRS<BR/>YEAHHH~");
		    }
		});
		</script>
		<input type="hidden" name="gpage" id="gpage" value="1" />
		<c:forEach var="article" items="${map.list}" varStatus="status">
			<!-- <div id="gitem" style="float:left"> --><a href="/SpringTiles/board/gallery_board_view.do?bno=${article.bno}"><img id="gitem" src="/SpringTiles/upload/displayFile.do?fileName=${article.ufile_name}" style="margin: 3px;"></a>
			<!-- </div> -->
		</c:forEach>
	</div>
		
		<div class="gal_container" id="body">
				<center><button class="demo-more" id="more">더보기</button></center>
		</div>
	
</div>






