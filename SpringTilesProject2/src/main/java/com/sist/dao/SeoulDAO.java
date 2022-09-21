package com.sist.dao;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.mapper.*;
import com.sist.vo.SeoulVO;

@Repository
public class SeoulDAO {

	@Autowired
	private SeoulMapper mapper;
	
	/*@Select("SELECT no,title,poster,num "
			+ "FROM (SELECT no,title,poster,rownum AS num "
			+ "FROM (SELECT no,title,poster "
			+ "FROM ${table_name} ORDER BY no ASC)) "
			+ "WHERE num BETWEEN #{start} AND #{end}")*/
	public List<SeoulVO> seoulListData(Map map)
	{
		return mapper.seoulListData(map);
	}
	
	/*@Select("SELECT * FROM ${table_name} "
			+ "WHERE no=#{no}")*/
	public SeoulVO seoulDetailData(Map map)
	{
		mapper.hitIncrement(map);
		return mapper.seoulDetailData(map);
	}
	
	
	public int seoulTotalPage(Map map)
	{
		return mapper.seoulTotalPage(map);
	}
	
	public List<SeoulVO> seoulTop5List(Map map)
	{
		return mapper.seoulTop5List(map);
	}
	
	
}
