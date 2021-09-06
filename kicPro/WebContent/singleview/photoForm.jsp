<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>회원 사진 등록</title>
</head>
<body> 사진 photo 폴더 생성 (f5)
<h3>업로드</h3>
<form action="<%=request.getContextPath() %>/member/photoPro" method="post" 
enctype="multipart/form-data">
  <input type="file" name="picture">
  <input type="submit" value="사진등록">
</form>
</body>
</html>