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
.row1{
  width: 850px;
}
</style>
</head>
<body>
  <div class="container">
    <h1 class="text-center">VueJS 목록 출력</h1>
    <div class="row row1">
      <table class="table">
        <tr>
          <td>
            <a href="../board/insert.do" class="btn btn-sm btn-primary">새글</a>
          </td>
        </tr>
      </table>
      <table class="table">
        <tr>
          <th width="10%" class="text-center">번호</th>
          <th width="45%" class="text-center">제목</th>
          <th width="15%" class="text-center">이름</th>
          <th width="20%" class="text-center">작성일</th>
          <th width="10%" class="text-center">조회수</th>
        </tr>
        <tr v-for="vo in board_list">
          <td width="10%" class="text-center">{{vo.no}}</td>
          <td width="45%"><a :href="'../board/detail.do?no='+vo.no">{{vo.subject}}</a></td>
          <td width="15%" class="text-center">{{vo.name}}</td>
          <td width="20%" class="text-center">{{vo.dbday}}</td>
          <td width="10%" class="text-center">{{vo.hit}}</td>
        </tr>
      </table>
      <table class="table">
        <tr>
          <td class="text-center">
          <input type="button" value="이전" class="btn btn-sm btn-info">
            {{curpage}} page / {{totalpage}} pages
          <input type="button" value="다음" class="btn btn-sm btn-info">
          </td>
        </tr>
      </table>
    </div>
  </div>
<script type="text/javascript">
  new Vue({
	  el:'.container',
	  data:{
		  board_list:[],
		  curpage:1,
		  totalpage:0
	  },
	  mounted:function(){
		  axios.get("http://localhost:8080/web/board/list_vue.do",{
			  params:{
				  page:this.curpage  //페이지는 여기 매개변수가 가진 커런트 페이지 (1)을 쓸거야라고 값을 넘겨주는곳이고
			  }
		  }).then(result=>{
			  console.log(result.data);
			  this.board_list=result.data;  //위에 data 안에 보드 리스트 배열 값에 리설트 값을 대입해줘라~~라고 결과 값을 받아 오는 곳
			  this.curpage=result.data[0].curpage;
			  this.totalpage=result.data[0].totalpage;
		  })
	  }  
  })
</script>
</body>
</html>