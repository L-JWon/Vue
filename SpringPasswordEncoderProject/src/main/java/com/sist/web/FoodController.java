package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.sist.dao.FoodDAO;
import com.sist.dao.*;
import com.sist.vo.*;
import java.util.*;


//구조화된 프로그램 ==> 재사용이 가능하도록 만드는 것
@Controller
public class FoodController {
	@Autowired
	private FoodDAO dao;
	
	//사용자 요청 처리 => 구분 (getMapping post... Request...
	@GetMapping("food/food_list.do")
	public String food_list(int cno,Model model)
	{
		//food_list에서 출력한 데이터 전송
		List<FoodVO> list=dao.foodCategoryListData(cno);
		for(FoodVO vo:list)
		{
			String addr=vo.getAddress();
			addr=addr.substring(0,addr.lastIndexOf("지"));
			vo.setAddress(addr);
			
			String poster=vo.getPoster();
			poster=poster.substring(0,poster.indexOf("^"));
			vo.setPoster(poster);
				
		}
		CategoryVO vo=dao.categoryInfoData(cno);
		
		model.addAttribute("cvo",vo);
		model.addAttribute("list",list);
		model.addAttribute("main_jsp","../food/food_list.jsp");
		return "main/main";
	}
	
	@GetMapping("food/detail.do")
	public String food_detail(int fno,Model model)
	{
		model.addAttribute("fno",fno);
		model.addAttribute("main_jsp","../food/food_detail.jsp");
		return "main/main";
	}
}
