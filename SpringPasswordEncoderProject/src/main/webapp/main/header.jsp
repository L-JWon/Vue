<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
 <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>
<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="../main/main.do">Security</a>
    </div>
    <ul class="nav navbar-nav">
      <li class="active"><a href="../main/main.do">Home</a></li>
      <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">레시피<span class="caret"></span></a>
        <ul class="dropdown-menu">
          <li><a href="#">레시피 목록</a></li>
          <li><a href="#">쉐프</a></li>
          <li><a href="#">레시피 만들기</a></li>
        </ul>
      </li>
      <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">커뮤니티<span class="caret"></span></a>
        <ul class="dropdown-menu">
          <li><a href="#">공지사항</a></li>
          <li><a href="#">자유게시판</a></li>
          <li><a href="#">묻고답하기</a></li>
        </ul>
      </li>
      <li><a href="#">Page 2</a></li>
    </ul>
    <ul class="nav navbar-nav navbar-right">
    <c:if test="${sessionScope.id==null }">
      <li><a href="../member/join.do"><span class="glyphicon glyphicon-user"></span>회원가입</a></li>
      <li><a href="../member/login.do"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
    </c:if>
    <c:if test="${sessionScope.id!=null }">
      <li><a href="../member/join_update.do"><span class="glyphicon glyphicon-user"></span>회원수정</a></li>
      <li><a href="../member/logout.do"><span class="glyphicon glyphicon-log-in"></span> Logout</a></li>
    </c:if>
    </ul>
  </div>
</nav>
</body>
</html>