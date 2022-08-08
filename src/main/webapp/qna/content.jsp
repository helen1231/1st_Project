<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="qna.P02_qnaDAO"%>
<%@ page import="qna.P02_qnaDTO"%>
<%@ include file="/top.jsp" %>
<br/>
<br/>
<h2>Q&A 게시판</h2>
<%	
	sid = (String)session.getAttribute("sid");
	String admin = (String)session.getAttribute("admin");
	if(!(sid != null || admin != null) ){
%>
	<script>
		alert("로그인 후 사용가능합니다.");
		window.location="/ProTwo/account/loginForm.jsp";
	</script>
<% }
	int qnaNum = Integer.parseInt(request.getParameter("qnaNum"));
	P02_qnaDAO dao = new P02_qnaDAO();
	P02_qnaDTO dto = dao.readQna(qnaNum);
%>
<table>
	<tr>
		<td>제목</td>
		<td><%=dto.getTitle() %></td>
	</tr>
	<tr>
		<td>사진</td>
<%
		if(dto.getImage() == null){
%>		
		<td>사진이 없습니다</td>
<%			
		}else{
%>
		<td><img width="75" height="75" src="/ProTwo/image/<%=dto.getImage() %>"></td>
<%		} %>		
	</tr>
	<tr>
		<td>내용</td>
		<td><%=dto.getContent() %></td>
	</tr>
	<tr>
		<td>작성일</td>
		<td><%=dto.getReg() %></td>
	</tr>
</table>
<%
	if(dto.getId().equals(sid) || admin != null ){ 
%>
<input type="button" value ="수정" onclick="window.location='/ProTwo/qna/update.jsp?qnaNum=<%=qnaNum%>'"/>
<%	}
	if(dto.getId().equals(sid) || admin != null){
%>
<input type="button" value ="삭제" onclick="window.location='/ProTwo/qna/delete.jsp?qnaNum=<%=qnaNum%>'"/>
<%	
	}
	if(dto.getStep() == 0 && admin != null){ 
%>
<input type="button" value ="답글" onclick="window.location='/ProTwo/qna/question.jsp?qnaNum=<%=qnaNum%>&ref=<%=dto.getRef()%>&step=<%=dto.getStep()%>'"/>
<%	}%>
<br/>
<br/>	
<%@ include file="/footer.jsp" %>	