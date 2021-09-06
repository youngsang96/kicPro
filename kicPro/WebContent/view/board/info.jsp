<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html><html><head><meta charset="UTF-8"><title>게시물 상세보기</title>
</head><body>
<div class="w3-content">
	<table>
		<caption>${boardName} 상세 보기</caption>
		<tr>	<td width="20%">글쓴이</td>
			<td width="80%" style="text-align: left">${board.name }</td>		</tr>
		<tr>
			<td>제목</td>
			<td style="text-align: left">${board.subject}</td>
		</tr>		<tr>
			<td>내용</td>
			<td>
				<table style="width: 100%; height: 250px;">
					<tr><td
							style="border-width: 0px; vertical-align: top; text-align: left;">
							${board.content }</td></tr>				</table>
			</td>		</tr>		<tr>
			<td>첨부파일</td>
			<td ><img src="<%=request.getContextPath() %>/upfile/${board.file1 }" width="200px"></td></tr>		<tr>
			<td colspan="2">
			<a href="replyForm?num=${board.num}">[답변]</a> 
			<a href="updateForm?num=${board.num}">[수정]</a> 
			<a href="deleteForm?num=${board.num}">[삭제]</a> 
			<a href="list">[목록]</a>
			</td>		</tr>	</table><br><br><br>
			</div>
			
			</body></html>