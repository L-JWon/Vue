package com.sist.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RecommandController {
	@GetMapping("recommand/recommand")
	public String food_recommand()
	{
		return "recommand/recommand";
	}
	
	@GetMapping("recommand/recommand_vue.do")
	public String food_recommand_vue()
	{
		return "recommand/recommand_vue";
	}
}
