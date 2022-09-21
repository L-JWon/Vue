package com.sist.dao;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.*;
import com.sist.mapper.*;
import com.sist.vo.*;

// MyBatis 이용한 DAO
/*
 * 	1. 스프링 : 클래스를 모아서 관리
 * 	          ============== 사용자 정의 클래스나 라이브러리를 모아서 관리해주는 것 
 * 							 (왜 모아서 관리하느냐? : 의존성이 낮은 프로그램 = 유지보수 시에 다른 클래스에 영향이 없는 프로그램을 만들기 위해)
 * 							 클래스 : 컴포넌트(기능을 가지고 있는 클래스)
 * 						     컨테이너에 대한 종류, 하는 역할 (*****)
 * 							BeanFactory
 * 				                |
 * 							ApllicationContext - AnnotationConfigApplicationContext (자바로 환경 설정할 때)
 * 								|
 * 							WebApllicationContext - AnnotationConfigWebApplicationContext
 * 
 * 	2. 클래스를 모아서 관리
 * 		사용자가 요청을 하면 => 클래스를 찾아주는 역할 ==>( DL Look-up ) => getBean()
 * 		클래스를 관리를 하기 위해 필요한 데이터를 첨부하여 보내줌 => ( DI Injection )
 * 
 * 	3. MVC (스프링 라이브러리)
 * ========================================================================
 * 		면접
	 * 		자바
	 * 		오라클
	 * 		JSP
	 * 		스프링
	 * 		조금더 나가면 AWS
 * 
 * 		스프링의 생명주기 (객체 생명주기 관리: 생성과 소멸 )
 * 									 ======== 필요한 데이터가 있을 수 있다 (DI)
 * 		       *IoC (제어의 역전) : 말 그대로 스프링을 통해 객체를 가지고 온다
 * 								========= 프로그래머가 직접 넣는 것이 아니라 스프링에게 요청을 하여 사용
 * 								하지만 지금은 DI로 통합이 되었음 (객체와 객체의 연관관계를 설계한다는 것이 IoC)
 * 			1. 클래스 메모리할당 (모든 클래스)	==> 생성자를 통해서 필요한 데이터를 주입 => 자주 변경되지않는 데이터라면 생성자 아니라면 setter가 편리
 * 					1) 한 개 씩 생성
 * 						<bean id="구분자" class="패키지.클래스명">
 * 					2) 패키지 단위로 생성
 * 						<context:component-scan base-package="패키지명"/>
 * 						*패키지 단위로 설정하게되면 메모리 할당 대상을 설정을 해줘야하는데 그것이 선택적 어노테이션이다(메모리 할당이 필요한 대상, 필요없는 대상을 구분해주는 역할 )
 * 																			  -------------
 * 																               @Controller : 웹 화면을 변경한다거나 사용자 요청 처리를 해줄 때 /JSP에 출력할 데이터를 전송할 때
 * 																							 ----------------
 * 																								forward (request를 유지하면서 새로운 데이터를 추가)
 * 																								스프링에서는 request 대신에 Model이라는 전송객체를 많이 사용한다
 * 																								=> .addAtrribute
 * 																								  => return "경로명/파일명";
 * 																								redirect: request를 초기화 시키고 기존에 있는 파일로 이동을 할 때 사용 
 * 																								=> Model을 사용할 수 없다 (재전송이기때문)
 * 																								  => return "redirect: ~.do";
 * 																						***핵심: '매개변수'가 중요하다 사용자가 요청한 데이터를 받아주는 곳!!!
 * 																						***웹: C/S (Client / Server) ==> 리턴형 (String, void)
 * 																							 요청/응답
 * 				
 * 																						*** 매개변수 ?no=10를 받았다면 (int no)로 매개변수 이름을 일치시켜야한다
 * 																						*** [], List, 일반변수를 받을 수 있다 (일반 변수는 모든데이터를 String으로)
 * 																							[] => checkbox를 받은 경우
 * 																							List => 파일이 여러개 업로드된 경우 
 * 																						***커맨드 객체란 ==> ~VO
 * 																			   @RestController : 사용자 요청처리를 한다는 것은 같지만 다른 프로그램에 데이터를 전송하는 목적으로 사용한다
 * 																									=> 자바 스크립트 , 코틀린 , C#
 * 																										Vue,Ajax,React...
 * 																									=>JSON (약자로 자바스크립트의 객체 표현법)
 * 																									=> {키:밸류,키:밸류} => 키는 멤버변수
 * 																									=> ex) let sa={"sabun":"1","name":"홍길동"}
 * 																										    sa.sabun을 출력해라 sa.name을 출력해라
 * 																									=> 일반 데이터(msg에 출력문자만 전송했을 때처럼), VO단위, List단위
 * 																																			{}      [{},{},{},{},{}...]
 * 																																		   JSONObject,  JSONArray 
 * 																									=> Spring-Boot는 자동으로 JSON으로 변경시켜 전송해줌 편리함
 * 																						   ======== Get / Post
 * 																							Get  => @GetMappng
 * 																							Post => @PostMapping
 * 																							둘다 처리 => @RequestMapping
 * 																							@DeleteMapping(삭제), @PutMapping(Update할 때)
 * 																						
 * 																			   @Component : 일반 클래스 메모리 할당을 했을 때 (AOP, Intercepter, MainClass, Manager...)
 * 																			   @Repository : 데이터 저장소 DB와 관련이 있는 클래스 (DAO)
 * 																			   @Service : BI (DAO를 통합) 
 * 																			   @ControllerAdvice : 통합 예외처리
 * 										
 * 																			   => 메모리 할당에 제외가 되는 것들이 있는데	
 * 																					~VO : 사용자 정의 데이터 형
 * 																					~Mapper : 인터페이스 영역이라 메모리 할당해주면 오류난다!!!
 * 			2. setter DI 	set메소드에 값을 채우는 것
 * 				어노테이션으로 메모리 할당 시 DI를 사용할 수 없다 
 * 				-----------------------------------
 * 					DI)
 * 						= 일반 데이터 주입 (X) => 억지로 한다면 properties 파일을 만들어서 사용이 가능하긴함
 * 						= 객체 주소 주입 => @Autowired
 * 						  ========== <bean id="" class="" p:~="">
 * 									      	                            일반변수 p:name=""
 * 										                                    주소값    p:ds-ref="id명";
 * 			3. 대기
 * 			------------ 객체 생성 (사용자가 호출 시)
 * 		    4. 사용자가 필요한 위치에서 객체 요청
 * 			------------------------------
 * 			5. 객체 소멸
 * 
 *      기능 
 *       1) DI : Setter DI, Constructor DI
 *       		 method DI(객체 생성 시 : init-method
 *       				      객체 소멸 시 : destroy-method 에서만 사용이 가능한 DI)
 *       2) AOP : 공통 모듈 (모든 Web에서 사용) => 자동호출
 *       		---------------
 *       	     두가지 Join Point :공통모듈을 첨부할 위치
 *       			   Before
 *       			   After
 *       			   After-Returning
 *       			   After-Throwing
 *       			   Around
 *       			Point Cut : 공통모듈을 첨부할 메소드 대상
 *       		--------------- + Advice
 *       		------------------------- Aspect
 *       3) MVC :																   HandlerMapping
 *       	  사용자 요청 ~.do 를 주게되면 ===>무조건 DispatcherServlet이 호출된다!!!!  ====>그럼 서블릿이 @Controller나 @RestController를 호출해준다 
	 *       																			 ===구분자(GetMapping,PostMapping)
	 *       																			  |  사용자가 보내준 요청 데이터를 매개변수로 받을 수 있다
	 *       																			  |  처리 결과값을 보낸다
	 *       																			ViewResolver이 결과값을 받은 뒤 보내줄 JSP를 파일을 찾는다
	 *       																						=> 경로명/파일명
	 *       																			  |
	 *       																			 JSP									
 *       4) ORM (MyBatis)
 *       	  = XML버전이 많이 사용
 *       	  = 어노테이션 버전으로 변경 
 *       	  = 스프링 4버전(XML혼합), 5버전 (순수 자바코딩)
 */
@Repository
public class SeoulDAO {
	//스프링에서 구현된 Mapper 주소값 주입 요청!!
	@Autowired
	private SeoulMapper mapper;
	
	public List<SeoulVO> seoulListData(Map map)
	{
		return mapper.seoulListData(map);
	}
	
	
	public int seoulTotalPage(Map map)
	{
		return mapper.seoulTotalPage(map);
	}
	
	
	
	
	public SeoulVO seoulDetailData(Map map)
	{
		mapper.hitIncrement(map);
		return mapper.seoulDetailData(map);
	}
	
	
	//로그인
	public MemberVO isLogin(String id,String pwd)
	{
		MemberVO vo=new MemberVO();
		int count=mapper.memberIdCheck(id);
		if(count==0)
		{
			vo.setMsg("NOID");
		}
		else
		{
			MemberVO rvo=mapper.memverInfoData(id);
			if(pwd.equals(rvo.getPwd()))
			{
				vo.setMsg("OK");
				vo.setName(rvo.getName());
				vo.setId(id);
			}
			else 
			{
				vo.setMsg("NOPWD");
			}
		}
		return vo;
	}
	
	
}
