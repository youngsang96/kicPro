<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html><html><head>
<meta charset="UTF-8"><title>게시물 목록 보기</title>
</head><body>
<div class="w3-content">
	<table  id="board">
		<caption>${boardName}</caption>
		<c:if test="${boardcount == 0 }">
			<tr>
				<td colspan="6">등록된 게시글이 없습니다.</td>
			</tr>
		</c:if>
		<c:if test="${boardcount != 0 }">
			<tr>
				<td colspan="6" style="text-align: right">글개수:${boardcount}</td>
			</tr>
			<tr>
				<th width="8%">번호</th>
				<th width="50%">제목</th>
				<th width="14%">작성자</th>
				<th width="17%">등록일</th>
				<th width="17%">파일</th>
				<th width="11%">&nbsp;</th>
			</tr>
		</c:if>
		
		<c:forEach  var="board"   items="${list}">
		<tr>
				<td width="8%">${boardnum}</td>
				<c:set var="boardnum"  value="${boardnum-1 }"/>
				<td width="50%"   id="left">
				<c:if test="${board.reflevel > 0 }">
				<img src="<%=request.getContextPath() %>/image/level.gif"  width="${5*board.reflevel}px">
				<img src="<%=request.getContextPath() %>/image/re.gif"  >
				</c:if>
				<a href="info?num=${board.num}">${board.subject}</a>
				</td>
				<td width="14%">${board.name}</td>
				<td width="17%">${board.regdate}</td>
				<td width="17%">
				<c:if test="${board.file1 !=null && board.file1.trim() ne '' }">
				<a href="<%=request.getContextPath()%>/upfile/${board.file1}">${board.file1}</a>
				</c:if>
				&nbsp;			
				</td>
				<td width="11%">${board.readcnt}</td>
			</tr>
		</c:forEach>
		
		
			<tr>
					<td colspan="6"><c:if test="${startpage <= bottomLine}"><font color="grey">[이전]</font> </c:if>
						<c:if test="${startpage > bottomLine}">
							<a href="list?pageNum=${startpage - bottomLine}"><b>[이전]</b></a>
						</c:if> 
						<c:forEach var="a" begin="${startpage}" end="${endpage}">

							<c:if test="${a==pageNum }"><font color="red">[${a}]</font> </c:if>
							<c:if test="${a!=pageNum }">

								<a href="list?pageNum=${a}">[${a}]</a>
							</c:if>
						</c:forEach>
						
						
						 <c:if test="${endpage >= maxpage}"> <font color="grey"> [다음] </font></c:if> 
						 <c:if 	test="${endpage < maxpage}">
							<a href="list?pageNum=${startpage + bottomLine}"><b>[다음]</b></a>
						</c:if></td>
				</tr>
		
		
		
		
		
		
		
		
		
		<tr>
			<td colspan="6" style="text-align: right"><a href="writeForm">[글쓰기]</a></td>
		</tr>
	</table>	</div><br><br></body></html>





