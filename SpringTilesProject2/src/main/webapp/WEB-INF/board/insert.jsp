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
     <h1>글쓰기</h1>
     <div class="row row1">
       <table class="table">
         <tr>
           <th width="20%" class="text-right">이름</th>
           <td width="80%">
             <input type="text" v-model="name" size="20" class="input-sm" ref="name">
           </td>
         </tr>
         <tr>
           <th width="20%" class="text-right">제목</th>
           <td width="80%">
             <input type="text" v-model="subject" size="50" class="input-sm" ref="subject">
           </td>
         </tr>
         <tr>
           <th width="20%" class="text-right">내용</th>
           <td width="80%">
             <textarea rows="10" cols="50" v-model="content" ref="content"></textarea>
           </td>
         </tr>
         <tr>
           <th width="20%" class="text-right">비밀번호</th>
           <td width="80%">
             <input type="password" v-model="pwd"size="15" class="input-sm" ref="pwd">
           </td>
         </tr>
         <tr>
           <td colspan="2" class="text-center">
             <input type="button" value="글쓰기" class="btn btn-sm btn-warning" v-on:click="boardWrite()">
             <!-- @click -->
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
		   pwd:''
	   },
	   methods:{
		   boardWrite:function(){
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
			   
			   //전송
			   axios.get("http://localhost:8080/web/board/insert_vue.do",{
				   params:{
					   name:this.name,
					   subject:this.subject,
					   content:this.content,
					   pwd:this.pwd  //param은 값을 넘겨 주는 곳!!!!! 꼭 기억 
				   }
			   }).then(result=>{
				   location.href="../board/list.do";
			   })
		   }
	   }
   })
   </script>
</body>
</html>