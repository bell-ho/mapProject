package com.example.demo.controller;

import java.io.InputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Arrays;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
	@RequestMapping(value = "/Test", produces = "application/xml;charset=UTF-8")
	public String Test(String key,int page ) {
		//검색어를 받을 매개변수 key
		//페이지변환 int형의 페이지 넘버
		System.out.println(key);
		System.out.println(page);
		String str="";
		try {
			StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/B552657/ErmctInsttInfoInqireService/getParmacyListInfoInqire"); /*URL*/	       
			urlBuilder.append("?" + URLEncoder.encode("ServiceKey","UTF-8") + "=HRtSF9l%2B9iDrX9QOfocSYjGRaMCKHOEwwQFTB9xyHjkyVWTjZw7%2FwOISNztL%2FPonOqC4nkA813iICxLfaAz1UA%3D%3D"); /*Service Key*/ /*Service Key는 ?가 와야함*/
	        urlBuilder.append("&" + URLEncoder.encode("Q0","UTF-8") + "=" + URLEncoder.encode("서울특별시", "UTF-8"));
	        urlBuilder.append("&" + URLEncoder.encode("Q1","UTF-8") + "=" + URLEncoder.encode(""+key, "UTF-8"));
	        urlBuilder.append("&" + URLEncoder.encode("ORD","UTF-8") + "=" + URLEncoder.encode("NAME", "UTF-8")); /*순서*/
	        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode(""+page, "UTF-8")); /*페이지 번호*/
	        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("10", "UTF-8")); /*목록 건수*/

			System.out.println(urlBuilder.toString());
			URL url = new URL(urlBuilder.toString());
			InputStream ips = url.openStream();
			
			byte data[] = new byte[100]; //문자열 읽어오기
			while(true) {
				int n = ips.read(data); //완전 공백도 없으면 -1이 들어감
				if(n == -1) {
					break; //항상 반복문을 탈출 즉 while문 아에 탈출
				}
				String s = new String( data , 0 , n );
				str += s;
				Arrays.fill(data, (byte)0); //인코딩할때 필요하다
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		
		return str;
	}
}
