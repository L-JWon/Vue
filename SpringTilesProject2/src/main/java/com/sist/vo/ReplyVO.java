package com.sist.vo;

import java.util.*;

import lombok.Getter;
import lombok.Setter;

/*
NO      NOT NULL NUMBER       
CNO              NUMBER       
TYPE             NUMBER       
ID               VARCHAR2(20) 
NAME    NOT NULL VARCHAR2(34) 
MSG     NOT NULL CLOB         
REGDATE          DATE    
 */
@Getter
@Setter
public class ReplyVO {
	private int no,cno,type;
	private String id,name,msg,dbday;
	private Date regdate;
}
