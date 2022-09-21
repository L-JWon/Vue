<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.min.js"></script>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
</head>
<body>
  <div class="container">
    <div class="row row1">
	      Search:<input type="text" size="20" class="input-sm" :value="ss" ref="ss" v-model="ss">
	      <!-- vo-model은 아래 new Vue 안에 있는 data ss='강남' 값을 바꿔주는 역할  -->
	      <input type="button" value="검색" class="btn btn-sm btn-primary"  v-on:click="foodFind()">
    </div>
    <div style="height: 50px"></div>
    <div class="row">
         <div class="col-md-4" v-for="vo in food_list">
		    <div class="thumbnail">
		      <a :href="'../food/food_detail_vue.do?fno='+vo.fno">
		        <img :src="vo.poster" alt="Lights" style="width:100%">
		        <div class="caption">
		          <p>{{vo.name}}</p>
		        </div>
		      </a>
		    </div>
		  </div>
    </div>
    <div style="height: 20px"></div>
    <div class="row">
      <div class="text-center">
        <input type="button" class="btn btn-sm btn-danger" v-on:click="prev()" value="이전">
        {{curpage}} page / {{totalpage}} pages
        <input type="button"  class="btn btn-sm btn-danger" v-on:click="next()" value="다음">
      </div>
    </div>
  </div>
  <script type="text/javascript">
   new Vue({
	   el:'.container',
	   data:{
		   curpage:1,
		   totalpage:0,
		   ss:'강남',
		   food_list:[]  
	   },
	   //params => vue_find.do?ss='' &page=''    ''여기 안에 들어가서 넘겨주는 과정 표현을 아래 처럼 쓰는 것  
	   mounted:function(){
		   //http://localhost:8080/web/food/vue_find.do?ss=%EA%B0%95%EB%82%A8&page=1 데이터가 이렇게 넘어감
		  /*  axios.get('http://localhost:8080/web/food/vue_find.do',{
			   params:{
				   ss:this.ss,  //this를 꼭 적어서 맴버 변수를 넘겨준다
				   page:this.curpage
			   }//여기까지가 값을 넘겨주는 곳 
		   }).then(result=>{   
			 //ㄴ얘가 결과값을 다시 받아오는 놈임
			 console.log(result); //일단 출력이 되는지 확인
			 
			 this.food_list=result.data;
			 this.curpage=this.food_list[0].curpage;
			 this.totalpage=this.food_list[0].totalpage;
		   }) */
		   this.send();
	   },
	   methods:{
		   send:function(){
			   axios.get('http://localhost:8080/web/food/vue_find.do',{
				   params:{
					   ss:this.ss,  //this를 꼭 적어서 맴버 변수를 넘겨준다
					   page:this.curpage
				   }//여기까지가 값을 넘겨주는 곳 
			   }).then(result=>{   
				 //ㄴ얘가 결과값을 다시 받아오는 놈임
				 console.log(result); //일단 출력이 되는지 확인
				 
				 this.food_list=result.data;
				 this.curpage=this.food_list[0].curpage;
				 this.totalpage=this.food_list[0].totalpage;
			   })
		   },
		   foodFind:function(){
			   if(this.ss=="")
			   {
				   alert("검색어를 입력하세요");
				   this.$refs.ss.focus();
				   return;
			   }
			   this.send();
		   },
		   prev:function(){
			   this.curpage=this.curpage>1?this.curpage-1:this.curpage;
			   this.send();
		   },
		   next:function(){
			   this.curpage=this.curpage<this.totalpage?this.curpage+1:this.curpage;
			   this.send();
		   }
	   }
   
   })
  </script>
</body>
</html>