<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="http://unpkg.com/vue@3"></script>
<script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.min.js"></script>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
.container{
  margin-top: 30px;
}
.row{
  margin: 0px auto;
  width: 100%;
}
</style>
</head>
<body>
   <div class="container">
      <div class="row">
        <table class="table">
          <tr>
            <td>
              <input type="text" size="30" class="input-sm" v-model="msg" ref="msg">
              <input type="button" class="btn btn-sm btn-danger" value="입력" v-on:click="myclick()">
              																<!-- @click 으로 써도 동일 -->
              
            </td>
          </tr>
          <tr> 
            <td>{{msg}}</td>
          </tr>
        </table>
      </div>
   </div>
   <script type="text/javascript">
     new Vue({
    	el:'.container',
    	data:{
			msg:'' /*멤버변수 사용 시에는 무조건 this를 앞에 적어줘야한다  ex) this.데이터  */
    	},
     beforeCreate:function(){
    	 console.log("beforeCreate:Vue 객체 생성 전...")
     },
     created:function(){
    	 console.log("created:Vue 객체 생성...")
     },
     beforeMount:function(){
    	 console.log("beforeMount:가상 메모리에 DOM을 만들기 전 상태...")
     	 // componentWillMount()
     },
     mounted:function(){
    	 console.log("mounted:메모리에 HTML이 저장된 상태 : window.onload... $(function(){})")
     	 // componentDidMount()
     },
     beforeUpdate:function(){
    	 // 상태(state) 가 변경된 상태  ==> 상태가 뭐냐면 data값
    	 console.log("beforeUpdate: 상태가 변경 되기 직전에...")
    	 // React : state, Vue : data
    	 // Vue2 ==> Vue3
     },
     updated:function(){
    	 console.log("updated: 변경된 상태...")
     },
     beforeDestroy:function(){
    	 console.log("beforeDestroy: 메모리 해제 전...")
     },
     destroyed:function(){
    	 console.log("destroyed: 메모리 해제 된 상태...") //cookie,session 다 존재함
     },
     // 이것을 생명 주기 함수 (시스템에 의해 자동 호출되는 함수!!)
     methods:{
    	 myclick:function(){
    		 // data에 설정된 변수는 '멤버변수' = 반드시 사용 시에는 this.msg
    		 if(this.msg==='') // ES6 ==> 같다 (===), 같지않다 (!==) ''==='로 권장 
    		 {
    			 alert("데이터를 입력하세요!!");
    			 this.$refs.msg.focus(); //참조 변수 ==> 반드시 ref 속성을 이용한다
    			 // $refs
    		 }
    	 }
    	 
     }
     
     })
   </script>
   
   
</body>
</html>