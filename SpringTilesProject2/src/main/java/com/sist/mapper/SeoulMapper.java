package com.sist.mapper;
import java.util.*;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.sist.vo.SeoulVO;
public interface SeoulMapper {
	@Select("SELECT no,title,poster,num "
			+ "FROM (SELECT no,title,poster,rownum AS num "
			+ "FROM (SELECT no,title,poster "
			+ "FROM ${table_name} ORDER BY no ASC)) "
			+ "WHERE num BETWEEN #{start} AND #{end}")
	public List<SeoulVO> seoulListData(Map map);
	
	@Select("SELECT CEIL(COUNT(*)) FROM ${table_name}")
	public int seoulTotalPage(Map map);
	
	@Select("SELECT * FROM ${table_name} "
			+ "WHERE no=#{no}")
	public SeoulVO seoulDetailData(Map map);
	
	@Update("UPDATE ${table_name} SET "
			+ "hit=hit+1 "
			+ "WHERE no=#{no}")
	public void hitIncrement(Map map);
	
	@Select("SELECT no,title,poster,hit,rownum "
			+ "FROM (SELECT no,title,poster,hit "
			+ "FROM ${table_name} ORDER BY hit DESC) "
			+ "WHERE rownum<=5")
	public List<SeoulVO> seoulTop5List(Map map);
}
