package com.sist.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.mapper.RecipeMapper;
import com.sist.vo.RecipeVO;

@Repository
public class RecipeDAO {
	@Autowired
	private RecipeMapper mapper;
	
	/*@Select("SELECT no,title,poster,chef,num "
			+ "FROM (SELECT no,title,poster,chef,rownum AS num "
			+ "FROM (SELECT /+ INDEX_ASC(recipe recipe_no_pk)/no,title,poster,chef "
			+ "FROM recipe))"
			+ "WHERE num BETWEEN #{start} AND #{end}")*/
	public List<RecipeVO> recipeListData(Map map)
	{
		return mapper.recipeListData(map);
	}
	
	public int recipeTotalPage()
	{
		return mapper.recipeTotalPage();
	}
	
	/*@Select("SELECT no,title,poster,rownum "
			+ "FROM recipe "
			+ "WHERE REGEXP_LIKE(title,#{ss})")*/
	public List<RecipeVO> recipeFindData(String ss)
	{
		return mapper.recipeFindData(ss);
	}
}
