<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<!DOCTYPE html>
<html>
<title>W3.CSS Template</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/main.css">
<style>
body {font-family: "Raleway", sans-serif}
h1, h2, h3, h4, h5, h6 {
  font-family: "Playfair Display";
  letter-spacing: 5px;
}
.w3-top a {
  font-weight: 900;
}
</style>
<body>

<!-- Navbar (sit on top) -->
<div class="w3-top">
  <div class="w3-bar w3-white w3-padding w3-card" style="letter-spacing:4px;">
    <a href="<%=request.getContextPath() %>/member/main" class="w3-bar-item w3-button"><img src="<%=request.getContextPath() %>/image/logo.png"   width="50%"/></a>
    <!-- Right-sided navbar links. Hide them on small screens -->
    <div class="w3-right w3-hide-small">
    <c:if test="${login == null }">
      <a href="<%=request.getContextPath() %>/member/memberInput" class="w3-bar-item w3-button">회원가입</a>
      <a href="<%=request.getContextPath() %>/member/loginForm" class="w3-bar-item w3-button">로그인</a>
      </c:if>
     <c:if test="${login != null }">
        <a href="<%=request.getContextPath() %>/member/memberInfo?userid=${login}" class="w3-bar-item w3-button">${login}님 정보수정</a>
       <a href="<%=request.getContextPath() %>/member/logOut" class="w3-bar-item w3-button">로그아웃</a>
     <c:if test="${login eq 'admin' }">
     <a href="<%=request.getContextPath() %>/member/memberList" class="w3-bar-item w3-button">회원리스트</a>
     </c:if>
     </c:if>
     
      <a href="<%=request.getContextPath() %>/board/list?boardid=1" class="w3-bar-item w3-button">공지사항[${boardid}]</a>
      <a href="<%=request.getContextPath() %>/board/list?boardid=2" class="w3-bar-item w3-button">자유게시판</a>
         <a href="<%=request.getContextPath() %>/board/list?boardid=3" class="w3-bar-item w3-button">QnA</a>
    </div>
  </div>
</div>
<br><br><br>



