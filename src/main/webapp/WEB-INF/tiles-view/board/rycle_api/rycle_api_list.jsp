<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script>
   var resultlocal; //로컬json파일을 담기위한 전역변수
   var map;
   var markers = [];

   // M : 매장
   // S : 식수대
   var icons = {
      M : {
         icon: 'https://raw.githubusercontent.com/jmh8025/api/master/ic_red.png'
      },
      S : {
         icon: 'https://raw.githubusercontent.com/jmh8025/api/master/ic_blue.png'
      }
   };

   function initMap() {
      map = new google.maps.Map(document.getElementById('map'), {
         zoom : 13,
         center : new google.maps.LatLng(37.4989993,127.0299699),
      //  mapTypeId: 'terrain' //지형으로 보이도록
      });

      infowindow = new google.maps.InfoWindow(); //마커클릭시 정보창을 띄우기위한 함수 및 그함수를 변수에 담음

      //JSONP를 요청하려면 createElement()를 사용하여 script 태그를 문서의 헤드에 추가합니다.
      var script = document.createElement('script');
      script.src = '/js/lycle_map.js';
      document.getElementsByTagName('head')[0].appendChild(script);

        var infoWindow = new google.maps.InfoWindow({map: map});
    /*   
      if (navigator.geolocation) {
         navigator.geolocation.getCurrentPosition(function(position) {
            var pos = {
               lat : position.coords.latitude,
               lng : position.coords.longitude
            };

            infoWindow.setPosition(pos); //지도에 현재 위치를 나타내도록 함
            infoWindow.setContent('내위치'); // 가리키는 지점에 텍스트 상자 보이도록 하기
            //나중에 넣어서 텍스트 상자 창을 띄울 수 있음
            map.setCenter(pos);
            
         }, function() {
            handleLocationError(true, infoWindow, map.getCenter());
         });
      } else {
         // Browser doesn't support Geolocation
         handleLocationError(false, infoWindow, map.getCenter());
      } */
      // e: navigator.geolocation

      // 버튼 뿌리기

      var centerControlDiv = document.createElement('div');
      var centerControl = new CenterControl(centerControlDiv, map);

      centerControlDiv.index = 1;
      map.controls[google.maps.ControlPosition.TOP_CENTER]
            .push(centerControlDiv);

   } //e: initMap

   function CenterControl(controlDiv, map) {

      // Set CSS for the control border.
      var controlUI = document.createElement('div');
      controlUI.style.backgroundColor = '#fff';
      controlUI.style.border = '2px solid #fff';
      controlUI.style.borderRadius = '3px';
      controlUI.style.boxShadow = '0 2px 6px rgba(0,0,0,.3)';
      controlUI.style.cursor = 'pointer';
      controlUI.style.marginBottom = '22px';
      controlUI.style.textAlign = 'center';
      controlUI.title = 'Click to recenter the map';
      controlDiv.appendChild(controlUI);

      
      
      //매장
      var controlText = document.createElement('span');
      controlText.className = 'controlText';
      /*          controlText.style.display = "inline"; */
      controlText.style.color = 'rgb(25,25,25)';
      controlText.style.fontFamily = 'Roboto,Arial,sans-serif';
      controlText.style.fontSize = '16px';
      controlText.style.height = '35px';
      controlText.style.lineHeight = '38px';
      controlText.style.paddingLeft = '5px';
      controlText.style.paddingRight = '5px';
      controlText.innerHTML = "";
      controlText.innerHTML = "";
      /*          controlText.innerHTML += "<a onMouseOver='alarm();'>";
       */controlText.innerHTML += "매장";
      controlText.innerHTML += "</a>";
      controlUI.append(controlText);

      //구분
      var controlText_gubun = document.createElement('span');
      /*          controlText.style.display = "inline"; */
      controlText_gubun.innerHTML = "";
      controlText_gubun.innerHTML = "";
      controlText_gubun.innerHTML += " | ";
      controlUI.append(controlText_gubun);

      //식수대
      var controlText2 = document.createElement('span');
      controlText2.className = 'controlText2';
      /*          controlText.style.display = "inline"; */
      controlText2.style.color = 'rgb(25,25,25)';
      controlText2.style.fontFamily = 'Roboto,Arial,sans-serif';
      controlText2.style.fontSize = '16px';
      controlText2.style.height = '35px';
      controlText2.style.lineHeight = '38px';
      controlText2.style.paddingLeft = '5px';
      controlText2.style.paddingRight = '5px';
      controlText2.innerHTML = "";
      controlText2.innerHTML = "";
      /*          controlText.innerHTML += "<a onMouseOver='alarm();'>";
       */controlText2.innerHTML += "식수대";
      controlText2.innerHTML += "</a>";
      controlUI.append(controlText2);

      // s: 매장 클릭 시
      controlText.addEventListener('click', function() {
         clearMarkers()
         for (var i = 0, result; result = resultlocal[i]; i++) { //local값전부보내버렷
        	 if('M'==result.CLASS){
            	addmarkerlocal('M', result, map);
        	 }
         }
      });
      // e: 매장 클릭 

      // s: 식수대 클릭
      controlText2.addEventListener('click', function() {
          clearMarkers()
          for (var i = 0, result; result = resultlocal[i]; i++) { //local값전부보내버렷
         	 if('S'==result.CLASS){
             	addmarkerlocal('S', result, map);
         	 }
          }
      });
      // e: 식수대 클릭
   }

   //1.로컬js먼저읽고
   window.eqfeed_callback = function(results) {
      resultlocal = results.DATA; //객체담기
   }
   
   //로컬 값받아서 마커 찍기
   function addmarkerlocal(chk, result, map) { //로컬값의 마커추가

      var lat = result.LAT;
      var lon = result.LNG;

      var latLng = new google.maps.LatLng(lat, lon);
      var icon = icons[chk] || {};

      console.log(parseFloat(lat) + "///" + parseFloat(lon));
      console.log("icon.label : " + icon.label);

      //마커 생성
      marker = new google.maps.Marker({
         map : map,
         position : {
            lat : parseFloat(lat),
            lng : parseFloat(lon)
         },
 	    icon: {
	    	url: icon.icon, //service값과 일치하는 이미지읽어오기
	        anchor: new google.maps.Point(10, 10),
	        scaledSize: new google.maps.Size(30, 30)
		}
      });
		markers.push(marker); //마커를 배열에 삽입 (나중에 지우기위해서)
		
		var content = "<b>주소 : "+result.ADDRESS+"</b><br><br> <img src='"+result.FILENAME+"' width='300px'>"; //콘텐츠 새성
		google.maps.event.addListener(marker,'click', (function(marker,content,infowindow){ //마커 클릭시 이벤트(click->mouseover(?),over)등으로 변경가능 
			return function(){
				infowindow.setContent(content); //정보창의 컨텐츠(내용)  
		  		infowindow.open(map,marker); //정보창의 오픈 위치
		  		};
		  		})(marker,content,infowindow));
   }

   function handleLocationError(browserHasGeolocation, infoWindow, pos) {
      infoWindow.setPosition(pos);
      infoWindow
            .setContent(browserHasGeolocation ? 'Error: The Geolocation service failed.'
                  : 'Error: Your browser doesn\'t support geolocation.');
   }
   
   function clearMarkers() {  //배열에 담았던 마커들을 지우는 함수
	   for (var i = 0; i < markers.length; i++) {
	     markers[i].setMap(null);
	   }
	    markers = [];
	 }
   
</script>
<script
	src="https://developers.google.com/maps/documentation/javascript/examples/markerclusterer/markerclusterer.js"></script>
<script async defer
	src="https://maps.googleapis.com/maps/api/js?key=AIzaSyA67w35cw4kd5G741egvTz3Iihp0q54cbI&callback=initMap">
   
</script>
<div class="content_inner">
	<div id="map" style="height: 500px"></div>
</div>

