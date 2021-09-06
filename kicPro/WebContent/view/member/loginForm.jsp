<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class="w3-content" style="width:50%;">

<form  action="<%=request.getContextPath() %>/member/loginPro"   method="post">

<table>
<caption><h2>로그인</h2></caption>
<tr><th>아이디</th><td><input type="text"  name="id"></td></tr>
<tr><th>비밀번호</th><td><input type="password" name="pass"></td></tr>
<tr><td  colspan="2"><input type="submit"  value="로그인">
<input type="button" value="회원가입"  
onclick="location.href='<%=request.getContextPath() %>/member/memberInput'"></td></tr>
</table></form>
<br><br>
</div></body></html>