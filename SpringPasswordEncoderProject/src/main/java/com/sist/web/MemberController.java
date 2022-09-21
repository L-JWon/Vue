package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

import javax.servlet.http.HttpSession;

import com.sist.vo.*;
import com.sist.dao.*;
@Controller
public class MemberController {
	@Autowired
	private MemberDAO dao;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@GetMapping("member/join.do")
	public String member_join(Model model)
	{
		model.addAttribute("main_jsp","../member/join.jsp");
		return "main/main"; //포워드 
	}
	
	@PostMapping("member/join_ok.do")
	public String member_join_ok(MemberVO vo)
	{
		vo.setTel(vo.getTel1()+"-"+vo.getTel2());
		String en=encoder.encode(vo.getPwd());
		vo.setPwd(en);//암호화 시켜서 저장
		dao.memberJoinInsert(vo);
		
		
		return "redirect:../main/main.do";
	}
	
	@PostMapping("member/idcheck.do")
	@ResponseBody
	//jsp파일명 , .do 로 전송 하는 것이 아니고 => 일반 데이터,JSON  얘가 발전해서 생긴게 => @RestController
	public String member_idcheck(String id) //회원가입할 때 
	{
		String result="";
		int count=dao.memberIdCheck(id);
		if(count==0)
		{
			result="YES";
		}
		else
		{
			result="NO";
		}
		
		return result;
	}
	
	@GetMapping("member/login.do")
	public String member_login(Model model)
	{
		model.addAttribute("main_jsp","../member/login.jsp");
		return "main/main";
	}
	
	
	@PostMapping("member/login_ok.do")
	@ResponseBody
	public String member_login_ok(String id,String pwd,boolean ck,HttpSession session)
	{
		String result="";
		int count=dao.memberIdCheck(id);
		if(count==0)
		{
			result="NOID";
		}
		else
		{
			MemberVO vo=dao.memberJoinInfoData(id);
			if(encoder.matches(pwd, vo.getPwd()))// 암호화와 일반 비밀번호를 비교할 때
			{
				session.setAttribute("id", id);
				session.setAttribute("name", vo.getName());
				session.setAttribute("role", vo.getRole());
			}
			else
			{
				result="NOPWD";
			}
		}
		return result;
	}
	
	@GetMapping("member/logout.do")
	public String m_logout(HttpSession session)
	{
		
		session.invalidate();
		return "redirect:../main/main.do";
	}
	
}
