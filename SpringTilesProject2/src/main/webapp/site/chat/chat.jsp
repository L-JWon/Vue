<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
.row{
	width: 800px;
	height: 750px;
}
#chatArea{
	height: 250px;
	overflow-y: auto;
	border: 1px solid black;
}
</style>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript" src="http://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.3.0/sockjs.min.js"></script>
<script type="text/javascript">
let websocket;
function connection() { // 연결
	//websocket ==> 웹에서 연결이 되게 만드는 소프트웨어 (ex 전화기 전화선 연결해주듯이)
	websocket=new WebSocket("ws://localhost:8080/web/chat/chat-ws")
	websocket.onopen=onOpen; // callback함수 ==> 시스템 연결되면 자동으로 호출되는 부분
	websocket.onclose=onClose;
	websocket.onmessage=onMessage;
}
function onOpen(event) { // 연결되었을 때 처리
	alert("채팅 서버와 연결되었습니다");
}
function onClose(event) { // 퇴장 시 처리
	alert("서버와 연결을 종료합니다");
}

function onMessage(event) { // 채팅 메시지 전송 시 처리
	let data=event.data;// 이 데이터가 사용자가 보낸 메시지
	if(data.substring(0,4)==="msg:")  
	// 만약 방만들기같은 명령어 => (roomin, makeroom, roomout) 이 부분이 메시지가 들어왔을 때 어떤 명령어인지 검토하는 곳
	{
		appendMessage(data.substring(4)); //msg:시작하면 채팅 문자열에 추가해라!!
	}
	
}
function disconnection() {
	//퇴장 버튼 클릭
	websocket.close(); //onClose(); 호출
}
function send() {
	let name=$('#name').val(); //이름이 입력됐는지 확인하는 부분
	if(name.trim()==="")
	{
		$('#name').focus();
		return;
	}
	
	let msg=$('#sendMsg').val();
	if(msg.trim()==="")
	{
		$('#sendMsg').focus();
		return;
	}
	
	websocket.send("msg:["+name+"]"+msg); //onMessage() 호출
	$('#sendMsg').val(""); //채팅을 보내고 나면 입력창을 공백으로 바꿔줘라
	$('#sendMsg').focus();
}
function appendMessage(msg) { //채팅 문자열이 추가될 때
	$('#recvMsg').append(msg+"<br>");
	let ch=$('#chatArea').height();
	let m=$('#recvMsg').height()-ch;
	$('#chatArea').scrollTop(m)
	
}
$(function () {
	$('#startBtn').click(function () {
		connection();
	})
	$('#endBtn').click(function () {
		disconnection();
	})
	$('#sendBtn').click(function () {
		send();
	})
})
</script>
</head>
<body>
  <div class="container">
    <div class="row row1">
      <h1 class="text-center">WebSocket Chat</h1>
      <table class="table">
        <tr>
          <td>
	                이름:&nbsp;&nbsp;<input type="text" id="name" size="15" class="input-sm">
	            <input type="button" id="startBtn" value="입장" class="btn btn-sm btn-danger">
	            <input type="button" id="endBtn" value="퇴장" class="btn btn-sm btn-primary">
          </td>
        </tr>
        <tr>
          <td>
	          <div id="chatArea">
	            <div id="recvMsg"></div>
	          </div>
          </td>
        </tr>
        <tr>
          <td>
            <input type="text" id="sendMsg" size="80" class="input-sm">
            <input type="button" id="sendBtn" value="전송" class="btn btn-sm btn-success">
          </td>
        </tr>
      </table>
    </div>
  </div>
</body>
</html>