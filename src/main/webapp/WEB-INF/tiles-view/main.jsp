<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:useBean id="CountManager" class="kr.spring.tiles.util.CountManager" scope="application" />
<link rel="stylesheet" href="/SpringTiles/css/Nwagon.css">
<c:set var="path" value="${pageContext.request.contextPath}" />
<script
	src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDsCvBGdPdGKaFym6WNsUUoQoaU0lrGLhw&callback=initMap&libraries=places,visualization"
	async defer></script>
<script src="/SpringTiles/js/gmap.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.4.0/Chart.min.js"></script>
<ul class="cards">


	<li>
		<h2>
			Like + Bicycle <small>BY <span>LYCLE</span> FROM KIC CAMPUS
			</small>
		</h2>
		<div class="survey">
			<div class="survey_cycleyn">
				<canvas id="chartjs-cycleyn" class="chartjs"></canvas>
			</div>
		
			<div class="survey_cyclekind">
				<center>보유자전거 종류</center>
				<canvas id="chartjs-cyclekind" class="chartjs"></canvas>
			</div>
			<div class="survey_cycleweek">
				<center>주 이용횟수</center>
				<canvas id="chartjs-cycleweek" class="chartjs"></canvas>
			</div>
		</div>
	</li>
	<li>
		<div class="weather">
			<h3>
				<br>${today.day_week}, ${today.month}
			</h3>
			<br>
			<h3>
				<i class="icon-plus-circled"></i>Seoul, ${today.sky}
			</h3>
			<br>
			<p>
				<span>${today.temp}˚</span><img src="${path}/${today.skyicon}"
					width="115px">
			</p>
			<br>
		</div>
	</li>
	<li>
		<div class="gallary"><img style="border-radius: 12px;" src="/SpringTiles/img/maincycle.jpg" width="100%" height="100%"></div>
		<div class="today">
			<center>
				<span><h3><br>현재 접속자수 : <%= CountManager.getCount() %></h3></span>
			</center>
		</div>
	</li>
	<li><div class="notice">


			<table class="noticetab">
				<colgroup>
					<col style="width: 40%;">
					<col style="width: 15%;">
				</colgroup>
				<thead>
					<tr>
						<th scope="col" class="sub">최근게시물</th>
						<th scope="col" class="more"><a
							href="${path}/board/free_board_list.do"> <i
								class="icon-plus-circled"></i></a></th>

					</tr>
				</thead>
				<tbody>
					<c:forEach var="article" items="${map.list}" varStatus="status">
						<tr>
							<c:choose>
								<c:when test="${fn:length(article.subject) > 18}">
									<td class="names"><a
										href="/SpringTiles/board/free_board_view.do?bno=${article.no}&cate_chk=${article.cate_chk}${board_link}">
											<c:out value="${fn:substring(article.subject,0,17)}" />....
									</a></td>
								</c:when>
								<c:otherwise>
									<td class="names"><a
										href="/SpringTiles/board/free_board_view.do?bno=${article.no}&cate_chk=${article.cate_chk}${board_link}">
											${article.subject} </a></td>
								</c:otherwise>
							</c:choose>
							<td class="date">${article.k_date}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div></li>
	<li><div class="route">
			<div id="map"></div>
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



<script>
new Chart(document.getElementById("chartjs-cycleyn"),{
	"type":"doughnut",
	"data":{
	"labels":["자전거가있다","자전거가없다"],
	"datasets":[{
	"label":"My First Dataset",
	"data":[${cycle.y},${cycle.n}],
	"backgroundColor":["rgba(142, 161, 165,0.6)","rgba(33, 55, 75,0.6)"]
	}]
	},
	options: { 
	    legend: {
	        labels: {
	            fontColor: "white",
	            fontSize: 12
	        }
	    }
	}
	});
	new Chart(document.getElementById("chartjs-cyclekind"),{
		"type":"doughnut",
		"data":{
		"labels":["미니벨로","로드","하이브리드","MTB","픽시","투어링바이크","기타"],
		"datasets":[{
		"label":"My First Dataset",
		"data":[${cycle.mini},${cycle.load},${cycle.hib},${cycle.mtb},${cycle.pixy},${cycle.tour},${cycle.none}],
		"backgroundColor":["rgba(142, 161, 165,0.6)","rgba(33, 55, 75,0.6)","rgba(88,100,115,0.6)","rgba(231,218,203,0.6)","rgba(74,137,170,0.6)","rgba(10,53,63,0.6)","rgba(32,36,37,0.6)"]
		}]
		},
		options: { 
		    legend: { 
		        	 display: false
		    }
		}
		});
	new Chart(document.getElementById("chartjs-cycleweek"),{
		"type":"doughnut",
		"data":{
		"labels":["주 0~1번","주 2~3번","주 4~5번","주 6~7번"],
		"datasets":[{
		"label":"My First Dataset",
		"data":[${cycle.how_01},${cycle.how_23},${cycle.how_45},${cycle.how_67}],
		"backgroundColor":["rgba(142, 161, 165,0.6)","rgba(33, 55, 75,0.6)","rgba(88,100,115,0.6)","rgba(231,218,203,0.6)"]
		}]
		},
		options: { 
		    legend: { 
		        	 display: false
		    }
		}
		});
</script>