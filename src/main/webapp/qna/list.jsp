<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="qna.P02_qnaDTO"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="qna.P02_qnaDAO"%>
<%@ include file="/top.jsp" %>
<br/>
<br/>
<h2>Q&A 게시판</h2>
<%	
	sid = (String)session.getAttribute("sid");
	if(sid == null){
%>
	<script>
		alert("로그인 후 사용가능합니다.");
		window.location="/ProTwo/account/loginForm.jsp";
	</script>
<% }
	P02_qnaDAO dao = new P02_qnaDAO();
	int count = dao.count();
	String pageNum = request.getParameter("pageNum");
	if(pageNum == null){
		pageNum = "1";
	}
	int pageSize = 10;
	int currentPage = Integer.parseInt(pageNum);
	int start = (currentPage -1) * pageSize + 1;
	int end = (currentPage) * pageSize;
%>
<table>
	<tr>
		<td>글번호</td>
		<td>제목</td>
		<td>작성자</td>
		<td>작성일</td>
	</tr>
<%	
	ArrayList<P02_qnaDTO> list = dao.listQna(start, end);
	if(list.size()>0){
		for(P02_qnaDTO dto : list){
%>
		<tr>
			<td><%=dto.getQnaNum() %></td>
			<td>
			<%
				if(dto.getStep() == 1){
			%> 
			<% }
				if(dto.getAns() == 2){
			%>
			삭제된 글입니다
			<%		
				}else{
			%>
			<a href="/ProTwo/qna/content.jsp?qnaNum=<%=dto.getQnaNum() %>"><%=dto.getTitle() %></a>
			<%	}%>
			</td>
			<td><%=dto.getId() %></td>
			<td><%=dto.getReg() %></td>
		</tr>
<%		}
	}else{
%>
	<tr><td>작성된 내용이 없습니다</td></tr>
<%	}%>
</table>
<input type="button" value ="글쓰기" onclick="window.location='/ProTwo/qna/question.jsp'"/>
<%
	if(count > 0){
		int pageCount = count / pageSize + (count % pageSize == 0 ? 0 : 1);
		int startPage = (int)(currentPage / 10)*10 +1;
		int pageBlock = 10;
		int endPage = startPage + pageBlock +1;
		if(endPage > startPage){
			endPage = pageCount;
		}
		if(startPage>10){
%>
			<a href="list.jsp?pageNum=<%=startPage-10%>">[이전]</a>
<%					
		}
		for(int i = startPage ; i <=endPage ; i++){
%>
			<a href="list.jsp?pageNum=<%=i %>">[<%=i %>]</a>
<%		}	
		if(endPage < pageCount){
%>
			<a href="list.jsp?pageNum=<%=startPage+10%>">[다음]</a>
<%		}
	}%>
<br/>
<br/>	
<%@ include file="/footer.jsp" %>	