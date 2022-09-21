package com.sist.dao;

import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.mapper.RecommandMapper;
import java.util.*;
import com.sist.vo.*;
import com.sist.mapper.*;

@Repository
public class RecommandDAO {
	@Autowired
	private RecommandMapper mapper;
	
	public List<String> recommandNameData()
	{
		return mapper.recommandNameData();
	}
	
	public FoodVO recommandDetailData(String name)
	{
		return mapper.recommandDetailData(name).get(0);
	}
}
