package com.sist.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.sist.vo.*;
import com.sist.mapper.*;
@Repository
public class MemberDAO {
	@Autowired
	private MemberMapper mapper;
	
	/*@Select("SELECT COUNT(*) FROM spring_join WHERE id=#{id}")*/
	public int memberIdCheck(String id)
	{
		return mapper.memberIdCheck(id);
	}
	
	/*@Insert("INSERT INTO spring_join VALUES("
			+ "#{id},#{pwd},#{name},#{sex},#{birthday},#{email},#{post},"
			+ "#{addr1},#{addr2},#{tel},#{content},#{sessionid},#{limited},'ROLE_USER'")*/
	public void memberJoinInsert(MemberVO vo)
	{
		mapper.memberJoinInsert(vo);
	}
	
	/*@Select("SELECT pwd,name,role FROM spring_join WHERE id=#{id}")*/
	public MemberVO memberJoinInfoData(String id)
	{
		return mapper.memberJoinInfoData(id);
	}
}
