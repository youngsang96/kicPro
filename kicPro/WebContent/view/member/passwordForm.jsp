<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html><html><head>
<meta charset="UTF-8">
<title>비밀번호 변경</title>
<script type="text/javascript">
   function inchk(f) {
	   if(f.chgpass.value != f.chgpass2.value) {
		   alert("변경 비밀번호 와 변경 비밀번호 재입력이 다릅니다.");
		   f.chgpass2.value="";
		   f.chgpass2.focus();
		   return false;
	   }	   return true;  }</script>
</head><body>
<form action="memberPassPro" method="post" name="f" 
     onsubmit="return inchk(this)">
<table>
  <caption>비밀번호 변경</caption>
  <tr><td>현재 비밀번호</td>
  <td><input type="password" name="pass"></td></tr>
  <tr><td>변경 비밀번호</td>
  <td><input type="password" name="chgpass"></td></tr>
  <tr><td>변경 비밀번호 재입력</td>
  <td><input type="password" name="chgpass2"></td></tr>
  <tr><td colspan="2">
      <input type="submit" value="비밀번호 변경"></td></tr>
</table></form></body></html>