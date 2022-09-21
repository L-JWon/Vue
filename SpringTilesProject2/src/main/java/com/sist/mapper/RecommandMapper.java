package com.sist.mapper;
import java.util.*;

import org.apache.ibatis.annotations.Select;

import com.sist.vo.*;
public interface RecommandMapper {
	@Select("SELECT DISTINCT name FROM food_location "
			+ "WHERE LENGTH(name)>1")
	public List<String> recommandNameData();
	
	@Select("SELECT fno,name,poster FROM food_location "
			+ "WHERE name=#{name}")
	public List<FoodVO> recommandDetailData(String name);
}
