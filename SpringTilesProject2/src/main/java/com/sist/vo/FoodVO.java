package com.sist.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FoodVO {
	private int cno,fno;
	private String name,address,tel,type,price,time,parking,menu,poster;
	private double score;
}
