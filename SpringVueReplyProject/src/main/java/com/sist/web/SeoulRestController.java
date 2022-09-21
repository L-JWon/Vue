package com.sist.web;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sist.dao.*;
import com.sist.vo.*;

//자바스크립트 데이터 전송 
@RestController
public class SeoulRestController {
	@Autowired
	private SeoulDAO sdao;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@GetMapping(value = "seoul/list_vue.do",produces = "text/plain;charset=utf-8")
	public String seoul_list_vue(String page,String type/*테이블을 바꿔치기하는 매개변수*/,Model model)
	{
		String result="";
		
		if(page==null)
			page="1";
		if(type==null)
			type="1";
		
		int index=Integer.parseInt(type);
		String[] table_name= {"","seoul_location","seoul_nature","seoul_shop"};
		
		int curpage=Integer.parseInt(page);
		Map map=new HashMap();
		int rowsize=12;
		int start=(rowsize*curpage)-(rowsize-1);
		int end=rowsize*curpage;
		map.put("start", start);
		map.put("end", end);
		map.put("table_name", table_name[index]);
		
		List<SeoulVO> list=sdao.seoulListData(map);
		
		int totalpage=sdao.seoulTotalPage(map);
		
		JSONArray arr=new JSONArray(); // []를 만들어준다  오브젝트{} 요게 12개 들어간다
		int k=0;
		for(SeoulVO vo:list)
		{
			JSONObject obj=new JSONObject();// {}로 묶어준다
			obj.put("no", vo.getNo());
			obj.put("title", vo.getTitle());
			obj.put("poster", vo.getPoster());
			
			if(k==0)  //이 부분이 첫 페이지만 12개 집어넣으려는 코드
			{
				obj.put("curpage", curpage);
				obj.put("totalpage", totalpage);
				obj.put("type", type);
				
			}
			
			arr.add(obj);
			k++;
		}
		result=arr.toJSONString();
		
		return result;
	}
	
	@GetMapping(value = "seoul/detail_vue.do", produces = "text/plain;charset=utf-8")
	public String seoul_detail_vue(int no,int type)
	{
		String result="";
		try {
				String[] table_name= {"","seoul_location","seoul_nature","seoul_shop"};
				Map map=new HashMap();
				map.put("table_name", table_name[type]);
				map.put("no", no);
				
				SeoulVO vo=sdao.seoulDetailData(map);
				JSONObject obj=new JSONObject();
				
				obj.put("no", vo.getNo());
				obj.put("title", vo.getTitle());
				obj.put("address", vo.getAddress().substring(vo.getAddress().indexOf(" ")).trim());
				obj.put("msg", vo.getMsg());
				obj.put("poster", vo.getPoster());
				
				result=obj.toJSONString();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}
	
	@GetMapping(value = "seoul/cook_list.do", produces = "text/plain;charset=utf-8")
	public String seoul_cook_list(String type,HttpServletRequest request)
	{
		if(type==null)
		{
			type="1";
		}
		int t=Integer.parseInt(type);
		String[] cook_name= {"","location","nature","shop"};
		String result="";
		Cookie[] cookies=request.getCookies();
		List<SeoulVO> list=new ArrayList();
		if(cookies!=null)
		{
			for(int i=cookies.length-1;i>=0;i--)
			{
				if(cookies[i].getName().startsWith(cook_name[t]))
				{
					Map map=new HashMap();
					String no=cookies[i].getValue();
					map.put("no", no);
					map.put("table_name", "seoul_"+cook_name[t]);
					SeoulVO vo=sdao.seoulDetailData(map);
					list.add(vo);
				}
			}
		}
		//list => JSON
		JSONArray arr=new JSONArray();
		for(SeoulVO vo:list)
		{
			JSONObject obj=new JSONObject();
			
			obj.put("no", vo.getNo());
			obj.put("title", vo.getTitle());
			obj.put("address", vo.getAddress().substring(vo.getAddress().indexOf(" ")).trim());
			obj.put("msg", vo.getMsg());
			obj.put("poster", vo.getPoster());
			arr.add(obj);
		}
		result=arr.toJSONString();
		return result;
	}
	
	//로그인 처리
	@GetMapping(value = "member/login_vue.do",produces = "text/plain;charset=utf-8")
	public String login_vue(String id,String pwd,HttpSession session)
	{
		String result="";
		String temp=encoder.encode(pwd);
		System.out.println("pwd: "+pwd+" 암호화: "+temp);
		
		MemberVO vo=sdao.isLogin(id, pwd);
		if(vo.getMsg().equals("OK"))
		{
			session.setAttribute("id", vo.getId());
			session.setAttribute("name", vo.getName());
			//session은 기본이 30분 유지 더 늘리려면 
			//session.setMaxInactiveInterval(60*60*24);
		}
		result=vo.getMsg(); //NOID ,NOPWD, OK    이걸 받는 곳이 Vue result부분
		// List => React,Redux, Vue, Vue3, Vuex, Ajax ==> [] (JSONArray)
		// VO => {} (JSONObject)
		// 일반 데이터형 전송
		return result;
	}
	
	
}
