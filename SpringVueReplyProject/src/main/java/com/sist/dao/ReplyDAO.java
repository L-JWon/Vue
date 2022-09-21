package com.sist.dao;

import org.springframework.stereotype.Repository;

import com.sist.vo.ReplyVO;

import oracle.jdbc.OracleTypes;

import java.util.*;
import java.sql.*;


//Procedure 이용해서 댓글 달기
@Repository
public class ReplyDAO {
	private Connection conn;
	private CallableStatement cs;
	private final String URL="jdbc:oracle:thin:@localhost:1521:XE";
	
	public ReplyDAO()
	{
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void getConnection()
	{
		try {
			conn=DriverManager.getConnection(URL,"hr","happy");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public void disConnection()
	{
		try {
			if(cs!=null) cs.close();
			if(conn!=null) conn.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	// 댓글 읽기
	/*
		create or replace NONEDITIONABLE PROCEDURE replyListData(
		    pCno spring_reply2.cno%TYPE,
		    pType spring_reply2.type%TYPE,
		    pResult OUT SYS_REFCURSOR
		)
		IS
		BEGIN
		    OPEN pResult FOR
		        SELECT no,cno,type,id,name,msg,TO_CHAR(regdate,'YYYY-MM-DD HH24:MI:SS')
		        FROM spring_reply2
		        WHERE cno=pCno AND type=pType
		        ORDER BY no DESC;
		END;
	 */
	public List<ReplyVO> replyListData(ReplyVO vo)
	{
		List<ReplyVO> list=new ArrayList<ReplyVO>();
		try {
			getConnection();
			String sql="{CALL replyListData(?,?,?)}";
			cs=conn.prepareCall(sql);
			cs.setInt(1, vo.getCno()); //명소의 참조 번호 
			cs.setInt(2, vo.getType()); //명소냐 자연이냐 쇼핑이냐 구분해주는 타입 번호 
			// 1. Int형으로 값을 받아 왔다 => 여기서 OracleTypes.INTEGER
			// 1. String형으로 값을 받아 왔다 => 여기서 OracleTypes.VARCHAR
			cs.registerOutParameter(3, OracleTypes.CURSOR);
			cs.executeQuery();
			ResultSet rs=(ResultSet)cs.getObject(3);
			//Cursor ==ResultSet
			//type 1,2,3이 이미 전 프로젝트에서 쓰고 있기 때문에 4,5,6으로 변경해줌
			//4 (location) , 5 (nature),  6(shop) => 1,2,3 (+3)
			while(rs.next())
			{
				ReplyVO rvo=new ReplyVO();
				rvo.setNo(rs.getInt(1));
				rvo.setCno(rs.getInt(2));
				rvo.setType(rs.getInt(3));
				rvo.setId(rs.getString(4));
				rvo.setName(rs.getString(5));
				rvo.setMsg(rs.getString(6));
				rvo.setDbday(rs.getString(7));
				list.add(rvo);
				
			}
			rs.close();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally {
			disConnection();
		}
		return list;
	}
	// 댓글 쓰기
	/*
		create or replace NONEDITIONABLE PROCEDURE replyInsert(
		    pCno spring_reply2.cno%TYPE,
		    pType spring_reply2.type%type,
		    pId spring_reply2.id%TYPE,
		    pName spring_reply2.name%TYPE,
		    pMsg spring_reply2.msg%TYPE
		)
		IS
		BEGIN
		    INSERT INTO spring_reply2 VALUES(
		        (SELECT NVL(MAX(no)+1,1) FROM spring_reply2),
		        pCno,pType,pId,pName,pMsg,SYSDATE
		    );
		    COMMIT;
		END;

	 */
	public void replyInsert(ReplyVO vo)
	{
		try {
			getConnection();
			String sql="{CALL replyInsert(?,?,?,?,?)}";
			cs=conn.prepareCall(sql); //프로시저 함수 호출
			// <select ~~ statement="CALLABLE">{}</select>  => select
			cs.setInt(1, vo.getCno());
			cs.setInt(2, vo.getType());
			cs.setString(3, vo.getId());
			cs.setString(4, vo.getName());
			cs.setString(5, vo.getMsg());
			cs.executeQuery();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally {
			disConnection();
		}
	}
	// 댓글 수정
	/*
		create or replace NONEDITIONABLE PROCEDURE replyUpdate(
		    pNo spring_reply2.no%TYPE,
		    pMsg spring_reply2.msg%TYPE
		)
		IS
		BEGIN
		    UPDATE spring_reply2 SET
		    msg=pMsg
		    WHERE no=pNo;
		    COMMIT;
		END;
	 */
	public void replyUpdate(ReplyVO vo)
	{
		try {
			getConnection();
			String sql="{CALL replyOpdate(?,?)}";
			cs=conn.prepareCall(sql);
			cs.setInt(1, vo.getNo());
			cs.setString(2, vo.getMsg());
			cs.executeQuery();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally {
			disConnection();
		}
	}
	
	// 댓글 삭제 
	/*
		create or replace NONEDITIONABLE PROCEDURE replyDelete(
		    pNo spring_reply2.no%TYPE
		)
		IS
		BEGIN
		    DELETE FROM spring_reply2
		    WHERE no=pNo;
		    COMMIT;
		END;

	 */
	public void replyDelete(int no)
	{
		try {
			getConnection();
			String sql="{CALL replyDelete(?)}";
			cs=conn.prepareCall(sql);
			cs.setInt(1, no);
			cs.executeQuery();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally {
			disConnection();
		}
	}
}
