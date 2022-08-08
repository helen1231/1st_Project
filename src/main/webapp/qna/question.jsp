<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="qna.P02_qnaDAO"%>
<%@ page import="qna.P02_qnaDTO"%>
<%@ include file="/top.jsp" %>
<br/>
<br/>
<h2>Q&A 작성</h2>
<%	
	sid = (String)session.getAttribute("sid");
	if(sid == null){
%>
	<script>
		alert("로그인 후 사용가능합니다.");
		window.location="/ProTwo/account/loginForm.jsp";
	</script>
<% }
	P02_qnaDTO dto = new P02_qnaDTO();
	P02_qnaDAO dao = new P02_qnaDAO();
	int qnaNum=0, ref=0, step=0;
	if(request.getParameter("qnaNum") != null){
		qnaNum = Integer.parseInt(request.getParameter("qnaNum"));
		ref = Integer.parseInt(request.getParameter("ref"));
		step = Integer.parseInt(request.getParameter("step"));
	}
%>
<form name="frm" action="/ProTwo/qna/questionPro.jsp" method="post" enctype="multipart/form-data">
		<input type="hidden" name="qnaNum" value="<%=qnaNum%>"/>
		<input type="hidden" name="ref" value="<%=ref%>"/>
		<input type="hidden" name="step" value="<%=step%>"/>
	제목	<input type="text" name="title" required/><br/>
	사진	<input type="file" name="image"/><br/>
	내용	<textarea name="content" rows="10" cols="40" required></textarea><br/>
	<input type="submit" value="등록"/>
</form>
<br/>
<br/>	
<%@ include file="/footer.jsp" %>	