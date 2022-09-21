<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.min.js"></script>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
<style type="text/css">

.row1{
	margin: 0px auto;
	width: 700px;
}
h1{
	text-align: center;
}
</style>
</head>
<body>
   <div class="container">
     <h1>수정하기</h1>
     <div class="row row1">
       <table class="table">
         <tr>
           <th width="20%" class="text-right">이름</th>
           <td width="80%">
             <input type="text"  ref="name" size="20" class="input-sm" v-model="name">
             <input type="hidden" name=no :value="vo.no"> 
           </td>
         </tr>
         <tr>
           <th width="20%" class="text-right">제목</th>
           <td width="80%">
             <input type="text"  ref="subject" size="50" class="input-sm" v-model="subject">
           </td>
         </tr>
         <tr>
           <th width="20%" class="text-right">내용</th>
           <td width="80%">
             <textarea rows="10" cols="50" v-model="content" ref="content">{{content }}</textarea>
           </td>
         </tr>
         <tr>
           <th width="20%" class="text-right">비밀번호</th>
           <td width="80%">
             <input type="password" v-model="pwd" ref="pwd" size="15" class="input-sm">
           </td>
         </tr>
         <tr>
           <td colspan="2" class="text-center">
             <input type="button" value="수정" class="btn btn-sm btn-warning" id="writeBtn" v-on:click="boardUpdate()">
             <input type="button" value="취소" class="btn btn-sm btn-info"
             onclick="javascript:history.back()">
           </td>
         </tr>
       </table>
     </div>
   </div>
   <script type="text/javascript">
   new Vue({
	   el:'.container',
	   data:{
		   name:'',
		   subject:'',
		   content:'',
		   pwd:'',
		   vo:{},
		   no:${no},
		   res:'' //비밀번호가 틀리냐 맞냐 검증하는 변수 
	   },
	   mounted:function(){
		   let _this=this;
		   axios.get("http://localhost:8080/web/board/update_vue.do",{
	    		//이 부분이 요청하는 부분
	    			params:{
	    				no:_this.no
	    			}
	    		}).then(function(result){
	    			// 이 부분이 요청처리 결과값 읽기 => 데이터값 변경(상태변경) => 상태관리 프로그램(Vue,React)
	    			_this.vo=result.data;
	    			_this.name=_this.vo.name;
	    			_this.subject=_this.vo.subject;
	    			_this.content=_this.vo.content;
	    		})
	   },
	   methods:{
		   boardUpdate:function(){
			   //여기서 유효성 검사
			   if(this.name.trim()=="")
			   {
				   this.$refs.name.focus(); //ref라는 태그를 읽어서 속성 값을 가져옴
				   return;
			   }
			   if(this.subject.trim()=="")
			   {
				   this.$refs.subject.focus(); //ref라는 태그를 읽어서 속성 값을 가져옴
				   return;
			   }
			   if(this.content.trim()=="")
			   {
				   this.$refs.content.focus(); //ref라는 태그를 읽어서 속성 값을 가져옴
				   return;
			   }
			   if(this.pwd.trim()=="")
			   {
				   this.$refs.pwd.focus(); //ref라는 태그를 읽어서 속성 값을 가져옴
				   return;
			   }
			   
			   let _this=this;
			   axios.get("http://localhost:8080/web/board/update_vue_ok.do",{
				   params:{
					   no:_this.no,
					   name:_this.name,
					   subject:_this.subject,
					   content:_this.content,
					   pwd:_this.pwd
				   }
			   }).then(function(result){
				   _this.res=result.data; //Yes or No 비밀번호 맞냐 틀리냐
				   if(_this.res==='yes')
				   {
					   location.href="../board/detail.do?no="+_this.no;
				   }
				   else
				   {
					   alert("비밀번호를 확인해주세요");
					   _this.pwd="";
					   _this.$refs.pwd.focus();
				   }
			   })
		   }
	   }
   })
   </script>
</body>
</html>