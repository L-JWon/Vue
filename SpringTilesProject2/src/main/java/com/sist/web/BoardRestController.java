package com.sist.web;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import com.sist.dao.*;
import com.sist.vo.*;

@RestController
//데이터 전송을 위한 컨트롤러
//프론트 <===> 백엔드
//     JSON(JSONP,클로저) ==> async axios.get() await
					//      싱크 => 동기화로 가겠다는 의미 
// 프로트에서 좀 잘한다! 어필할 수 있는 한가지가
// '웹팩' 사용할 수 있으면  이점!!
public class BoardRestController {
	@Autowired
	private BoardDAO dao;
	
	//VueJS에서 페이지 전송
	@GetMapping(value = "board/list_vue.do",produces = "text/plain;charset=utf-8")
	public String board_list_vue(String page)
	{
		if(page==null)
			page="1";
		
		int curpage=Integer.parseInt(page);
		
		Map map=new HashMap();
		int rowSise=10;
		int start=(rowSise*curpage)-(rowSise-1);
		int end=(rowSise*curpage);
		
		map.put("start", start);
		map.put("end", end);
		
		List<BoardVO> list=dao.boardListData(map);
		
		int totalpage=dao.boardTotalPage();
		
		//JS로 데이터 전송 시작
		String result="";
		try {
			//주의 해야할 것이 리스트로 보내는데 
			// 매칭을 잘 시켜야하는데
			//리스트는 어레이 인식한다
			// VO 는 오브젝트로 인식
			/*
			 *  List => Array => []   ====> JSONArray
			 *  
			 *  VO => Object => {}    ====> JSONObject
			 *  
			 *  [{no:1, "subject:제목, ...},{},{},{],{}....]  객체 하나가 array의 밸류값이 될 수 있다
			 *  -------------------- 이런 구성
			 *  
			 */
			
			JSONArray arr=new JSONArray();
			int k=0;
			for(BoardVO vo:list)
			{
				JSONObject obj=new JSONObject(); //vo를 for문 돌려서 array로 만드니까 이곳은 jsonobject 사용하는 것
				obj.put("no", vo.getNo());
				obj.put("subject", vo.getSubject());
				obj.put("name", vo.getName());
				obj.put("dbday", vo.getDbday());
				obj.put("hit", vo.getHit());
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
	
	
	@GetMapping(value = "board/insert_vue.do", produces = "text/plain;charset=utf-8")
	public String board_insert(BoardVO vo)
	{
		dao.boardInsert(vo);
		return "ok";
	}
	
	@GetMapping(value = "board/detail_vue.do",produces = "text/plain;charset=utf-8")
	public String board_detail(int no)
	{
		String result="";
		BoardVO vo=dao.boardDetailData(no); //이걸 vo로 바로 보내주려하니까 Vue에서 값을 못읽으니까

		JSONObject obj=new JSONObject(); //JSON으로 바꿔서 보내주자
		obj.put("no", vo.getNo());
		obj.put("name", vo.getName());
		obj.put("subject", vo.getSubject());
		obj.put("content", vo.getContent());
		obj.put("dbday", vo.getDbday());
		obj.put("hit", vo.getHit());
		
		result=obj.toJSONString();

		return result;
	}
	
	@GetMapping(value = "board/update_vue.do",produces = "text/plain;charset=utf-8")
	public String board_update(int no)
	{
		String result="";
		BoardVO vo=dao.boardUpdateData(no); //이걸 vo로 바로 보내주려하니까 Vue에서 값을 못읽으니까

		JSONObject obj=new JSONObject(); //JSON으로 바꿔서 보내주자
		obj.put("no", vo.getNo());
		obj.put("name", vo.getName());
		obj.put("subject", vo.getSubject());
		obj.put("content", vo.getContent());

		result=obj.toJSONString();

		return result;
	}
	
	@GetMapping(value = "board/update_vue_ok.do",produces = "text/plain;charset=utf-8")
	public String board_update_ok(BoardVO vo)
	{
		String result=dao.boardUpdate(vo);
		return result;
	}

	@GetMapping(value = "board/delete_vue.do",produces = "text/plain;charset=utf-8")
	public String board_delete_ok(int no,String pwd)
	{
		String result=dao.boardDelete(no, pwd);
		return result;
	}
	
}
