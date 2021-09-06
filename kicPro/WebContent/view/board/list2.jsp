<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시물 목록 보기</title>

</head>
<body>
	<div class="w3-content">
		<table id="board">
			<caption>${boardName }</caption>
			<c:if test="${boardcount == 0 }">
				<tr>
					<td colspan="5">등록된 게시글이 없습니다.</td>
				</tr>
			</c:if>
			<c:if test="${boardcount != 0 }">
				<tr>
					<td colspan="5" style="text-align: right">글개수:${boardcount}</td>
				</tr>
				<tr>
					<th width="8%">번호</th>
					<th width="50%">제목</th>
					<th width="14%">작성자</th>
					<th width="17%">등록일</th>
					<th width="11%">조회수</th>
				</tr>
				<c:forEach var="b" items="${list}">
					<tr>
						<td>${boardnum}</td>
						<c:set var="boardnum" value="${boardnum-1 }" />
						<td style="text-align: left"><c:if
								test="${b.file1 != null && !b.file1.trim() eq ''}">
								<a href="<%=request.getContextPath() %>/upfile/${b.file1}"
									style="text-decoration: none;">@</a>
							</c:if> <c:if test="${b.file1 == null || b.file1.trim() eq ''}">
     				&nbsp;&nbsp;&nbsp; </c:if> <c:if test="${b.reflevel > 0}">
								<c:forEach var="i" begin="1" end="${b.reflevel-1 }">
	              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	     		 </c:forEach>	       
	  └</c:if> <a href="info?num=${b.num}">${b.subject}</a></td>
						<td>${b.name}</td>
						<td>${b.regdate}</td>
						<td>${b.readcnt}</td>
					</tr>
				</c:forEach>

				<tr>
					<td colspan="5"><c:if test="${startpage <= bottomLine}">[이전] </c:if>
						<c:if test="${startpage > bottomLine}">
							<a href="list?pageNum=${startpage - bottomLine}">[이전]</a>
						</c:if> <c:forEach var="a" begin="${startpage}" end="${endpage}">

							<c:if test="${a==pageNum }">[${a}] </c:if>
							<c:if test="${a!=pageNum }">

								<a href="list?pageNum=${a}">[${a}]</a>
							</c:if>
						</c:forEach> <c:if test="${endpage >= maxpage}">  [다음]</c:if> <c:if
							test="${endpage < maxpage}">
							<a href="list?pageNum=${startpage + bottomLine}">[다음]</a>
						</c:if></td>
				</tr>


			</c:if>


			<tr>
				<td colspan="5" style="text-align: right"><a href="writeForm">[글쓰기]</a></td>
			</tr>
		</table>
	</div>
	<br>
	<br>
</body>
</html>





