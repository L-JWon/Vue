package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.sist.service.*;
import java.util.*;
import com.sist.vo.*;
@Controller
public class RecipeController {
	@Autowired
	private RecipeService service;
	
	@GetMapping("recipe/list.do")
	public String recipe_list(String page,Model model)
	{
		if(page==null)
			page="1";
		int curpage=Integer.parseInt(page);
		Map map=new HashMap();
		int rowSize=12;
		int start=(rowSize*curpage)-(rowSize-1);
		int end=(rowSize*curpage);
		map.put("start", start);
		map.put("end", end);
		List<RecipeVO> list=service.recipeListData(map);
		for(RecipeVO vo:list)
		{
			String name=vo.getTitle();
			if(name.length()>25)
			{
				name=name.substring(0,23)+"...";
				vo.setTitle(name);
			}
			vo.setTitle(name);
		}
		
		//총페이지
		int total=service.recipeTotalPage();
		//블록나누기
		final int BLOCK=10;
		int startPage=((curpage-1)/BLOCK*BLOCK)+1; // 1 ~ 11 ~ 21
		/*
		 * curpage 1~10 ==> startPage = 1
		 * curpage 11~20 ==> startPage =11
		 * 
		 */
		int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK; // 10,20,30
		if(endPage>=total)
			endPage=total;
		//예 토탈이 13이면 => 1,2,3,4~10, 11 12 13까지만 
		
		model.addAttribute("list",list);
		model.addAttribute("curpage",curpage);
		model.addAttribute("startPage",startPage);
		model.addAttribute("endPage",endPage);
		model.addAttribute("total",total);
		return "recipe/list";
	}
}
