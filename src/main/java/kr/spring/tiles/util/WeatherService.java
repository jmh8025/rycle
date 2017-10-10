package kr.spring.tiles.util;

import java.util.HashMap;

public interface WeatherService {

	//날씨데이터 얻어오기
	String weather();
	
	//오전,오후 날씨정보를 아이콘사용을 위해 숫자로 치환
	String weathericon(String sky);
	
	//오늘날씨
	HashMap<String, Object> todayWeather(String juso);
}
