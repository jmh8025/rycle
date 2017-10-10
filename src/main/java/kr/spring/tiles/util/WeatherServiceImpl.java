package kr.spring.tiles.util;

import java.io.BufferedReader;


import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class WeatherServiceImpl implements WeatherService {

	@Override
	public String weather() {
		 String result = null;
		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {
		String callUrl ="http://rycle.xyz/resources/upload/weather22.json"; 
		  HttpGet httpGet = new HttpGet(callUrl); 
		  HttpResponse response = httpclient.execute(httpGet);
		  InputStream in = response.getEntity().getContent();
		  BufferedReader bf = new BufferedReader(new InputStreamReader(in,"utf-8"));
		  String line; 
		  StringBuffer results = new StringBuffer();
		  while ((line =bf.readLine()) != null) { 
			  results.append(line); 
			  } 
		  result = results.toString(); 
		  System.out.println(result);
		  bf.close(); 
		  in.close();
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return result;
	}
	
	//날씨 아이콘을 위한 sky 치환
	@Override
	public String weathericon(String sky) {
		SimpleDateFormat toweatherTime = new SimpleDateFormat("HH"); 
		int weatherTime = Integer.parseInt(toweatherTime.format(new Date()).toString());
		if(6<weatherTime&&weatherTime<18) {//오전
			if(sky.equals("맑음")) {sky="01";}
			else if(sky.equals("구름조금")) {sky="02";}
			else if(sky.equals("구름많음")) {sky="03";}
		}else {//오후
			if(sky.equals("맑음")) {sky="04";}
			else if(sky.equals("구름조금")) {sky="05";}
			else if(sky.equals("구름많음")) {sky="06";}
		}
		if(sky.equals("구름많고 눈") || sky.equals("구름많고 비 또는 눈")) {sky="07";}
		else if(sky.equals("구름많고 비")) {sky="08";}
		else if(sky.equals("흐림")) {sky="09";}
		else if(sky.equals("흐리고 비")) {sky="10";}
		else if(sky.equals("흐리고 눈")) {sky="11";}
		else if(sky.equals("흐리고 비 또는 눈")) {sky="12";}
		else if(sky.equals("흐리고 낙뢰")) {sky="13";}
		else if(sky.equals("뇌우, 비")) {sky="14";}
		else if(sky.equals("뇌우, 비 또는 눈")) {sky="15";}
		String sky_icon = "/img/weather_icons/"+sky+".png";
		return sky_icon;
	}
	
	//today정보 얻어오기
	@Override
	public HashMap<String, Object> todayWeather(String juso) {
		String result = weather();
		HashMap<String, Object> today = new HashMap<String, Object>();
		try {
			ObjectMapper mapper = new ObjectMapper();
			JsonNode root = mapper.readTree(result);
			JsonNode todayResult = root.path(juso).path("today");
			String sky = todayResult.path("sky").toString().replaceAll("\"", "");
			String wspd = todayResult.path("wspd").toString().replaceAll("\"", "");
			String wdir = todayResult.path("wdir").toString().replaceAll("\"", "");
			String temp = todayResult.path("temp").toString().replaceAll("\"", "");
			String wdper = todayResult.path("wdper").toString().replaceAll("\"", "");
			String skyicon = weathericon(sky);
			temp = temp.substring(0,temp.indexOf("."));
			today.put("sky", sky);
			today.put("skyicon", skyicon);
			today.put("wspd", wspd);
			today.put("wdir", wdir);
			today.put("temp", temp);
			today.put("wdper", wdper);
			String[] weekDay = { "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday" };     
			Calendar cal = Calendar.getInstance(); 
			int num = cal.get(Calendar.DAY_OF_WEEK)-1; 
			String dayweek = weekDay[num]; 
			today.put("day_week", dayweek);
			String[] monthDay = { "January", "Febuary", "March", "April", "May", "June", "July","August","September","October", "November", "December" };
			int ntime = cal.get(Calendar.MONTH);
			String daymonth = monthDay[ntime];
			today.put("month", daymonth);
			}catch (Exception e) {
				System.out.println(e.getMessage());
			}
		return today;
	}
}
