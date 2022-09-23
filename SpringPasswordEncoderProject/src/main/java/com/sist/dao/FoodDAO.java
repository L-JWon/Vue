package com.sist.dao;

import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.*;
import com.sist.mapper.*;
import com.sist.vo.*;
@Repository
public class FoodDAO {
	@Autowired
	private FoodMapper mapper;
	
	
	
	public List<CategoryVO> foodCategoryAllData()
	{
		return mapper.foodCategoryAllData();
	}
	
	/*@Select("SELECT * fno,cno,name,tel,poster,type FROM food_house "
			+ "WHERE cno=#{cno}")*/
	public List<FoodVO> foodCategoryListData(int cno)
	{
		return mapper.foodCategoryListData(cno);
	}
	
	public CategoryVO categoryInfoData(int cno)
	{
		return mapper.categoryInfoData(cno); 
		//여기서 가져온 데이터를 jsp에 뿌려줘야하는데 그걸 하는 역할이 컨트롤러  그러니까 컨트롤러에서 dao를 autowired 하잖아 
	}
	
	/*@Select("SELECT poster,rownum FROM recipe "
			+ "WHERE REGEXP_LIKE(title,#{type}) "
			+ "AND rownum<=12")*/
	public List<String> foodLikeRecipe(String type)
	{
		return mapper.foodLikeRecipe(type);
	}
	
	public FoodVO foodDetailData(int fno)
	{
		return mapper.foodDetailData(fno);
	}
}
