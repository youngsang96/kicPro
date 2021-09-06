<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- 
   1. 파일 업로드하기
           업로드 위치는  /photo 로 설정 
   2. 파일의 내요을 opener에 출력하기. 현재 윈도우는 close 함        
--%>    

<script>
   img = opener.document.getElementById("pic");
   img.src = "<%=request.getContextPath()%>/photo/${filename}";  //업로드된 이미지 회원가입 화면에 출력
   opener.document.f.picture.value="${filename}"; //memberInput.jsp 파라미터에 파일이름 설정
   self.close();
</script>