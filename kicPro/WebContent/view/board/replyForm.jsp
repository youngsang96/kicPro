<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 글쓰기</title>
<script>
	function board_submit() {
		var f = document.f;
		if (f.name.value == "") {
			alert("이름을 입력하세요") 
			f.name.focus()
			return
		}
		if (f.pass.value == "") {
			alert("비밀번호를 입력하세요")
			f.pass.focus();
			return;
		}
		if (f.subject.value == "") {
			alert("제목을 입력하세요")
			f.subject.focus();
			return;
		}
		if (f.content.value == "") {
			alert("내용을 입력하세요")
			f.content.focus();
			return;
		}
		f.submit();
	}
</script>
</head>
<body>
	<form action="replyPro" method="post"  	name="f">
	<input type="hidden"  name="num" value="${board.num}">
	<input type="hidden"  name="ref" value="${board.ref}">
	<input type="hidden"  name="refstep" value="${board.refstep}">
	<input type="hidden"  name="reflevel" value="${board.reflevel}">
		<div class="w3-content">
			<table>
				<caption>${boardName }:답글쓰기</caption>
				<tr>
					<td>글쓴이</td>
					<td><input type="text" name="name"></td>
				</tr>
				<tr>
					<td>비밀번호</td>
					<td><input type="password" name="pass"></td>
				</tr>
				<tr>
					<td>제목</td>
					<td><input type="text" name="subject" value="re:${board.subject}"> </td>
				</tr>
				<tr>
					<td>내용</td>
					<td><textarea rows="15" name="content"></textarea></td>
				</tr>
				<tr>
					<td colspan="2"><a href="javascript:board_submit()">[답글등록]</a></td>
				</tr>
			</table>
		</div>
		<br>
		<br>
		<br> 
	</form>
</body>
</html>