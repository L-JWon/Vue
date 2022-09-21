package com.sist.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sist.vo.SeoulVO;
import com.sist.dao.*;
/*
 * 
 *    1.@Controller
 *    2.@RestController
 *    3.@Repository
 *    4.@Service ==> BI (DAO를 통합)
 *    -------------------------------------면접질문 repository와 service의 차이점 
 *    5.@Component
 *    ------------------------자주 등장
 *    6.@ControllerAdvice : 통합 
 *    ----------------------------------여가까지 가장 많이 쓰이는 형태
 *    DI 의존성 주입할 때 쓰이는 어노테이션
 *    @Autowired
 *    ----------- 정도만 할줄 안다면 기본
 *    @Inject
 *    
 */
@Service
public class SeoulServiceImpl implements SeoulService {

	@Autowired
	private SeoulDAO dao;
	
	@Override
	public List<SeoulVO> seoulListData(Map map) {
		// TODO Auto-generated method stub
		return dao.seoulListData(map);
	}

	@Override
	public SeoulVO seoulDetailData(Map map) {
		// TODO Auto-generated method stub
		return dao.seoulDetailData(map);
	}

	@Override
	public int seoulTotalPage(Map map) {
		// TODO Auto-generated method stub
		return dao.seoulTotalPage(map);
	}

	@Override
	public List<SeoulVO> seoulTop5List(Map map) {
		// TODO Auto-generated method stub
		return dao.seoulTop5List(map);
	}

}
