package com.sist.aop;
import java.util.*;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.sist.vo.*;
import com.sist.manager.*;
import com.sist.service.*;

@Aspect
@Component
public class CommonsAOP {
	@Autowired
	private SeoulService service;
	
	@Autowired
	private MusicManager manager;
	
	 @After("execution(* com.sist.web.*Controller.*(..))")
	   public void after()
	   {
		   // 현재 사용중인 request를 얻어 온다 
		   HttpServletRequest request=
				   ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		   
		Map map=new HashMap();
		map.put("table_name", "seoul_location");
		List<SeoulVO> sList=service.seoulTop5List(map);
		
		map.put("table_name", "seoul_nature");
		List<SeoulVO> nList=service.seoulTop5List(map);
		
		List<MusicVO> mList=manager.musicTop5();
		
		request.setAttribute("mList", mList);
		request.setAttribute("sList", sList);
		request.setAttribute("nList", nList);
	}
}
