package com.sist.service;
import java.util.*;

import com.sist.vo.SeoulVO;

public interface SeoulService {
	public List<SeoulVO> seoulListData(Map map);
	public SeoulVO seoulDetailData(Map map);// 클래스 캡슐화
	public int seoulTotalPage(Map map);
	public List<SeoulVO> seoulTop5List(Map map);
}
