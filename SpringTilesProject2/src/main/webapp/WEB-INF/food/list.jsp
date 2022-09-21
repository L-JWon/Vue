<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.min.js"></script>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
<style type="text/css">
.container-fluid{
	margin-top: 50px;
}
.row1{
	margin: 0px auth;
	width: 100%;
}
.images:hover{
	cursor: pointer;
	
}
</style>
</head>
<body>
  <div class="container-fluid">
    <div class="row row1">
      <div class="col-sm-8">
       <template id="food_template">
         <div class="col-md-4" v-for="vo in food_data">
		    <div class="thumbnail">
		        <img :src="vo.poster" alt="Lights" style="width:100%" class="images" v-on:click="detailData(vo.fno)">
     									<!-- 
     										 v-on:click = @click
     										 v-bind:src = :src :Vue의 변수(data)와 매칭을 시켜주는 것이 bind 
     										 v-model : 양방향 통신을 할 수있게 한다
     										 
     										 Vue / React  :react는 단방향은 지원 x
     										 --- 양뱡향,단반향 둘 다 가능하지만
     									-->
		        <div class="caption">
		          <p>{{vo.name }}</p>
		        </div>
		    </div>
		  </div>
	   </template>
      <div style="height: 20px"></div>
        <div class="text-center">
          <button class="btn btn-sm btn-info" v-on:click="prev()">이전</button>
            {{curpage}} page / {{totalpage}} pages
          <button class="btn btn-sm btn-info" v-on:click="next()">다음</button>
        </div>
      </div>
    
      <div class="col-sm-4" v-show="isShow" v-if="isShow===true">
        <table class="table">
          <tr>
            <td class="text-center" v-for="img in food_detail.poster.split('^')">
              <img :src="img" style="width: 100%">
            </td>
          </tr>
        </table>
          <div style="height: 10px"></div>
          <table class="table">
            <tr>
              <td colspan="2">
                <h3>{{food_detail.name}}&nbsp;&nbsp;<span style="color: orange;">{{food_detail.score}}</span></h3>
              </td>
            </tr>
            <tr>
              <td style="color: gray;" width="20%">주소</td>
              <td style="width: 80%">{{food_detail.address}}</td>
            </tr>
            <tr>
              <td style="color: gray;" width="20%">전화</td>
              <td style="width: 80%">{{food_detail.tel}}</td>
            </tr>
            <tr>
              <td style="color: gray;" width="20%">음식 종류</td>
              <td style="width: 80%">{{food_detail.type}}</td>
            </tr>
            <tr>
              <td style="color: gray;" width="20%">가격대</td>
              <td style="width: 80%">{{food_detail.}}</td>
            </tr>
            <tr>
              <td style="color: gray;" width="20%">주차</td>
              <td style="width: 80%"></td>
            </tr>
            <tr>
              <td style="color: gray;" width="20%">시간</td>
              <td style="width: 80%"></td>
            </tr>
            <tr>
              <td style="color: gray;" width="20%">메뉴</td>
              <td style="width: 80%"></td>
            </tr>

           
          </table>
      </div>
    </div>
  </div>
  <script type="text/javascript">
    new Vue({
    	el:'.row',
    	data:{
    		curpage:1,
    		totalpage:0,
    		food_data:[],
    		food_detail:{},
    		fno:0,
    		isShow:false
    	},
    	mounted:function(){
    		console.log("VueJS=>this:"+this); //Vue(Object)
    		let _this=this;
    		axios.get("http://localhost:8080/web/food/food_all_vue.do",{
    			params:{
    				page:_this.curpage
    			}
    		}).then(function(result){
    			console.log("function=>this:"+_this); // Window가 가지고 있는 this
    			_this.food_data=result.data;
    			_this.curpage=result.data[0].curpage;
    			_this.totalpage=result.data[0].totalpage;
    		})
    	},
    	methods:{
    		prev:function(){
    			this.curpage=this.curpage>1?this.curpage-1:this.curpage;
    			let _this=this;
        		axios.get("http://localhost:8080/web/food/food_all_vue.do",{
        			params:{
        				page:_this.curpage
        			}
        		}).then(function(result){
        			_this.food_data=result.data;
        			_this.curpage=result.data[0].curpage;
        			_this.totalpage=result.data[0].totalpage;
        		})
    		},
    		next:function(){
    			this.curpage=this.curpage<this.totalpage?this.curpage+1:this.curpage;
    			let _this=this;
        		axios.get("http://localhost:8080/web/food/food_all_vue.do",{
        			params:{
        				page:_this.curpage
        			}
        		}).then(function(result){
        			_this.food_data=result.data;
        			_this.curpage=result.data[0].curpage;
        			_this.totalpage=result.data[0].totalpage;
        		})
    		},
    		/*
    				//vue.do?fno=1&gno=10
    						public String detail(int no1, int no2) 매개변수를 자동으로 인식하는 것이 아님
    												
    		*/
    		detailData:function(no){ //얼로우 함수면 this 유지 function은 오브젝트로 인식해서 let에 this값을 넣어줘야함
    			let _this=this;
    			_this.isShow=true;
    		axios.get("http://localhost:8080/web/food/detail_vue.do",{
    			params:{
    				fno:no     //vue.do?fno=1
    			}
    		}).then(function(result){
    			_this.food_detail=result.data; //result 안에는 config등 모든 정보가 다 들어있기 때문에 데이터 값만 받아오기 위해 .data를 꼭 붙여준다
    		})
    			
    		}
    	}
    })
  </script>
</body>
</html>