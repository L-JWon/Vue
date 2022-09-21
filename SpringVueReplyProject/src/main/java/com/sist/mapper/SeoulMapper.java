package com.sist.mapper;
import java.util.*;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.sist.vo.*;

public interface SeoulMapper {
	@Select("SELECT no,poster,title,num "
			+ "FROM (SELECT no,poster,title,rownum AS num "
			+ "FROM (SELECT no,poster,title "
			+ "FROM ${table_name} ORDER BY no ASC)) "
			+ "WHERE num BETWEEN #{start} AND #{end}")
	public List<SeoulVO> seoulListData(Map map);
	
	@Select("SELECT CEIL(COUNT(*)/12.0) FROM ${table_name}")
	public int seoulTotalPage(Map map);
	
	@Update("UPDATE ${table_name} SET "
			+ "hit=hit+1"
			+ "WHERE no=#{no}")
	public void hitIncrement(Map map);
	
	@Select("SELECT * FROM ${table_name} WHERE no=#{no}")
	public SeoulVO seoulDetailData(Map map);
	
	
	//Login 처리
	@Select("SELECT COUNT(*) FROM spring_member2 "
			+ "WHERE id=#{id}")
	public int memberIdCheck(String id);
	
	@Select("SELECT pwd,name FROM spring_member2 "
			+ "WHERE id=#{id}")
	public MemberVO memverInfoData(String id);
}
