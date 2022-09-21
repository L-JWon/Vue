package com.sist.web;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;

import com.sist.dao.FoodDAO;
import com.sist.service.*;
import com.sist.vo.*;
@RestController
public class FoodRestController {
	@Autowired
	private FoodService service;
	
	@Autowired
	private FoodDAO dao;
	
	@GetMapping(value = "food/vue_find.do", produces = "text/plain;charset=UTF-8")
	public String food_find(String page, String ss) //Model 필요없이 곧장 넘어감
	{
		System.out.println("ss="+ss);
		System.out.println("page="+page);
		String result="";
		try {
			if(page==null)
				page="1";
			
			if(ss==null)
				ss="강남";
			
			int curpage=Integer.parseInt(page);
			
			Map map=new HashMap();
			int rowSize=12;
			int start=(rowSize*curpage)-(rowSize-1);
			int end=rowSize*curpage;
			
			map.put("start", start);
			map.put("end", end);
			map.put("address", ss);
			
			List<FoodVO> list=service.foodFindData(map);
			int totalpage=service.foodLocationTotalPage(ss);
			
			JSONArray arr=new JSONArray(); //List를 array로 바꿔주는 것 => []로 바꿔준다 => 자바 스크립트 객체 표현법의 줄임말 JSON
			//FoodVO ==> JSONObject => {}
			// [{},{},{}... {} 12개 들어있음...] 이렇게 구성되어있다 
			int k=0;
			for(FoodVO vo:list)
			{
				JSONObject obj=new JSONObject(); // {"no":1, "name":"ddd"} <= 객체 표현법 형태 기억!!
				obj.put("fno", vo.getFno());
				obj.put("name", vo.getName());
				obj.put("poster", vo.getPoster().substring(0,vo.getPoster().indexOf("^")));
				
				if(k==0)
				{
					obj.put("curpage", curpage);
					obj.put("totalpage", totalpage);
				}
				arr.add(obj);
				k++;
			}
			result=arr.toJSONString();
			
		} catch (Exception e) {}
		return result;
	}
	
	@GetMapping(value = "food/detail_vue.do",produces = "text/plain;charset=UTF-8")
	public String detail_vue(int fno)
	{
		String result="";
		try {
			FoodVO vo=service.foodDetailVueData(fno);
			JSONObject obj=new JSONObject(); //vo를 요청했으면 object로 묶어 줘야한다!!!
			obj.put("fno", vo.getFno());
			obj.put("name", vo.getName());
			obj.put("poster", vo.getPoster());
			obj.put("address", vo.getAddress().substring(0,vo.getAddress().lastIndexOf("지")).trim());
			obj.put("tel", vo.getTel());
			obj.put("type", vo.getType());
			obj.put("time", vo.getTime());
			obj.put("parking", vo.getParking());
			obj.put("price", vo.getPrice());
			obj.put("menu", vo.getMenu());
			obj.put("score", vo.getScore());
	
			result=obj.toJSONString();
		} catch (Exception e) {}
		return result;
	}
	
	@GetMapping(value = "food/food_all_vue.do",produces = "text/plain;charset=UTF-8" )
	public String food_all_vue(String page)
	{
		String result="";
		try {
			if(page==null)
				page="1";
			
			
			int curpage=Integer.parseInt(page);
			
			Map map=new HashMap();
			int rowSize=12;
			int start=(rowSize*curpage)-(rowSize-1);
			int end=rowSize*curpage;
			
			map.put("start", start);
			map.put("end", end);
			
			List<FoodVO> list=dao.foodAllData(map);
			int totalpage=dao.foodTotalPage();
			
			JSONArray arr=new JSONArray(); //List를 array로 바꿔주는 것 => []로 바꿔준다 => 자바 스크립트 객체 표현법의 줄임말 JSON
			//FoodVO ==> JSONObject => {}
			// [{},{},{}... {} 12개 들어있음...] 이렇게 구성되어있다 
			int k=0;
			for(FoodVO vo:list)
			{
				JSONObject obj=new JSONObject(); // {"no":1, "name":"ddd"} <= 객체 표현법 형태 기억!!
				obj.put("fno", vo.getFno());
				obj.put("name", vo.getName());
				obj.put("poster", vo.getPoster().substring(0,vo.getPoster().indexOf("^")));
				
				if(k==0)
				{
					obj.put("curpage", curpage);
					obj.put("totalpage", totalpage);
				}
				arr.add(obj);
				k++;
			}
			result=arr.toJSONString();
			
		} catch (Exception e) {}
		return result;
	}
}
