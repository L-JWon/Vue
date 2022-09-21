package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.*;
import com.sist.dao.*;
import com.sist.vo.*;

@Controller
//컨트롤러는 화면 변경 용도
public class BoardController {
	@Autowired
	private BoardDAO dao;
	
	
	//사용자 요청 ==> URI
	@GetMapping("board/list.do")
	public String boardList()
	{
		return "board/list";
	}
	
	@GetMapping("board/insert.do")
	public String board_insert()
	{
		return "board/insert";
	}
	
	@GetMapping("board/detail.do")
	public String board_detail(int no,Model model)
	{
		model.addAttribute("no",no);
		//이제 디테일 데이터는 vueJS가 읽어 오기 떄문에 restcontroller에서 처리한다 
		return "board/detail";
	}
	
	@GetMapping("board/update.do")
	public String board_update(int no,Model model)
	{
		model.addAttribute("no",no);
		return "board/update";
	}
	
	@GetMapping("board/delete.do")
	public String board_delete(int no,Model model)
	{
		model.addAttribute("no",no);
		return "board/delete";
	}

	
	// Vue / React ==> 화면 변경 (Router) 
	// View단, 서버단, DB단 이렇게 구성  이렇게 한 팀
}
