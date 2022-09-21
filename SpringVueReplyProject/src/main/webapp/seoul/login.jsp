<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
.container{
  margin-top: 30px;
}
.row{
  margin: 0px auto;
  width: 420px;
}
</style>
<script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.min.js"></script>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
</head>
<body>
  <div class="container">
    <h1 class="text-center">Login</h1>
    <!-- 
    1. 비밀번호 암호화 / 복호화
    2. 권한 부여
    3. 자동로그인
    4. 메소드 보안
    5. Task
    6. Validation 
    -->
    <div class="row">
      <table class="table">
        <tr>
          <th width="30%" class="text-center">ID</th>
          <td width="70%">
            <input type="text" size="15" ref="id" class="inpu-sm" v-model="id">
          </td>
        </tr>
        <tr>
          <th width="30%" class="text-center">PWD</th>
          <td width="70%">
            <input type="password" size="15" ref="pwd" class="inpu-sm" v-model="pwd">
          </td>
        </tr>
        <tr>
          <td colspan="2" class="text-center">
            <input type="button" value="로그인" class="btn btn-sm btn-danger" @click="login()">
            <input type="button" value="회원가입" class="btn btn-sm btn-danger" @click="join()">
          </td>
        </tr>
      </table>
    </div>
  </div>
  <!-- 
  	Vue, React는 라이브러리일뿐 기본은 자바스크립트(문법, 데이터, 함수)
  	==========
  	   자바스크립트 
  	   	데이터형
  	   	  = let, const, var (자동 지정 변수)
  	   	    let a='' =>  a: String
  	   	    let a="" =>  a: String
  	   	    let a=10 =>  a:Number ==> int
  	   	    let a=10.5 =>  a:Number ==> double
  	   	    let a={} => a:Object ==> ~VO (객체)
  	   	    let a=[] => a:Object ==> Array ==> List
  	   	    let a=true => a:boolean
  	   	 함수
  	   	 	function func_name(매개변수..){} => * 매개변수는 이름만 (let a) x => (a) o
  	   	 	let func_name=function(매개변수){}
  	   	 	let function=>(){} arrow함수
  	   	 			    ---- 리턴형 / function을 제거 
  	   	    
   -->
  <script type="text/javascript">
  new Vue({
	  el:'.container',  //el (element = 태그)
	  data:{
		  id:'',
		  pwd:'',
		  res:''
	  },
	  //사용자 정의 함수 => 자바스크립트 문법을 그대로 적용
	  methods:{
		  login:function(){
			  //유효성 검사
			  if(this.id.trim()==="")
			  {
				  this.$refs.id.focus();
				  return;
			  }
			  if(this.pwd.trim()==="")
			  {
				  this.$refs.pwd.focus();
				  return;
			  }
			  
			  //데이터 전송 (스프링 서버) => 전송 / 응답을 페이지 전환 없이 바로바로 
			  let _this=this;
			  axios.get("http://localhost:8080/web/member/login_vue.do",{
				  params:{
					  id:_this.id,
					  pwd:_this.pwd
				  }
			  }).then(function(result){ // 콜백 함수 시스템에 의해 자동 호출 되는 함수 => ajax에서 success:function(result)) 과 동일
				  _this.res=result.data;
			  
			  	  if(_this.res=='NOID')
				  {
					alert("ID가 존재하지 않습니다");
					_this.id="";
					_this.pwd="";
					_this.$refs.id.focus();
				  }
				  else if(_this.res=='NOPWD')
				  {
						alert("비밀번호를 확인해주세요");
						_this.pwd="";
						_this.$refs.pwd.focus();
				  }
				  else
				  {
					  location.href="../seoul/list.do";
				  }
			  })
		  }
	  }
  })
  </script>
</body>
</html>