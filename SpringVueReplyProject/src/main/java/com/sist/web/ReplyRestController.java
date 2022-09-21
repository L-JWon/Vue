package com.sist.web;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import com.sist.dao.*;
import com.sist.vo.*;
import java.util.*;

import javax.servlet.http.HttpSession;

@RestController  // VueJS와 연동 
public class ReplyRestController {
		
		@Autowired
		private ReplyDAO dao;
		// 수정이나 삭제가 들어갔을 때 본인인 경우에만 처리하는데 
		// 뷰에서 v-if를 통해서 처리하는데 ${sessionScope 이게 불가능 그래서 아이디 변수가 하나 필요함 => 아이디를 뷰에 넘겨주기 위해 
		public String reply_json_data(List<ReplyVO> list,String id)
		{
			/*
			private int no,cno,type;
			private String id,name,msg,dbday;
			 */
			JSONArray arr=new JSONArray();
			int k=0;
			for(ReplyVO rvo:list)
			{
				JSONObject obj=new  JSONObject();
				obj.put("no", rvo.getNo());
				obj.put("cno", rvo.getCno());
				obj.put("type", rvo.getType());
				obj.put("id", rvo.getId());
				obj.put("name", rvo.getName());
				obj.put("msg", rvo.getMsg());
				obj.put("dbday", rvo.getDbday());
				if(k==0)
				{
					obj.put("sessionId", id);
				}
				k++;
				arr.add(obj);
				
			}
			return arr.toJSONString();
		}
		
		@GetMapping(value = "seoul/reply_list.do",produces = "text/plain;charset=utf-8")
		public String reply_list(int cno,int type,HttpSession session)
		{
			String id=(String)session.getAttribute("id");
			String result="";
			ReplyVO vo=new ReplyVO();
			vo.setCno(cno);
			vo.setType(type+3);
			List<ReplyVO> list=dao.replyListData(vo);
			
			result=reply_json_data(list,id);
			
			return result; //넘겨주는 result 데이터가 JSON이여야 하는 것 기억해라
		}
		
		@GetMapping(value = "seoul/reply_insert.do", produces = "text/plain;charset=utf-8")
		public String reply_insert(ReplyVO vo,HttpSession session)
		{
			String id=(String)session.getAttribute("id");
			String name=(String)session.getAttribute("name");
			vo.setId(id);
			vo.setName(name);
			vo.setType(vo.getType()+3);
			
			dao.replyInsert(vo);
			List<ReplyVO> list=dao.replyListData(vo);
			String result=reply_json_data(list, id);
			
			return result;
			
		}


		@GetMapping(value = "seoul/reply_delete.do",produces = "text/plain;charset=utf-8")
		public String reply_delete(ReplyVO vo,HttpSession session)
		{
			String result="";
			String id=(String)session.getAttribute("id");
			
			//여기서부터 삭제
			dao.replyDelete(vo.getNo());
			//삭제 후 목록을 리턴
			vo.setType(vo.getType()+3);
			List<ReplyVO> list=dao.replyListData(vo);
			result=reply_json_data(list, id);
			return result;
		}
		// 수정 예정!!!!!! *************************************************
		@PostMapping(value = "seoul/reply_update.do",produces = "text/plain;charset=utf-8")
		public String reply_update(ReplyVO vo)
		{
			String result="<script>location.href=\"../seoul/detail.do?cno="+vo.getCno()+"&type="+vo.getType()+"\";</script>";
			dao.replyUpdate(vo);
			return result;
		}
}
