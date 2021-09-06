<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script >
	function win_upload() {
		var op = "width=500, height=150, left=50, top=150";
		open("photoForm", "", op);
	}
</script>
<body>
<div class="w3-content " style="width:80%;">
	<form action="<%=request.getContextPath() %>/member/memberUpdatePro" 
	name="f" method="post">
		<input type="hidden" name="picture" value="">
		<input type="hidden" name="userid" value="${member.id}">
		<table   >
			<caption>회원가입</caption>
			<tr>
				<td rowspan="4" valign="bottom"><img src="<%=request.getContextPath()%>/photo/${member.picture}" width="100"
					height="120" id="pic"><br> <font size="1"><a
						href="javascript:win_upload()">사진등록</a></font></td>
				<td>아이디</td>
				<td>${member.id}</td>
			</tr>
			<tr>
				<td>비밀번호</td>
				<td><input type="password" name="pass"></td>
			</tr>
			<tr>
				<td>이름</td>
				<td><input type="text" name="name"    value="${member.name}"></td>
			</tr>			<tr>
				<td>성별</td>
				<td><input type="radio"  value="1" name="gender" ${member.gender==1? 'checked':'' }>남
					<input type="radio"  value="2" name="gender" ${member.gender==2? 'checked':'' }>여</td>
			</tr>			<tr>				<td>전화번호</td>
				<td colspan="2"><input type="text" name="tel" value="${member.tel}"></td>
			</tr>			<tr>
				<td>이메일</td>
				<td colspan="2"><input type="text" name="email"  value="${member.email}"></td>
			</tr>			<tr>
				<td colspan="3"  align="right"><input type="submit"   value="정보수정"></td>
			</tr>
		</table>
		
	</form></div>
</body>
</html>