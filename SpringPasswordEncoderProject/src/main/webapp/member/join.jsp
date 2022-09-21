<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="//code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css">
<link rel="stylesheet" href="/resources/demos/style.css">
<script src="https://code.jquery.com/jquery.js"></script>
<script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script>
<script type="text/javascript" src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
$( function() {
	
	$('#okId').hide();
	
  $('#idCheck').click(function () {
	$("#dialog").dialog({
		autoOpen:false,
		width:450,
		height:300,
		modal:true
	}).dialog("open");
  })
  
  $('#okBtn').click(function () {
	  $('#myId').val($('#id').val());
	  $("#dialog").dialog("close");
  })
  
  
  
  $('#postBtn').click(function () {
	  new daum.Postcode({
			oncomplete:function(data)
			{
				$('#post').val(data.zonecode)
				$('#addr1').val(data.address)
			}
		}).open();
   })
  
   $('#idBtn').click(function () {
	  let id=$('#id').val();
	  if(id.trim()==="")
	  {
		  $('#id').focus(); // 태그 $('.클래스명'), $('#아이디명') $('태그명')
		  // $refs.ref명 ===> VueJS, React
		  //nodeJS => 서버 축 사이드 자바스크립트 (Spring)
		  //app.get('../a.do',function(request,response){처리})
		  return;
	  }
  
	  $.ajax({
		type:'post',
		url:'../member/idcheck.do',
		data:{"id":id}, //컨트롤러의 해당 메소드의 매개변수로 이 값을 집어넣어줄래
		success:function(result){ //그리고 메소드 수행 결과값을 보내줘 ~~~
			let res=result.trim();
			if(res==='YES')
			{
				console.log(res);
				let msg='<span style="color:blue">'+id+'는(은) 사용 가능한 아이디 입니다</span>';
				$('#result').html(msg);
				$('#okId').show();
			}
			else
			{
				let msg='<span style="color:red">'+id+'는(은) 이미 사용 중인 아이디 입니다</span>';
				$('#result').html(msg);
				
			}
		 }
	  })
   })
});
  </script>

</head>
<body>
  <div class="container">
    <h1 class="text-center">회원가입</h1>
    <div class="row">
     <form method="post" action="../member/join_ok.do">
      <table class="table">
        <tr>
          <th width="10%" class="warning text-right">아이디</th>
          <td width="90%">
            <input type="text" class="input-sm" size=20 name="id" readonly="readonly" id="myId">
            <input type="button" class="btn btn-sm btn-danger"  value="중복체크" id="idCheck">
          </td>
        </tr>
        <tr>
          <th width="10%" class="warning text-right">비밀번호</th>
          <td width="90%">
            <input type="text" class="input-sm" size=20 name="pwd">
          </td>
        </tr>
        <tr>
          <th width="10%" class="warning text-right">이름</th>
          <td width="90%">
            <input type="text" class="input-sm" size=20 name="name">
          </td>
        </tr>
        <tr>
          <th width="10%" class="warning text-right">성별</th>
          <td width="90%">
            <input type="radio" name="sex" value="남자" checked="checked">남자
            <input type="radio" name="sex" value="여자">여자
          </td>
        </tr>
        <tr>
          <th width="10%" class="warning text-right">생년월일</th>
          <td width="90%">
            <input type="date" size=20 name="birth">
          </td>
        </tr>
        <tr>
          <th width="10%" class="warning text-right">이메일</th>
          <td width="90%">
            <input type="text" size=70 name="email" class="input-sm">
          </td>
        </tr>
        <tr>
          <th width="10%" class="warning text-right">우편번호</th>
          <td width="90%">
            <input type="text" size=10 name="post" readonly="readonly" class="input-sm" id="post">
            <input type="button" class="btn btn-sm btn-danger" value="우편번호 검색" class="input-sm" id="postBtn">
        </tr>
        <tr>
          <th width="10%" class="warning text-right">주소</th>
          <td width="90%">
            <input type="text" size=70 name="addr1" class="input-sm" id="addr1">
          </td>
        </tr>
        <tr>
          <th width="10%" class="warning text-right">상세주소</th>
          <td width="90%">
            <input type="text" size=70 name="addr2" class="input-sm">
          </td>
        </tr>
        <tr>
          <th width="10%" class="warning text-right">전화번호</th>
          <td width="90%">
            <input type="text" size=10 name="tel1" class="input-sm" value="010" readonly="readonly">
            <input type="text" size=15 name="tel1" class="input-sm">
          </td>
        </tr>
        <tr>
          <th width="10%" class="warning text-right">소개</th>
          <td width="90%">
            <textarea rows="3" cols="70" name="content"></textarea>
          </td>
        </tr>
        <tr>
          <td colspan="2" class="text-center">
            <input type="submit" value="회원가입" class="btn btn-sm btn-primary">
            <input type="button" value="취소" class="btn btn-sm btn-danger" onclick="javascript:history.back()">
          </td>
        </tr>
      </table>
      </form>
    </div>
    <div id="dialog" title="아이디 중복체크" style="display: none">
	  <table class="table">
	    <tr>
	      <td>
	                아이디:<input type="text" name="id" size="15" class="input-sm" id="id">
	              <input type="button" class="btn btn-sm btn-success" id="idBtn" value="아이디 체크">
	      </td>
	    </tr>
	    <tr>
	      <td class="text-center" id="result"></td>
	    </tr>
	    <tr id="okId">
	      <td class="text-center">
	        <input type="button" class="btn btn-sm btn-info" id="okBtn" value="확인">
	      </td>
	    </tr>
	  </table>
	</div>
  </div>
</body>
</html>