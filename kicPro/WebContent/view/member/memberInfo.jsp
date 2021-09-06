<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class="w3-content " style="width:80%;">
	
	
		<table   >
			<caption>회원정보수정</caption>
			<tr>
				<td rowspan="5" valign="bottom"><img src="<%=request.getContextPath()%>/photo/${member.picture}" width="100"
					height="120" id="pic"><br> </td>
				<td>아이디</td>
				<td>${member.id}</td>
			</tr><tr>
				<td>이름</td>
				<td>${member.name}</td>
			</tr><tr>
				<td>성별</td>
				<td>${member.gender==1?'남자':'여자'}</td>
			</tr><tr>
				<td>전화번호</td>
				<td colspan="2">${member.tel}</td>
			</tr><tr>
				<td>이메일</td>
				<td colspan="2">${member.email}</td>
			</tr>
		  <c:if test="${login == member.id}">
			<tr>
				<td colspan="3"  align="right">
				<input type="button"   
				value="회원정보수정"  
				onclick="location.href='<%=request.getContextPath() %>/member/memberUpdate?userid=${member.id }'" >
				<input type="button"   
				value="회원탈퇴"
				onclick="location.href='<%=request.getContextPath() %>/member/memberDelete?userid=${member.id }'" >
				
				
				</td>
			</tr></c:if>
		</table>
		
	</div><br><br>
</body>
</html>