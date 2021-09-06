<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<div class="w3-content  w3-padding"   >
<table>

<tr><th>id</th><th>name</th><th>pass</th><th>gender</th><th>tel</th>
<th>email</th><th>picture</th><th>&nbsp;</th><th>&nbsp;</th><th>&nbsp;</th></tr>
<c:forEach  var="mem"   items="${mlist}" >
<tr>
<td>${mem.id}</td>
<td>${mem.name}</td>
<td>${mem.pass }</td>
<td>${mem.gender==1 ? '남자':'여자' }</td>
<td>${mem.tel }</td>
<td>${mem.email }</td>
<td>${mem.picture }</td>
<td><a 
href="<%=request.getContextPath() %>/member/memberInfo?userid=${mem.id}">[회원정보]</a></td>
<td><a 
href="<%=request.getContextPath() %>/member/memberUpdate?userid=${mem.id}">[수정]</a></td>
<td><a 
href="<%=request.getContextPath() %>/member/memberDelete?userid=${mem.id}">[강제탈퇴]</a></td>
</tr>
</c:forEach>
</table>
</div>
<br><br>
</body>
</html>