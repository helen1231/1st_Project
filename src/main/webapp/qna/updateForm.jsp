<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="qna.P02_qnaDAO"%>
<%@ page import="qna.P02_qnaDTO"%>
<%@ include file="/top.jsp" %>
<br/>
<br/>
<h2>Q&A 수정</h2>
<%	
	sid = (String)session.getAttribute("sid");
	String pw = request.getParameter("pw");
	P02_qnaDAO dao = new P02_qnaDAO();
	boolean check = dao.checkUser(sid, pw);
	if(check == false){
%>
	<script>
		alert("비밀번호를 확인해주세요");
		history.go(-1);
	</script>
<% }else{
		int qnaNum = Integer.parseInt(request.getParameter("qnaNum"));
		P02_qnaDTO dto = dao.readQna(qnaNum);
%>
	<form action="/ProTwo/qna/updatePro.jsp" method="post" enctype="multipart/form-data">
			<input type="hidden" name="qnaNum" value="<%=qnaNum%>"/>
			<input type="hidden" name="originalImage" value="<%=dto.getImage()%>"/>
		제목	<input type="text" name="title" value="<%=dto.getTitle()%>"/><br/>
		사진	<input type="file" name="image" /><br/>
		내용	<textarea name="content" rows="10" cols="40"><%=dto.getContent()%></textarea><br/>
		<input type="submit" value="등록"/>
	</form>
<%	} %>
<br/>
<br/>	
<%@ include file="/footer.jsp" %>	