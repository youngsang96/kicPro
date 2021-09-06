<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html><head><meta charset="UTF-8">
<title>KIC 프로그램</title></head>
<body>
/member/main
<c:if test="${login == null }">
<script>
location.href="<%=request.getContextPath()%>/member/loginForm"
</script></c:if>
<c:if test="${login != null }">${login }님이 로그인 했습니다 </c:if>

</body></html>