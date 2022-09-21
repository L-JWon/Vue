package com.sist.recommand;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Component;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class RecommandManager {
	public List<String> jsonParser(String json)
	{
		List<String> list=new ArrayList<String>();
		try {
			JSONParser jp=new JSONParser();
			JSONObject root=(JSONObject)jp.parse(json); //{} 객체로 받아와서
			JSONArray arr=(JSONArray)root.get("items"); //[] 객체에 담긴 것 중에 item을 가져와라
			for(int i=0;i<arr.size();i++)
			{
				JSONObject obj=(JSONObject)arr.get(i);
				String desc=(String)obj.get("description");
				list.add(desc);
			}
		} catch (Exception e) {}
		return list;
	}
	/*public static void main(String[] args) {
		Scanner scan=new Scanner(System.in);
		System.out.println("계절 입력 : ");
		String fd=scan.next();
		String json=NaverDataClass.recommandData(fd);

		List<String> jList=jsonParser(json);

		FoodRecommandDAO dao=new FoodRecommandDAO();
		
		List<String> list=dao.foodAllData();
		
		try {
			Pattern[] p=new Pattern[list.size()]; //[A-Z] 단어패턴 (이름)
			
			for(int i=0;i<p.length;i++)
			{
				p[i]=Pattern.compile(list.get(i));
			}
			
			Matcher[] m=new Matcher[list.size()];
			List<RecommandVO> rList=new ArrayList<RecommandVO>();
			int[] count=new int[list.size()];
			for(String s:jList)
			{
				for(int i=0;i<m.length;i++)
				{
					m[i]=p[i].matcher(s); //matcher는 찾았냐 못찾았냐
					if(m[i].find())
					{
						String ss=m[i].group();
						
						 // RecommandVO vo=new RecommandVO(); vo.setName(ss);
						// vo.setCount(vo.getCount()+1); rList.add(vo);
						 
						count[i]++;
					}
				}
			}
			// 실제 추천할 데이터 목록
			for(int i=0;i<list.size();i++)
			{
				String name=list.get(i);
				if(count[i]>=2)
				{
					System.out.println(name+":"+count[i]);
				}
			}
		} catch (Exception e) {}
	}*/
}
