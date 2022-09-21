<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
.row1{
   margin: 0px auto;
   width: 420px;
   height: 850px;
}
</style>
<script src="https://unpkg.com/vue"></script>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
</head>
<body>
<!-- 
	VueJS 동작하는 생명주기 
		  beforeCreate  componentWillCreate
		  created       componentDidCreate
		  ==================
		  beforeMount
		  mounted    ============> window.onload ($(function())
		  ================== 메모리에 올라가는 상테 (가상메모리)
		  beforeUpdate
		  updated
		  beforeDestroy
		  -------------------
	            가상 돔 : 메모리를 더블 버퍼링을 사용하고 있음 (가상 메모리)
		  MVVM
		  VueJS VS React ===> vue3(react) vuex vue-cli
		  					  =========================
		 
		 = 디렉티브 
		     v-( ... )
		     	 제어문 
		     	    v-for
		     	    v-if
		     	    v-else
		     	    v-show
		     	 연결
		     	    v-model ==> 생략할 때 : 을 사용 할 수 있다 
		     	 이벤트 처리
		     	    v-on:click , v-on:change
		 = 컴포넌트 : 재사용	  
	
	형식)
		<script>
		  = Vue 하나만 쓰는게 아니라 여러 개를 사용할 수 있다 (제어하는 영역이 서로 다를 경우에만)
		  
		  new Vue({
		  	el: => class:. id:# '.table' 테이블 클래스 영역을 제어 (Element 제어영역 어디서 부터 ~ 어디까지)
		  	data:{
		  			멤버변수:Vue에서 제어하는 변수
		  			a:[] => 배열 (스프링 => ArrayList=>JSONArray)
		  			b:{} => 객체 (스프링 => VO =>JSONObject) 자바와 자바 스크립트가 데이터가 맞지않아서 바꿔줘야함
		  			c:true ==> boolean
		  			d:'' ==> 문자
		  			e:0  ==> 정수형
		  	}
		  	생명주기 함수 
		  	mounted:function(){}
		  	
		  	사용자 정의 함수(이벤트 처리)
		  	methods:{
		  	
		  	}
		  	
		  	필터
		  	filters{
		  	
		  	}
		  })
		</script>
		
		AJAX : 단방향, Vue : 양방향
		
		=> 데이터 화면 출력
			{{변수명}} ==> 한번에 출력 ==> {{$data}}  <img src="{{}}"> (오류)
												 <img :src="poster">
												 *v-model ==> 생략할 때 : 을 사용 할 수 있다 
												 data에 설정되지 않은 변수 사용 시 오류!!
 -->
  <div class="container">
    <div class="row row1">
    <form method="post" action="../member/login_ok.do">
      <table class="table">
        <caption><h3 class="text-center">Login</h3></caption>
        <tr>
          <td width="30%" class="text-right">ID</td>
          <td width="70%">
            <input type="text" name="id" size="15" class="input-sm">
          </td>
        </tr>
        <tr>
          <td width="30%" class="text-right">PWD</td>
          <td width="70%">
            <input type="password" name="pwd" size="15" class="input-sm">
          </td>
        </tr>
        <tr>
          <td colspan="2" class="text-center">
            <input type="submit" class="btn btn-sm btn-danger" value="로그인">
          </td>
        </tr>
      </table>
      </form>
    </div>
  </div>
</body>
</html>