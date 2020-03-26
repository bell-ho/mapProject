package com.example.demo.controller;

import java.io.InputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Arrays;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KakaoMapController {
	@RequestMapping(value = "/KaKao" , produces = "application/xml;charset=UTF-8")
	public String Test(String key ,String local ,int page) {
		String str="";
		try {
			 StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/B552657/HsptlAsembySearchService/getHsptlMdcncListInfoInqire"); /*URL*/
			  urlBuilder.append("?" + URLEncoder.encode("ServiceKey","UTF-8") + "=HRtSF9l%2B9iDrX9QOfocSYjGRaMCKHOEwwQFTB9xyHjkyVWTjZw7%2FwOISNztL%2FPonOqC4nkA813iICxLfaAz1UA%3D%3D"); /*Service Key*/
			  urlBuilder.append("&" + URLEncoder.encode("Q0","UTF-8") + "=" + URLEncoder.encode(""+local, "UTF-8")); /*주소(시군구)*/
//			  urlBuilder.append("&" + URLEncoder.encode("Q1","UTF-8") + "=" + URLEncoder.encode("마포구", "UTF-8")); /*주소(시군구)*/
			  urlBuilder.append("&" + URLEncoder.encode("Q1","UTF-8") + "=" + URLEncoder.encode(""+key, "UTF-8")); //q1만 쓸시 너무 많이나옴
			  urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode(""+page, "UTF-8")); /*페이지 번호*/
			  urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("20", "UTF-8")); /*목록 건수*/
			  
			  URL url = new URL(urlBuilder.toString());
			  System.out.println(url);
			  InputStream ips = url.openStream();
			  
			  byte data[] = new byte[100];
			  while(true) {
				  int n = ips.read(data);
				  if(n == -1) {
					  break;
				  }
				  String s = new String(data , 0 , n);
				  str += s;
				  Arrays.fill(data, (byte)0);
			  }
			  
		} catch (Exception e) {
			// TODO: handle exception
		}
		return str;
	}
}
