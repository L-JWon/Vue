package com.sist.dao;

import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.*;
import com.sist.mapper.*;
import com.sist.vo.CategoryVO;
import com.sist.vo.FoodVO;

@Repository
public class FoodDAO {
	@Autowired
	private FoodMapper mapper;
	
	/*@Select("SELECT cno,title,poster,subject "
			+ "FROM food_category "
			+ "WHERE cno=#{cno}")*/
	public List<CategoryVO> categoryListData(Map map)
	{
		return mapper.categoryListData(map);
	}
	
	/*@Select("SELECT fno,name,poster,address,tel,type "
			+ "FROM food_house "
			+ "WHERE cno=#{cno}")*/
	public List<FoodVO> foodListData(int cno)
	{
		return mapper.foodListData(cno);
	}
	
	/*@Select("SELECT cno,title,subject "
			+ "FROM food_category "
			+ "WHERE cno=#{cno}")*/
	public CategoryVO categoryInfoData(int cno)
	{
		return mapper.categoryInfoData(cno);
	}
	
	/*@Select("SELECT fno,name,poster,address,score,tel,type,time,parking,menu "
			+ "FROM food_house "
			+ "WHERE fno=#{fno}")*/
	public FoodVO foodDetailData(int fno)
	{
		return mapper.foodDetailData(fno);
	}
	
	/*
	 * 	@Select("SELECT fno,name,poster,num "
			+ "FROM (SELECT fno,name,poster,rownum AS num "
			+ "FROM (SELECT fno,name,poster "
			+ "FROM food_location WHERE address LIKE '%'||#{address}||'%'))"
			+ "WHERE num BETWEEN #{start} AND #{end} ")
	public List<FoodVO> foodFindData(Map map);
	
	@Select("SELECT CEIL(COUNT(*)/12.0) FROM food_location "
			+ "WHERE address LIKE '%'||#{address}||'%'")
	public int foodLocationTotalPage(String addr);
	 */
	
	public List<FoodVO> foodFindData(Map map)
	{
		return mapper.foodFindData(map);
	}
	
	public int foodLocationTotalPage(String addr)
	{
		return mapper.foodLocationTotalPage(addr);
	}
	
	public FoodVO foodDetailVueData(int fno)
	{
		return mapper.foodDetailVueData(fno);
	}
	
	public List<FoodVO> foodAllData(Map map)
	{
		return mapper.foodAllData(map);
	}
	
	public int foodTotalPage()
	{
		return mapper.foodTotalPage();
	}
	
}
