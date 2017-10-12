<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<link rel="stylesheet" href="/SpringTiles/css/Nwagon.css">
<c:set var="path" value="${pageContext.request.contextPath}" />
<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDsCvBGdPdGKaFym6WNsUUoQoaU0lrGLhw&callback=initMap&libraries=places,visualization" async defer></script>
<script src="/SpringTiles/js/gmap.js"></script>
 <script src="/SpringTiles/js/Nwagon.js"></script>
<ul class="cards">
    <li>
        <h2>Like + Bicycle <small>BY <span>LYCLE</span> FROM KIC CAMPUS</small></h2>
        <div class="survey">2 
	<div id="chart_d"></div>
	<script>
		var options = {
			'dataset': {
				values:[18, 12, 3, 10, 7],
				colorset: ['#2BC8C9', '#FF8C00', '#DC143C','#2EB400', '#666666'],
				fields: ['A', 'B',  'C', 'D', 'E'] 
			},
			'donut_width' : 40, 
			'core_circle_radius':60,
			'chartDiv': 'chart_d',
			'chartType': 'donut',
			'chartSize': {width:600, height:300}
		};

		Nwagon.chart(options);
	</script>

</div>
    </li>
    <li>
        <div class="weather">
        <h3><br>${today.day_week}, ${today.month}</h3><br>
        <h3><i class="icon-plus-circled"></i>Seoul, ${today.sky}</h3><br>
        <p><span>${today.temp}˚</span><img src="${path}/${today.skyicon}" width="115px"></p><br>
        </div>
    </li>
    <li>
        <div class="gallary">갤러리</div>
        <div class="today"><center><h2>준비중!</h2></center> </div>
    </li>
    <li><div class="notice">
          
                  <table class="noticetab">
                <colgroup>
                    <col style="width:40%;">
                    <col style="width:15%;">
                </colgroup>
                <thead>
                    <tr>
                        <th scope="col" class="sub">최근게시물</th>
                        <th scope="col" class="more">  
                        <a href="${path}/board/free_board_list.do">
                        <i class="icon-plus-circled"></i></a></th>
                  
                    </tr>
                </thead>
                <tbody>
	<c:forEach var="article" items="${map.list}"  varStatus="status">
		<tr>
		 <c:choose>
           <c:when test="${fn:length(article.subject) > 18}">
           <td class="names">
            <a href="/SpringTiles/board/free_board_view.do?bno=${article.no}&cate_chk=${article.cate_chk}${board_link}">
            <c:out value="${fn:substring(article.subject,0,17)}"/>....
            </a>
            </td>
           </c:when>
           <c:otherwise>
           <td class="names">
           <a href="/SpringTiles/board/free_board_view.do?bno=${article.no}&cate_chk=${article.cate_chk}${board_link}">
           ${article.subject}
           </a>
           </td>
           </c:otherwise> 
          </c:choose>
			<td class="date">${article.k_date}</td>
		</tr>	
	</c:forEach>
                </tbody>
                </table>
            </div>



</li>
    <li><div class="route"><div id="map"></div>
  <!--   <form name="controls">
		<input type="radio" name="type" value="all" onclick="searchsort()"
			checked="checked" />모두 <br /> <input type="radio" name="type"
			value="normal" onclick="searchsort()" />보관소 <br /> <input
			type="radio" name="type" value="gonggi" onclick="searchsort()" />공기주입기
		<br /> <input type="radio" name="type" value="store" onclick="searchsort()" />대리점 <input onclick="clearMarkers();"
			type=button value="Hide Markers"></input>
	</form> -->
	</div></li>
</ul>