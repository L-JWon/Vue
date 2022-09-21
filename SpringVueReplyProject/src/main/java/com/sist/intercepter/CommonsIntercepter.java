package com.sist.intercepter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import java.text.SimpleDateFormat;
import java.util.*;


public class CommonsIntercepter  extends HandlerInterceptorAdapter{

	//Controller 메소드 수행 전에 처리하는 부분!!! ==> 자동 로그인 => main.do를 불러오기 전에 세션에 저장되어있는 아이디를 불러와 로그인 처리
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		String today=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());
		System.out.println("==========================================");
		System.out.println("요청처리 완료 시간"+today); 
		System.out.println("1. 클라이언트 요청");
		System.out.println("2. 클라이언트 요청 URI: "+request.getRequestURI());
		System.out.println("3. 클라이언트 요청 처리 준비완료...");
		// @GetMapping() => 밑에 있는 메소드 호출
		return super.preHandle(request, response, handler);
	}

	//Controller 메소드 수행 완료가 되었을 때 처리되는 부분 => 권한
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		String today=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());
		System.out.println("==========================================");
		System.out.println("요청처리 완료 시간"+today);
		System.out.println("1. @GetMapping, @PostMapping,@RequestMapping 찾기 = HandlerMapping ");
		System.out.println("2. 요청 처리 완료");
		System.out.println("3. 결과값을 JSP로 전송");
		System.out.println("4. 전송 준비 완료...");
		super.postHandle(request, response, handler, modelAndView);
	}
	
	// Controller 메소드가 종료 => JSP에서 출력 전에 팝업창을 띄워준다거나 하는 때 
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		String today=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());
		System.out.println("==========================================");
		System.out.println("요청처리 완료 시간"+today);
		System.out.println("1. JSP로 출력에 필요한 데이터 전송 완료");
		System.out.println("2. JSP 화면 출력...");
		super.afterCompletion(request, response, handler, ex);
	}

}
